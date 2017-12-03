package breakout;

import java.awt.*;

public class Ball {

    private int x = 200;
    private int y = 200;
    private int xDirection = 1;
    private int yDirection = 3;
    private int ballSize = 30;

    public void update() {
        setPosition();
    }

    public void render(Graphics2D graphics) {
        graphics.setColor(Color.DARK_GRAY);
        graphics.setStroke(new BasicStroke(4));
        graphics.fillOval(x, y, ballSize, ballSize);
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

        if (x > MainWindow.WIDTH - ballSize) {
            xDirection = -xDirection;
        }

        if (y > MainWindow.HEIGHT - ballSize) {
            yDirection = -yDirection;
        }
    }
}
