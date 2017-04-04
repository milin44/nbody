package nbody.model;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BodySystemTest {
    public static final String EARTH = "EARTH";
    public static final String KILO = "KILO";
    public static final int HEIGHT = 100;

    private BodySystem bodySystem;

    @Before
    public void setUp() {
        bodySystem = new BodySystem();
        bodySystem.addBody(createEarthBody());
        bodySystem.addBody(createKiloBody());
    }

    /**
     * Test simulation dropping a body with mass 1 kg from height 100 m in two timeslices, each one second.
     */
    @Test
    public void bodySystemTestTimeSlice_1() {
        double timeSlice = 1;
        double g = 9.817;
        double time = 0;
        double distanceFallen = 0;
        Body kilo = getKILO();

        // move system to t = 1 sek
        time += bodySystem.update(timeSlice);
        assertEquals(g, kilo.getAcceleration().length(), .01);
        assertEquals(-g, kilo.getAcceleration().y, .01);
        assertEquals(g, kilo.getVelocity().length(), .01);
        assertEquals(-g, kilo.getVelocity().y, .01);
        distanceFallen = HEIGHT + CelestialBody.EARTH.radius - kilo.location.y;
        assertEquals(5, distanceFallen, .1);

        // move system to t = 2 sek
        time += bodySystem.update(timeSlice);
        assertEquals(g, kilo.getAcceleration().length(), .01);
        assertEquals(-g, kilo.getAcceleration().y, .01);
        assertEquals(2 * g, kilo.getVelocity().length(), .01);
        assertEquals(-2 * g, kilo.getVelocity().y, .01);
        distanceFallen = HEIGHT + CelestialBody.EARTH.radius - kilo.location.y;
        assertEquals(20, distanceFallen, 1);
    }

    /**
     * Test simulation dropping a body with mass 1 kg from height 100 m in two timeslices, each two seconds.
     */
    @Test
    public void bodySystemTestTimeSlice_2() {
        double timeSlice = 2;
        double g = 9.817;
        double time = 0;
        double distanceFallen = 0;
        Body kilo = getKILO();

        // move system to t = 2 sek
        time += bodySystem.update(timeSlice); // move system to t = 1 sek
        assertEquals(g, kilo.getAcceleration().length(), .01);
        assertEquals(-g, kilo.getAcceleration().y, .01);
        assertEquals(2 * g, kilo.getVelocity().length(), .01);
        assertEquals(-2 * g, kilo.getVelocity().y, .01);
        distanceFallen = HEIGHT + CelestialBody.EARTH.radius - kilo.location.y;
        assertEquals(20, distanceFallen, 1);
    }

    private Body createEarthBody() {
        Body massive = new Body();
        massive.location = new Vector2D(0, 0);
        massive.mass = CelestialBody.EARTH.mass;
        massive.name = EARTH;
        return massive;
    }

    // 1 kg, 100 meters up, gives g = 9.82
    private Body createKiloBody() {
        Body kilo = new Body();
        kilo.location = new Vector2D(0, HEIGHT + CelestialBody.EARTH.radius);
        kilo.mass = 1;
        kilo.name = KILO;
        return kilo;
    }

    private Body getKILO() {
        return bodySystem.getBody(KILO).get();
    }

}
