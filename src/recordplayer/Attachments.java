/* CLASS COMMENT: 
 * This file contains an abstract class that 
 * serves as the base for the Decorator Pattern.*/
package recordplayer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import util.ImageLoader;

public abstract class Attachments implements DeviceAttachments{
	DeviceAttachments attachments;
	protected BufferedImage img;
	protected double xPos, yPos;
	protected AudioPlayer sound;

	public Attachments(DeviceAttachments devBase, String filename, String soundName, Minim minim) {
		img = ImageLoader.loadImage(filename);
		sound = minim.loadFile(soundName);
		attachments = devBase;
		
	}
	
	public abstract void drawObject (Graphics2D g2);
	
	public void drawAttachments (Graphics2D g2) {
		attachments.drawAttachments(g2);
	}
	
	public AudioPlayer getSound () {
		return this.sound;
	}
}
