package eg.egu.alexu.csd.oop.draw.cs07_cs59.gui;

import java.awt.Point;

public class CheckPoint {
	static final double EPS = 1e-9;
	public boolean check_square_Rec(Point starts, Point ends, Point req) {
		Point start = new Point(Math.min(starts.x, ends.x), Math.min(starts.y, ends.y));
		Point end = new Point(Math.max(starts.x, ends.x), Math.max(starts.y, ends.y));
		/*System.out.println(Math.min(starts.x, ends.x) + "  "+ Math.min(starts.y, ends.y));
		System.out.println(Math.max(starts.x, ends.x) + "  "+ Math.max(starts.y, ends.y));
		System.out.println(req.x + " " + req.y );*/
		if (req.x >= start.x && req.x <= end.x &&
			req.y >= start.y && req.y <= end.y) {
			return true;
		}
		return false;
	}
	
	public double dist(Point p1, Point p2)
	{
	    return Math.sqrt(Math.abs( (p1.x - p2.x) * (p1.x - p2.x) )
	                 + Math.abs( (p1.y - p2.y) * (p1.y - p2.y)) );
	}
	
	public boolean isOnSegment_line(Point p1, Point p2, Point p3)
	{
		/*System.out.println(p1.x + " " + p1.y);
		System.out.println(p2.x + " " + p2.y);
		System.out.println(p3.x + " " + p3.y);
		System.out.println((int) (dist(p1, p3)+dist(p3, p2)) + " " + (int)dist(p1 , p2));*/
	    if ( (int) (dist(p1, p3)+dist(p3, p2))
	        == (int)dist(p1 , p2))
	        return true;

	    return false;
	}
	
	public boolean check_circle(Point center, int rad,Point req) {
		int dis = (int)dist(center, req);
		if ((int)Math.pow(dis, 2) <= (int)Math.pow(rad, 2)) {
			return true;
		}
		return false;
	}
	
	public boolean check_ellipse(Point center, double half_w, double half_h, Point req) {
		double t1 = (Math.pow( (req.x - center.x), 2))/ Math.pow(half_w, 2);
		double t2 = (Math.pow( (req.y - center.y), 2) / Math.pow(half_h, 2));
		if (t1+t2 <= 1) {
			return true;
		}
		return false;
		
	}
	
	public boolean isInside(Point p1, Point p2, Point p3, Point req) 
	{    
	   /* Calculate area of triangle ABC */
	   double A = area (p1.x, p1.y, p2.x, p2.y, p3.x, p3.y); 
	  
	   /* Calculate area of triangle PBC */   
	   double A1 = area (req.x, req.y, p2.x, p2.y, p3.x, p3.y); 
	  
	   /* Calculate area of triangle PAC */   
	   double A2 = area (p1.x, p1.y, req.x, req.y, p3.x, p3.y); 
	  
	   /* Calculate area of triangle PAB */    
	   double A3 = area (p1.x, p1.y, p2.x, p2.y, req.x, req.y); 
	    
	   /* Check if sum of A1, A2 and A3 is same as A */ 
	   return (A == A1 + A2 + A3); 
	}
	
	double area(int x1, int y1, int x2, int y2, int x3, int y3) 
	{ 
	   return Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2.0); 
	}
}