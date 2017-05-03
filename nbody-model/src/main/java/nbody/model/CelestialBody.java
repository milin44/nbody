package nbody.model;

// https://nssdc.gsfc.nasa.gov/planetary/factsheet

/*
// https://www.physicsforums.com/threads/parameters-for-solar-system-simulation.723132/
// https://redirect.viglink.com/?format=go&jsonp=vglnk_149373073806713&key=6afc78eea2339e9c047ab6748b0d37e7&libId=j27k7v5o010009we000DAkjrkfcy5&loc=https%3A%2F%2Fwww.physicsforums.com%2Fthreads%2Fparameters-for-solar-system-simulation.723132%2F&v=1&out=http%3A%2F%2Fssd.jpl.nasa.gov%2Fhorizons.cgi&ref=https%3A%2F%2Fwww.google.se%2F&title=Parameters%20for%20Solar%20system%20simulation%20%7C%20Physics%20Forums%20-%20The%20Fusion%20of%20Science%20and%20Community&txt=JPL%20Horizons%20Web%20Interface
// https://en.wikipedia.org/wiki/Numerical_model_of_the_Solar_System

km, km /sek, @sun


Earth
 X =-1.128220705591621E+08 Y =-1.000033721571109E+08 VX= 1.928586288314945E+01 VY=-2.239901732736472E+01

Mars
2457875.500000000 = A.D. 2017-May-02 00:00:00.0000 TDB
 X = 2.724709753214656E+07 Y = 2.306996591659316E+08 VX=-2.314443861909190E+01 VY= 4.902582183346213E+00

Mercury
 X =-2.959492239234352E+07 Y =-6.270794633912294E+07 VX= 3.424853331595948E+01 VY=-1.841860134152282E+01

*/
public enum CelestialBody {
    /*
    //Name                  Mass            Radius                          X                       Y                       VX                     VY
    SUN("Sun",              1.98855e30,     695700000.0,                    0,                      0,                      0,                     0),

    MERCURY("Mercury",      0.33011e24,     2439.7e3,                      -2.959492239234352E10,  -6.270794633912294E10,   3.424853331595948E04, -1.841860134152282E04),




    EARTH("Earth",          5.9723e24,      6371.008e3,                     1.128220705591621e11,  -1.000033721571109e11,   1.928586288314945e04, -2.239901732736472e04),

    MARS("Mars",            0.64171e24,     3389.5e3,                       2.724709753214656e10,   2.306996591659316e11,  -2.314443861909190e04,  4.902582183346213e03);

*/

    // All X:s at Aphelion and min orbital velocity

    //Name                  Mass            Radius                          X               Y       Velocity
    SUN("Sun",              1.98855e30,     695700000.0,                    0,              0,      0),
    MERCURY("Mercury",      0.33011e24,     2439.7e3,                       69.82e9,        0.0,    38.86e3),
    VENUS("Venus",          4.8675e24,      6051.8e3,                       108.94e9,       0.0,    35.26e3),
    EARTH("Earth",          5.9723e24,      6371.008e3,                     152.10e9,       0.0,    29.29e3),
    MARS("Mars",            0.64171e24,     3389.5e3,                       249.23e9,       0.0,    21.97e3),
    JUPITER("Jupiter",      1898.19e24,     69911e3,                        816.62e9,       0.0,    12.44e3),
    SATURN("Saturn",        568.34e24,      58232e3,                        1514.50e9,      0.0,    9.09e3),
    URANUS("Uranus",        86.813e24,      25362e3,                        3003.62e9,      0.0,    6.493e3),
    NEPTUNE("Neptune",      102.413e24,     24622e3,                        4545.67e9,      0.0,    5.37e3),
    PLUTO("Pluto",          0.01303e24,     1187e3,                         7375.93e9,      0.0,    3.71e3);



    public final String celestialName;
    public final double mass;   // in kilograms
    public final double radius; // in meters
    public Vector2D location;
    public Vector2D velocity;

    CelestialBody(String celestialName, double mass, double radius, double x, double y, double avgOrbSpeed) {
        this.celestialName = celestialName;
        this.mass = mass;
        this.radius = radius;
        this.location = new Vector2D(x, y);
        this.velocity = new Vector2D(0, avgOrbSpeed);
    }

    CelestialBody(String celestialName, double mass, double radius, double x, double y, double x_vel, double y_vel) {
        this.celestialName = celestialName;
        this.mass = mass;
        this.radius = radius;
        this.location = new Vector2D(x, y);
        this.velocity = new Vector2D(x_vel, x_vel);
    }

    public Body getAsBody() {
        Body body = new Body();
        body.location = new Vector2D(this.location);
        body.mass = this.mass;
        body.name = this.celestialName;
        body.velocity = new Vector2D(this.velocity);
        body.radius = this.radius;
        return body;
    }
}
