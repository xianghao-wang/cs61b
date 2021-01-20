
/* A planet, star or various objects in this universe */
public class Planet {
    /* G */
    final private static double G = 6.67e-11;

    /* Current position (xxPos, yyPos) */
    public double xxPos;
    public double yyPos;

    /* Velocity in x and y directions */
    public double xxVel;
    public double yyVel;

    /* Mass */
    public double mass;

    /* Name of the image depicts the body */
    public String imgFileName;

    /* Contructor */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /* Copy b */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /* Calculate its distance with planet p  */
    public double calcDistance(Planet p) {
        return Math.sqrt(Math.pow((xxPos-p.xxPos), 2) + Math.pow((yyPos-p.yyPos), 2));
    }

    /* Calculate the force exerted by another planet, namely p */
    public double calcForceExertedBy(Planet p) {
        return (G * mass * p.mass) / Math.pow(calcDistance(p), 2);
    }

    /* Calculate the force exerted by p in x-directio*/
    public double calcForceExertedByX(Planet p) {
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dx = p.xxPos - xxPos;

        return (F * dx) / r;
    }

    /* Calculate the force exerted by p in y-directio*/
    public double calcForceExertedByY(Planet p) {
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dy = p.yyPos - yyPos;

        return (F * dy) / r;
    }

    /* Calculate net x-force exerted by p */
    public double calcNetForceExertedByX(Planet[] ps) {
        double netF = 0.0;
        for (Planet p : ps) {
            if (!equals(p)) {
                netF += calcForceExertedByX(p);
            }
        }

        return netF;
    }

    /* Calculate net y-force exerted by p */
    public double calcNetForceExertedByY(Planet[] ps) {
        double netF = 0.0;
        for (Planet p : ps) {
            if (!equals(p)) {
                netF += calcForceExertedByY(p);
            }
        }

        return netF;
    }

    /* Update velocity and postion */
    public void update(double dt, double fX, double fY) {
        double ANetX = fX / mass, ANetY = fY / mass;
        xxVel += dt * ANetX;
        yyVel += dt * ANetY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    /* Draw the planet itself */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
