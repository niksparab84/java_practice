package org.nikhil.examples.streams;

import java.util.LinkedHashMap;
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
    }
}
