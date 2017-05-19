package nbody.model;

import java.util.Arrays;
import java.util.Random;

import static nbody.model.CelestialBody.*;

public class SolarSystem extends BodySystem {

    //private static CelestialBody[] CELESTIAL_BODIES_IN_SYSTEM = new CelestialBody[] {SUN, MERCURY, VENUS, EARTH, MARS, JUPITER, SATURN, URANUS, NEPTUNE, PLUTO};

    private static CelestialBody[] CELESTIAL_BODIES_IN_SYSTEM = new CelestialBody[] {SUN, MERCURY, VENUS, EARTH, MARS, JUPITER, SATURN, NEPTUNE, URANUS, PLUTO};

    //private static CelestialBody[] CELESTIAL_BODIES_IN_SYSTEM = new CelestialBody[] {SUN, VENUS};


    public SolarSystem() {
        super();
        createSolarSystem();
    }

    private  void createSolarSystem() {
        Random rand = new Random();

        Arrays.stream(CELESTIAL_BODIES_IN_SYSTEM).forEach((celestialBody) -> {
            final Body body = celestialBody.getAsBody();

            /*


            // position body at random angle from sun
            if (celestialBody != CelestialBody.SUN) {
                int deg = rand.nextInt(360) + 1;

                // update location
                double x = Math.cos(Math.toRadians(deg)) * celestialBody.location.x;
                double y = Math.sin(Math.toRadians(deg)) * celestialBody.location.x;
                body.location.x = x;
                body.location.y = y;

                // velocity is stored for celestial body with only Y-component so convert to angle in tangent with movement
                double x_vel = Math.cos(Math.toRadians(deg + 90)) * celestialBody.avgOrbSpeed;
                double y_vel = Math.sin(Math.toRadians(deg + 90)) * celestialBody.avgOrbSpeed;
                body.velocity.x = x_vel;
                body.velocity.y = y_vel;
            }
            */

            addBody(body);
        });
    }

}
