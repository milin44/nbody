package nbody.model;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SolarSystemTest {
    private Body earth;
    private Body sun;

    @Before
    public void setUp() {
        final double timeSlice = 1e5;
        SolarSystem solarSystem = new SolarSystem();
        solarSystem.update(timeSlice);
        earth = solarSystem.getBody(SolarSystem.EARTH_NAME).get();
        sun = solarSystem.getBody(SolarSystem.SUN_NAME).get();
    }

    @Test
    public void solarSystemTest() {
        assertTrue(Math.abs(sun.acceleration.x) < Math.abs(earth.acceleration.x));
        assertTrue(Math.abs(sun.velocity.x) < Math.abs(earth.velocity.x));
    }
}
