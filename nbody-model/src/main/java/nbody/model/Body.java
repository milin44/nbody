package nbody.model;

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

    public Body() {
    }

    public Body(Vector2D location, Vector2D velocity, double radius, double mass) {
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
    public void addForce(Vector2D force) {
        Vector2D accByForce = new Vector2D(force);
        accByForce.div(mass);
        acceleration.add(accByForce);
    }

    /**
     * Calculates the gravitational force between this body and another body and adds the resulting force.
     *
     * Newton's law of universal gravitation: F = G * (m1 * m2)/r²
     *  - F = force between bodies in newtons. The force is a vector pointing towards the current body (attracting)
     *  - m1 = mass of body1 in kilograms
     *  - m2 = mass of body2 in kilograms
     *  - G = gravitaional constant (6.674×10−11 N · (m/kg)² )
     *  - r = distance between the centers of the masses in meters
     *
     *
     * @param otherBody
     */
    public void addGravityForce(Body otherBody) {
        // get direction vector
        Vector2D directionVect = new Vector2D(this.location);
        directionVect.sub(otherBody.location).normalize().mul(-1);

        // distance between bodies
        double r = this.location.distance(otherBody.location);

        // calculate force
        Vector2D grativationalForce = new Vector2D(directionVect);
        grativationalForce.mul(Physics.G).mul(this.mass).mul(otherBody.mass).div(r*r);

        // add force
        addForce(grativationalForce);
    }


    public void update() {
        updateAcceleration();
        updateVelocity();
        updateLocation();
    }

    protected void updateAcceleration() {}

    protected void updateVelocity() {
        velocity.add(acceleration);
    }

    protected void updateLocation() {
        location.add(velocity);
    }
}
