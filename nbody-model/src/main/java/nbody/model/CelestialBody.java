package nbody.model;

public enum CelestialBody {
    SUN(1.98855e30, 6957000.0, 0),
    EARTH(5.972e24, 63710.0, 149597870700.0);

    public final double mass;   // in kilograms
    public final double radius; // in meters
    public final double meanDistanceToGravitationalCenter; // in meters

    CelestialBody(double mass, double radius, double meanDistanceToGravitationalCenter) {
        this.mass = mass;
        this.radius = radius;
        this.meanDistanceToGravitationalCenter = meanDistanceToGravitationalCenter;
    }
}
