package org.nikhil.examples.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {
    // This class demonstrates the use of ExecutorService for managing threads.
    // It can be used to execute tasks asynchronously and manage thread pools efficiently.

    // Write method to demonstrate the use of all ExecutorServices
    // such as FixedThreadPool, CachedThreadPool, SingleThreadExecutor, and ScheduledThreadPool.
    // Each method should create an ExecutorService instance, submit tasks, and demonstrate the behavior of each type of ExecutorService.
    public void demoSingleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        System.out.println("SingleThreadExecutor demo started.");
        for(int i = 0; i <= 5; i++) {
            final int taskId = i;
            singleThreadExecutor.submit(() -> {
                System.out.println("Single Thread task " + taskId + " executed by Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        singleThreadExecutor.shutdown();
    }

    public void demoFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        System.out.println("FixedThreadPool demo started.");
        for(int i = 0; i <= 5; i++) {
            final int taskId = i;
            fixedThreadPool.submit(() -> {
                System.out.println("Fixed Thread Pool task " + taskId + " executed by Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        fixedThreadPool.shutdown();
    }

    public void demoCachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("CachedThreadPool demo started.");
        for(int i = 0; i <= 5; i++) {
            final int taskId = i;
            cachedThreadPool.submit(() -> {
                System.out.println("Cached Thread Pool task " + taskId + " executed by Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        cachedThreadPool.shutdown();
    }

    public void demoScheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        System.out.println("ScheduledThreadPool demo started.");
        for(int i = 0; i <= 5; i++) {
            final int taskId = i;
            scheduledThreadPool.schedule(() -> {
                System.out.println("Scheduled Thread Pool task " + taskId + " executed by Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, 3, TimeUnit.SECONDS);
        }
        scheduledThreadPool.shutdown();
    }

    public static void main(String[] args) {
        // Example usage of ExecutorService can be added here
        System.out.println("ExecutorServiceDemo is ready to be implemented.");
        ExecutorServiceDemo demo = new ExecutorServiceDemo();
        demo.demoSingleThreadExecutor();
        demo.demoFixedThreadPool();
        demo.demoCachedThreadPool();
        demo.demoScheduledThreadPool();
    }

    //Java ExecutorService is a high-level concurrency framework that simplifies the management of threads and tasks in Java applications.
    // It provides a way to decouple task submission from the mechanics of how each task will be run, allowing for more flexible and efficient thread management.
    // ExecutorService is part of the java.util.concurrent package and provides a higher-level abstraction for managing threads compared to the traditional Thread class.
    // It allows you to submit tasks for execution, manage thread pools, and handle task completion and cancellation.
    // ExecutorService provides several implementations, including:
    // 1. FixedThreadPool: A thread pool with a fixed number of threads.
    //    The FixedThreadPool Executor Service maintains a fixed number of threads that are reused to execute submitted tasks.
    //    It is suitable for scenarios where you have a known number of tasks to execute concurrently.
    //    It helps to limit the number of concurrent threads and manage system resources effectively.
    //    If the number of submitted tasks exceeds the number of threads, the excess tasks will be queued until a thread becomes available.
    //    It is created using Executors.newFixedThreadPool(int nThreads) method.

    // 2. CachedThreadPool: A thread pool that creates new threads as needed and reuses previously constructed threads when they are available.
    //    The CachedThreadPool Executor Service creates new threads as needed and reuses previously constructed threads when they are available.
    //    It is suitable for scenarios where you have a variable number of tasks to execute concurrently.
    //    It helps to optimize resource usage by creating threads on demand and reusing them when possible.
    //    If a new task is submitted and no existing threads are available, a new thread will be created to execute the task.
    //    If threads are idle for a certain period, they will be terminated to free up resources.
    //    It is created using Executors.newCachedThreadPool() method.

    // 3. SingleThreadExecutor: A thread pool that uses a single worker thread to execute tasks.
    //    The SingleThreadExecutor Executor Service uses a single worker thread to execute tasks sequentially.
    //    It is suitable for scenarios where you need to ensure that tasks are executed in the order they are submitted.
    //    It helps to avoid concurrency issues by ensuring that only one task is executed at a time.
    //    It is created using Executors.newSingleThreadExecutor() method.

    // 4. ScheduledThreadPool: A thread pool that can schedule tasks to run after a delay or at fixed intervals.
    //    The ScheduledThreadPool Executor Service can schedule tasks to run after a delay or at fixed intervals.
    //    It is suitable for scenarios where you need to perform periodic tasks or delayed execution of tasks.
    //    It helps to manage scheduled tasks efficiently and provides methods for scheduling tasks with fixed delays or at fixed rates.
    //    It is created using Executors.newScheduledThreadPool(int corePoolSize) method.

    // These implementations allow you to choose the appropriate thread management strategy based on your application's requirements.
    // You can use ExecutorService to submit tasks, manage their execution, and retrieve results using Future objects.

    // ExecutorService V/S ThreadPoolExecutor:
    // ExecutorService is a higher-level interface that provides a way to manage and control the execution of tasks asynchronously.
    // ThreadPoolExecutor is a concrete implementation of ExecutorService that provides more control over the thread pool's behavior.
    // ExecutorService is a more abstract and user-friendly interface, while ThreadPoolExecutor provides more fine-grained control over thread management.
    // ExecutorService is often used in scenarios where you need to execute tasks asynchronously, manage thread pools, and handle task completion and cancellation.
    // ThreadPoolExecutor is used when you need more control over the thread pool's behavior, such as setting core and maximum pool sizes, queue types, and rejection policies.
    // ExecutorService is a powerful tool for managing concurrent tasks in Java applications, providing a flexible and efficient way to handle thread management and task execution.
    // It is commonly used in scenarios where you need to perform tasks asynchronously, manage thread pools, and handle task completion and cancellation.
    // ExecutorService is a key component of the Java Concurrency framework, which provides a set of classes and interfaces for building concurrent applications.


}
