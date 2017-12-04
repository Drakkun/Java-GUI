package breakout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static breakout.CollisionSide.*;
import static breakout.MainWindow.WINDOW_HEIGHT;
import static breakout.MainWindow.WINDOW_WIDTH;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class BreakoutPanel extends JPanel implements Runnable, Animated {

    // Game objects
    private Ball ball = new Ball();
    private Paddle paddle = new Paddle();
    private BrickBoard brickBoard = new BrickBoard();
    private Controller controller = new Controller();
    private HUD hud = new HUD();

    // Game info
    private boolean running = false;
    private int points = 0;
    private BufferedImage image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, TYPE_INT_RGB);
    private Graphics2D graphics = (Graphics2D) image.getGraphics();


    private void init() {
        running = true;
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

        CollisionSide offSide = sideOfIntersection(paddleHitbox, ballHitbox);
        ball.bounce(offSide);

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

    private CollisionSide sideOfIntersection(Rectangle r1, Rectangle r2) {
        if (!r1.intersects(r2)) {
            return NONE;
        }
        if (r1.getY() <= r2.getY() - (r2.getHeight()/2) || r1.getY() >= r2.getY() + (r2.getHeight()/2)) {
            System.out.println("Up/down");
            return TOP;
        } else {
            System.out.println("Side");
            return RIGHT;
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
        graphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        ball.render(graphics);
        paddle.render(graphics);
        brickBoard.render(graphics);
        hud.render(graphics);
    }

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
