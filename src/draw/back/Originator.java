package draw.back;

import java.util.ArrayList;

import draw.Shape;

public class Originator {
	ArrayList<Shape> state;
	public void setstate(ArrayList<Shape> state) {
		this.state = state;
	}
	public ArrayList<Shape> getstate(ArrayList<Shape> state) {
		return state;
	}
	public Momento savestatetomemento() {
		return new Momento(state);
	}
	public void getstatefrommemento(Momento mem) {
		state = mem.getstate();
	}
	
	
}