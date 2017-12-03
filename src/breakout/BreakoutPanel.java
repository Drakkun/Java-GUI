package breakout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakoutPanel extends JPanel implements Runnable {

    private boolean running = false;
    private BufferedImage image;
    private Graphics2D graphics;


    private void init() {
        running = true;

        // Draw game on a buffered image
        image = new BufferedImage(MainWindow.WIDTH, MainWindow.HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();
    }

    @Override
    public void run() {
        init();
        runGameLoop();
    }

    private void runGameLoop() {
        while (running) {
            update();
            render();
            repaint();
        }
    }

    public void update() {

    }

    public void render() {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, MainWindow.WIDTH, MainWindow.HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.drawImage(image, 0, 0, MainWindow.WIDTH, MainWindow.HEIGHT, null);
        // Prevent memory leaks
        graphics2D.dispose();
    }
}
