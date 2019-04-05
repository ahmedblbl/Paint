package eg.edu.alexu.csd.oop.draw.cs07_cs59;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Momento {
	private ArrayList<Shape> state;
	public Momento(ArrayList<Shape> steps) {
		this.state = steps;
	}
	public ArrayList<Shape>getstate(){
		return state;
	}
}
