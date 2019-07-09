package draw.back;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Map;

import draw.Shape;

public class Ellipse extends BaseShape{
	
	@Override
	public void draw(Object canvas) {
		int x1 = getPosition().x;
		int y1 = getPosition().y;
		int x2 = (int) Math.round(getProperties().get("point_x"));
		int y2 = (int) Math.round(getProperties().get("point_y"));
		Graphics2D g = (Graphics2D) canvas;
		g.setColor(getColor());
		Map<String, Double> properties = getProperties();
	    properties.put("center_x", (double) x1);
		properties.put("center_y", (double) y1);
		setProperties(properties);
		int x3 = (int) (x1 - (Math.abs(x1-x2) ));
		int y3 = (int) (y1 - ( Math.abs(y1-y2) ));
		Ellipse2D.Double shape = new Ellipse2D.Double(x3,
				y3,2*Math.abs(x1-x2),2*Math.abs(y1-y2));
		if (this.getFillColor().equals(Color.white)) {
			g.draw(shape);
		} else {
			g.setColor(this.getFillColor());
			g.fill(shape);
		}
		properties.put("shape", (double) 5);
		properties.put("width", (double) 2*Math.abs(x1-x2));
		properties.put("height", (double) 2*Math.abs(y1-y2));
		properties.put("end_x", (double) x3 + Math.abs(x1-x2)*2);
		properties.put("end_y", (double) y3 + Math.abs(y1-y2));
		setProperties(properties);
		
	}
	
	@Override
	public Object clone(){
		Shape s = new Ellipse();
		s.setProperties(this.getProperties());
		s.setPosition(this.getPosition());
		s.setColor(this.getColor());
		s.setFillColor(this.getFillColor());
		return s;
	}
}
