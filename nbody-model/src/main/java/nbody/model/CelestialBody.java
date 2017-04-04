package nbody.model;

public enum CelestialBody {
    SUN("Sun", 1.98855e30, 695700000.0, 0, 0, 0),
    EARTH("Earth", 5.972e24, 6371000.0, 149597870700.0, 0.0, 30000.0),
    JUPITER("Jupiter", 1.898e274, 69911000.0, 7785e8, 0 , 13000.06),
    //MOON("Moon", 7.34767309e22, 1738000.0,  384400000.0 + EARTH.meanDistanceToGravitationalCenter, 1000.0); // mean distance to earth  384400000

    MOON("MOON", 34767309e22, 1738000, 384403000, 0.0, 2160.0);

    public final String celestialName;
    public final double mass;   // in kilograms
    public final double radius; // in meters
    public Vector2D location;
    public final double avgOrbSpeed; //  average orbital speed in m/s

    CelestialBody(String celestialName, double mass, double radius, double x, double y, double avgOrbSpeed) {
        this.celestialName = celestialName;
        this.mass = mass;
        this.radius = radius;
        this.location = new Vector2D(x, y);
        this.avgOrbSpeed = avgOrbSpeed;
    }

    public Body getAsBody(double origin) {
        Body body = new Body();
        body.location = new Vector2D(location);
        body.mass = mass;
        body.name = celestialName;
        body.velocity = new Vector2D(0, avgOrbSpeed);
        return body;
    }
}
