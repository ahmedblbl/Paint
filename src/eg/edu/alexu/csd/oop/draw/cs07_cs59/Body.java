package eg.edu.alexu.csd.oop.draw.cs07_cs59;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Body implements Shape{
	private Point position = new Point(0,0);
	Map<String, Double> properties = new HashMap<String, Double>();
	Color color = Color.BLACK,fillcolor = Color.WHITE;
	
	public Body() {
		this.properties.put("key", (double) -1);
		this.setPosition(new Point(-1,-1));
		this.setColor(Color.black);
		this.setFillColor(Color.WHITE);
		this.properties.put("point_x", (double) -1);
		this.properties.put("point_y", (double) -1);
		
	}
	@Override
	public void setPosition(Object position) {
		// TODO Auto-generated method stub
		this.position = (Point) position;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		this.properties.putAll(properties);
	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return properties;
	}

	@Override
	public void setColor(Object color) {
		// TODO Auto-generated method stub
		this.color = (Color) color;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void setFillColor(Object color) {
		// TODO Auto-generated method stub
		fillcolor = (Color) color;
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return fillcolor;
	}

	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		return null;
	}
	public boolean equals(Shape one) {
		
		return false;
		
	}

}
