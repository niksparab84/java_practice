package org.nikhil.examples.concurrency;

public class SequentialTasks {

    //Write a method that takes a list of 4 tasks and executes them sequentially.
    //On the completion of each task, print the task name and the time taken to complete it.
    //and on the completion of 4 tasks, trigger 5th task.
    public static void main(String[] args) {
        // Create a list of tasks
        Runnable task1 = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Task 1 completed in 1 second");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task2 = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("Task 2 completed in 2 seconds");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task3 = () -> {
            try {
                Thread.sleep(3000);
                System.out.println("Task 3 completed in 3 seconds");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task4 = () -> {
            try {
                Thread.sleep(4000);
                System.out.println("Task 4 completed in 4 seconds");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Execute tasks sequentially
        executeTasksSequentially(task1, task2, task3, task4);
    }

    public static void executeTasksSequentially(Runnable... tasks) {
        for (Runnable task : tasks) {
            task.run();
        }
        // Trigger 5th task
        Runnable task5 = () -> System.out.println("All tasks completed. Triggering 5th task.");
        task5.run();
    }


}
