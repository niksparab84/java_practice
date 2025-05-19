package org.nikhil.examples.coreJava.ArrayProblems;

import junit.framework.TestCase;
import org.junit.Assert;

import static org.junit.Assert.assertArrayEquals;

public class ArraySolutionTest extends TestCase {

    public void testFindMissingElement() {
        ArraySolution arraySolution = new ArraySolution();

        // Test case 1: Normal case
        int[] arr1 = {1, 2, 4, 5};
        int expected1 = 3;
        int result1 = arraySolution.findMissingElement(arr1);
        assertEquals(expected1, result1);

        // Test case 2: Missing last element
        int[] arr2 = {1, 2, 3, 4};
        int expected2 = 5;
        int result2 = arraySolution.findMissingElement(arr2);
        assertEquals(expected2, result2);

        // Test case 3: Missing first element
        int[] arr3 = {2, 3, 4, 5};
        int expected3 = 1;
        int result3 = arraySolution.findMissingElement(arr3);
        assertEquals(expected3, result3);

        // Test case 4: Single element missing
        int[] arr4 = {1};
        int expected4 = 2;
        int result4 = arraySolution.findMissingElement(arr4);
        assertEquals(expected4, result4);

        // Test case 5: Element missing
        int[] arr5 = {8, 2, 4, 5, 3, 7, 1};
        int expected5 = 6;
        int result5 = arraySolution.findMissingElement(arr5);
        assertEquals(expected5, result5);
    }

    public void testRearrangeArray() {
        ArraySolution arraySolution = new ArraySolution();

        // Test case 1: Normal case
        int[] arr1 = {0, 2, -1, 1};
        int[] expected1 = {0, 1, 2, -1};
        int[] result1 = arraySolution.rearrangeArray(arr1);
        assertArrayEquals(expected1, result1);

        // Test case 2: All elements present
        int[] arr2 = {0, 1, 2, 3};
        int[] expected2 = {0, 1, 2, 3};
        int[] result2 = arraySolution.rearrangeArray(arr2);
        assertArrayEquals(expected2, result2);

        // Test case 3: All elements missing
        int[] arr3 = {-1, -1, -1};
        int[] expected3 = {-1, -1, -1};
        int[] result3 = arraySolution.rearrangeArray(arr3);
        assertArrayEquals(expected3, result3);

        // Test case 4: Mixed elements
        int[] arr4 = {3, -1, 2, 0};
        int[] expected4 = {0, -1, 2, 3};
        int[] result4 = arraySolution.rearrangeArray(arr4);
        assertArrayEquals(expected4, result4);

        // Test case 5: Random array
        int[] arr5 = {-1, -1, 6, 1, 9, 3, 2, -1, 4, -1};
        int[] expected5 = {-1, 1, 2, 3, 4, -1, 6, -1, -1, 9};
        int[] result5 = arraySolution.rearrangeArray(arr5);
        assertArrayEquals(expected5, result5);
    }

}