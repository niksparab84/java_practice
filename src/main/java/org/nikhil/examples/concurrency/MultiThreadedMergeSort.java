package org.nikhil.examples.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadedMergeSort {

    public static void main(String[] args) throws Exception {
        List<Integer> arrayToSort = Arrays.asList(5, 2, 9, 1, 5, 6, 3, 7, 8, 4);
        System.out.println("Array to sort: " + arrayToSort);

        Sorter sorter = new Sorter(arrayToSort);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<List<Integer>> sortedArrayF = executor.submit(sorter);

        List<Integer> sortedArray = sortedArrayF.get();
        System.out.println("Sorted Array: " + sortedArray);
    }
}

class Sorter implements Callable<List<Integer>> {

    List<Integer> arrayToSort;

    public Sorter(List<Integer> arrayToSort) {
        this.arrayToSort = arrayToSort;
    }

    @Override
    public List<Integer> call() throws Exception {
        if(arrayToSort.size() <= 1) {
            return arrayToSort;
        }

        //Divide array in 2 parts
        int mid = arrayToSort.size()/2;
        List<Integer> left = arrayToSort.subList(0, mid);
        List<Integer> right = arrayToSort.subList(mid, arrayToSort.size());

        Sorter leftSorter = new Sorter(left); // Task to sort the left part
        Sorter rightSorter = new Sorter(right);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<List<Integer>> leftSortedArrayF= executor.submit(leftSorter);
        Future<List<Integer>> rightSortedArrayF = executor.submit(rightSorter);

        List<Integer> leftSortedArray = leftSortedArrayF.get();
        List<Integer> rightSortedArray = rightSortedArrayF.get();

        List<Integer> mergedArray = new ArrayList<>();

        int i = 0, j = 0;
        while(i < leftSortedArray.size() && j < rightSortedArray.size()) {
            if(leftSortedArray.get(i) <= rightSortedArray.get(j)) {
                mergedArray.add(leftSortedArray.get(i));
                i++;
            } else {
                mergedArray.add(rightSortedArray.get(j));
                j++;
            }
        }

        while(i < leftSortedArray.size()) {
            mergedArray.add(leftSortedArray.get(i));
            i++;
        }
        while(j < rightSortedArray.size()) {
            mergedArray.add(rightSortedArray.get(j));
            j++;
        }

        return mergedArray;
    }
}
