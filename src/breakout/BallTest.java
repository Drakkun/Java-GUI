package breakout;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BallTest {
    @Test
    public void getBallOffScreen() throws Exception {
    }

    @Test
    public void bounceVertically() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void speedUpToFour() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Ball.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
