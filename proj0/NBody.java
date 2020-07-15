public class NBody {
		
		public static double readRadius(String ad) {
			In in = new In(ad);
			int num = in.readInt();
			double radius = in.readDouble();
			in.close();
			return radius;
		}

		public static Planet[] readPlanets(String ad) {
			In in = new In(ad);
			int num = in.readInt();
			Planet[] planets = new Planet[num];
			double radius = in.readDouble();
			double xxPos, yyPos, xxVel, yyVel;
			double mass;
			String img;
			Planet p;
			for(int i = 0; i < num; i++) {
				xxPos = in.readDouble();
				yyPos = in.readDouble();
				xxVel = in.readDouble();
				yyVel = in.readDouble();
				mass = in.readDouble();
				img = in.readString();
				p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
				planets[i] = p;
			}
			in.close();
			return planets;
		}

		public static void main(String[] args) {
			double T = Double.parseDouble(args[0]);
			double dt = Double.parseDouble(args[1]);
			String filename = args[2];

			double radius = NBody.readRadius(filename);
			Planet[] planets = NBody.readPlanets(filename);


			/*Draw the background
			*/
			// the address of the background picture
			String bgp = "images/starfield.jpg";
			//set up the scale of the universe
			StdDraw.setScale(-radius, radius);
			/*clears the drawing window
			*/
			StdDraw.clear();
			StdDraw.picture(0, 0, bgp);
			//draw all of the planets
			for (Planet p : planets) {
				p.draw();
			}

			/*creating an animation
			*/
			//enable double buffering  to prevent flickering in the animation
			StdDraw.enableDoubleBuffering();

			int numOfPlanets = planets.length;
			double t = 0;
			while (t < T) {
				double[] xForces = new double[numOfPlanets];
				double[] yForces = new double[numOfPlanets];
				for(int i = 0; i < numOfPlanets; i++) {
					xForces[i] = planets[i].calcNetForceExertedByX(planets);
					yForces[i] = planets[i].calcNetForceExertedByY(planets);
					t += dt;
				}
				// update each planet's position, velocity, and acceleration
				for (int i = 0; i < numOfPlanets; i++) {
					planets[i].update(dt, xForces[i], yForces[i]);
				}
				// draw background picture and each planet
				StdDraw.picture(0, 0, bgp);
				for (Planet p : planets) {
					p.draw();
				}
				// show the offScreen buffer due to the double buffering
				StdDraw.show();
				// pause the animation for 10 milliseconds
				StdDraw.pause(10);
			}


		}

}