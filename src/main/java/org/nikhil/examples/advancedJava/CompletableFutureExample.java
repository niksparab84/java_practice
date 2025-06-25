package org.nikhil.examples.advancedJava;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) {
        // Create a CompletableFuture that runs a task asynchronously
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Hello";
        });

        // Chain another task to process the result
        CompletableFuture<String> resultFuture = future.thenApply(result -> result + " World");

        // Print the result
        resultFuture.thenAccept(result -> System.out.println(result));

        // Wait for the result (optional, for demonstration purposes)
        try {
            resultFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * This example demonstrates the use of CompletableFuture to run a task asynchronously and then process the result using method chaining.
     * It simulates a long-running task and prints the final result.
     * CompletableFuture allows for non-blocking asynchronous programming,
     * making it easier to write concurrent code in Java.
     * * Note: In a real-world application, you would typically handle exceptions and cancellation
     * more gracefully.
     * For more complex scenarios, you can also use methods like `thenCombine`, `thenAcceptBoth`, and `handle`
     * to combine multiple futures or handle errors.
     * CompletableFuture is a powerful tool for asynchronous programming in Java,
     * allowing you to write cleaner and more maintainable code
     * without the need for explicit thread management.
     * CompletableFuture can be used in various scenarios,
     * such as parallel processing, asynchronous I/O operations,
     * and building reactive applications.
     * CompletableFuture also supports combining multiple futures,
     * allowing you to wait for multiple asynchronous tasks to complete
     * and process their results together.
     * CompletableFuture provides a rich set of methods for composing asynchronous tasks,
     * including methods for handling exceptions, combining results,
     * and applying transformations to the results.
     * CompletableFuture is part of the java.util.concurrent package,
     * which provides a framework for concurrent programming in Java.
     */
}