package draw.back;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Map;

import draw.Shape;

public class Line extends BaseShape{
	@Override
	public void draw(Object canvas) {
		
		Graphics2D g = (Graphics2D) canvas;
	    g.setColor(getColor());
	    Line2D.Double shape = new Line2D.Double(getPosition().x,getPosition().y,
	    		getProperties().get("point_x"), getProperties().get("point_y"));
	    if (this.getFillColor().equals(Color.white)) {
	    	g.draw(shape);
	    }else {
	    	g.setColor(this.getFillColor());
	    	g.draw(shape);
	    }
	    Map<String, Double> properties = getProperties();
	    properties.put("start_x", (double) getPosition().x);
		properties.put("start_y", (double) getPosition().y);
		properties.put("end_x", (double) this.getProperties().get("point_x"));
		properties.put("end_y", (double) this.getProperties().get("point_y"));
		properties.put("shape", (double) 4);
	    setProperties(properties);
	}
	
	@Override
	public Object clone(){
		Shape s = new Line();
		s.setProperties(this.getProperties());
		s.setPosition(this.getPosition());
		s.setColor(this.getColor());
		s.setFillColor(this.getFillColor());
		return s;
		
	}
}