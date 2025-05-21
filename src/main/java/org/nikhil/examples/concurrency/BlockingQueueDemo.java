package org.nikhil.examples.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueDemo {

    //Refer: https://www.netjstech.com/2016/02/blockingqueue-in-java-concurrency.html
    //Refer: https://www.netjstech.com/2016/03/linkedblockingqueue-in-java.html

    //BlockingQueue is a type of Queue that is thread-safe and supports operations that wait for the queue to become non-empty when retrieving an element
    // and wait for space to become available in the queue when storing an element.
    //It is part of the java.util.concurrent package and is commonly used in producer-consumer scenarios.
    //BlockingQueue is an interface that extends the Queue interface and provides additional methods for blocking operations.
    //It is important to note that BlockingQueue is not a thread-safe implementation of the Queue interface, but rather a higher-level abstraction that provides thread-safety.
    //BlockingQueue is a powerful tool for managing concurrent access to shared resources in Java applications.
    //It is commonly used in scenarios where multiple threads need to communicate and share data, such as in producer-consumer patterns, thread pools, and message queues.

    public static void main(String[] args) {
        LinkBuffer buffer = new LinkBuffer();
        // Starting two threads
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new LinkProdTask(buffer));
        executor.execute(new LinkConTask(buffer));
        executor.shutdown();
    }

}

/**
 * Producer class
 */
class LinkProdTask implements Runnable{
    LinkBuffer buffer;
    LinkProdTask(LinkBuffer buffer){
        this.buffer = buffer;
    }
    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            buffer.put(i);
        }
    }
}

/**
 * Consumer Class
 */
class LinkConTask implements Runnable{
    LinkBuffer buffer;
    LinkConTask(LinkBuffer buffer){
        this.buffer = buffer;
    }
    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            buffer.get();
        }
    }
}

//Shared class used by threads
class LinkBuffer {
    int i;
    // Bounded LinkedBlockingQueue of size 1
    BlockingQueue<Integer> linkedBlockingQ = new LinkedBlockingQueue<Integer>(1);
    public void get() {
        try {
            // take method to get from blockingqueue
            int i = linkedBlockingQ.take();
            System.out.println("Consumer recd - " + i);
        } catch (InterruptedException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void put(int i) {
        this.i = i;
        try {
            // putting in blocking queue
            linkedBlockingQ.put(i);
            System.out.println("Putting - " + i);
        } catch (InterruptedException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
