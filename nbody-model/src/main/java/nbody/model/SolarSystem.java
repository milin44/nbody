package nbody.model;

import java.util.Arrays;

import static nbody.model.CelestialBody.*;

public class SolarSystem extends BodySystem {
    public static double SOLAR_SYSTEM_RADIUS = EARTH.location.x * 2;
    
    private static CelestialBody[] CELESTIAL_BODIES_IN_SYSTEM = new CelestialBody[] {SUN, MERCURY, VENUS, EARTH, MARS, JUPITER, SATURN, URANUS, NEPTUNE, PLUTO};

    //private static CelestialBody[] CELESTIAL_BODIES_IN_SYSTEM = new CelestialBody[] {CelestialBody.EARTH, CelestialBody.SUN};

    //private static CelestialBody[] CELESTIAL_BODIES_IN_SYSTEM = new CelestialBody[] {CelestialBody.EARTH, CelestialBody.MOON};

    //private static CelestialBody[] CELESTIAL_BODIES_IN_SYSTEM = new CelestialBody[] {CelestialBody.EARTH};

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
