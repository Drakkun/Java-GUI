package breakout;

import org.junit.Test;

import static org.junit.Assert.*;

public class BallTest {

    Ball ball = new Ball();

    @Test
    public void update() throws Exception {
        int yBeforeUpdate = ball.getHitbox().y;
        int xBefore = ball.getHitbox().x;
        ball.update();
        int yAfterUpdate = ball.getHitbox().y;
        int xAfter = ball.getHitbox().x;

        assertTrue(yBeforeUpdate != yAfterUpdate);
        assertTrue(xBefore != xAfter);
    }

    @Test
    public void bounceVertically() {
        double yDir = ball.getYDirection();

        ball.bounceVertically();

        double yDirAfterBounce = ball.getYDirection();

        assertTrue(yDir == (yDirAfterBounce * -1));
    }

    @Test
    public void speedUpToFour() throws Exception {
        double yDBefore;

        double yDAfter = ball.getYDirection();

        while(Math.abs(yDAfter) < 4) {
            yDBefore = ball.getYDirection();
            ball.speedUpToFour();
            yDAfter = ball.getYDirection();

            assertNotEquals(yDBefore, yDAfter);
            assertNotEquals(yDAfter, 0);
            assertTrue(4 - Math.abs(yDAfter) < 4 - Math.abs(yDBefore));
        }
    }
}
