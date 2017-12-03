package breakout;

import java.awt.*;

public class Paddle implements Animated {

    private final int WIDTH = 120;
    private final int HEIGHT = 25;
    private final int y = MainWindow.HEIGHT - 80;
    private double x = 260;

    @Override
    public void update() {}

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int) x, y, WIDTH, HEIGHT);
    }
}
