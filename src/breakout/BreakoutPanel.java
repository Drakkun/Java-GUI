package breakout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class BreakoutPanel extends JPanel implements Runnable, Animated {

    private boolean running = false;
    private BufferedImage image = new BufferedImage(MainWindow.WIDTH, MainWindow.HEIGHT, BufferedImage.TYPE_INT_RGB);
    private Ball ball = new Ball();
    private Paddle paddle = new Paddle();
    private BrickBoard brickBoard = new BrickBoard();
    private Graphics2D graphics;
    private Controller controller = new Controller();


    private void init() {
        running = true;

        // Draw game on a buffered image and make the rendering more smooth
        graphics = (Graphics2D) image.getGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.requestFocus();
        this.addKeyListener(controller);
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

    public void detectCollisions() {
        Rectangle ballHitbox = ball.getHitbox();
        Rectangle paddleHitbox = paddle.getHitbox();
        Brick[][] bricks = brickBoard.getBricks();

        if (ballHitbox.intersects(paddleHitbox)) {
            ball.bounceVertically();
        }

        outer:
        for (Brick[] row : bricks) {
            for (Brick brick : row) {
                Rectangle brickHitbox = brick.getHitbox();

                if (ballHitbox.intersects(brickHitbox)) {
                    brick.destroy();
                    ball.bounceVertically();

                    break outer;
                }
            }
        }
    }

    @Override
    public void update() {
        detectCollisions();
        ball.update();
        paddle.update();
    }

    @Override
    public void render(Graphics2D g) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, MainWindow.WIDTH, MainWindow.HEIGHT);

        ball.render(graphics);
        paddle.render(graphics);
        brickBoard.render(graphics);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.drawImage(image, 0, 0, MainWindow.WIDTH, MainWindow.HEIGHT, null);
        // Prevent memory leaks
        graphics2D.dispose();
    }

    private class Controller implements KeyListener {


        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                paddle.slideRight();
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                paddle.slideLeft();
            }
        }

        // Detect if keys are being held down by waiting until keyReleased() to stop moving the paddle
        @Override
        public void keyReleased(KeyEvent e) {
            paddle.stopSliding();
        }

        @Override
        public void keyTyped(KeyEvent e) {}
    }
}
