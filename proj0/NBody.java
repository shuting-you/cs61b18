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

}