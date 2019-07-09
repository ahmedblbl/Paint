package draw.back;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import draw.Shape;

public abstract class BaseShape implements Shape{
	//center of the shape
	private Point position = new Point(0,0);
	// map contains all properties of the shape
	private Map<String, Double> properties = new HashMap<String, Double>();
	
	private Color color = Color.BLACK,fillcolor = Color.WHITE;
	
	public BaseShape() {
		this.properties.put("key", (double) -1);
		this.setPosition(new Point(-1,-1));
		this.setColor(Color.black);
		this.setFillColor(Color.WHITE);
		this.properties.put("point_x", (double) -1);
		this.properties.put("point_y", (double) -1);
		
	}
	@Override
	public void setPosition(Object position) {
		this.position = (Point) position;
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		this.properties.putAll(properties);
	}

	@Override
	public Map<String, Double> getProperties() {
		return this.properties;
	}

	@Override
	public void setColor(Object color) {
		this.color = (Color) color;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setFillColor(Object color) {
		fillcolor = (Color) color;
	}

	@Override
	public Color getFillColor() {
		return this.fillcolor;
	}

	@Override
	public abstract void draw(Object canvas);
	
	@Override
	public abstract Object clone();

}
