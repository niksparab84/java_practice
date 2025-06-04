package org.nikhil.examples.designPatterns.streamBasedProcessing;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamActorExample {
    // Message classes
    public static class ProcessChunk {
        public final List<Integer> chunk;
        public ProcessChunk(List<Integer> chunk) { this.chunk = chunk; }
    }
    public static class ChunkResult {
        public final int sum;
        public ChunkResult(int sum) { this.sum = sum; }
    }

    // Worker Actor
    public static class Worker extends AbstractActor {
        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .match(ProcessChunk.class, msg -> {
                        int sum = msg.chunk.stream().mapToInt(Integer::intValue).sum();
                        getSender().tell(new ChunkResult(sum), getSelf());
                    })
                    .build();
        }
    }

    // Collector Actor
    public static class Collector extends AbstractActor {
        private int total = 0, expected, received = 0;
        public Collector(int expected) { this.expected = expected; }
        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .match(ChunkResult.class, res -> {
                        total += res.sum;
                        received++;
                        if (received == expected) {
                            System.out.println("Total sum: " + total);
                            getContext().getSystem().terminate();
                        }
                    })
                    .build();
        }
    }

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("StreamActorSystem");
        List<Integer> data = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        int chunkSize = 25;
        List<List<Integer>> chunks = new ArrayList<>();
        for (int i = 0; i < data.size(); i += chunkSize) {
            chunks.add(data.subList(i, Math.min(i + chunkSize, data.size())));
        }

        ActorRef collector = system.actorOf(Props.create(Collector.class, chunks.size()), "collector");
        for (List<Integer> chunk : chunks) {
            ActorRef worker = system.actorOf(Props.create(Worker.class));
            worker.tell(new ProcessChunk(chunk), collector);
        }
    }
}
