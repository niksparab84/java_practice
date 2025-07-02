package org.nikhil.examples.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamUtils {

    // Find first non-repeated character in a string
    public static Character findFirstNonRepeatedCharacter(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> input.indexOf(c) == input.lastIndexOf(c))
                .findFirst()
                .orElse(null);
    }

    // Find first non-repeated character in a string using LinkedHashMap to preserve order
    public static Character findFirstNonRepeatedCharacterUsingLHM(String input) {
        LinkedHashMap<Character, Long> charCountMap = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new, // Preserve insertion order
                        Collectors.counting()
                ));
        System.out.println("charCountMap => " + charCountMap);

        return charCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(null);
    }

    // Find frequency of each character in a string using LinkedHashMap
    public static LinkedHashMap<Character, Long> findCharacterFrequency(String inputStr) {
        LinkedHashMap<Character, Long> charFrequencyMap = inputStr.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        return charFrequencyMap;
    }

    public void printInvertedTriangle(int n) {
        for(int j = 0 ;j < n; n = n-2) {
            for (int i = 0; i < n; i++) {
                System.out.print(" * ");
            }
            System.out.println();
            if(n == 2) {
                System.out.println(" * ");
            }
        }
    }

    public void printInvertedSymmetricTriangle(int n) {
        int rows = (n + 2) / 2;
        for (int i = 0; i < rows; i++) {
            int stars = n - (i * 2);
            int spaces = i;
            // Print leading spaces
            for (int s = 0; s < spaces; s++) {
                System.out.print("   ");
            }
            // Print stars with spaces
            for (int j = 0; j < stars; j++) {
                System.out.print("*  ");
            }
            System.out.println();
            if(stars == 2){
                for (int s = 0; s < spaces; s++) {
                    System.out.print("   ");
                }
                System.out.print("  *");
            }
        }
    }

    public static void main(String[] args) {
        //String input = "swiss";
        String input = "Hello World";
        Character firstNonRepeated = findFirstNonRepeatedCharacter(input);
        if (firstNonRepeated != null) {
            System.out.println("First non-repeated character: " + firstNonRepeated);
        } else {
            System.out.println("No non-repeated character found.");
        }

        Character firstNonRepeatedUsingLHM = findFirstNonRepeatedCharacterUsingLHM(input);
        if (firstNonRepeatedUsingLHM != null) {
            System.out.println("First non-repeated character using LinkedHashMap: " + firstNonRepeatedUsingLHM);
        } else {
            System.out.println("No non-repeated character found using LinkedHashMap.");
        }

        String freqInput = "character frequency example";
        LinkedHashMap<Character, Long> charFrequencyMap = findCharacterFrequency(freqInput);
        System.out.println("Character frequency map of string '" + freqInput +  "' is: " + charFrequencyMap);

        List<String> list1 = Arrays.asList("erf", "y4f", "hufre", "ab", null, "jkn5", "oidf");

        List<String> resultList = list1.stream()
                .filter(str -> str != null)
                .filter( str -> str.length() > 3)
                .map(str -> str.toUpperCase()).collect(Collectors.toList());
        System.out.println("result List => " + resultList);

        StreamUtils sd = new StreamUtils();
        System.out.println("Print inverted triangle...");
        sd.printInvertedTriangle(20);
        System.out.println("Print symmetric inverted triangle...");
        sd.printInvertedSymmetricTriangle(20);


        // Find length of each string in the list
        Map<String, Integer> lengthMap = list1.stream()
                .filter(str -> str != null)
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println("Length of each string => " + lengthMap);

        // Find the last but one longest string in a list
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "grape");
        String lastButOneLongest = strings.stream()
                .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length())) // Sort by length in descending order
                .distinct() // Remove duplicates
                .skip(1) // Skip the longest string
                .findFirst() // Get the next one
                .orElse(null); // Default value if not found
        if (lastButOneLongest != null) {
            System.out.println("List of strings: " + strings);
            System.out.println("Last but one longest string: " + lastButOneLongest);
        } else {
            System.out.println("No second longest string found.");
        }

        String secondLastLongest = strings.stream()
                .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length()))
                .distinct()
                .skip(2)
                .findFirst()
                .orElse(null);
        System.out.println("Second last longest string: " + secondLastLongest);
    }
}
