package nbody.model;

import java.util.Arrays;
import java.util.List;

public class SolarSystem extends BodySystem {
    public static double SOLAR_SYSTEM_RADIUS = CelestialBody.EARTH.meanDistanceToGravitationalCenter * 2;
    private static CelestialBody[] CELESTIAL_BODIES_IN_SYSTEM = new CelestialBody[] {CelestialBody.EARTH, CelestialBody.SUN};

    public SolarSystem() {
        super();
        createSolarSystem();
    }

    private  void createSolarSystem() {
        Arrays.stream(CELESTIAL_BODIES_IN_SYSTEM).forEach((celestialBody) -> {
            addBody(celestialBody.getAsBody(SOLAR_SYSTEM_RADIUS));
        });
    }
}
