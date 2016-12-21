package nbody.model;

import org.junit.Before;

/**
 * Implementation of a generic body that can move in a two-dimensional coordinate system.
 *
 */
public class Body {
    public Vector2D location;
    public Vector2D velocity;
    public Vector2D acceleration;

    /** mass in kilograms */
    public double mass;

    /** radius in kilometers */
    public double radius;

    public String name;


    public Body() {
        if (acceleration == null) {
            acceleration = new Vector2D();
        }
        if (velocity == null) {
            velocity = new Vector2D();
        }
        if (location == null) {
            location = new Vector2D();
        }

    }

    public Body(Vector2D location, Vector2D velocity, double radius, double mass) {
        this();
        this.location = location;
        this.velocity = velocity;
        this.radius = radius;
        this.mass = mass;
    }

    /**
     * Adds a force vector to the body wich implies a change in acceleration depending on mass.
     *
     *  Newton's second law: F = m * a, a = F/m
     *
     *  - F = force acting on the body in newtons
     *  - m = mass of body in kilograms
     *  - a = acceleration in m/s²

     *
     * @param force
     */
    public void addForce(Vector2D force, double timeSlice) {
        Vector2D accByForce = new Vector2D(force);
        accByForce.div(mass);
        acceleration.add(accByForce).mul(timeSlice);
    }


    public void addGravityForce(Body other, double timeSlice) {
        addForce(calculateGravitationalForce(other), timeSlice);
    }

    /**
     * Calculates the gravitational force between this body and another body.
     *
     * Newton's law of universal gravitation: F = G * (m1 * m2)/r²
     *  - F = force between bodies in newtons. The force is a vector pointing towards the current body (attracting)
     *  - m1 = mass of body1 in kilograms
     *  - m2 = mass of body2 in kilograms
     *  - G = gravitaional constant (6.674×10−11 N · (m/kg)² )
     *  - r = distance between the centers of the masses in meters
     *
     *
     * @param other
     */
    protected Vector2D calculateGravitationalForce(Body other) {
        // get direction vector
        Vector2D directionVect = new Vector2D(this.location);
        directionVect.sub(other.location).normalize().mul(-1);

        // distance between bodies
        double r = this.location.distance(other.location);

        // calculate force
        Vector2D grativationalForce = new Vector2D(directionVect);
        grativationalForce.mul(Physics.G).mul(this.mass).mul(other.mass).div(r*r);

        return grativationalForce;
    }


    public void update(double timeSlice) {
        updateAcceleration();
        updateVelocity();
        updateLocation(timeSlice);
    }

    protected void updateAcceleration() {}

    protected void updateVelocity() {
        velocity.add(acceleration);
    }

    protected void updateLocation(double timeSlice) {
        location.add(velocity.mul(timeSlice));
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append(String.format("\n%s", name)).append('\n');
        buf.append(String.format("acc_x = %f, acc_y = %f", acceleration.x, acceleration.y)).append('\n');
        buf.append(String.format("vel_x = %f, vel_y = %f", velocity.x, velocity.y)).append('\n');
        buf.append(String.format("loc_x = %f, loc_y = %f", location.x, location.y)).append('\n');
        return buf.toString();
    }
}
