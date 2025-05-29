package org.nikhil.examples.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    private List<Integer> myList = Arrays.asList(7000, 5000, 4000, 24000, 17000, 6000);
    private List<Integer> myList2 = Arrays.asList(5, 6, 9, 8, 7, 23, 12, 33, 45, 18, 13);
    Stream<Integer> stream = myList.stream();

    public void streamOperations() {
        System.out.println("List => " + myList);
        List<Integer> largerList = myList.stream()
                .filter(i -> i > 10000)
                .map(i -> i * 2)
                .sorted().collect(Collectors.toList());
        //.forEach(System.out::println);
        System.out.println("Larger List => " + largerList);

        System.out.println("List => " + myList2);
        List<Integer> evenList = myList2.stream()
                .filter(n -> n % 2 == 0)
                .sorted().collect(Collectors.toList());
        System.out.println("evenList => " + evenList);

        //with Exception Handling
        List<Integer> safeEvenList = myList2.stream()
                .filter(n -> {
                    try {
                        return n % 2 == 0;
                    } catch (Exception e) {
                        System.out.println("Exception occurred: " + e.getMessage());
                        return false;
                    }
                })
                .sorted().collect(Collectors.toList());
        System.out.println("safeEvenList => " + safeEvenList);

        List<Integer> evenSqrList = myList2.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .sorted().collect(Collectors.toList());
        //.forEach(System.out::println);
        System.out.println("evenSqrList => " + evenSqrList);
    }

    public void timedStreamOperations() {
        List<Integer> numList = new ArrayList<>();
        for(int i=0; i < 10; i++) {
            Random random = new Random();
            numList.add(random.nextInt(900) + 100);
        }
        System.out.println("Original numList => " + numList);

        long startTime = System.currentTimeMillis();
        List<Integer> oddLinearList = numList.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * n)
                .sorted().collect(Collectors.toList());
        System.out.println("oddLinearList => " + oddLinearList);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken for linear stream operations: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        List<Integer> oddParallelList = numList.parallelStream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * n)
                .sorted().collect(Collectors.toList());
        System.out.println("oddParallelList => " + oddParallelList);
        endTime = System.currentTimeMillis();
        System.out.println("Time taken for parallel stream operations: " + (endTime - startTime) + " ms");
    }

    public static void main(String[] args) {
        StreamDemo streamDemo = new StreamDemo();
        streamDemo.streamOperations();
        streamDemo.timedStreamOperations();
    }

}
