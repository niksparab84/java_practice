package org.nikhil.examples.coreJava.StringChallenges;

import junit.framework.TestCase;

public class StringChallengesTest extends TestCase {

    public void testReverseString() {
        String input = "Hello, World!";
        String expected = "!dlroW ,olleH";
        System.out.println("input String: " + input);
        System.out.println("expected String: " + expected);
        String actual = StringChallenges.reverseString(input);
        assertEquals(expected, actual);
    }

    public void testReverseStringWithSpaces() {
        String input = "Hello World";
        String expected = "dlroW olleH";
        String actual = StringChallenges.reverseString(input);
        assertEquals(expected, actual);
    }

    public void testReverseStringWithSpecialCharacters() {
        String input = "A@rohiParab";
        String expected = "baraPihor@A";
        System.out.println("input String: " + input);
        System.out.println("expected String: " + expected);
        String actual = StringChallenges.reverseString(input);
        assertEquals(expected, actual);
    }

    public void testIsPalindrome1() {
        String input = "madam";
        boolean expected = true;
        boolean actual = StringChallenges.isPalindrome(input);
        System.out.println("For input: " + input + ", expected: " + expected + ", actual: " + actual);
        assertEquals(expected, actual);
    }

    public void testIsPalindrome2() {
        String input = "abcddcba";
        boolean expected = true;
        boolean actual = StringChallenges.isPalindrome(input);
        System.out.println("For input: " + input + ", expected: " + expected + ", actual: " + actual);
        assertEquals(expected, actual);
    }

    public void testIsPalindrome3() {
        String input = "Palindrome";
        boolean expected = false;
        boolean actual = StringChallenges.isPalindrome(input);
        System.out.println("For input: " + input + ", expected: " + expected + ", actual: " + actual);
        assertEquals(expected, actual);
    }

    public void testIsAnagram1() {
        String str1 = "listen";
        String str2 = "silent";
        boolean expected = true;
        boolean actual = StringChallenges.isAnagram(str1, str2);
        System.out.println("For input: " + str1 + " and " + str2 + ", expected: " + expected + ", actual: " + actual);
        assertEquals(expected, actual);
    }

    public void testIsAnagram2() {
        String str1 = "triangle";
        String str2 = "integral";
        boolean expected = true;
        boolean actual = StringChallenges.isAnagram(str1, str2);
        System.out.println("For input: " + str1 + " and " + str2 + ", expected: " + expected + ", actual: " + actual);
        assertEquals(expected, actual);
    }

    public void testIsAnagram3() {
        String str1 = "hello";
        String str2 = "world";
        boolean expected = false;
        boolean actual = StringChallenges.isAnagram(str1, str2);
        System.out.println("For input: " + str1 + " and " + str2 + ", expected: " + expected + ", actual: " + actual);
        assertEquals(expected, actual);
    }

    public void testRemoveDuplicates1() {
        String input = "Hello World";
        String expected = "Helo Wrd";
        String actual = StringChallenges.removeDuplicates(input);
        System.out.println("For input: " + input + ", expected: " + expected + ", actual: " + actual);
        assertEquals(expected, actual);
    }

}