package nbody.model;

// https://nssdc.gsfc.nasa.gov/planetary/factsheet
public enum CelestialBody {
    //              Mass            Radius          X-Distance          Velocity
    SUN("Sun",      1.98855e30,     695700000.0,    0,          0,      0),
    EARTH("Earth",  5.9723e24,      6378.137e3,     152.10e6,   0.0,    29.29e3),   // Aphelion
    MARS("Mars",    0.64171e24,     3396.2e3,       -249.23e6,  0.0,    21.97e3);   // Aphelion

    //JUPITER("Jupiter", 1.898e274, 69911000.0, 7785e8, 0 , ),

    //MOON("MOON", 0.07346e24, 1738.1e3, EARTH.location.x + 0.4055e6, 0.0, 0.964e3); // location and speed at apogee

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
