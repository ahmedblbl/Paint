package eg.edu.alexu.csd.oop.draw.cs07_cs59;

import java.awt.Color;
import eg.edu.alexu.csd.oop.draw.RoundRectangle;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.egu.alexu.csd.oop.draw.saveJson.ReadJson;
import eg.egu.alexu.csd.oop.draw.saveJson.SaveJson;
import eg.egu.alexu.csd.oop.draw.saveJson.xml;

public class Draw implements DrawingEngine{
	Originator org = new Originator();
	CareTaker taker = new CareTaker();
	int undonum = 0;
	public Shape[] instances = new Shape[1000];
	
	int undo_int = 0, redo_int = 0, load_int = 0;
	int current = 0;
	@Override
	public void refresh(Object canvas) {
		// TODO Auto-generated method stub
		//System.out.println(canvas.toString());
		for(int i = 0; i < current; i++) {
			instances[i].draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		taker.deleteredo();
		//System.out.println(shape +"  shapeadded");
		undo_int++;
		// if not first shape add to undo
		if(current != 0)
			redomethod();
		instances[current] = shape;
		current++;
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
		taker.deleteredo();
		undo_int++;
		redomethod();//add to undo steps
		int f =0 ;
		for (int i = 0; i < current ; i++) {
			if (equality(instances[i], shape)) {
				f = 1;
				for (int j = i ; j < current - 1; j++){
					instances[j] = instances[j+1];
					
				}
			}
		}
		if (f == 1) {
			current--;
		}
	}
	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		taker.deleteredo();
		undo_int++;
		// add to undo
		redomethod();
		for (int i = 0; i < current ; i++) {
			if (equality(oldShape, instances[i])) {
				try {
					instances[i] = (Shape) newShape.clone();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//System.out.println(redo.size());
		
	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		Shape [] temp = new Shape[current];
		int j = 0;
		for (int i = 0; i < current; i++) {
			temp[j] = instances[i];
			j++;
		}
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		ArrayList<Class<? extends Shape>> t = new ArrayList<>();
		try {
			t.add((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs07_cs59.Circle"));
			t.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs07_cs59.Ellipse")));
			t.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs07_cs59.Rectangle")));
			t.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs07_cs59.Square")));
			t.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs07_cs59.Triangle")));
			t.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs07_cs59.Line")));
			t.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.RoundRectangle")));
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("no shape added");
		}
		return t;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		if(undo_int>0 && (taker.undocheck()) && taker.check && undonum < 20) {
			undonum++;
			ArrayList<Shape> temp = new ArrayList<>();
			undo_int--;
			try {
			for(int i = 0; i < current; i++) {
					temp.add((Shape) instances[i].clone());		
			}
			org.setstate(temp);
			taker.addredo(org.savestatetomemento());
			
				int sizee = taker.undosize();
				if(sizee < 1 && taker.undosize() < 20) {
					for(int i = 0; i < current;i++) {
						instances[i] = null;
					}
					current = 0;
				}else {
					ArrayList<Shape> temp2 = taker.Undo();
					current = temp2.size();
					for(int i = 0; i < temp2.size();i++) {
						instances[i] = (Shape) temp2.get(i).clone();
					}
				}
				}catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}else {
			if(undonum>=20) {
				undonum--;
			}
		}
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		if(taker.redosize() >= 1) {
			redomethod();
			undo_int++;
			ArrayList<Shape> temp = taker.redo();
			for(int i = 0; i < temp.size(); i++) {
				try {
					instances[i] = (Shape) temp.get(i).clone();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			current = temp.size();
			undonum--;
		}
		
	}

	@Override
	public void save(String path) {
		path = path.toLowerCase();
		if (path.contains(".json")) {
			savejson(path);
		}else if (path.contains(".xml")){
			savexml(path);
		}
	}

	@Override
	public void load(String path) {
		path = path.toLowerCase();
		if (path.contains(".json")) {
			try {
				loadjson(path);
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (path.contains(".xml")) {
			loadxml(path);
		}
		current = load_int;
		
	}
	
	public boolean equality(Shape one, Shape two) {
		if(one.getProperties() == null && two.getProperties() == null) {
			return true;
		}
		if(one.getProperties().equals(two.getProperties()) ) {
			return true;
		}
		return false;
		
	}
	//using in redo
	public void redomethod () {
		ArrayList<Shape>temp = new ArrayList<>();
		
		for(int i = 0; i < current; i++) {
			try {
				temp.add((Shape) instances[i].clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(temp.size()>= 1 ) {
			org.setstate(temp);
			taker.add((org).savestatetomemento());
		}
	}

	public void savexml(String path) {
		xml x = new xml();
		x.opentag("paint");
		x.newline();
		x.setFile(path);
		for (int i = 0; i < current; i++) {
			x.puttattr(instances[i].getClass().toString());
			x.opentag("shape");x.newline();
			x.opentag("position_x");
			if (instances[i].getPosition() == null) {
				instances[i].setPosition(new Point(0,0));
			}
			Point p = (Point) instances[i].getPosition();
			x.putval(String.valueOf(p.x));
			x.closetag("position_x");
			x.opentag("position_y");
			x.putval(String.valueOf(p.y));
			x.closetag("position_y");
			x.opentag("color");
			if (instances[i].getColor() == null) {
				instances[i].setColor(Color.black);
			}
			Color c1 = (Color) instances[i].getColor();
			x.putval(String.valueOf(c1.getRGB()));
			x.closetag("color");
			x.opentag("fillcolor");
			if (instances[i].getFillColor() == null) {
				instances[i].setFillColor(Color.white);
			}
			c1 = (Color) instances[i].getFillColor();
			x.putval(String.valueOf(c1.getRGB()));
			x.closetag("fillcolor");
			if (instances[i].getProperties() != null) {
			Map <String , Double> temp = instances[i].getProperties();
			x.opentag("map");x.newline();
			for (Map.Entry<String, Double> entry : temp.entrySet()) {
				x.opentag(entry.getKey());
				x.putval(String.valueOf(entry.getValue()));
				x.closetag(entry.getKey());
			}
			x.closetag("map");
			}
			x.closetag("shape");
		}
		x.closetag("paint");
		x.saveFile();
	}
	public void savejson(String path) {
		SaveJson json = new SaveJson();
		json.setFile(path);
		json.startArr();
		json.getArrName("instances");
		for (int i = 0; i < current; i++) {
			json.startArr();
			json.putJson("id", instances[i].getClass().toString());
			if (instances[i].getPosition() == null) {
				instances[i].setPosition(new Point(0,0));
			}
			Point p = (Point) instances[i].getPosition();
			json.putJson("position_x", String.valueOf(p.x));
			json.putJson("position_y", String.valueOf(p.y));
			if (instances[i].getColor() == null) {
				instances[i].setColor(Color.black);
			}
			Color c1 = (Color) instances[i].getColor();
			json.putJson("color", String.valueOf(c1.getRGB()));
			if (instances[i].getFillColor() == null) {
				instances[i].setFillColor(Color.white);
			}
			c1 = (Color) instances[i].getFillColor();
			json.putJson("fillcolor", String.valueOf(c1.getRGB()));
			if (instances[i].getProperties() != null) {
			Map <String , Double> temp = instances[i].getProperties();
			for (Map.Entry<String, Double> entry : temp.entrySet()) {
				json.putJson(entry.getKey(), String.valueOf(entry.getValue()));
			}
			}
			json.endArr();
		}
		json.getEndArr();
		json.saveFile();
	}
	public void loadjson(String path) throws CloneNotSupportedException {
		ReadJson r = new ReadJson();
		current = 0;
		ArrayList<Shape> res = r.read(path);
		for (int i = 0; i < res.size(); i++) {
			addShape(res.get(i));
		}
		undo_int = 0;
		load_int = res.size();
		//System.out.println(load_int + "load int");
	}
	public void loadxml(String path) {
		try {
			xmlreader x = new xmlreader();
			current = 0;
			ArrayList<Shape> res;
			res = x.read(path);
			
			for (int i = 0; i < res.size(); i++) {
				addShape(res.get(i));
			}
			undo_int = 0;
			load_int = res.size();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
}