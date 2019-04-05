package eg.egu.alexu.csd.oop.draw.cs07_cs59.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs07_cs59.Circle;
import eg.edu.alexu.csd.oop.draw.cs07_cs59.Ellipse;
import eg.edu.alexu.csd.oop.draw.cs07_cs59.Line;
import eg.edu.alexu.csd.oop.draw.cs07_cs59.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs07_cs59.Square;
import eg.edu.alexu.csd.oop.draw.cs07_cs59.Triangle;
import eg.edu.alexu.csd.oop.draw.cs07_cs59.factory;

public class program {
	mypanel panel = new mypanel();
	static Shape shape;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		factory f = new factory();
		mypanel panel = new mypanel();
		/**
		 * photo and its label.
		 */
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(15, 11, 93, 54);
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(program.class.getResource("/paint.png")).getImage()
				.getScaledInstance(88, 54, Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(imageIcon);
		JLabel lblPaint = new JLabel("    PAINT");
		lblPaint.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPaint.setBounds(15, 67, 93, 35);
		
		/**
		 * Circle button.
		 */
		JButton b1 = new JButton();
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shape = f.check("Circle");
				panel.setshapes(new Circle());
			}
		});
		ImageIcon imageIcon1 = new ImageIcon(new ImageIcon(program.class.getResource("/circle.png")).getImage()
				.getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		b1.setIcon(imageIcon1);
		b1.setBackground(Color.WHITE);
		b1.setBounds(133, 11, 60, 43);
		b1.setToolTipText("Draw Circle");
		
		/**
		 * Rectangle Button.
		 */
		JButton b2 = new JButton();
		ImageIcon imageIcon2 = new ImageIcon(new ImageIcon(program.class.getResource("/rectangle.png"))
				.getImage().getScaledInstance(45, 30, Image.SCALE_DEFAULT));
		b2.setIcon(imageIcon2);
		b2.setBackground(Color.WHITE);
		b2.setBounds(198, 11, 60, 43);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shape = f.check("Rectangle");
				panel.setshapes(shape);
			}
		});
		b2.setToolTipText("Draw Rectangle");

		/**
		 * Square button.
		 */
		JButton b3 = new JButton();
		ImageIcon imageIcon3 = new ImageIcon(new ImageIcon(program.class.getResource("/square.png")).getImage()
				.getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		b3.setIcon(imageIcon3);
		b3.setBackground(Color.WHITE);
		b3.setBounds(263, 11, 60, 43);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shape = f.check("Square");;
				panel.setshapes(shape);
			}
		});
		b3.setToolTipText("Draw square");

		/**
		 * Ellipse button.
		 */
		JButton b4 = new JButton();
		ImageIcon imageIcon5 = new ImageIcon(new ImageIcon(program.class.getResource("/ellipse.png"))
				.getImage().getScaledInstance(45, 30, Image.SCALE_DEFAULT));
		b4.setIcon(imageIcon5);
		b4.setBackground(Color.WHITE);
		b4.setBounds(198, 62, 60, 40);
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shape = f.check("Ellipse");
				panel.setshapes(shape);
			}
		});
		b4.setToolTipText("Draw Ellipse");
		
		/**
		 * line button.
		 */
		JButton b6 = new JButton();
		ImageIcon imageIcon4 = new ImageIcon(new ImageIcon(program.class.getResource("/line.png")).getImage()
				.getScaledInstance(45, 30, Image.SCALE_DEFAULT));
		b6.setIcon(imageIcon4);
		b6.setBackground(Color.WHITE);
		b6.setBounds(263, 62, 60, 40);
		b6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shape = f.check("Line");
				panel.setshapes(shape);
			}
		});
		b6.setToolTipText("Draw Line");

		JButton b9 = new JButton();
		ImageIcon imageIcon6 = new ImageIcon(new ImageIcon(program.class.getResource("/traingle.png"))
				.getImage().getScaledInstance(45, 30, Image.SCALE_DEFAULT));
		b9.setIcon(imageIcon6);
		b9.setBackground(Color.WHITE);
		b9.setBounds(133, 62, 60, 40);
		b9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shape = f.check("Triangle");
				panel.setshapes(shape);
			}
		});
		b9.setToolTipText("Draw Traingle");

		JButton b5 = new JButton();
		ImageIcon imageIcon111 = new ImageIcon(new ImageIcon(program.class.getResource("/ref.png")).getImage()
				.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		b5.setIcon(imageIcon111);
		b5.setBackground(Color.WHITE);
		b5.setBounds(1112, 30, 60, 59);
		b5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.refresh();
			}
		});
		b5.setToolTipText("Refresh");

		JButton b7 = new JButton();
		ImageIcon imageIconp = new ImageIcon(new ImageIcon(program.class.getResource("/pen.png")).getImage()
				.getScaledInstance(50, 35, Image.SCALE_DEFAULT));
		b7.setIcon(imageIconp);
		b7.setBackground(UIManager.getColor("Label.background"));
		b7.setBounds(435, 11, 95, 43);
		b7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JColorChooser chooser = new JColorChooser();
				Color c = chooser.showDialog(null, "select", Color.black);
				panel.co = c;
			}
		});
		b7.setToolTipText("Set Color");

		// mouse to chose shape
		JButton b8 = new JButton();
		ImageIcon imageIconb = new ImageIcon(new ImageIcon(program.class.getResource("/click.png")).getImage()
				.getScaledInstance(50, 54, Image.SCALE_DEFAULT));
		b8.setIcon(imageIconb);
		b8.setBackground(Color.WHITE);
		b8.setBounds(348, 30, 60, 48);
		b8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setshapes(null);
			}
		});
		b8.setToolTipText("Select");
		
		JButton b10 = new JButton();
		ImageIcon imageIcon8 = new ImageIcon(new ImageIcon(program.class.getResource("/undo.png")).getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT));
		b10.setIcon(imageIcon8);
		b10.setBackground(SystemColor.menu);
		b10.setBounds(557, 30, 87, 59);
		b10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.Undo_panel();
			}
		});
		b10.setToolTipText("Undo");
		
		JButton b11 = new JButton();
		ImageIcon imageIcon9 = new ImageIcon(new ImageIcon(program.class.getResource("/redo.png")).getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT));
		b11.setIcon(imageIcon9);
		b11.setBackground(SystemColor.menu);
		b11.setBounds(663, 30, 87, 59);
		b11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.Redo_panel();
			}
		});
		b11.setToolTipText("Redo");
		
		JButton b12 = new JButton();
		ImageIcon imageIcon7 = new ImageIcon(new ImageIcon(program.class.getResource("/fill.png")).getImage().getScaledInstance(45, 30, Image.SCALE_DEFAULT));
		 b12.setIcon(imageIcon7);
		 b12.setBackground(UIManager.getColor("Label.background"));
		 b12.setBounds(435, 62, 95, 43);
		 b12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JColorChooser chooser = new JColorChooser();
				Color c = chooser.showDialog(null, "select", Color.black);
				panel.fill_co = c;
				panel.shape.setFillColor(c);
				panel.update();
				panel.refresh();
			}
		});
		 b12.setToolTipText("Set fill color");
		 
		 JButton b13 = new JButton();
		 ImageIcon imageIcon11 =  new ImageIcon(new ImageIcon(program.class.getResource("/del.png")).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		 b13.setIcon(imageIcon11);
		 b13.setBounds(1000, 30, 83, 59);
		 b13.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					panel.Delete();
				}
			});
		 b13.setToolTipText("Delete Selected");
		 
		 JButton b14 = new JButton();
		 ImageIcon imageIcon10 = new ImageIcon(new ImageIcon(program.class.getResource("/loaf.png")).getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT));
		 b14.setIcon(imageIcon10);
		 b14.setBackground(new Color(255, 255, 255));
		 b14.setBounds(886, 30, 87, 59);
			b14.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(panel);
					File f = chooser.getSelectedFile();
					panel.loader(f.toString());
				}
			});
			b14.setToolTipText("Load");
			
			JButton b15 = new JButton();
			ImageIcon imageIcon19 = new ImageIcon(new ImageIcon(program.class.getResource("/save.png")).getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT));
			b15.setIcon(imageIcon19);
			b15.setBackground(new Color(255, 255, 255));
			b15.setBounds(780, 30, 87, 59);
			b15.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				chooser.showSaveDialog(panel);
				File f = chooser.getSelectedFile();
				/*FileWriter fileWriter = new FileWriter(f);
				System.out.println(f);
				fileWriter.write("yehia");
				fileWriter.close();*/
				panel.saver(f.toString());
					
				}
			});
			b15.setToolTipText("Save");
			
			
			JButton btnDraw = new JButton("draw");
			btnDraw.setBounds(1193, 62, 96, 29);
			btnDraw.setEnabled(false);
			btnDraw.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						panel.drawSup();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			JButton btnNewButton_3 = new JButton("plug in");
			btnNewButton_3.setBounds(1193, 30, 96, 29);
			btnNewButton_3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						JFileChooser chooser = new JFileChooser();
						chooser.showOpenDialog(panel);
						File f = chooser.getSelectedFile();
						//panel.loader(f.toString());
						panel.plugSupport(f.toString());
						btnDraw.setEnabled(true);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			
			//adding the separators between buttons
			JSeparator separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setBounds(335, 11, 13, 91);
			JSeparator separator_1 = new JSeparator();
			separator_1.setOrientation(SwingConstants.VERTICAL);
			separator_1.setBounds(419, 11, 13, 91);
			JSeparator separator_3 = new JSeparator();
			separator_3.setOrientation(SwingConstants.VERTICAL);
			separator_3.setBounds(765, 11, 13, 91);
			JSeparator separator_2 = new JSeparator();
			separator_2.setOrientation(SwingConstants.VERTICAL);
			separator_2.setBounds(546, 11, 13, 91);
			JSeparator separator_4 = new JSeparator();
			separator_4.setOrientation(SwingConstants.VERTICAL);
			separator_4.setBounds(988, 11, 13, 91);
			JSeparator separator_5 = new JSeparator();
			separator_5.setOrientation(SwingConstants.VERTICAL);
			separator_5.setBounds(1187, 11, 13, 91);
			JSeparator separator_6 = new JSeparator();
			separator_6.setOrientation(SwingConstants.VERTICAL);
			separator_6.setBounds(1099, 11, 13, 91);
				
		/**
		 * finished coding the actions of buttons.
		 * add them to the frame.
		 */
		myframe frame = new myframe("Paint");
		// panel to draw in
		panel.setBounds(15, 116, 1274, 699);
		//adding frame components
		panel.setBackground(Color.white);
		frame.addcomponent(panel);
		frame.addcomponent(lblNewLabel);
		frame.addcomponent(lblPaint);
		frame.addcomponent(b6);
		frame.addcomponent(b5);
		frame.addcomponent(b4);
		frame.addcomponent(b2);
		frame.addcomponent(b3);
		frame.addcomponent(b1);
		frame.addcomponent(b10);
		frame.addcomponent(b11);
		frame.addcomponent(b7);
		frame.addcomponent(b8);
		frame.addcomponent(b9);
		frame.addcomponent(b12);
		frame.addcomponent(b13);
		frame.addcomponent(b14);
		frame.addcomponent(b15);
		frame.addcomponent(btnNewButton_3);
		frame.addcomponent(btnDraw);
		frame.addcomponent(separator);
		frame.addcomponent(separator_1);
		frame.addcomponent(separator_2);
		frame.addcomponent(separator_3);
		frame.addcomponent(separator_4);
		frame.addcomponent(separator_5);
		frame.addcomponent(separator_6);
		
		
	}

}
