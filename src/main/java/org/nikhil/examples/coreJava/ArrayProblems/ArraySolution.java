package org.nikhil.examples.coreJava.ArrayProblems;

import java.util.Arrays;

public class ArraySolution {

    //You are given an array arr[] of size n - 1 that contains distinct integers in the range from 1 to n (inclusive).
    // This array represents a permutation of the integers from 1 to n with one element missing.
    // Your task is to identify and return the missing element.
    int findMissingElement(int[] arr) {
        int n = arr.length + 1;
        int missingElement = 0;
        System.out.println("Input Array: " + Arrays.toString(arr));

        int totalSum = (n * (n + 1)) / 2;
        int arrSum = 0;
        for (int i = 0; i < arr.length; i++) {
            arrSum += arr[i];
        }
        missingElement = totalSum - arrSum;
        System.out.println("Missing Element is: " + missingElement);

        return missingElement;
    }

    //Given an array of elements of length N, ranging from 0 to N-1.
    //All elements may not be present in the array. If the element is not present, it is represented by -1.
    //Rearrange the array such that A[i] = i and if i is not present, then A[i] = -1.
    int[] rearrangeArray(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        System.out.println("Input Array: " + Arrays.toString(arr));
        // Initialize the result array with -1
        Arrays.fill(result, -1);

        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0 && arr[i] < n) {
                result[arr[i]] = arr[i];
            }
            //System.out.println("Result array: " + Arrays.toString(result));
        }
        System.out.println("Rearranged Array: " + Arrays.toString(result));
        return result;
    }
}
