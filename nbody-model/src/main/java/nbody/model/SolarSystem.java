package nbody.model;


import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
    public static double SOLAR_SYSTEM_RADIUS = CelestialBody.EARTH.meanDistanceToGravitationalCenter * 2;

    public List<Body> createSolarSystem() {
        List<Body> list = new ArrayList<>();

        Body sun = new Body();
        Vector2D locationSun = new Vector2D(0, 0);
        sun.location = locationSun;
        sun.mass = CelestialBody.SUN.mass;
        list.add(sun);

        Body earth = new Body();
        Vector2D locationEarth = new Vector2D(CelestialBody.EARTH.meanDistanceToGravitationalCenter, 0);
        earth.location = locationEarth;
        earth.mass = CelestialBody.EARTH.mass;
        list.add(earth);

        return list;
    }
}
