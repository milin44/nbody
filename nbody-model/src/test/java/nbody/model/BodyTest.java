package nbody.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BodyTest {
    private Body body;
    private Body other;

    @Before
    public void setUp() {
        body = new Body();
        body.location = new Vector2D(0, 0);
        body.mass = 1000000;
        other = new Body();
        other.location = new Vector2D(0, 0);
        other.mass = 1000000;
    }

    @Test
    public void calculateGravitationalForceAlongPositiveHorizontalAxis() {
        body.location.x = 0;
        body.location.y = 0;
        other.location.x = 1000;
        other.location.y = 0;
        Vector2D force = body.calculateGravitationalForce(other);
        assertEquals(0, force.y, 0.01);
        assertTrue(force.x > 0.00001);
    }

    @Test
    public void calculateGravitationalForceAlongNegativeHorizontalAxis() {
        body.location.x = 1000;
        body.location.y = 0;
        other.location.x = 0;
        other.location.y = 0;
        Vector2D force = body.calculateGravitationalForce(other);
        assertEquals(0, force.y, 0.01);
        assertTrue(force.x < 0.0);
    }
    
    @Test
    public void calculateGravitationalForceAlongPositiveVerticalAxis() {
        body.location.x = 0;
        body.location.y = 0;
        other.location.x = 0;
        other.location.y = 1000;
        Vector2D force = body.calculateGravitationalForce(other);
        assertEquals(0, force.x, 0.01);
        assertTrue(force.y > 0.00001);
    }

    @Test
    public void calculateGravitationalForceAlongNegativeVerticalAxis() {
        body.location.x = 0;
        body.location.y = 1000;
        other.location.x = 0;
        other.location.y = 0;
        Vector2D force = body.calculateGravitationalForce(other);
        assertEquals(0, force.x, 0.01);
        assertTrue(force.y < 0.00001);
    }

    @Test
    public void calculateGravitationalForceOtherAt45Deg() {
        body.location.x = 0;
        body.location.y = 0;
        other.location.x = 1000;
        other.location.y = 1000;
        Vector2D force = body.calculateGravitationalForce(other);
        assertTrue(force.x > 0.00001);
        assertTrue(force.y > 0.00001);
    }

    @Test
    public void calculateGravitationalForceOtherAt225Deg() {
        body.location.x = 1000;
        body.location.y = 1000;
        other.location.x = 0;
        other.location.y = 0;
        Vector2D force = body.calculateGravitationalForce(other);
        assertTrue(force.x < 0.00001);
        assertTrue(force.y < 0.00001);
    }

    @Test
    public void calculateGravitationalForceBetweenSunAndEarth() {
        SolarSystem solarSystem = new SolarSystem();
        Body sun = CelestialBody.SUN.getAsBody(0);
        Body earth = CelestialBody.EARTH.getAsBody(0);

        Vector2D gravitationalForceEarth = earth.calculateGravitationalForce(sun);
        assertTrue(gravitationalForceEarth.x > 1e22);

        Vector2D gravitationalForceSun = sun.calculateGravitationalForce(earth);
        assertTrue(gravitationalForceSun.x < 1e22);

        assertEquals(Math.abs(gravitationalForceSun.x), Math.abs(gravitationalForceEarth.x), .001);
    }

    @Test
    public void updateGravitationalForceBetweenSunAndEarth() {
        final double timeSlice = 1e5;
        SolarSystem solarSystem = new SolarSystem();
        Body sun = CelestialBody.SUN.getAsBody(0);
        Body earth = CelestialBody.EARTH.getAsBody(0);

        earth.addGravityForce(sun);
        sun.addGravityForce(earth);
        solarSystem.update(timeSlice);

        assertTrue(Math.abs(sun.acceleration.x) < Math.abs(earth.acceleration.x));

        earth.updateVelocityAndLocation(timeSlice);
        sun.updateVelocityAndLocation(timeSlice);

        assertTrue(Math.abs(sun.acceleration.x) < Math.abs(earth.acceleration.x));
        assertTrue(Math.abs(sun.velocity.x) < Math.abs(earth.velocity.x));

    }
}
