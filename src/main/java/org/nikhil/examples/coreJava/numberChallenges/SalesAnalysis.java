package org.nikhil.examples.coreJava.numberChallenges;

import java.util.Arrays;

public class SalesAnalysis {

    public static void main(String[] args) {
        // rows = years, columns = months
        int[][] salesData = {
            {1200, 1500, 1800, 2000}, // Year 1
            {1400, 1600, 1700, 2100}, // Year 2
            {1300, 1550, 1900, 2200}  // Year 3
        };

        int totalSales = 0;
        for(int year=0; year < salesData.length; year++) {
            for(int month=0; month < salesData[year].length; month++) {
                totalSales += salesData[year][month];
            }
        }
        System.out.println("Total Sales over all years: " + totalSales);

        int total = Arrays.stream(salesData)
                          .flatMapToInt(Arrays::stream)
                          .sum();
        System.out.println("Total Sales (using streams): " + total);

        //Explain: the use if Arrays.stream here
        // Arrays.stream(salesData) creates a stream of int[] (each row of the 2D array).
        // flatMapToInt(Arrays::stream) takes each int[] and converts it into an IntStream, effectively flattening the 2D array into a single stream of integers.
        // Finally, sum() computes the sum of all integers in that flattened stream.

        // Other Arrays methods that can be used with 2D arrays include:
        // Arrays.deepToString(salesData) - Returns a string representation of the 2D array.

        //Give me a few more examples of Arrays methods that can be used with 2D arrays
        // Arrays.deepEquals(array1, array2) - Compares two 2D arrays
        // Arrays.deepHashCode(array) - Returns a hash code for a 2D array
        // Arrays.setAll(array, generator) - Sets all elements of a 2D array

        //Give me a few more problems that can be solved using 3D arrays
        // 1. Representing a Rubik's Cube: A 3D array can be used to represent the state of a Rubik's Cube, where each dimension corresponds to the cube's layers, rows, and columns.
        // 2. Storing RGB Color Values: A 3D array can be used to store RGB color values for an image, where the first dimension represents the height, the second dimension represents the width, and the third dimension represents the color channels (Red, Green, Blue).
        // 3. Simulating a 3D Game World: A 3D array can be used to represent a 3D game world, where each element corresponds to a voxel or block in the game environment, allowing for efficient storage and manipulation of the game state.
        // 4. Weather Data Analysis: A 3D array can be used to store weather data over time, where the first dimension represents different locations, the second dimension represents different weather parameters (temperature, humidity, etc.), and the third dimension represents time intervals (hours, days, etc.).
        // 5. Medical Imaging: A 3D array can be used to represent medical imaging data, such as MRI or CT scans, where each dimension corresponds to different slices of the scan, allowing for detailed analysis and visualization of the internal structures of the body.

    }
}
