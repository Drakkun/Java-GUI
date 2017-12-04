package breakout;

import java.awt.*;

public class Ball implements Animated, Collidable {

    private final int SIZE = 30;

    private double x = 200;
    private double y = 200;
    private double xDirection = 1;
    private double yDirection = 3;


    @Override
    public Rectangle getHitbox() {
        return new Rectangle((int) x, (int) y, SIZE, SIZE);
    }

    public void bounceVertically() {
        yDirection = -yDirection;
    }

    private void bounceHorizontally() {
        xDirection = -xDirection;
    }

    public void bounce(CollisionSide fromSide) throws IllegalArgumentException {
        switch (fromSide) {
            case NONE:
                break;
            case LEFT:
            case RIGHT:
                bounceHorizontally();
                break;
            case BOTTOM:
            case TOP:
                bounceVertically();
                break;
            default:
                throw new IllegalArgumentException(
                        "Argument 'fromSide' must be a nonnull CollisionSide. Value was " + fromSide
                );
        }
    }

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

        if (x > MainWindow.WINDOW_WIDTH - SIZE) {
            xDirection = -xDirection;
        }

        if (y > MainWindow.WINDOW_HEIGHT - SIZE) {
            yDirection = -yDirection;
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.DARK_GRAY);
        graphics.setStroke(new BasicStroke(4));
        graphics.fillOval((int) x, (int) y, SIZE, SIZE);
    }
}
