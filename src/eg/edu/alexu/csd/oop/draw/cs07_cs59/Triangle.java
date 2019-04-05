package eg.edu.alexu.csd.oop.draw.cs07_cs59;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import eg.edu.alexu.csd.oop.draw.Shape;

public class Triangle extends Body{
	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D) canvas;
		//g.drawArc(position.x, position.y, 100, 100, 0, 360);
		/* Enable anti-aliasing and pure stroke */
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	    g.setColor(getColor());
	    /* Construct a shape and draw it */
	    //Ellipse2D.Double shape = new Ellipse2D.Double(position.x, position.y, 100, 100);
	    int x1 = this.getPosition().x;
	    int y1 = this.getPosition().y;
	    int x2 = (int) Math.round( properties.get("point_x"));
	    int y2 = (int) Math.round( properties.get("point_y"));
	    int [] arr_X = {x1,x2,Math.min(x1, x2)};
	    int x = y1;
		if(x1 < x2) {
			x = y2;
		}
	    int [] arr_Y ={y1,y2,x};
	    if (this.getFillColor().equals(Color.white)) {
	    	g.drawPolygon(arr_X,arr_Y , 3);
	    }else {
	    	g.setColor(this.getFillColor());
	    	g.fillPolygon(arr_X,arr_Y , 3);
	    }
	    properties.put("one_x", (double) x1);
	    properties.put("two_x", (double) x2);
	    properties.put("three_x", (double) Math.min(x1, x2));
	    properties.put("one_y", (double) y1);
	    properties.put("two_y", (double) y2);
	    properties.put("three_y", (double) x);
		properties.put("shape", (double) 6);
		properties.put("end_x", (double) x2);
		properties.put("end_y", (double) y2);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Shape s = new Triangle();
		s.setProperties(this.getProperties());
		s.setPosition(this.getPosition());
		s.setColor(this.getColor());
		s.setFillColor(this.getFillColor());
		return s;
	}
}
