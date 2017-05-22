package nbody.model;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BodyTest {
    private final double EXPECTED_FORCE = 6673;
    private final double BODY_MASS = 1e10;
    private final double BODY_DISTANCE = 1e3;
    private Body body;
    private Body other;

    @Before
    public void setUp() {
        body = new Body();
        body.location = new Vector3D(0, 0, 0);
        body.mass = BODY_MASS;
        other = new Body();
        other.location = new Vector3D(0, 0, 0);
        other.mass = BODY_MASS;
    }

    @Test
    public void calculateGravitationalForceAlongHorizontalAxis() {
        body.location.x = 0;
        body.location.y = 0;
        other.location.x = BODY_DISTANCE;
        other.location.y = 0;

        Vector3D bodyForce = body.calculateGravitationalForce(other);
        assertEquals(0, bodyForce.y, 0.01);
        assertEquals(EXPECTED_FORCE, bodyForce.x, 0.01);

        Vector3D otherForce = body.calculateGravitationalForce(other);
        assertEquals(0, otherForce.y, 0.01);
        assertEquals(EXPECTED_FORCE, otherForce.x, 0.01);
    }

    @Test
    public void calculateGravitationalForceAlongVerticalAxis() {
        body.location.x = 0;
        body.location.y = 0;
        other.location.x = 0;
        other.location.y = BODY_DISTANCE;

        Vector3D bodyForce = body.calculateGravitationalForce(other);
        assertEquals(0, bodyForce.x, 0.01);
        assertEquals(EXPECTED_FORCE, bodyForce.y, 0.01);

        Vector3D otherForce = body.calculateGravitationalForce(other);
        assertEquals(0, otherForce.x, 0.01);
        assertEquals(EXPECTED_FORCE, otherForce.y, 0.01);
    }

    @Test
    public void calculateGravitationalForceAlongDepthAxis() {
        body.location.x = 0;
        body.location.y = 0;
        body.location.z = 0;
        other.location.x = 0;
        other.location.y = 0;
        other.location.z = BODY_DISTANCE;

        Vector3D bodyForce = body.calculateGravitationalForce(other);
        assertEquals(0, bodyForce.x, 0.01);
        assertEquals(EXPECTED_FORCE, bodyForce.z, 0.01);

        Vector3D otherForce = body.calculateGravitationalForce(other);
        assertEquals(0, otherForce.x, 0.01);
        assertEquals(EXPECTED_FORCE, otherForce.z, 0.01);
    }

    @Test
    public void calculateGravitationalForceOtherAt45Deg() {
        body.location.x = 0;
        body.location.y = 0;
        other.location.x = Math.sin(Math.toRadians(45)) * BODY_DISTANCE;
        other.location.y = Math.cos(Math.toRadians(45)) * BODY_DISTANCE;
        Vector3D bodyForce = body.calculateGravitationalForce(other);
        assertEquals(EXPECTED_FORCE, bodyForce.length(), 0.01);
    }

    @Test
    public void calculateGravitationalForceOtherAt225Deg() {
        body.location.x = Math.sin(Math.toRadians(225)) * BODY_DISTANCE;
        body.location.y = Math.cos(Math.toRadians(225)) * BODY_DISTANCE;;
        other.location.x = 0;
        other.location.y = 0;
        Vector3D bodyForce = body.calculateGravitationalForce(other);
        assertEquals(EXPECTED_FORCE, bodyForce.length(), 0.01);
    }

    @Test
    public void calculateGravitationalAccelerationForEarth() {
        Body earth = new Body();
        earth.mass = CelestialBody.EARTH.mass;
        Body oneKg = new Body();
        oneKg.location = new Vector3D(0, CelestialBody.EARTH.radius, 0);
        oneKg.mass = 1;
        assertEquals(9.82, Math.abs(oneKg.calculateGravitationalForce(earth).length()), 0.01);
    }

}
