package org.nikhil.examples.concurrency;

import java.util.concurrent.*;

public class CallableExample {

    //Refer: https://www.netjstech.com/2016/04/callable-and-future-in-java-concurrency.html

    //Callable, an interface, was added in Java 5. It allows you to define a task to be completed by a thread asynchronously.
    //Unlike Runnable, Callable can return a result and can throw checked exceptions.
    //Callable is a functional interface, meaning it has a single abstract method that can be implemented using a lambda expression or method reference.
    //The call() method in Callable is similar to the run() method in Runnable, but it can return a value and throw checked exceptions.
    //Callable is often used in conjunction with the Future interface, which represents the result of an asynchronous computation.
    //The Future interface provides methods to check if the computation is complete, retrieve the result, and cancel the computation.
    //Callable is commonly used in scenarios where you need to perform a task asynchronously and retrieve the result later, such as in parallel processing,
    //concurrent programming, and asynchronous programming.

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        try {
            Future<String> future = executor.submit(new Callable<String>(){
                @Override
                public String call() {
                    try {
                        Thread.sleep(2000); // Simulating a long-running task
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Callable Task Completed";
                }
            });
            Future<String> future1 = executor.submit(() -> {
                try {
                    Thread.sleep(2500); // Simulating a long-running task
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "Lambda Callable Task Completed";
            });
            System.out.println("Future isDone - " + future.isDone());
            System.out.println("Future result - " + future.get());
            System.out.println("Future isDone - " + future.isDone());

            System.out.println("Future1 isDone - " + future1.isDone());
            System.out.println("Future1 result - " + future1.get());
            System.out.println("Future1 isDone - " + future1.isDone());

            // Submitting 6 tasks
            Future<String> f1 = executor.submit(new MyCallable("callable"));
            Future<String> f2 = executor.submit(new MyCallable("future"));
            Future<String> f3 = executor.submit(new MyCallable("executor"));
            Future<String> f4 = executor.submit(new MyCallable("executor service"));
            Future<String> f5 = executor.submit(new MyCallable("executors"));
            Future<String> f6 = executor.submit(new MyCallable("scheduled executor"));

            // Calling some other method
            tempMethod();

            // Calling get() method to get the future value
            System.out.println("1. " + f1.get());
            System.out.println("2. " + f2.get());
            System.out.println("3. " + f3.get());
            while(!f4.isDone()){
                System.out.println("waiting for task to finish");
                Thread.sleep(10);
            }
            System.out.println("4. " + f4.get());
            System.out.println("5. " + f5.get());
            System.out.println("6. " + f6.get());
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        } finally {
            // Shutdown the executor service
            if (executor != null && !executor.isShutdown()) {
                System.out.println("Inside finally block, shutting down executor service");
                executor.shutdown();
            }
        }
    }

    public static void tempMethod(){
        System.out.println("I am in temp method");
    }

    static class MyCallable implements Callable<String> {

        private String str;
        public MyCallable(String str) {
            this.str = str;
        }

        @Override
        public String call() {
           StringBuilder sb = new StringBuilder();
           return sb.append("Length of string ").append(str).append(" is: ").append(str.length()).toString();
        }
    }

}
