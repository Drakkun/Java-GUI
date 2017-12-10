package breakout;

import java.awt.*;

import static breakout.MainWindow.WINDOW_WIDTH;

class HUD {

    private int points = 0;
    private Font font = new Font("Verdana", Font.BOLD, 18);


    void incrementPoints(int value) {
        points += value;
    }

    void render(Graphics2D graphics) {
        graphics.setFont(font);

        graphics.setColor(Color.BLACK);
        graphics.drawString("Points: " + Integer.toString(points), WINDOW_WIDTH/2 - 60, 20);
    }

    // Display loss
    void gameOver(Graphics2D graphics) {
        font = new Font("Monospaced", Font.BOLD, 72);
        graphics.setFont(font);

        graphics.setColor(Color.RED);
        graphics.drawString("GAME OVER",120,220);
    }

    // Display win
    void playerWins(Graphics2D graphics) {
        font = new Font("Monospaced", Font.BOLD, 72);
        graphics.setFont(font);

        graphics.setColor(Color.GREEN);
        graphics.drawString("YOU WIN!",160,220);
    }
}
