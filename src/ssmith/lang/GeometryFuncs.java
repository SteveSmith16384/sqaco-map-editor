package ssmith.lang;

import java.awt.Point;

import ssmith.lib2d.MyRectF;

public class GeometryFuncs {

	public static double distance(int x1, int y1, int x2, int y2) {
		//System.out.println(x1+","+y1+"  "+x2+","+y2);
		double side1 = 0;
		if (x1 != x2) {
			side1 = Math.pow( (double) (x2 - x1), 2);
		}
		//System.out.println("Side1: "+side1);
		double side2 = 0;
		if (y1 != y2) {
			side2 = Math.pow( (double) (y2 - y1), 2);
		}
		//System.out.println("Side2: "+side2);
		if (side1 == 0 && side2 == 0) {
			return 0;
		} else {
			double result = Math.sqrt(side1 + side2);
			//System.out.println("Distance: "+result);
			return result;
		}
	}


	public static double distance(float x1, float y1, float x2, float y2) {
		//System.out.println(x1+","+y1+"  "+x2+","+y2);
		double side1 = 0;
		if (x1 != x2) {
			float x3 = (x2 - x1);
			side1 = x3 * x3;
		}
		//System.out.println("Side1: "+side1);
		double side2 = 0;
		if (y1 != y2) {
			float y3 = (y2 - y1);
			side2 = y3 * y3;
		}
		double result = Math.sqrt(side1 + side2);
		return result;
	}


	public static double distance(double x1, double y1, double x2, double y2) {
		//System.out.println(x1+","+y1+"  "+x2+","+y2);
		double side1 = 0;
		if (x1 != x2) {
			side1 = Math.pow( (x2 - x1), 2);
		}
		//System.out.println("Side1: "+side1);
		double side2 = 0;
		if (y1 != y2) {
			side2 = Math.pow( (y2 - y1), 2);
		}
		double result = Math.sqrt(side1 + side2);
		return result;
	}


	public static double distance(float x1, float y1, float z1, float x2, float y2, float z2) {
		double side1 = Math.pow( (double) (x2 - x1), 2);
		double side2 = Math.pow( (double) (y2 - y1), 2);
		double side3 = Math.pow( (double) (z2 - z1), 2);

		double result = Math.sqrt(side1 + side2 + side3);
		return result;
	}


	public static int GetDiffBetweenAngles(int angle1, int angle2) {
		// Rotate angle1 with angle2 so that the sought after
		// angle is between the resulting angle and the x-axis
		angle1 -= angle2;

		// "Normalize" angle1 to range [-180,180)
		while(angle1 < -180) {
			angle1 += 360;
		}
		while(angle1 >= 180) {
			angle1 -= 360;
		}

		// angle1 has the signed answer, just "unsign it"
		return Maths.mod(angle1);
	}


	public static Point GetPointFromAngle(int degrees, float len) {
		float x = (float)(Math.toRadians(Math.cos(degrees)) * len);
		float y = (float)(Math.toRadians(Math.sin(degrees)) * len);
		return new Point((int)x, (int)y);
	}


	public static int NormalizeAngle(int a) {
		while (a >=360) {
			a-=360;
		}
		while (a < 0) {
			a+=360;
		}
		return a;
	}


	/**
	 * Point where the segments intersect, or null if they don't
	 */
	public static Point GetLineIntersection (
			int sx1, int sy1, int ex1, int ey1, 
			int sx2, int sy2, int ex2, int ey2
	) {
		int d = (sx1-ex1)*(sy2-ey2) - (sy1-ey1)*(sx2-ex2);
		if (d == 0) return null;

		int xi = ((sx2-ex2)*(sx1*ey1-sy1*ex1)-(sx1-ex1)*(sx2*ey2-sy2*ex2))/d;
		int yi = ((sy2-ey2)*(sx1*ey1-sy1*ex1)-(sy1-ey1)*(sx2*ey2-sy2*ex2))/d;

		//return new Point(xi,yi);
		Point p = new Point(xi,yi);
		if (xi < Math.min(sx1,ex1) || xi > Math.max(sx1,ex1)) return null;
		if (xi < Math.min(sx2,ex2) || xi > Math.max(sx2,ex2)) return null;
		return p;

	}


	/**
	 * Point where the segments intersect, or null if they don't
	 */
	/*public static Point2D.Float GetLineIntersection (
			float sx1, float sy1, float ex1, float ey1, 
			float sx2, float sy2, float ex2, float ey2
	) {
		float d = (sx1-ex1)*(sy2-ey2) - (sy1-ey1)*(sx2-ex2);
		if (d == 0) return null;

		float xi = ((sx2-ex2)*(sx1*ey1-sy1*ex1)-(sx1-ex1)*(sx2*ey2-sy2*ex2))/d;
		float yi = ((sy2-ey2)*(sx1*ey1-sy1*ex1)-(sy1-ey1)*(sx2*ey2-sy2*ex2))/d;

		//return new Point(xi,yi);
		Point2D.Float  p = new Point2D.Float(xi,yi);
		if (xi < Math.min(sx1,ex1) || xi > Math.max(sx1,ex1)) return null;
		if (xi < Math.min(sx2,ex2) || xi > Math.max(sx2,ex2)) return null;
		return p;

	}*/


	public static boolean DoLineAndRectCross (
			int sx1, int sy1, int ex1, int ey1, 
			int sx2, int sy2, int ex2, int ey2
	) {
		MyRectF rect = new MyRectF(sx1, sy1, ex1, ey1);
		MyRectF rect2 = new MyRectF(sx2, sy2, ex2, ey2);

		return rect.intersects(rect2);
	}


	public static boolean RectangleIntersects(int rect1l, int rect1t, int rect1r, 
			int rect1b, int rect2l, int rect2t, 
			int rect2r, int rect2b)
	{
		return (rect1r >= rect2l &&
				rect1b >= rect2t &&
				rect1l <= rect2r &&
				rect1t <= rect2b);
	}


	/*public static boolean DoLineAndRectCross (
			float sx1, float sy1, float ex1, float ey1, 
			float sx2, float sy2, float ex2, float ey2
	) {
		MyRectF rect = new MyRectF(sx1, sy1, ex1, ey1);
		MyRectF rect2 = new MyRectF(sx2, sy2, ex2, ey2);

		return MyRectF.intersects(rect, rect2); // rect2.contain(rect)
	}*/

}
