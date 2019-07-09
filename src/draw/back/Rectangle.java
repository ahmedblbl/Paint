package draw.back;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import draw.Shape;
public class Rectangle extends BaseShape{
	@Override
	public void draw(Object canvas) {
		int x1 = getPosition().x;
		int y1 = getPosition().y;
		int x2 = (int) Math.round(getProperties().get("point_x"));
		int y2 = (int) Math.round(getProperties().get("point_y"));
		Graphics canvass = (Graphics) canvas;
		canvass.setColor(getColor());
		if (this.getFillColor().equals(Color.white)) {
			canvass.drawRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2));
		}else {
			canvass.setColor(this.getFillColor());
			canvass.fillRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2));
		}
		Map<String, Double> properties = getProperties();
		properties.put("start_x", (double) Math.min(x1,x2));
		properties.put("start_y", (double) Math.min(y1,y2));
		properties.put("end_x", (double) Math.max(x1,x2));
		properties.put("end_y", (double) (Math.max(y1,y2)));
		properties.put("shape", (double) 3);
		setProperties(properties);
	}
	
	@Override
	public Object clone(){
		Shape s = new Rectangle();
		s.setProperties(this.getProperties());
		s.setPosition(this.getPosition());
		s.setColor(this.getColor());
		s.setFillColor(this.getFillColor());
		return s;
	}
}
