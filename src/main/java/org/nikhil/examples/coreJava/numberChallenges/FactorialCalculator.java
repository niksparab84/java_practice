package org.nikhil.examples.coreJava.numberChallenges;

import java.util.Scanner;

public class FactorialCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number to calculate its factorial: ");
        int number = sc.nextInt();

        long factorial = calculateFactorial(number);
        System.out.println("Factorial of " + number + " is: " + factorial);

        long recursiveFactorial = recursiveFactorial(number);
        System.out.println("Factorial of " + number + " using recursion is: " + recursiveFactorial);
    }

    private static long calculateFactorial(int number) {
        if(number == 0) {
            return 1;
        }

        long factorial = 1;
        for(int i=1; i <= number; i++) {
            factorial *= i;
        }

        return factorial;
    }

    private static long recursiveFactorial(int number) {
        if(number == 0) {
            return 1;
        }
        return number * recursiveFactorial(number - 1);
    }
}
