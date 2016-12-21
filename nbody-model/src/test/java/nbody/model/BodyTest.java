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
        Vector2D location = new Vector2D(0, 0);
        body.location = location;
        body.mass = 1000000;

        other = new Body();
        Vector2D locationOther = new Vector2D(0, 0);
        other.location = locationOther;
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
        Body sun = solarSystem.createSun();
        Body earth = solarSystem.createEarth();

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
        Body sun = solarSystem.createSun();
        Body earth = solarSystem.createEarth();

        earth.addGravityForce(sun, timeSlice);
        sun.addGravityForce(earth, timeSlice);

        assertTrue(Math.abs(sun.acceleration.x) < Math.abs(earth.acceleration.x));

        earth.update(timeSlice);
        sun.update(timeSlice);

        assertTrue(Math.abs(sun.acceleration.x) < Math.abs(earth.acceleration.x));
        assertTrue(Math.abs(sun.velocity.x) < Math.abs(earth.velocity.x));

    }
}
