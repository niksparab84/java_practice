package org.nikhil.examples.coreJava.numberChallenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ContinuousSequences {

    // Question: Find the continuous sequences of numbers in a given input. Provide all possible test cases to test
    // your code with proper error handling.
    // Example: Input: 1 2 3 5 9 a 6 7 8 4 @ -5 -7 -3 -2 -1
    // Output: There are 3 sequences:
    // 1. 1 2 3
    // 2. 6 7 8
    // 3. -3 -2 -1

    // Approach:
    // 1. Read the input as a string and split it into an array of strings
    // 2. Convert the array of strings into an array of integers, ignoring any non-integer values
    // 3. Sort the array of integers
    // 4. Iterate through the sorted array and find continuous sequences of numbers
    // 5. Store the sequences in a list and print them out
    // Make sure to handle edge cases such as empty input, input with no valid integers, and input with only one valid integer.
    // Make separate methods for each step of the approach to keep the code clean and modular.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter numbers separated by spaces: ");
        String input = scanner.hasNextLine() ? scanner.nextLine() : "";
        List<List<Integer>> sequences = findSequences(input);
        System.out.print(formatSequences(sequences));
    }

    public static String[] splitInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null.");
        }
        String trimmedInput = input.trim();
        return trimmedInput.isEmpty() ? new String[0] : trimmedInput.split("\\s+");
    }

    public static List<Integer> parseIntegers(String[] tokens) {
        if (tokens == null) {
            throw new IllegalArgumentException("Tokens must not be null.");
        }

        List<Integer> numbers = new ArrayList<>();
        for (String token : tokens) {
            if (token == null) {
                continue;
            }
            try {
                numbers.add(Integer.parseInt(token));
            } catch (NumberFormatException ignored) {
                // Non-integers and values outside the int range are ignored.
            }
        }
        return numbers;
    }

    public static List<Integer> sortIntegers(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Numbers must not be null.");
        }

        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers;
    }

    public static List<List<Integer>> findSequences(String input) {
        List<Integer> numbers = parseIntegers(splitInput(input));
        List<List<Integer>> sequences = collectSequences(sortIntegers(numbers));
        Map<Integer, Integer> firstPositions = new HashMap<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (!firstPositions.containsKey(numbers.get(i))) {
                firstPositions.put(numbers.get(i), i);
            }
        }
        Collections.sort(sequences, (left, right) -> Integer.compare(
                firstOccurrencePosition(left, firstPositions),
                firstOccurrencePosition(right, firstPositions)));
        return sequences;
    }

    private static int firstOccurrencePosition(List<Integer> sequence, Map<Integer, Integer> positions) {
        int firstPosition = Integer.MAX_VALUE;
        for (Integer number : sequence) {
            firstPosition = Math.min(firstPosition, positions.get(number));
        }
        return firstPosition;
    }

    private static List<List<Integer>> collectSequences(List<Integer> sortedNumbers) {
        List<List<Integer>> sequences = new ArrayList<>();
        List<Integer> currentSequence = new ArrayList<>();

        for (Integer number : sortedNumbers) {
            if (number == null) {
                continue;
            }
            if (currentSequence.isEmpty()) {
                currentSequence.add(number);
                continue;
            }

            int previous = currentSequence.get(currentSequence.size() - 1);
            if (number == previous) {
                continue;
            }
            if ((long) number == (long) previous + 1) {
                currentSequence.add(number);
            } else {
                addSequenceIfContinuous(sequences, currentSequence);
                currentSequence = new ArrayList<>();
                currentSequence.add(number);
            }
        }

        addSequenceIfContinuous(sequences, currentSequence);
        return sequences;
    }

    private static void addSequenceIfContinuous(List<List<Integer>> sequences, List<Integer> sequence) {
        if (sequence.size() >= 2) {
            sequences.add(sequence);
        }
    }

    public static String formatSequences(List<List<Integer>> sequences) {
        if (sequences == null) {
            throw new IllegalArgumentException("Sequences must not be null.");
        }
        if (sequences.isEmpty()) {
            return "No continuous sequences found." + System.lineSeparator();
        }

        StringBuilder output = new StringBuilder();
        output.append("There are ").append(sequences.size()).append(" sequences:")
                .append(System.lineSeparator());
        for (int i = 0; i < sequences.size(); i++) {
            output.append(i + 1).append(". ");
            List<Integer> sequence = sequences.get(i);
            for (int j = 0; j < sequence.size(); j++) {
                if (j > 0) {
                    output.append(' ');
                }
                output.append(sequence.get(j));
            }
            output.append(System.lineSeparator());
        }
        return output.toString();
    }
}
