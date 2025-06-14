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

    public static void main(String[] args) {
        String input = "swiss";
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
    }
}
