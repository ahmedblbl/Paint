package eg.edu.alexu.csd.oop.draw.cs07_cs59;

import java.awt.Color;
import java.awt.Graphics;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Square extends Body{
	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		int x1 = getPosition().x;
		int y1 = getPosition().y;
		int x2 = (int) Math.round(properties.get("point_x"));
		int y2 = (int) Math.round(properties.get("point_y"));
		Graphics canvass = (Graphics)canvas;
		canvass.setColor(this.getColor());
		if (this.getFillColor().equals(Color.white)) {
			canvass.drawRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(x2-x1));
			
		} else {
			canvass.setColor(this.getFillColor());
			canvass.fillRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(x2-x1));
			
		}
		properties.put("start_x", (double) Math.min(x1,x2));
		properties.put("start_y", (double) Math.min(y1,y2));
		properties.put("end_x", (double) Math.max(x1,x2));
		properties.put("end_y", (double) ( Math.min(y1,y2) + Math.abs(x1-x2)));//bdl y1
		properties.put("shape", (double) 2);
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		Shape s = new Square();
		s.setProperties(this.getProperties());
		s.setPosition(this.getPosition());
		s.setColor(this.getColor());
		s.setFillColor(this.getFillColor());
		return s;
	}

}
