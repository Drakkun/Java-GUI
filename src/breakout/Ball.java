package breakout;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import static breakout.MainWindow.WINDOW_WIDTH;

class Ball implements Animated {

    private final int SIZE = 30;

    // Make x a bit random to ensure a different ball pattern each game
    private double x = ThreadLocalRandom.current().nextInt(250, WINDOW_WIDTH - 250);
    private double y = 315;
    // Move the ball toward the center at the beginning to make the player's life a little easier, since we've
    // randomized ball's starting x position
    private double xDirection = (x < WINDOW_WIDTH/2) ? 2 : -2;
    private double yDirection = -0.1;
    // Helps decide if player has lost
    private boolean ballOffScreen = false;


    boolean getBallOffScreen() {
        return this.ballOffScreen;
    }

    // Return the most updated 'hitbox' used to check if ball has collided with anything
    Rectangle getHitbox() {
        return new Rectangle((int) x, (int) y, SIZE, SIZE);
    }

    void bounceVertically() {
        yDirection = -yDirection;
    }

    // Change the direction as needed (when hitting an object or the side of the screen)
    @Override
    public void update() {
        x += xDirection;
        y += yDirection;

        if (x < 0) {
            xDirection = -xDirection;
        }

        if (y < 0) {
            yDirection = -yDirection;
        }

        if (x > WINDOW_WIDTH - SIZE) {
            xDirection = -xDirection;
        }

        if (y > MainWindow.WINDOW_HEIGHT - SIZE) {
            // When moving down, the ball should only bounce up off the paddle. If this hasn't happened before moving
            // of screen, the player will lose
            ballOffScreen = true;
        }

        speedUpToFour();
    }

    // Ramp up yDirection speed at beginning to start slowly start game; the closer the number is to 0 (whether negative
    // or positive, the slower it is moving)
    void speedUpToFour() {
        if (Math.abs(yDirection) < 4) {
            // If the direction is currently positive we must add 0.01 to add speed
            if (yDirection > 0) {
                yDirection += 0.01;
            // If the direction is currently positive we must remove 0.01 to add speed
            } else {
                yDirection -= 0.01;
            }
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.DARK_GRAY);
        graphics.setStroke(new BasicStroke(4));
        graphics.fillOval((int) x, (int) y, SIZE, SIZE);
    }
}
