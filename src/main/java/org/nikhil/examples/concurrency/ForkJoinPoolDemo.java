package org.nikhil.examples.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolDemo {

    // This is a simple Java program that demonstrates the use of ForkJoinPool
    // to perform parallel computation on a list of integers.
    // It uses the ForkJoinPool class to create a pool of threads that can
    // execute tasks in parallel. The program defines a RecursiveTask that
    // computes the sum of a list of integers in parallel. The main method
    // creates a ForkJoinPool, submits the task to the pool, and waits for
    // the result. The program prints the result of the computation to the
    // console.
    // ForkJoinPool is a specialized implementation of the ExecutorService
    // interface that is designed for parallel processing of tasks. It uses
    // a work-stealing algorithm to balance the load among the threads in the
    // pool. The ForkJoinPool class is part of the java.util.concurrent
    // package, which provides a set of classes and interfaces for concurrent
    // programming in Java. The ForkJoinPool class is designed
    // to work with tasks that can be broken down into smaller subtasks,
    // which can be executed in parallel. The ForkJoinPool class is
    // particularly useful for tasks that can be represented as a tree of
    // subtasks, where each subtask can be executed independently of the
    // others. The ForkJoinPool class is also designed to work with tasks
    // that can be executed in a divide-and-conquer fashion, where the
    // problem is divided into smaller subproblems, which can be solved
    // independently and combined to produce the final result. The
    // ForkJoinPool class is designed to work with tasks that can be executed
    // in parallel, which can improve the performance of the program by
    // taking advantage of multiple processors or cores. The ForkJoinPool
    // class is designed to work with tasks that can be executed in a
    // non-blocking fashion, which can improve the performance of the
    // program by reducing the overhead of thread management. The

    //How do you use ForkJoinPool in Java?
    // RecursiveTask to calculate the sum of an array
    static class SumTask extends RecursiveTask<Integer> {
        private static final int THRESHOLD = 10; // Threshold for splitting tasks
        private final int[] array;
        private final int start;
        private final int end;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= THRESHOLD) {
                // Compute sum directly if below threshold
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                // Split task into two subtasks
                int mid = (start + end) / 2;
                SumTask leftTask = new SumTask(array, start, mid);
                SumTask rightTask = new SumTask(array, mid, end);

                // Fork subtasks
                leftTask.fork();
                int rightResult = rightTask.compute();
                int leftResult = leftTask.join();

                // Combine results
                return leftResult + rightResult;
            }
        }
    }

    public static void main(String[] args) {
        // Example array
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; // Fill array with values 1 to 100
        }

        // Create ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        // Submit the task to the pool
        SumTask task = new SumTask(array, 0, array.length);
        int result = pool.invoke(task);

        // Print the result
        System.out.println("Sum of array: " + result);
    }


}
