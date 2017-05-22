package nbody.model;


/*

Mass and radius fetched from:
    https://nssdc.gsfc.nasa.gov/planetary/factsheet

Initial positions and speed generated from:
    https://ssd.jpl.nasa.gov/horizons.cgi

    Date: 2457875.500000000 = A.D. 2017-May-02 00:00:00.0000 TDB
    Units: km, km /sek, @0 (sun barycenter)


*/
public enum CelestialBody {

    //Name                  Mass            Radius         X                        Y                        Z                       VX                       VY                      VZ
    SUN("Sun",              1.98855E+30,    695700000.0,   0,                       0,                       0,                      0,                       0,                      0),

    MERCURY("Mercury",      0.33011E+24,    2439.7E+3,    -2.914712614752511E+07,  -6.199930532058012E+07,  -2.431019447296567E+06,  3.424177060854215E+01,  -1.840752506021413E+01, -4.646878989229692E+00),

    VENUS("Venus",          4.8675e24,      6051.8E+3,    -4.540221440982638E+07,  -9.761702225397852E+07,   1.275022972156055E+06,  3.149189089973654E+01,  -1.494411854718451E+01, -2.022651128423027E+00),

    EARTH("Earth",          5.9723E+24,     6371.008E+3,  -1.123742743143437E+08,  -9.929473113856807E+07,  -1.719909168424457E+04,  1.927910017573211E+01,  -2.238794104605604E+01,  7.654215803398756E-05),

    MARS("Mars",            0.64171E+24,    3389.5E+3,     2.769489375730218E+07,   2.314083002586504E+08,   4.143650166228279E+06, -2.315120132716055E+01,   4.913658465424496E+00,  6.708963405868693E-01),

    JUPITER("Jupiter",      1898.19E+24,    69911E+3,     -7.670423564156541E+08,  -2.760915741715643E+08,   1.830090612901382E+07,  4.273937961349422E+00,  -1.167254830604601E+01, -4.700656450718288E-02),

    SATURN("Saturn",        568.34E+24,     58232E+3,     -1.847985294188508E+08,  -1.491810077910302E+09,   3.329327731071341E+07,  9.057064141245075E+00,  -1.217469136865241E+00, -3.397713403584295E-01),

    NEPTUNE("Neptune",      102.413E+24,    24622E+3,      4.257186540489833E+09,  -1.395306114349077E+09,  -6.937752828586924E+07,  1.655967756688154E+00,   5.197288115337841E+00, -1.446099865733843E-01),

    URANUS("Uranus",        86.813e24,      25362E+3,      2.714797267277413E+09,   1.233207333719350E+09,  -3.059052863518852E+07, -2.866519506810219E+00,   5.882836573485944E+00,  5.882566688337931E-02),

    PLUTO("Pluto",          0.01303E+24,     1187E+3,      1.501099735943540E+09,  -4.752004467812544E+09,   7.428723010835433E+07 , 5.302832537658332E+00,   4.978205559367923E-01, -1.608658326954050E+00);


    public final String celestialName;
    public final double mass;   // kg
    public final double radius; // meters
    public Vector3D location; // meters
    public Vector3D velocity; // m/s


    /**
     *
     * @param celestialName
     * @param mass      kg
     * @param radius    m
     * @param x         m
     * @param y         m
     * @param z         m
     * @param x_vel     km/s
     * @param y_vel     km/s
     * @param z_vel     km/s
     */
    CelestialBody(String celestialName, double mass, double radius, double x, double y, double z, double x_vel, double y_vel, double z_vel) {
        this.celestialName = celestialName;
        this.mass = mass;
        this.radius = radius;
        this.location = new Vector3D(x*1000, y*1000, z*1000);
        this.velocity = new Vector3D(x_vel*1000, y_vel*1000, z_vel*1000);
    }

    public Body getAsBody() {
        Body body = new Body();
        body.location = new Vector3D(this.location);
        body.mass = this.mass;
        body.name = this.celestialName;
        body.velocity = new Vector3D(this.velocity);
        body.radius = this.radius;
        return body;
    }
}
