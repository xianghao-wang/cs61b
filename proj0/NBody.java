/**
 * Simulate of N bodies
*/
public class NBody {
    /* Background image file name */
    final private static String backgroundFilename = "images/starfield.jpg";

    /* Read radius from file with given file name */
    public static double readRadius(String fn) {
        In in = new In(fn);

        /* Skips reading number of planets */
        in.readInt();

        return in.readDouble();
    }

    /* Returns an array of Planets corresponding to the planets in the file */
    public static Planet[] readPlanets(String fn) {
        In in = new In(fn);

        int n = in.readInt();

        /* Skips reading radius */
        in.readDouble();

        Planet[] planets = new Planet[n];
        for (int i = 0; i < n; i ++) {
            /* Each line has x-position, y-position, x-velocity, y-velocity, mass, planets' image file name */
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }

        return planets;
    }

    /* Draw background */
    public static void drawBackground(double radius) {
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        StdDraw.picture(0, 0, backgroundFilename, 2 * radius, 2 * radius);
    }

    /* Draw all planets, be sure to call it after drawBackground(double radius) */
    public static void drawPlanets(Planet[] planets) {
        for (Planet p : planets) {
            p.draw();
        }
    } 

    /* Print results, called after animation was done */
    public static void printUniverse(int n, double radius, Planet[] planets) {
        System.out.println(n);
        System.out.printf("%.2e\n", radius);
        for (Planet p : planets) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %12s\n", p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
        }
    }

    /* Main */
    public static void main(String[] args) {
        /* Meta data */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);

        /* All planets */
        Planet[] planets = readPlanets(filename);

        /* Prevents flickering */
        StdDraw.enableDoubleBuffering();

        /* Animation */
        double currentT = 0.0;
        while (currentT < T) {
            Double[] xForces = new Double[planets.length];
            Double[] yForces = new Double[planets.length];

            /* Calculates and stores x and y net forces*/
            for (int i = 0; i < planets.length; i ++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /* Update position and velocity */
            for (int i = 0; i < planets.length; i ++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            /* Draw */
            drawBackground(radius);
            drawPlanets(planets);
            StdDraw.show();

            StdDraw.pause(10);
            currentT += dt;
        }
    }

}
