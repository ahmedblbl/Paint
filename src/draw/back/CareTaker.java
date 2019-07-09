package draw.back;
import java.util.ArrayList;


import draw.Shape;
/*
 * this class used to make undo and redo
 */
public class CareTaker {
	private ArrayList<Momento> momentolist = new ArrayList<>();
	private ArrayList<Momento> redolist = new ArrayList<>();
	private ArrayList<Integer> num = new ArrayList<>();
	boolean check = true;
	
	public void add(Momento state) {
		momentolist.add(state);
		System.out.println(1);
		for(int i = 0; i < num.size(); i++) {
			int t = num.get(i);
			num.set(i, t+1);
		}
		num.add(1);
		System.out.println(num.get(0) + " rkm");
		
	}
	
	public ArrayList<Shape> Undo()
	{
		ArrayList<Shape> temp =  momentolist.get(momentolist.size() - 1).getstate();   
		momentolist.remove(momentolist.size() - 1);
		num.remove(num.size()-1);
		return temp;
	} 
	
	public ArrayList<Shape> redo()
	{
		ArrayList<Shape> temp =  redolist.get(redolist.size() - 1).getstate();   
		redolist.remove(redolist.size() - 1);
		return temp;
		
	}
	
	public void deleteredo() {
		redolist.clear();
	}
	
	public int undosize() {
		return momentolist.size();
	}
	
	public int redosize() {
		return redolist.size();
	}
	
	public void addredo(Momento state) {
		redolist.add(state);
	}
	
	public boolean undocheck() {
		if(num.size() == 0) {
			return true;
			
		}
		if(num.get(num.size()-1) > 20) {
			check = false;
			return true;
		}
		return true;
	}
}
