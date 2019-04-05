package eg.edu.alexu.csd.oop.draw.cs07_cs59;

import eg.edu.alexu.csd.oop.draw.Shape;

public class factory {
	public static String name = null;
	public static Shape cl = null;
	public Shape check(String shape) {
		if(name != null && shape.contains(name)) {
			System.out.println("hii");
			return cl;
		}
		else if(shape.contains("Circle")) {
			return new Circle();
		}else if(shape.contains("Rectangle")) {
			return new Rectangle();
		}
		else if(shape.contains("Line")) {
			return new Line();
		}else if(shape.contains("Ellipse")) {
			return new Ellipse();
		}else if(shape.contains("Square")) {
			return new Square();
		}else if(shape.contains("Triangle")) {
			return new Triangle();
		} else {
			return new Body();
		}
		
	}
}
