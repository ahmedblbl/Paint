package eg.egu.alexu.csd.oop.draw.cs07_cs59.gui;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
public class myframe extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();
	public myframe(String title) {
		// TODO Auto-generated constructor stub
		super(title);
		setBounds(100, 100, 1325, 881);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//panel.setBackground(Color.lightGray);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);
		setVisible(true);
	}
	public void addcomponent(Component c) {
		panel.add(c);
	}
}
