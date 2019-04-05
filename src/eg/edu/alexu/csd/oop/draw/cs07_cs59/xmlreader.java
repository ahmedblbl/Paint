package eg.edu.alexu.csd.oop.draw.cs07_cs59;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class xmlreader {
	ArrayList <Shape> read (String path) throws IOException{
		Shape s = null;
		Map<String, Double> properties = new HashMap<String, Double>();
		ArrayList <Shape> ret = new ArrayList<>();
		File fi = new File(path);
		if (!path.contains("\\")) {
			 fi = new File(path);
		} else {
		    fi = new File(path);
			fi.getParentFile().mkdirs(); 
		} 
		  BufferedReader br = new BufferedReader(new FileReader(fi)); 
		  
		  String st; 
		  factory f = new factory();
		  int x = 0,y = 0;
		  Color c1;
		  Color c2;
			while ((st = br.readLine()) != null) {
				if(st.contains("id=\"class")) {
					if(s != null) {
						try {
							s.setProperties(properties);
							ret.add((Shape) s.clone());
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			    	s = f.check(st);
			    	properties.clear();
			    }
				else if(st.contains("paint") || st.contains("map")|| st.contains("shape"))
			    	continue;
			    else if(st.contains("position_x")) {
			    	x = Integer.valueOf(filter(st));
			    	s.setPosition(new Point(x, y));
			    }else if (st.contains("position_y")) {
			    	y = Integer.valueOf(filter(st));
			    	s.setPosition(new Point(x, y));
			    }
			    else if (st.contains("fillcolor")) {
			    	s.setFillColor(new Color(Integer.parseInt(filter(st))));
			    }else if (st.contains("color")) {
			    	s.setColor(new Color(Integer.parseInt(filter(st))));
			    }else {
			    	String s1 = filterone(st);
			    	String s2 = filtertwo(st);
			    	properties.put(s1, Double.valueOf(s2));
			    }
			  }
			if(s != null) {
				try {
					s.setProperties(properties);
					ret.add((Shape) s.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return ret;
		
	}
	String filter(String s) {
		String temp = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '>') {
				i++;
				while(s.charAt(i) != '<') {
					temp += s.charAt(i);
					i++;
				}
				break;
			}
		}
		return temp;
	}
	private String filterone(String st) {
		// TODO Auto-generated method stub
		String s = "";
		for(int i = 1; i < st.length(); i++) {
			if(st.charAt(i) == '>') {
				break;
			}
			s += st.charAt(i);
		}
		return s;
	}
	private String filtertwo(String st) {
		// TODO Auto-generated method stub
		String s = "";
		boolean f = false;
		for(int i = 1; i < st.length(); i++) {
			if(st.charAt(i) == '>') {
				i++;
				while(st.charAt(i) != '<') {
					s += st.charAt(i);
					i++;
				}
				f = true;
				break;
			}
			if(f) {
				break;
			}
		}
		return s;
	}
}
