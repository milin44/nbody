package nbody.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SolarSystem {
    public static double SOLAR_SYSTEM_RADIUS = CelestialBody.EARTH.meanDistanceToGravitationalCenter * 2;
    public static final String EARTH_NAME = "Earth";
    public static final String SUN_NAME = "Sun";

    private List<Body> bodies;
    private long timeStart;
    public int timeMultiplier = 1;

    public SolarSystem() {
        createSolarSystem();
    }

    private  void createSolarSystem() {
        bodies = new ArrayList<>();
        bodies.add(createSun());
        bodies.add(createEarth());
    }

    public Body createEarth() {
        Body earth = new Body();
        Vector2D locationEarth = new Vector2D(CelestialBody.EARTH.meanDistanceToGravitationalCenter, SOLAR_SYSTEM_RADIUS);
        earth.location = locationEarth;
        earth.mass = CelestialBody.EARTH.mass;
        earth.name = EARTH_NAME;
        return earth;
    }

    public Body createSun() {
        Body sun = new Body();
        Vector2D locationSun = new Vector2D(SOLAR_SYSTEM_RADIUS, SOLAR_SYSTEM_RADIUS);
        sun.location = locationSun;
        sun.mass = CelestialBody.SUN.mass;
        sun.name = SUN_NAME;
        return sun;
    }

    public List<Body> getBodies() {
        return bodies;
    }

    public void update() {
        double timeSlice = calculateTimeSlice();

        for (int i = 0; i < bodies.size(); i++) {
            Body current = bodies.get(i);
            for (int j = 0; j < bodies.size(); j++) {
                Body other = bodies.get(j);
                if (other != current) {
                    current.addGravityForce(other, timeSlice);
                }
            }
            current.update(timeSlice);
        }
    }

    private double calculateTimeSlice() {
        if (timeStart == 0) {
            timeStart = System.currentTimeMillis();
        }
        double timeSlice = (System.currentTimeMillis() - timeStart) / 1000.0;
        if (timeSlice < 1) {
            timeSlice = 1;
        }
        return timeSlice * timeMultiplier;
    }

    public Optional<Body> getBody(String name) {
        return bodies.stream().filter(i -> i.name.equals(name)).findFirst();
    }

}
