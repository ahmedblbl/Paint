package draw.back;

import java.util.ArrayList;

import draw.Shape;

public class Momento {
	private ArrayList<Shape> state;
	public Momento(ArrayList<Shape> steps) {
		this.state = steps;
	}
	public ArrayList<Shape>getstate(){
		return state;
	}
}
