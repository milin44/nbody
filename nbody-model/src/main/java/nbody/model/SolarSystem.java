package nbody.model;

public class SolarSystem extends BodySystem{
    public static double SOLAR_SYSTEM_RADIUS = CelestialBody.EARTH.meanDistanceToGravitationalCenter * 2;
    public static final String EARTH_NAME = "Earth";
    public static final String SUN_NAME = "Sun";


    public SolarSystem() {
        super();
        createSolarSystem();
    }

    private  void createSolarSystem() {
        addBody(createSun());
        addBody(createEarth());
    }

    public Body createEarth() {
        Body earth = new Body();
        Vector2D locationEarth = new Vector2D(CelestialBody.EARTH.meanDistanceToGravitationalCenter, SOLAR_SYSTEM_RADIUS);
        earth.location = locationEarth;
        earth.mass = CelestialBody.EARTH.mass;
        earth.name = EARTH_NAME;

        //  Earth's average orbital speed is about 30 kilometers per second.
        Vector2D vel = new Vector2D(0, 30000);
        earth.velocity = vel;

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
}
