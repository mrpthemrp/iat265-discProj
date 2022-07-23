/* CLASS COMMENT: 
 * This file contains an abstract class
 * that is the main Factory object.
 * 
 * It also contains an abstract drawing method.*/
package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import util.ImageLoader;

public abstract class RoomObject {
	protected BufferedImage img;
	public double xPos, yPos;
	protected AudioPlayer sound;

	public RoomObject(String filename, String soundName, Minim minim) {
		img = ImageLoader.loadImage(filename);
		sound = minim.loadFile(soundName);
	}
	
	public double getX(){
		return this.xPos;
	}
	
	public double getY(){
		return this.yPos;
	}
	
	public AudioPlayer getSound() {
		return this.sound;
	}
	
	public abstract void draw (Graphics2D g2);
}
