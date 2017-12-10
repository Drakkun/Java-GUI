package breakout;

import java.awt.*;

import static breakout.Brick.BRICK_HEIGHT;
import static breakout.Brick.BRICK_WIDTH;

class BrickBoard implements Animated {

    private final int VER_PADDING = 50;
    private final int HOR_PADDING = 15;

    // 2D array of the brick brickArray the player is breaking through. A value of false represents a broken brick
    private Brick[][] brickArray = new Brick[3][7];

    BrickBoard() {
        build();
    }

    Brick[][] getBricks() {
        return brickArray;
    }

    private void build() {
        // Initialize the 2D array with unbroken bricks
        for (int row = 0; row < brickArray.length; row++) {
            for (int column = 0; column < brickArray[row].length; column++) {
                int x = column * BRICK_WIDTH + HOR_PADDING;
                int y = row * BRICK_HEIGHT + VER_PADDING;

                brickArray[row][column] = new Brick(x, y);
            }
        }
    }

    boolean isEmpty() {
        // Iterate through the board to find any intact bricks
        for (Brick[] row : brickArray) {
            for (Brick brick : row) {
                if (brick.isIntact()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void update() { }

    @Override
    public void render(Graphics2D graphics) {
        // Draw each brick in the brickArray array
        for (Brick[] row : brickArray) {
            for (Brick brick : row) {
                brick.render(graphics);
            }
        }
    }
}
