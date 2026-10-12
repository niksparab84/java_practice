package org.nikhil.examples.coreJava.numberChallenges;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

public class ContinuousSequencesTest extends TestCase {

    public void testExampleInputFindsThreeSequencesInFirstAppearanceOrder() {
        String input = "1 2 3 5 9 a 6 7 8 4 @ -5 -7 -3 -2 -1";

        List<List<Integer>> sequences = ContinuousSequences.findSequences(input);

        assertEquals(Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(6, 7, 8),
                Arrays.asList(-3, -2, -1)), sequences);
    }

    public void testEmptyAndWhitespaceInput() {
        assertTrue(ContinuousSequences.findSequences("").isEmpty());
        assertTrue(ContinuousSequences.findSequences("   \t  ").isEmpty());
    }

    public void testInputWithNoValidIntegers() {
        assertTrue(ContinuousSequences.findSequences("a @ 1.5 --").isEmpty());
    }

    public void testSingleIntegerDoesNotFormASequence() {
        assertTrue(ContinuousSequences.findSequences("42").isEmpty());
    }

    public void testUnorderedConsecutivePairIsASequence() {
        assertEquals(Collections.singletonList(Arrays.asList(3, 4)),
                ContinuousSequences.findSequences("4 3"));
    }

    public void testInputIsSplitParsedAndSortedByTheIndividualSteps() {
        assertEquals(Arrays.asList("3", "-1", "bad"),
                Arrays.asList(ContinuousSequences.splitInput("  3\t-1 bad  ")));
        assertEquals(Arrays.asList(3, -1),
                ContinuousSequences.parseIntegers(new String[]{"3", "bad", "-1", null}));

        List<Integer> numbers = Arrays.asList(3, -1, 2);
        assertEquals(Arrays.asList(-1, 2, 3), ContinuousSequences.sortIntegers(numbers));
        assertEquals(Arrays.asList(3, -1, 2), numbers);
    }

    public void testNonConsecutiveAndNegativeNumbers() {
        assertEquals(Arrays.asList(
                Arrays.asList(-4, -3),
                Arrays.asList(0, 1)), ContinuousSequences.findSequences("-4 -3 0 1 7 9"));
    }

    public void testInvalidTokensBetweenIntegersAreIgnored() {
        assertEquals(Collections.singletonList(Arrays.asList(1, 2, 3)),
                ContinuousSequences.findSequences("1 bad 2 @ 3"));
    }

    public void testDuplicateIntegersDoNotBreakOrDuplicateSequences() {
        assertEquals(Collections.singletonList(Arrays.asList(1, 2, 3)),
                ContinuousSequences.findSequences("3 2 2 1 1"));
    }

    public void testIntegerOverflowTokensAreIgnored() {
        assertEquals(Collections.singletonList(Arrays.asList(1, 2)),
                ContinuousSequences.findSequences("2147483648 1 2 -2147483649"));
    }

    public void testIntegerBoundariesDoNotOverflowWhenCheckingContinuity() {
        assertEquals(Arrays.asList(
                Arrays.asList(Integer.MAX_VALUE - 1, Integer.MAX_VALUE),
                Arrays.asList(Integer.MIN_VALUE, Integer.MIN_VALUE + 1)),
                ContinuousSequences.findSequences(
                        Integer.MAX_VALUE + " " + (Integer.MAX_VALUE - 1) + " "
                                + Integer.MIN_VALUE + " " + (Integer.MIN_VALUE + 1)));
    }

    public void testOutputForNoSequences() {
        assertEquals("No continuous sequences found." + System.lineSeparator(),
                ContinuousSequences.formatSequences(Collections.<List<Integer>>emptyList()));
    }

    public void testFormattedSequenceOutput() {
        assertEquals("There are 2 sequences:" + System.lineSeparator()
                        + "1. -2 -1" + System.lineSeparator()
                        + "2. 4 5" + System.lineSeparator(),
                ContinuousSequences.formatSequences(Arrays.asList(
                        Arrays.asList(-2, -1), Arrays.asList(4, 5))));
    }

    public void testNullInputIsReported() {
        try {
            ContinuousSequences.findSequences(null);
            fail("Expected null input to be rejected.");
        } catch (IllegalArgumentException expected) {
            assertEquals("Input must not be null.", expected.getMessage());
        }
    }

    public void testNullArgumentsToIndividualStepsAreReported() {
        assertIllegalArgument(() -> ContinuousSequences.parseIntegers(null));
        assertIllegalArgument(() -> ContinuousSequences.sortIntegers(null));
        assertIllegalArgument(() -> ContinuousSequences.formatSequences(null));
    }

    private void assertIllegalArgument(Runnable operation) {
        try {
            operation.run();
            fail("Expected null argument to be rejected.");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }
    }
}
