package breakout;

import java.awt.*;

import static breakout.MainWindow.WINDOW_WIDTH;

public class HUD {

    private int points = 0;

    public void incrementPoints(int value) {
        points += value;
    }

    public void render(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawString("Points: " + Integer.toString(points), WINDOW_WIDTH/2 - 30, 20);
    }
}
