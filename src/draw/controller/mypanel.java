package draw.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import draw.Shape;
import draw.back.Draw;
import draw.back.factory;

public class mypanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private ClassLoader classloader;
	Shape instance = null;
	public Color fill_co = Color.white;
	boolean moveflag = false,resizeflag = false;
	public Color co = Color.black;
	java.awt.Shape resize = new Rectangle(0, 0, 0, 0);
	int xx1 = 0,xx2 = 0,yy1 = 0,yy2 = 0;
	int resize_x,resize_y;
	public  Shape shape,oldshape;
	boolean resizecheck = false;
	boolean move = false;
	int move_x,move_y;
	boolean checkres = false;
	// make them draw
	private boolean paint = false;
	private Draw drawingEngine = new Draw();
	private BufferedImage image,tempimage;
	public mypanel() {
		// TODO Auto-generated constructor stub
		super();
		this.addMouseListener(ms);
		this.addMouseMotionListener(ml);
	}
	public void setshapes(Shape shape) {
		this.shape = shape;
		paint = true;
		resize = new Rectangle(0, 0, 0, 0);
		refresh();
		if(shape == null) {
			this.shape = null;
			paint = false;
		}
	}
	MouseListener ms = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			 checkres = false;
			resizecheck = true;
			if(paint) {
				try {
					drawingEngine.addShape((Shape) shape.clone());
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(shape != null) {
				refresh();
			}
			if(moveflag) {
				
				moveflag = false;
				drawingEngine.updateShape(oldshape, shape);
				shape = null;
				resize = new Rectangle(0, 0, 0, 0);
				refresh();
			}
			if(resizeflag) {
				resizeflag = false;
				drawingEngine.updateShape(oldshape, shape);
				shape = null;
				resize = new Rectangle(0, 0, 0, 0);
				refresh();
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			checkres = false;
			tempimage = ret(image);
			CheckPoint checker = new CheckPoint();
			// rectangle resize
			if(checker.check_square_Rec(new Point(resize_x, resize_y), new Point(resize_x+10, resize_y+10),e.getPoint() )) {
				resizecheck = true;
				move = false;
			}else {
			if(paint && shape != null) { //8yrt hna
				shape.setPosition(new Point(e.getX(), e.getY()));
			}else {
				Shape[] s = drawingEngine.getShapes();
				CheckPoint check = new CheckPoint();
				for(int i = s.length-1; i >= 0; i-- ) {
					if(s[i].getProperties().get("shape") == 1) {                                         
						move_x = e.getX();
						move_y = e.getY();
						Point center = new Point ((int) Math.round(s[i].getProperties().get("center_x")) ,
								(int)Math.round(s[i].getProperties().get("center_y")));
						int rad = (int)Math.round(s[i].getProperties().get("radius"));
						if (check.check_circle(center, rad, e.getPoint())) {
							oldshape = s[i];
							try {
								shape = (Shape) s[i].clone();
							} catch (CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							resizecheck = false;
							resize_x = (int)Math.round(shape.getProperties().get("end_x"));
						    resize_y = (int)Math.round(shape.getProperties().get("end_y"));
							resize = new Rectangle(resize_x, resize_y, 10, 10);
							resizecheck = false;
							break;
						}else {
							shape = null;
							resize = new Rectangle(0, 0, 0, 0);
						}
					}else if (s[i].getProperties().get("shape") == 2 || s[i].getProperties().get("shape") == 3) {
						move_x = e.getX();
						move_y = e.getY();
						Point start = new Point((int) Math.round(s[i].getProperties().get("start_x")),
								(int) Math.round(s[i].getProperties().get("start_y")));
						Point end = new Point((int) Math.round(s[i].getProperties().get("end_x")),
								(int) Math.round(s[i].getProperties().get("end_y")));
						if(check.check_square_Rec(start, end, e.getPoint())){
							oldshape = s[i];
							try {
								shape = (Shape) s[i].clone();
							} catch (CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							  resize_x = end.x;
							  resize_y = end.y;
							resize = new Rectangle(end.x, end.y, 10, 10);
							resizecheck = false;
							break;
						}else {
							shape = null;
							resize = new Rectangle(0, 0, 0, 0);
							refresh();
						}
					}else if (s[i].getProperties().get("shape") == 5) {
						move_x = e.getX();
						move_y = e.getY();
						Point start = new Point((int) Math.round(s[i].getProperties().get("center_x")),
								(int) Math.round(s[i].getProperties().get("center_y")));
						double width = s[i].getProperties().get("width");
						double height = s[i].getProperties().get("height");
						if(check.check_ellipse(start, width/2,height/2 ,e.getPoint())){
							oldshape = s[i];
							try {
								shape = (Shape) s[i].clone();
							} catch (CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							resize_x = (int) Math.round(shape.getProperties().get("end_x"));
							 resize_y =(int) Math.round(shape.getProperties().get("end_y"));
							resize = new Rectangle(resize_x, resize_y, 10, 10);
							resizecheck = false;
							break;
						}else {
							shape = null;
							resize = new Rectangle(0, 0, 0, 0);
							refresh();
						}
					}else if (s[i].getProperties().get("shape") == 4) {
						move_x = e.getX();
						move_y = e.getY();
						Point start = new Point((int) Math.round(s[i].getProperties().get("start_x")),
								(int) Math.round(s[i].getProperties().get("start_y")));
						Point end = new Point((int) Math.round(s[i].getProperties().get("end_x")),
								(int) Math.round(s[i].getProperties().get("end_y")));
						if(check.isOnSegment_line(start, end, e.getPoint())){
							oldshape = s[i];
							try {
								shape = (Shape) s[i].clone();
							} catch (CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							resize_x = end.x;
							  resize_y = end.y;
							resize = new Rectangle(end.x, end.y, 10, 10);
							resizecheck = false;
							break;
						}else {
							shape = null;
							resize = new Rectangle(0, 0, 0, 0);
							refresh();
						}
					}else if (s[i].getProperties().get("shape") == 6) {
						move_x = e.getX();
						move_y = e.getY();
						Point one = new Point((int) Math.round(s[i].getProperties().get("one_x")),
								(int) Math.round(s[i].getProperties().get("one_y")));
						Point two = new Point((int) Math.round(s[i].getProperties().get("two_x")),
								(int) Math.round(s[i].getProperties().get("two_y")));
						Point three = new Point((int) Math.round(s[i].getProperties().get("three_x")),
								(int) Math.round(s[i].getProperties().get("three_y")));
						if(check.isInside(one, two, three, e.getPoint())){
							oldshape = s[i];
							try {
								shape = (Shape) s[i].clone();
							} catch (CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							resize_x = one.x;
							  resize_y = one.y;
							resize = new Rectangle(one.x, one.y, 10, 10);
							
							resizecheck = false;
							break;
						}else {
							shape = null;
							resize = new Rectangle(0, 0, 0, 0);
							refresh();
						}
					}
					
				}
			}
		}
		}
		@Override	
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	MouseMotionListener ml = new MouseMotionListener() {
		
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			image = ret(tempimage);
			if(paint && shape != null) {
				Map<String, Double> properties = new HashMap<String, Double>();
				properties.put("point_x", (double)e.getX());
				properties.put("point_y", (double)e.getY());
				shape.setProperties(properties);
				Graphics2D g = image.createGraphics();
				shape.setColor(co);
				shape.draw(g);
				repaint();
			}else if(shape != null) {
					Point p = (Point) shape.getPosition();
					int x1 = p.x;
					int y1 = p.y;
					int x2 = (int) Math.round(shape.getProperties().get("point_x"));
					int y2 = (int) Math.round(shape.getProperties().get("point_y"));
					if(resizecheck == true) {
						if(shape.getProperties().get("shape") == 1) {
							int shift_x = (e.getX() - resize_x);
							int shift_y = (e.getY() - resize_y);
							Map<String, Double> properties = shape.getProperties();
							properties.put("point_x", (double) e.getX());
							properties.put("point_y", (double) e.getY());
							shape.setProperties(properties);
							Graphics2D g = image.createGraphics();
							shape.setColor(co);
							shape.draw(g);
							resize = new Rectangle((int)Math.round((properties.get("end_x")))
									, (int)Math.round((properties.get("end_y"))), 10, 10);
						}
						else if(shape.getProperties().get("shape") == 4) {
							int shift_x = (e.getX() - resize_x);
							int shift_y = (e.getY() - resize_y);
							Map<String, Double> properties = shape.getProperties();
							x2 += shift_x;
							y2 += shift_y;
							properties.put("point_x", (double) x2);
							properties.put("point_y", (double) y2);
							shape.setPosition(new Point(x1, y1));
							shape.setProperties(properties);
							Graphics2D g = image.createGraphics();
							shape.setColor(co);
							shape.draw(g);
							resize = new Rectangle((int)Math.round((properties.get("point_x")))
									, (int)Math.round((properties.get("point_y"))), 10, 10);
						}
						else if (shape.getProperties().get("shape") == 5) {
							int shift_x = (e.getX() - resize_x);
							int shift_y = (e.getY() - resize_y);
							x2 += shift_x;
							y2 += shift_y;
							Map<String, Double> properties = shape.getProperties();
							properties.put("point_x", (double) x2);
							properties.put("point_y", (double) y2);
							shape.setProperties(properties);
							Graphics2D g = image.createGraphics();
							shape.setColor(co);
							shape.draw(g);
							resize = new Rectangle((int)Math.round((properties.get("end_x")))
									, (int)Math.round((properties.get("end_y"))), 10, 10);
						}
						else {
							int shift_x = (e.getX() - resize_x);
							int shift_y = (e.getY() - resize_y);
							Map<String, Double> properties = shape.getProperties();
							
							if(!checkres) {
								checkres = true;
								if(x1 >= x2) {
									x1 += shift_x;
									xx1 = x1;
									xx2 = x2;
								}else {
									x2 += shift_x;
									xx1 = x2;
									xx2 = x1;
								}
								if(y1 >= y2) {
									y1 += shift_y;
									yy1 = y1;
									yy2 = y2;
								}else {
									y2 += shift_y;
									yy1 = y2;
									yy2 = y1;
								}
								properties.put("point_x", (double) x2);
								properties.put("point_y", (double) y2);
								shape.setPosition(new Point(x1, y1));
								shape.setProperties(properties);
								Graphics2D g = image.createGraphics();
								shape.setColor(co);
								shape.draw(g);
								resize = new Rectangle((int)Math.round((properties.get("point_x")))
										, (int)Math.round((properties.get("point_y"))), 10, 10);
							}else {
								xx1 += shift_x;
								yy1 += shift_y;
								
								properties.put("point_x", (double) xx2);
								properties.put("point_y", (double) yy2);
								shape.setPosition(new Point(xx1, yy1));
								shape.setProperties(properties);
								Graphics2D g = image.createGraphics();
								shape.setColor(co);
								shape.draw(g);
								resize = new Rectangle((int)Math.round((properties.get("end_x")))
										, (int)Math.round((properties.get("end_y"))), 10, 10);
								
							}
							
						}
						resize_x = e.getX();
						resize_y = e.getY();
						resizeflag = true;
						
						repaint();
					}else {
						moveflag = true;
						move = true;
						checkres = false;
						int shift_x = (e.getX() - move_x);
						int shift_y = (e.getY() - move_y);
						Map<String, Double> properties = shape.getProperties();
						properties.put("point_x", properties.get("point_x")+shift_x);
						properties.put("point_y", properties.get("point_y")+shift_y);
						p = (Point) shape.getPosition();
						shape.setPosition(new Point(p.x+shift_x,p.y+shift_y));
						move_x = e.getX();
						move_y = e.getY();
						resize = new Rectangle((int)Math.round((properties.get("end_x")+shift_x))
								, (int)Math.round((properties.get("end_y")+shift_y)), 10, 10);
						shape.setProperties(properties);
						Graphics2D g = image.createGraphics();
						shape.setColor(co);
						shape.draw(g);
						repaint();
					}
					
				}
		}
	};
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawRect(1, 1, this.getWidth()-2, this.getHeight()-2);
		if(image == null)
			image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.BITMASK);
		g.drawImage(image, 0,0,null);
	}
	/*
	 * void funcion make refresh of all shapes
	 */
	public void refresh() {
		image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.BITMASK);
		Graphics2D g = image.createGraphics();
		drawingEngine.refresh(g);
		g.draw(resize);
		repaint();
	}
	// make clone of message
	private BufferedImage ret(BufferedImage tempimagee) {
		// TODO Auto-generated method stub
		if(tempimagee == null) {
			return null;
		}
		ColorModel cm = tempimagee.getColorModel();
		boolean isalpha = cm.isAlphaPremultiplied();
		WritableRaster raster = tempimagee.copyData(null);
		
		return new BufferedImage(cm,raster,isalpha,null);
	}
	public void Undo_panel () {
		drawingEngine.undo();
		shape = null;
		resize = new Rectangle(0, 0, 0, 0);
		refresh();
	}
	public void Redo_panel () {
		drawingEngine.redo();
		shape = null;
		resize = new Rectangle(0, 0, 0, 0);
		refresh();
	}
	public void update() {
		drawingEngine.updateShape(oldshape, shape);
	}
	
	public void Delete() {
		if(shape != null)
			drawingEngine.removeShape(shape);
		shape = null;
		resize = new Rectangle(0, 0, 0, 0);
		refresh();
	}
	
	public void loader(String path) {
		drawingEngine.load(path);
		shape = null;
		resize = new Rectangle(0, 0, 0, 0);
		refresh();
		
	}
	
	public void saver(String path) {
		drawingEngine.save(path);
		shape = null;
		resize = new Rectangle(0, 0, 0, 0);
		refresh();
	}
	public void plugSupport(String path) throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
		File file = new File(path);
		URI uri = file.toURI();
		URL[] urls = new URL[] {uri.toURL()};
		classloader = new URLClassLoader (urls);
		String classname = null;
		List<String> classNames = new ArrayList<String>();
		ZipInputStream zip = new ZipInputStream(new FileInputStream(path));
		for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
		    if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
		        String className = entry.getName().replace('/', '.'); // including ".class"
		        classNames.add(className.substring(0, className.length() - ".class".length()));
		        classname = className.substring(0, className.length() - ".class".length());
		        if (classname.length() > 5) {
		        	factory.name = classname;
		        	break;
		        }
		    }
		    
		}
		Class clazz = classloader.loadClass(classname);
        instance = (Shape) clazz.newInstance();
        factory.cl = instance;
	    instance.setColor(Color.BLACK);
	    instance.setFillColor(Color.WHITE);
	}
	
	public void drawSup() throws CloneNotSupportedException {
		  JTextField xField = new JTextField(8);
	      JTextField yField = new JTextField(8);
	      JPanel myPanels = new JPanel(new GridLayout(0,1));
	      myPanels.add(new JLabel("x:"));
	      myPanels.add(xField);
	      myPanels.add(new JLabel("y:"));
	      myPanels.add(yField);
	      Map <String , Double> tempt = instance.getProperties();
	      ArrayList<JTextField> text = new ArrayList<>();
	      ArrayList<String> keys = new ArrayList<>();
	      for (Map.Entry<String, Double> entrys : tempt.entrySet()) {
	    	  myPanels.add(new JLabel(entrys.getKey()));  
	    	  JTextField take = new JTextField(8);
	    	  myPanels.add(take);
	    	  text.add(take);
	    	  keys.add(entrys.getKey());
	      }
	      int result = JOptionPane.showConfirmDialog(null, myPanels, 
	               "Please Enter The Values", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	  Point p = new Point(Integer.parseInt(xField.getText()),Integer.parseInt(yField.getText()));
	    	  instance.setPosition(p);
	    	  Map <String , Double> temp = new HashMap<>();
	    	  temp.put("shape", (double)7);
	    	  for (int i = 0 ; i < text.size(); i++) {
		    	  temp.put(keys.get(i), Double.parseDouble(text.get(i).getText().toString()));
		      }
	    	  instance.setProperties(temp);
	    	  Graphics2D g = image.createGraphics();
			  instance.setColor(co);
			  instance.draw(g);
			  drawingEngine.addShape((Shape) instance.clone());
			  repaint();
	      }
	      
	}
	
}
