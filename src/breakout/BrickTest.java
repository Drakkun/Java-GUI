package breakout;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class BrickTest {

    @Test
    public void Brick() throws Exception {
        Brick brick = new Brick(3, 4);
        assertNotNull(brick.getHitbox());
        assertTrue(brick.isIntact());
    }

    @Test
    public void destroy() {
        Brick brick = new Brick(7, 8);
        brick.destroy();

        assertTrue(!brick.isIntact());
    }
}
