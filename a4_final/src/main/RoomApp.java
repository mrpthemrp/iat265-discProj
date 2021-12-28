/* CLASS COMMENT: 
 * This file contains a class that creates a 
 * RoomApp object.
 * 
 * It is the default class that runs the application.*/
package main;

import javax.swing.JFrame;

public class RoomApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static RoomApp rm;
	
	public RoomApp(String title) {
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RoomPanel bpnl = new RoomPanel(this);
		this.add(bpnl); 
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main (String[] args){
		rm = new RoomApp("Digital Record Player Simulation");
	}
	
}
