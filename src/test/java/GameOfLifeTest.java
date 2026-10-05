import org.junit.Test;
import org.nikhil.examples.dsaChallenges.GameOfLife;

import static org.junit.Assert.assertArrayEquals;

public class GameOfLifeTest {

    @Test
    public void findNextGenReturnsNextGenerationFor2x2Matrix() {
        int[][] input = {
                {1, 1},
                {1, 0}
        };
        int[][] expected = {
                {1, 1},
                {1, 1}
        };

        assertArrayEquals(expected, GameOfLife.findNextGen(input));
    }

    @Test
    public void findNextGenReturnsNextGenerationFor3x3Matrix() {
        int[][] input = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        int[][] expected = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };

        assertArrayEquals(expected, GameOfLife.findNextGen(input));
    }

    @Test
    public void findNextGenReturnsNextGenerationFor4x4Matrix() {
        int[][] input = {
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0}
        };
        int[][] expected = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 0}
        };

        assertArrayEquals(expected, GameOfLife.findNextGen(input));
    }
}
