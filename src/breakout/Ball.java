package breakout;

import java.awt.*;

public class Ball implements Animated {

    private final int SIZE = 30;

    private double x = 200;
    private double y = 200;
    private double xDirection = 1;
    private double yDirection = 3;


    public Rectangle getHitbox() {
        return new Rectangle((int) x, (int) y, SIZE, SIZE);
    }

    public void bounceVertically() {
        yDirection = -yDirection;
    }

    @Override
    public void update() {
        setPosition();
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.DARK_GRAY);
        graphics.setStroke(new BasicStroke(4));
        graphics.fillOval((int) x, (int) y, SIZE, SIZE);
    }

    private void setPosition() {
        x += xDirection;
        y += yDirection;

        if (x < 0) {
            xDirection = -xDirection;
        }

        if (y < 0) {
            yDirection = -yDirection;
        }

        if (x > MainWindow.WIDTH - SIZE) {
            xDirection = -xDirection;
        }

        if (y > MainWindow.HEIGHT - SIZE) {
            yDirection = -yDirection;
        }
    }
}
