package breakout;

import java.awt.*;

interface Animated {
    // Update any object info
    public void update();
    // Actually render the next position & any updates
    public void render(Graphics2D graphics);
}
