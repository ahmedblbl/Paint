package draw.back;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Map;

import draw.Shape;

public class Triangle extends BaseShape{
	
		@Override
		public void draw(Object canvas) {
			
			Graphics2D g = (Graphics2D) canvas;
		    g.setColor(getColor());
		    
		    int x1 = this.getPosition().x;
		    int y1 = this.getPosition().y;
		    int x2 = (int) Math.round(getProperties().get("point_x"));
			int y2 = (int) Math.round(getProperties().get("point_y"));
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
		    Map<String, Double> properties = getProperties();
		    properties.put("one_x", (double) x1);
		    properties.put("two_x", (double) x2);
		    properties.put("three_x", (double) Math.min(x1, x2));
		    properties.put("one_y", (double) y1);
		    properties.put("two_y", (double) y2);
		    properties.put("three_y", (double) x);
			properties.put("shape", (double) 6);
			properties.put("end_x", (double) x2);
			properties.put("end_y", (double) y2);
			setProperties(properties);
		}
	
	@Override
	public Object clone() {
		Shape s = new Triangle();
		s.setProperties(this.getProperties());
		s.setPosition(this.getPosition());
		s.setColor(this.getColor());
		s.setFillColor(this.getFillColor());
		return s;
	}
}
