package breakout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class BreakoutPanel extends JPanel implements Runnable, Animated {

    private boolean running = false;
    private BufferedImage image = new BufferedImage(MainWindow.WIDTH, MainWindow.HEIGHT, BufferedImage.TYPE_INT_RGB);
    private Graphics2D graphics;
    private Ball ball = new Ball();
    private Paddle paddle = new Paddle();


    private void init() {
        running = true;

        // Draw game on a buffered image and make the rendering more smooth
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
            render(null);
            repaint();
        });

        while (running) {
            renderWithDelay.start();
        }
    }

    @Override
    public void update() {
        ball.update();
        paddle.update();
    }

    @Override
    public void render(Graphics2D g) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, MainWindow.WIDTH, MainWindow.HEIGHT);

        ball.render(graphics);
        paddle.render(graphics);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.drawImage(image, 0, 0, MainWindow.WIDTH, MainWindow.HEIGHT, null);
        // Prevent memory leaks
        graphics2D.dispose();
    }
}
