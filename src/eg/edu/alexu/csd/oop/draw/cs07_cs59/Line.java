package eg.edu.alexu.csd.oop.draw.cs07_cs59;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Line extends Body{
	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D) canvas;
		/* Enable anti-aliasing and pure stroke */
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	    
	    g.setColor(getColor());

	    /* Construct a shape and draw it */
	    //Ellipse2D.Double shape = new Ellipse2D.Double(position.x, position.y, 100, 100);
	    Line2D.Double shape = new Line2D.Double(getPosition().x,getPosition().y,
	    		this.properties.get("point_x"),this.properties.get("point_y"));
	    if (this.getFillColor().equals(Color.white)) {
	    	g.draw(shape);
	    }else {
	    	g.setColor(this.getFillColor());
	    	g.draw(shape);
	    }
	    properties.put("start_x", (double) getPosition().x);
		properties.put("start_y", (double) getPosition().y);
		properties.put("end_x", (double) this.properties.get("point_x"));
		properties.put("end_y", (double) this.properties.get("point_y"));
		properties.put("shape", (double) 4);
	    
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape s = new Line();
		s.setProperties(this.getProperties());
		s.setPosition(this.getPosition());
		s.setColor(this.getColor());
		s.setFillColor(this.getFillColor());
		return s;
		// TODO Auto-generated method stub
		
	}
}