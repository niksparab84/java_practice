package org.nikhil.examples.concurrency;

import java.util.concurrent.*;

public class ProducerConsumerMultiple {
    public static void main(String[] args) {
        // Create a Blocking Queue
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        // Create a producer and a consumer threads
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Start the producer and consumer threads
        for(int i = 1; i <= 2; i++) {
            executorService.submit(new Producer(queue, i));
        }
        for(int i = 1; i <= 2; i++) {
            executorService.submit(new Consumer(queue, i));
        }

        // Shutdown the executor service
        executorService.shutdown();
        System.out.println("Executor service shutdown");
    }

}

class Producer implements Runnable {
    private BlockingQueue<Integer> queue;
    private int producerId;

    public Producer(BlockingQueue<Integer> queue, int producerId) {
        this.queue = queue;
        this.producerId = producerId;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                int value = produce(producerId) + i;
                queue.put(value);
                System.out.println("Producer " + producerId + " produced value: " + value);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private int produce(int i) {
        return (int) (i * 100);
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;
    private int consumerId;

    public Consumer(BlockingQueue<Integer> queue, int consumerId) {
        this.queue = queue;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        try {
            while (!queue.isEmpty()) {
                int value = queue.take();
                System.out.println("Consumer " + consumerId + " consumed value: " + value);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
