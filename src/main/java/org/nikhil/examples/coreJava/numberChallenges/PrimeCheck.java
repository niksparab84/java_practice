package org.nikhil.examples.coreJava.numberChallenges;

import java.util.Scanner;

public class PrimeCheck {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number to check if it's prime: ");
        int num = sc.nextInt();

        boolean isPrime = true;
        if (num <= 1) {
            isPrime = false; // 0 and 1 are not prime numbers
        } else {
            System.out.println("Math.sqrt(" + num + ") = " + Math.sqrt(num));
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }

        if (isPrime) {
            System.out.println(num + " is a prime number.");
        } else {
            System.out.println(num + " is not a prime number.");
        }
    }
}
