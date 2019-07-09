package draw.back.files;
import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import draw.*;
import draw.back.BaseShape;
import draw.back.Circle;
import draw.back.factory;
public class ReadJson {
	
	public ArrayList<Shape> read (String path) throws CloneNotSupportedException {
		Shape shape = null;
		Shape tempo = null;
		Map <String , Double> prop = new HashMap<String , Double>();
		ArrayList<Shape> res = new ArrayList<>();
		BufferedReader bufferedReader;
		try {
			File fi = new File(path);
			if (!path.contains("\\")) {
				 fi = new File(path);
			} else {
			    fi = new File(path);
				fi.getParentFile().mkdirs(); 
			}
			bufferedReader = new BufferedReader(new FileReader(fi));
			String line = bufferedReader.readLine();
			line = bufferedReader.readLine();
			String kind = bufferedReader.readLine();
			factory f = new factory();
			shape = f.check(kind);
			line = bufferedReader.readLine();
			int counter = 0;
			Point p = null;
			int x = 0 , y=0;
			while (line != null) {
				
				if (line.contains("}")) {
					if (prop == null) {
						line = bufferedReader.readLine();
						line = bufferedReader.readLine();
						continue;
					}
					if(shape != tempo) {
						tempo = shape;
						shape.setProperties(prop);
						res.add((Shape) shape.clone());
					}
					//shape = new Circle();
					prop = new HashMap<String, Double>();
					line = bufferedReader.readLine();
					if(line != null && line.contains("class eg")) {
						shape = f.check(line);
						//System.out.println(shape);
					}
					line = bufferedReader.readLine();
					continue;
				}
				StringBuilder key = new StringBuilder();
				StringBuilder Value = new StringBuilder();
				int nom = 0;
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '\"') {
						nom++;
					}
					else if (nom == 1) {
						key.append(line.charAt(i));
					} else if (nom == 3) {
						Value.append(line.charAt(i));
					}
				}
				
				if (key.toString().equals("position_x")) {
					x = Integer.parseInt(Value.toString());
				} else if (key.toString().equals("position_y")) {
					//System.out.println(Value);
					y = Integer.parseInt(Value.toString());
					p = new Point(x, y);
					//System.out.println(p);
					shape.setPosition(new Point(x, y));
				} else if (key.toString().equals("color")) {
					Color c = new Color(Integer.parseInt(Value.toString()));
					shape.setColor((c));
				} else if (key.toString().equals("fillcolor")) {
					Color c = new Color(Integer.parseInt(Value.toString()));
					shape.setFillColor((c));
				} else {
					//System.out.println(Value.toString());
					prop.put(key.toString(), Double.parseDouble(Value.toString()));
					
					//System.out.println(prop);
				}
			
				line = bufferedReader.readLine();
				counter++;
			}
			bufferedReader.close();
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
		
	}
}
