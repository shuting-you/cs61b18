
public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;

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
		this.xxPos = p.xxPos;
		this.xxVel = p.xxVel;
		this.yyPos = p.yyPos;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		return Math.sqrt(dx * dx + dy * dy); 
	}

	public double calcForceExertedBy(Planet p) {
		if (calcDistance(p) == 0) 
			return 0;
		return G * this.mass * p.mass / (calcDistance(p) * calcDistance(p));

	}

	public double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - xxPos;
		return calcForceExertedBy(p) * dx / calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - yyPos;
		return calcForceExertedBy(p) * dy /calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] arr) {
		double netForce = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!this.equals(arr[i]))
			netForce += calcForceExertedByX(arr[i]);
		}
		return netForce;
	}

	public double calcNetForceExertedByY(Planet[] arr) {
		double netForce = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!this.equals(arr[i]))
			netForce += calcForceExertedByY(arr[i]);
		}
		return netForce;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;

		xxVel += aX * dt;
		yyVel += aY * dt;

		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}
	/**
	draw the planet at its appropriate position
	*/
	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}

}