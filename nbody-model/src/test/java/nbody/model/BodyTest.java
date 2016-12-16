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
}
