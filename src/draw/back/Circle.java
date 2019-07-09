package draw.back;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Map;

import draw.Shape;
import draw.controller.CheckPoint;

public class Circle extends BaseShape{

	@Override
	public void draw(Object canvas) {
		Graphics2D g = (Graphics2D) canvas;
		g.setColor(getColor());
	    int x1 = getPosition().x;
		int y1 = getPosition().y;
		int x2 = (int) Math.round(getProperties().get("point_x"));
		int y2 = (int) Math.round(getProperties().get("point_y"));
		CheckPoint x = new CheckPoint();
		int r = (int) x.dist(new Point(x1, y1), new Point(x2, y2));
		r *= 2;
		Map<String, Double> properties = getProperties(); 
	    properties.put("center_x", (double) x1);
		properties.put("center_y", (double) y1);
		setProperties(properties);
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
		setProperties(properties);
	    
	}
	@Override
	public Object clone(){
		Shape s = new Circle();
		s.setProperties(this.getProperties());
		s.setPosition(this.getPosition());
		s.setColor(this.getColor());
		s.setFillColor(this.getFillColor());
		return s;
	}
}
