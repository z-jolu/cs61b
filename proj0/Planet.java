public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, 
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + 
                         (yyPos - p.yyPos) * (yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double g = 6.67E-11;
        double r = calcDistance(p);

        return g * mass * p.mass / r / r;
    }

    public double calcForceExertedByX(Planet p) {
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);

        return f * (p.xxPos - xxPos) / r;
    }

    public double calcForceExertedByY(Planet p) {
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);

        return f * (p.yyPos - yyPos) / r;
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double f = 0;
        for (Planet p : ps) {
            if (!p.equals(this)) {
                f += calcForceExertedByX(p);
            }
        }

        return f;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double f = 0;
        for (Planet p : ps) {
            if (!p.equals(this)) {
                f += calcForceExertedByY(p);
            }
        }

        return f;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass, aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}