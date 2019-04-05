package eg.edu.alexu.csd.oop.draw.cs07_cs59;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.egu.alexu.csd.oop.draw.cs07_cs59.gui.CheckPoint;

public class Circle extends Body{

	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D) canvas;
		//g.drawArc(position.x, position.y, 100, 100, 0, 360);
		g.setColor(getColor());
		/* Enable anti-aliasing and pure stroke */
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

	    /* Construct a shape and draw it */
	    //Ellipse2D.Double shape = new Ellipse2D.Double(position.x, position.y, 100, 100);
	    int x1 = getPosition().x;
		int y1 = getPosition().y;
		int x2 = (int) Math.round(properties.get("point_x"));
		int y2 = (int) Math.round(properties.get("point_y"));
		CheckPoint x = new CheckPoint();
		int r = (int) x.dist(new Point(x1, y1), new Point(x2, y2));
		r *= 2;
		properties.put("center_x", (double) x1);
		properties.put("center_y", (double) y1);
		x1 = (int) (x1 - ( Math.abs(r) / 2));
		y1 = (int) (y1 - ( Math.abs(r) / 2));
		if(this.getFillColor().equals(Color.WHITE)) {
			g.drawOval(x1, y1, (int) Math.round(r), (int) Math.round(r));
		}else {
			g.setColor(this.getFillColor());
			g.fillOval(x1, y1, (int) Math.round(r), (int) Math.round(r));
		}
		properties.put("shape", (double) 1);
		properties.put("radius", (double) r/2);
		properties.put("end_x", (double) (x1 + r));
		properties.put("end_y", (double) (y1 + r/2));
		
	    
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Shape s = new Circle();
		s.setProperties(this.getProperties());
		s.setPosition(this.getPosition());
		s.setColor(this.getColor());
		s.setFillColor(this.getFillColor());
		return s;
	}
}
