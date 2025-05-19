import org.junit.Test;
import org.nikhil.examples.GameOfLife;

public class GameOfLifeTest {



    @Test
    public void procesNextStateTest() {
        int[][] input = {{1,1},{1,0}};

        GameOfLife g = new GameOfLife();
        g.processBoard(input);

        // implementation goes here

        int[][] expected = {{1,1},{1,1}};
        //assertEquals(expected, input);
    }

}
