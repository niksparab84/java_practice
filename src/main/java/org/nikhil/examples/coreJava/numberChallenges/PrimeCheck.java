package org.nikhil.examples.coreJava.numberChallenges;

import java.util.Scanner;

// What is prime number ?
// A prime number is a whole number greater than 1 that has exactly two positive divisors: 1 and itself.
// For example, 2, 3, 5, and 7 are prime. 4 is not prime because it can be divided evenly by 1, 2, and 4.
// 1 is not prime because it has only one positive divisor.

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
                // why we are using Math.sqrt(num) because if a number is not prime, it must have a factor less than or equal to its square root.
                // This reduces the number of iterations needed to check for primality.
                // What if we use i<num? It will still work, but it will be less efficient, especially for large numbers.
                // The square root method significantly reduces the number of checks needed.
                // What will be the result of Math.sqrt of non-perfect squares? It will return a decimal value. For example, Math.sqrt(10) returns approximately 3.162.
                // then the loop will run until i <= 3.162, which means it will check for factors 2 and 3. This is sufficient to determine if 10 is prime or not.
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
