package breakout;

import java.awt.*;

public class Ball implements Animated {

    private double x = 200;
    private double y = 200;
    private int xDirection = 1;
    private int yDirection = 3;
    private int size = 30;

    @Override
    public void update() {
        setPosition();
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.DARK_GRAY);
        graphics.setStroke(new BasicStroke(4));
        graphics.fillOval((int) x, (int) y, size, size);
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

        if (x > MainWindow.WIDTH - size) {
            xDirection = -xDirection;
        }

        if (y > MainWindow.HEIGHT - size) {
            yDirection = -yDirection;
        }
    }
}
