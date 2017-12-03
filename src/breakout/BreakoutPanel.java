package breakout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class BreakoutPanel extends JPanel implements Runnable {

    private boolean running = false;
    private BufferedImage image;
    private Graphics2D graphics;

    Ball ball = new Ball();


    private void init() {
        running = true;

        // Draw game on a buffered image
        image = new BufferedImage(MainWindow.WIDTH, MainWindow.HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    public void run() {
        init();
        runGameLoop();
    }

    private void runGameLoop() {
        Timer renderWithDelay = new Timer(8, (ActionEvent e) -> {
            update();
            render();
            repaint();
        });

        while (running) {
            renderWithDelay.start();
        }
    }

    public void update() {
        ball.update();
    }

    public void render() {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, MainWindow.WIDTH, MainWindow.HEIGHT);

        ball.render(graphics);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.drawImage(image, 0, 0, MainWindow.WIDTH, MainWindow.HEIGHT, null);
        // Prevent memory leaks
        graphics2D.dispose();
    }
}
