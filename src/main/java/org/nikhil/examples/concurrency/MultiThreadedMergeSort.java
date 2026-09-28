package org.nikhil.examples.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadedMergeSort {
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

        return mergedArray;
    }
}
