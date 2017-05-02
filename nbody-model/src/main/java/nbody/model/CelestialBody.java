package nbody.model;

// https://nssdc.gsfc.nasa.gov/planetary/factsheet
public enum CelestialBody {
    // All X:s at Aphelion and min orbital velocity

    //Name                  Mass            Radius                          X               Y       Velocity
    SUN("Sun",              1.98855e30,     695700000.0,                    0,              0,      0),
    MERCURY("Mercury",      0.33011e24,     2439.7e3,                       69.82e9,        0.0,    38.86e3),
    VENUS("Venus",          4.8675e24,      6051.8e3,                       108.94e9,       0.0,    35.26e3),
    EARTH("Earth",          5.9723e24,      (6378.137e3+6356.752e3)/2.0,    152.10e9,       0.0,    29.29e3),
    MARS("Mars",            0.64171e24,     3396.2e3,                       -249.23e9,      0.0,    21.97e3),
    JUPITER("Jupiter",      1898.19e24,     69911e3,                        816.62e9,       0.0,    12.44e3),
    SATURN("Saturn",        568.34e24,      58232e3,                        1514.50e9,      0.0,    9.09e3),
    URANUS("Uranus",        86.813e24,      25362e3,                        3003.62e9,      0.0,    6.493e3),
    NEPTUNE("Neptune",      102.413e24,     24622e3,                        4545.67e9,      0.0,    5.37e3),
    PLUTO("Pluto",          0.01303e24,     1187e3,                         7375.93e9,      0.0,    3.71e3);

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
