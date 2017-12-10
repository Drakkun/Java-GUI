package breakout;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class HUDTest {

    @Test(expected = IllegalArgumentException.class)
    public void incrementPoints() throws Exception {
        HUD hud = new HUD();

        hud.incrementPoints(5);
        assertEquals(hud.getPoints(), 5);

        hud.incrementPoints(0);
        assertEquals(hud.getPoints(), 5);

        // Should throw IllegalArgumentException
        hud.incrementPoints(-5);
    }
}
