package org.nikhil.examples.concurrency;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    //Refer: https://www.netjstech.com/2016/01/cyclicbarrier-in-java-concurrency.html

    //CyclicBarrier is a synchronization aid that allows a set of threads to all wait for each other to reach a common barrier point.
    //It is similar to CountDownLatch, but the main difference is that the barrier can be reused after the waiting threads are released.
    //This means that the barrier can be used multiple times, whereas CountDownLatch can only be used once.
    //CyclicBarrier is useful in scenarios where a group of threads need to work together and synchronize their progress.
    //For example, if you have a group of threads that need to perform a series of tasks in parallel, you can use a CyclicBarrier to ensure that all threads reach a certain point before continuing.
    //In this example, we will create a CyclicBarrier with a count of 3, which means that 3 threads need to reach the barrier before they can continue.
    //We will create 3 threads that will perform some work and then wait at the barrier. Once all 3 threads have reached the barrier, they will be released and continue their work.
    //We will also create a Runnable task that will be executed when the barrier is tripped.
    //We will use the CyclicBarrier class to create the barrier and the Runnable task.
    //We will also use the Thread class to create the threads and start them.
    //We will use the join() method to wait for the threads to finish before exiting the program.

    public static void main(String[] args) {
        // Create a CyclicBarrier with a count of 3
        CyclicBarrier barrier = new CyclicBarrier(3, new AfterAction());

        // Create 3 threads that will perform some work and then wait at the barrier
        Thread t1 = new Thread(new TxtReader("file1.txt", "Thread 1", barrier));
        Thread t2 = new Thread(new TxtReader("file2.txt", "Thread 2", barrier));
        Thread t3 = new Thread(new TxtReader("file3.txt", "Thread 3", barrier));

        // Start the threads
        t1.start();
        t2.start();
        t3.start();

        System.out.println("Done !!!");
    }
}

class TxtReader implements Runnable {
    //Runnable is a functional interface in Java that represents a task that can be executed by a thread.
    private String fileName;
    private String threadName;
    private CyclicBarrier barrier;

    public TxtReader(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public TxtReader(String fileName, String threadName, CyclicBarrier barrier) {
        this.fileName = fileName;
        this.threadName = threadName;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println( "Thread "+ threadName + " is reading the file: "+ fileName);
            Thread.sleep(2000); // Simulate reading time
            System.out.println("Thread "+ threadName + " has finished reading the file.");

            // Wait for other threads to reach the barrier
            // The barrier will be tripped when all 3 threads have reached it
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class AfterAction implements Runnable {
    @Override
    public void run() {
        System.out.println("In after action class, start further processing as all files are read");
    }
}
