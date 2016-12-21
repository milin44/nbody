package nbody.model;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SolarSystemTest {

    @Test
    public void solarSystemTest() {
        SolarSystem solarSystem = new SolarSystem();

        solarSystem.update();

        Body earth = solarSystem.getBody(SolarSystem.EARTH_NAME).get();
        Body sun = solarSystem.getBody(SolarSystem.SUN_NAME).get();

        //System.out.println(sun.toString());
        //System.out.println(earth.toString());

        assertTrue(Math.abs(sun.acceleration.x) < Math.abs(earth.acceleration.x));
        assertTrue(Math.abs(sun.velocity.x) < Math.abs(earth.velocity.x));
    }
}
