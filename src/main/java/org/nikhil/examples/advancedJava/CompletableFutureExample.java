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
}