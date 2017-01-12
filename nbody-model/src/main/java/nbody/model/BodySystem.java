package nbody.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BodySystem {

    private List<Body> bodies;

    public BodySystem() {
        bodies = new ArrayList<>();
    }

    public List<Body> getBodies() {
        return bodies;
    }

    public void addBody(Body body) {
        bodies.add(body);
    }

    public double update(double timeSlice) {
        // reset acceleration so we can accumulate acceleration due to gravitation from all bodies
        bodies.stream().forEach(i -> i.resetAcceleration()) ;

        // add gravitation force to each body from each body
        for (int i = 0; i < bodies.size(); i++) {
            Body current = bodies.get(i);
            for (int j = 0; j < bodies.size(); j++) {
                Body other = bodies.get(j);
                if (other != current) {
                    current.addGravityForce(other);
                }
            }
            current.updateVelocityAndLocation(timeSlice);
        }
        return timeSlice;
    }

    public Optional<Body> getBody(String name) {
        return bodies.stream().filter(i -> i.name.equals(name)).findFirst();
    }

}
