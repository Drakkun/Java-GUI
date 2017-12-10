package breakout;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class BreakoutPanelTest {


    @Test
    public void brickHit() {
        BreakoutPanel panel = new BreakoutPanel();
        Ball ball = new Ball();
        Brick brick = new Brick(5, 6);
        HUD hud = new HUD();

        boolean brickIntactBefore = brick.isIntact();
        double yDirBefore = ball.getYDirection();
        int pointsBefore = hud.getPoints();

        panel.brickHit(brick, ball, hud);

        boolean brickIntactAfter = brick.isIntact();
        double yDirAfter = ball.getYDirection();
        int pointsAfter = hud.getPoints();

        assertNotEquals(brickIntactBefore, brickIntactAfter);
        assertTrue(yDirBefore == (yDirAfter * -1));
        assertTrue(pointsAfter > pointsBefore);
    }
}
