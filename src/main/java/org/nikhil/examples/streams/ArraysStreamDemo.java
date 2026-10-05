package org.nikhil.examples.streams;

import java.util.Arrays;

public class ArraysStreamDemo {

    public static void main(String[] args) {
        int[] numbers = {0,1,2,3,4,5,6,7,8,9};

        //print all the numbers in Array
        Arrays.stream(numbers).forEach(System.out::print);

        //limit 5
        Arrays.stream(numbers).limit(5).forEach(System.out::print);
    }
}
