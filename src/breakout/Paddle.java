package breakout;

import java.awt.*;

import static breakout.SlideDirection.*;

class Paddle implements Animated {

    private final int WIDTH = 120;
    private final int HEIGHT = 25;
    private final int y = MainWindow.WINDOW_HEIGHT - 80;
    private final int RIGHT_BOUNDARY = MainWindow.WINDOW_WIDTH - WIDTH;
    private final int LEFT_BOUNDARY = 0;

    private double x = 260;
    // Used with keyReleased() to check if the player is holding down the arrow key
    private SlideDirection slidingTo = NOT_SLIDING;

    void setSlidingTo(SlideDirection value) {
        slidingTo = value;
    }

    // Return the most updated 'hitbox' used to check if paddle has collided with the ball
    Rectangle getHitbox() {
        return new Rectangle((int) x, y, WIDTH, HEIGHT);
    }

    // Slide in the correct direction but only if the paddle is still on screen
    @Override
    public void update() {
        if (slidingTo.equals(RIGHT) && x < RIGHT_BOUNDARY) {
            x += 2;
        }

        if (slidingTo.equals(LEFT) && x > LEFT_BOUNDARY) {
            x -= 2;
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int) x, y, WIDTH, HEIGHT);
    }
}

enum SlideDirection {
    RIGHT, LEFT, NOT_SLIDING
}