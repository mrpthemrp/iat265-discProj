/* CLASS COMMENT: 
 * This file contains a class that 
 * is called as a Decorator.
 * 
 * It draws the volume knob on the record player.*/
package recordplayer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import ddf.minim.Minim;
import main.RoomPanel;
import util.ImageLoader;

public class Knob extends Attachments implements DeviceAttachments {
	public static final int VOL_LOW = 0;
	public static final int VOL_MID = 1;
	public static final int VOL_HIGH =2;
	private int knobState;
	
	public Knob(DeviceAttachments dev, String filename, String sound, Minim minim) {
		super(dev, filename, sound, minim);
		knobState =0;
	}

	@Override
	public void drawAttachments(Graphics2D g2) {
		super.drawAttachments(g2);
		drawObject(g2);
	}

	@Override
	public void drawObject (Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH/3 +163, RoomPanel.W_HEIGHT/2 + 198);
		g2.scale(0.035, 0.035);
		g2.drawImage(img,0,0,RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);
		
	}

	@Override
	public boolean hit(double mouseX, double mouseY) {
		boolean hit = false;
		
		if((mouseX >= 585 && mouseX <= 625) 
			&&(mouseY >= 510 && mouseY <= 560)) {
			if(knobState ==0) {
				knobState =1;
				img = ImageLoader.loadImage("assets/recordPlayer/volKnobMid.png");
			}else if(knobState ==1) {
				knobState =2;
				img = ImageLoader.loadImage("assets/recordPlayer/volKnobHigh.png");
			}else if(knobState ==2) {
				knobState =0;
				img = ImageLoader.loadImage("assets/recordPlayer/volKnobLow.png");
			}
			hit = true;
		}
		
		return hit;
	}

	public int getState() {
		return this.knobState;
	}
}
