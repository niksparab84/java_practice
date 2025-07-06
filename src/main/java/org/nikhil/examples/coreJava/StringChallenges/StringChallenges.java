package org.nikhil.examples.coreJava.StringChallenges;

import java.util.HashSet;
import java.util.Set;

public class StringChallenges {

    //Write a method to Reverse a string — without using built-in methods
    public static String reverseString(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }

    //Write a method to check if a string is a palindrome
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() -1;
        while(left < right) {
            if(str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //Write a method to check if a string is an anagram of another string
    public static boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        int[] charCount = new int[256]; // Assuming ASCII characters
        for (int i = 0; i < str1.length(); i++) {
            charCount[str1.charAt(i)]++;
            charCount[str2.charAt(i)]--;
        }
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    //Write a method to Remove duplicate characters from a string
    public static String removeDuplicates(String str) {
        StringBuilder result = new StringBuilder();
        boolean[] charSet = new boolean[256]; // Assuming ASCII characters
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (!charSet[currentChar]) {
                charSet[currentChar] = true;
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    // other implementation to remove duplicate characters from a string
    public static String removeDuplicatesUsingSet(String str) {
        StringBuilder result = new StringBuilder();
        Set<Character> charSet = new HashSet<>();
        for (char currentChar : str.toCharArray()) {
            if (!charSet.contains(currentChar)) {
                charSet.add(currentChar);
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    //palindromic algorighms
    // LexicographicallySmallestPalindrome means that we want to create a palindrome from the given string
    // such that it is the smallest possible palindrome in lexicographical order.
    public static String makeLexicographicallySmallestPalindrome(String str) {
        StringBuilder result = new StringBuilder(str);
        int n = str.length();
        for (int i = 0; i < n / 2; i++) {
            char leftChar = str.charAt(i);
            char rightChar = str.charAt(n - 1 - i);
            if (leftChar != rightChar) {
                char smallerChar = (char) Math.min(leftChar, rightChar);
                result.setCharAt(i, smallerChar);
                result.setCharAt(n - 1 - i, smallerChar);
            }
        }
        return result.toString();
    }
}
