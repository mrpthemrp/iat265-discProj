/* CLASS COMMENT: 
 * This file contains a class that extends from the
 * abstract Factory class.
 * 
 * It holds all the information regarding the 
 * VinylBox and contains draw, collision methods.*/
package recordplayer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ddf.minim.Minim;
import main.RoomObject;
import main.RoomPanel;
import util.ImageLoader;

public class VinylBox extends RoomObject {
		private final BufferedImage boxFront;
		private Vinyl selectedVinyl;
		
	public VinylBox(String backImg, String soundName, Minim minim, String frontImg) {
		super(backImg, soundName, minim);
		boxFront = ImageLoader.loadImage(frontImg);
	}

	public void drawFront(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH/2 -15,  RoomPanel.W_HEIGHT/2 - 15);
		g2.scale(0.2, 0.2);
		g2.drawImage(boxFront, 0, 0,RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);
	}
	
	private void drawBack(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH/2 -15,  RoomPanel.W_HEIGHT/2 - 15);
		g2.scale(0.2, 0.2);
		g2.drawImage(img, 0, 0,RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);
	}

	@Override
	public void draw(Graphics2D g2) {
		drawBack(g2);
		drawFront(g2);
	}
	
	public boolean checkIfVinylSelected(ArrayList<Vinyl> vinylList, double x, double y) {
		boolean value = false;
		for(Vinyl v : vinylList) {
			if(v.isSelected(x, y)) {
				value = true;
				selectedVinyl = vinylList.get(v.getIndex());
				RoomPanel.state = RoomPanel.VINYL_SELECTED;
			}
		}
		return value;
	}
	
	public Vinyl getSelectedVinyl () {
		return this.selectedVinyl;
	}

}
