package breakout;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


public class BrickBoardTest {


    @Test
    public void getBricks() throws Exception {
        BrickBoard board = new BrickBoard();

        // Creating the board should create the bricks
        assertNotNull(board.getBricks());
    }

    @Test
    public void isEmpty() throws Exception {
        BrickBoard board = new BrickBoard();
        assertEquals(board.isEmpty(), false);

        // Break all bricks/empty board
        Brick[][] bricks = board.getBricks();
        for (Brick[] row : bricks) {
            for (Brick brick : row) {
                // this is just a setter
                brick.destroy();
            }
        }

        assertEquals(board.isEmpty(), true);
    }
}
