package breakout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static breakout.MainWindow.WINDOW_HEIGHT;
import static breakout.MainWindow.WINDOW_WIDTH;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

class BreakoutPanel extends JPanel implements Runnable, Animated {

    // Game objects
    private Ball ball = new Ball();
    private Paddle paddle = new Paddle();
    private BrickBoard brickBoard = new BrickBoard();
    private Controller controller = new Controller();
    private HUD hud = new HUD();

    // Game info
    private boolean running = false;
    private BufferedImage image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, TYPE_INT_RGB);
    private Graphics2D graphics = (Graphics2D) image.getGraphics();


    private void init() {
        running = true;
        // Make graphics smoother
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Request focus to the window so user can use arrow keys to play
        this.requestFocus();
        // Set arrow key listener
        this.addKeyListener(controller);
    }

    @Override
    public void run() {
        // Init info
        init();
        // Start game
        runGameLoop();
    }

    private void runGameLoop() {
        while (running) {
            update();
            render(null);
            repaint();

            // Sleep for a tiny fraction of a second to slow all objects' movements to a normal speed
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Check if ball has come into contact with paddle or any bricks and react accordingly
    void detectCollisions() {
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
                if (brickHitbox == null) {
                    continue;
                }

                if (ballHitbox.intersects(brickHitbox)) {
                    brick.destroy();
                    ball.bounceVertically();
                    hud.incrementPoints(50);

                    break outer;
                }
            }
        }
    }

    // Check if all bricks are broken or if ball went off screen to determine if the game has ended
    void endGameIfNecessary() {
        if (ball.getBallOffScreen()) {
            hud.gameOver(graphics);
            this.paintComponent(graphics);
            running = false;
        } else if (brickBoard.isEmpty()) {
            hud.playerWins(graphics);
            this.paintComponent(graphics);
            running = false;
        }
    }

    @Override
    public void update() {
        endGameIfNecessary();
        detectCollisions();
        ball.update();
        paddle.update();
    }

    @Override
    public void render(Graphics2D g) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        ball.render(graphics);
        paddle.render(graphics);
        brickBoard.render(graphics);
        hud.render(graphics);
    }

    // Tell swing to draw the game
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.drawImage(image, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
        // Prevent memory leaks
        graphics2D.dispose();
    }

    private class Controller implements KeyListener {


        @Override
        public void keyPressed(KeyEvent e) {
            // User pressed right arrow key
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                paddle.slideRight();
            }

            // User pressed left arrow key
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
