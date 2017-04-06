package nbody.model;

/**
 * Implementation of a generic body that moves in a two-dimensional coordinate system.
 * The movement is caused by applying forces on the body.
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

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getLocation() {
        return location;
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
     *
     */
    public void addAccelerationByForce(Vector2D force) {
        Vector2D accByForce = new Vector2D(force);
        accByForce.div(mass);
        acceleration.add(accByForce);
    }



    /**
     * Calculates the gravitational force between this body and another body and
     * accumulates the force.
     *
     * @param other
     */
    public void addAccelerationByGravityForce(Body other) {
        addAccelerationByForce(calculateGravitationalForce(other));
    }

    /**
     * Resets acceleration vector so that addAccelerationByGravityForce() can accumulate forces during a new timeslice.
     */
    public void resetAcceleration() {
        acceleration = new Vector2D();
    }

    /**
     * Calculates a new velocity and location for the body for current acceleration and
     * a given timeslice.
     *
     * Note that the velocity calculated by applying the acceleration during the timeslice
     * will be the final velocity. To calculate the new location we will use the average
     * velocity instead in order to get a better approximation.
     *
     * The accuracy of the calculated velocity and location will be dependent on how how long
     * the timeslice is and how constant the acceleration is during the timeslice.
     *
     * @param timeSlice the timeslice for which current acceleration should affect the body.
     */
    public void updateVelocityAndLocation(double timeSlice) {
        // caluclate final velocity when the time slice has occurred
        Vector2D oldVelocity = new Vector2D(this.velocity);
        updateVelocity(timeSlice);

        // updateVelocityAndLocation location using average velocity
        Vector2D changedVelocityAverage = new Vector2D(this.velocity).sub(oldVelocity).div(2.0);
        Vector2D averageVelocity = new Vector2D(oldVelocity).add(changedVelocityAverage);
        updateLocation(timeSlice, averageVelocity);
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

    /**
     * Calculates the final velocity when current accumulated acceleration has been applied during a timeslice.
     *
     * @param timeSlice
     */
    protected void updateVelocity(double timeSlice) {
        Vector2D velocityByAcc = new Vector2D(acceleration).mul(timeSlice);
        velocity.add(velocityByAcc);
    }

    /**
     * Calulates a new location given that an velocity has been applied a given timeslice.
     *
     * @param timeSlice the timeslice for which given average velocity should affect the body.
     * @param averageVelocity the average velocity during the timeslice
     */
    protected void updateLocation(double timeSlice, Vector2D averageVelocity) {
        Vector2D locationByVelocity = new Vector2D(averageVelocity).mul(timeSlice);
        location.add(locationByVelocity);
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append(String.format("\n%s", name)).append('\n');
        buf.append(String.format("mass = %f", mass)).append('\n');
        buf.append(String.format("acc_x = %f, acc_y = %f", acceleration.x, acceleration.y)).append('\n');
        buf.append(String.format("vel_x = %f, vel_y = %f", velocity.x, velocity.y)).append('\n');
        buf.append(String.format("loc_x = %f, loc_y = %f", location.x, location.y)).append('\n');
        return buf.toString();
    }
}
