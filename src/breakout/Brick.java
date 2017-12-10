package breakout;

import java.awt.*;


class Brick implements Animated {

    static final int BRICK_HEIGHT = 35;
    static final int BRICK_WIDTH = 85;

    private boolean intact = true;
    private int x;
    private int y;
    private Rectangle hitbox;


    private Brick() {}

    // Only use this constructor and get x/y from the brick board
    Brick(int x, int y) {
        this.x = x;
        this.y = y;
        hitbox = new Rectangle(x, y, BRICK_WIDTH, BRICK_HEIGHT);
    }

    // Tell the brick to keep rendering
    boolean isIntact() {
        return intact;
    }

    // Tell the brick to stop rendering
    void destroy() {
        this.intact = false;
    }

    // Get the rectangular 'hitbox' we use to decide if the brick has contacted the ball
    Rectangle getHitbox() {
        return isIntact() ? hitbox : null;
    }

    @Override
    public void update() { }

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
