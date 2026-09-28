package org.nikhil.examples.coreJava.numberChallenges;

import java.util.Scanner;

public class DivisibilityCheck {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int number = sc.nextInt();

        if(number % 4 == 0 && number % 6 == 0) {
            System.out.println(number + " is divisible by both 4 and 6");
        } else {
            System.out.println(number + " is NOT divisible by both 4 and 6");
        }
    }
}
