package org.nikhil.examples.coreJava.StringChallenges;

// Problem Statement: Given a string s and an integer n, find the number of occurrences of the letter 'a'
// in the first n letters of the infinite string created by repeating s infinitely.
// https://www.hackerrank.com/challenges/repeated-string/problem

import java.util.Scanner;

public class RepeatedString {

    public static long getLetterCount(String s, long strlength, char letter) {
        long count = 0;
        for (int i = 0; i < strlength; i++) {
            if (s.charAt(i) == letter) {
                count++;
            }
        }
        return count;
    }

    public static long getRepeatedStringCount(String s, long n, char letter) {
        long count = 0;
        long strlength = s.length();
        long completeRepeats = n / strlength;
        long remainingChars = n % strlength;

        count += completeRepeats * getLetterCount(s, strlength, letter);
        count += getLetterCount(s, remainingChars, letter);

        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String s = input.nextLine();
        System.out.print("Enter the number of characters to consider: ");
        long n = input.nextLong();
        System.out.print("Enter the letter to count: ");
        char letter = input.next().charAt(0);

        long result = getRepeatedStringCount(s, n, letter);
        System.out.println("Number of occurrences of '" + letter + "': " + result);

        input.close();
    }
}
