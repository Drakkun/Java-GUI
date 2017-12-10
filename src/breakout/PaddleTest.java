package breakout;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class PaddleTest {


    @Test
    public void update() throws Exception {
        Paddle paddle = new Paddle();

        // Set sliding to right then check that update actually moved paddle to right
        double xBeforeUpdate = paddle.getHitbox().x;
        paddle.setSlidingTo(SlideDirection.RIGHT);
        paddle.update();
        double xAfterUpdate = paddle.getHitbox().x;
        assertTrue(xBeforeUpdate < xAfterUpdate);

        // Set sliding to left then check that update actually moved paddle to lef
        xBeforeUpdate = paddle.getHitbox().x;
        paddle.setSlidingTo(SlideDirection.LEFT);
        paddle.update();
        xAfterUpdate = paddle.getHitbox().x;
        assertTrue(xBeforeUpdate > xAfterUpdate);

        // Don't move - don't update
        xBeforeUpdate = paddle.getHitbox().x;
        paddle.setSlidingTo(SlideDirection.NOT_SLIDING);
        paddle.update();
        xAfterUpdate = paddle.getHitbox().x;
        assertTrue(xBeforeUpdate == xAfterUpdate);
    }
}
