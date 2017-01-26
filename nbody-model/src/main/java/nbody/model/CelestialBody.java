package nbody.model;

public enum CelestialBody {
    SUN("Sun", 1.98855e30, 695700000.0, 0, 0),
    EARTH("Earth", 5.972e24, 6371000.0, 149597870700.0, 30000.0),
    JUPITER("Jupiter", 1.898e274, 69911000.0, 7785e8 , 13000.06),
    MOON("Moon", 7.34767309e22, 1738000.0,  384400000.0, 1000.0); // mean distance to earth  384400000

    public final String celestialName;
    public final double mass;   // in kilograms
    public final double radius; // in meters
    public final double meanDistanceToGravitationalCenter; // in meters
    public final double avgOrbSpeed; //  average orbital speed in m/s

    CelestialBody(String celestialName, double mass, double radius, double meanDistanceToGravitationalCenter, double avgOrbSpeed) {
        this.celestialName = celestialName;
        this.mass = mass;
        this.radius = radius;
        this.meanDistanceToGravitationalCenter = meanDistanceToGravitationalCenter;
        this.avgOrbSpeed = avgOrbSpeed;
    }

    public Body getAsBody(double origin) {
        Body body = new Body();
        body.location = new Vector2D(meanDistanceToGravitationalCenter, origin);
        body.mass = mass;
        body.name = celestialName;
        body.velocity = new Vector2D(0, avgOrbSpeed);
        return body;
    }
}
