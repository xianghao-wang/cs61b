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
   

    /* Main */
    public static void main(String[] args) {
        /* Meta data */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);

        /* All planets */
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);

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
            StdDraw.clear();
            StdDraw.picture(0, 0, backgroundFilename, 2 * radius, 2 * radius);
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();

            StdDraw.pause(10);
            currentT += dt;
        }

        /* Print results, called after animation was done */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }

}
