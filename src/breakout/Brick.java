package breakout;

import java.awt.*;


public class Brick implements Animated, Collidable {

    public static final int BRICK_HEIGHT = 35;
    public static final int BRICK_WIDTH = 85;

    private boolean intact = true;
    private int x;
    private int y;
    private Rectangle hitbox;


    private Brick() {}

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
        hitbox = new Rectangle(x, y, BRICK_WIDTH, BRICK_HEIGHT);
    }

    public boolean isIntact() {
        return intact;
    }

    public void destroy() {
        this.intact = false;
    }

    @Override
    public Rectangle getHitbox() {
        return isIntact() ? hitbox : null;
    }

    @Override
    public void update() {}

    @Override
    public void render(Graphics2D graphics) {
        if (!isIntact()) {
            // Brick is broken; do not render.
            return;
        }

        // Fill in brick
        graphics.setColor(Color.BLUE);
        graphics.fillRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);

        // Outline the brick with white so it's distinguishable from other bricks
        graphics.setStroke(new BasicStroke(3));
        graphics.setColor(Color.WHITE);
        graphics.drawRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
    }
}
