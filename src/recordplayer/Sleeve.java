/* CLASS COMMENT: 
 * This file contains a class that extends from the
 * abstract Factory class.
 * 
 * It holds all the information regarding the 
 * Sleeve and contains draw, collision methods.*/
package recordplayer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import ddf.minim.Minim;
import main.RoomObject;
import main.RoomPanel;
import util.ImageLoader;

public class Sleeve extends RoomObject {
	private final int index;
	private final BufferedImage sleeveSlant;
    private final BufferedImage allSleeves;

	public Sleeve(String filename, String soundName, Minim minim, String slantImage, double x, double y, int index) {
		super(filename, soundName, minim);
		this.sleeveSlant = ImageLoader.loadImage(slantImage);
		allSleeves = ImageLoader.loadImage("assets/sleeves/sleevesAll.png");
		xPos = x;
		yPos = y;
		this.index = index;

	}

	// 3 states of sleeves

	public void drawSleeveOnTable(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH / 2 + 280, RoomPanel.W_HEIGHT / 2 + 120);
		g2.rotate(-55);
		g2.scale(0.06, 0.35);
		g2.drawImage(img, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);
	}

	public void drawSleeveOutBox(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH / 6, RoomPanel.W_HEIGHT / 4);
		g2.scale(0.31, 0.58);
		g2.drawImage(img, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);
	}

	public void drawSleevesInBox(Graphics2D g2) {
		BufferedImage temp = allSleeves;
		if (RoomPanel.discPicked) {
			temp = sleeveSlant;
		}
		AffineTransform at = g2.getTransform();
		g2.translate(xPos - 15, yPos - 150);
		g2.scale(0.2, 0.4);
		g2.drawImage(temp, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);
	}

	public int getIndex() {
		return this.index;
	}

	@Override
	public void draw(Graphics2D g2) {
		if(!RoomPanel.discPicked) {
			drawSleevesInBox(g2);
		}
		else {
			drawSleeveOnTable(g2);
			drawSleevesInBox(g2);
		}
	}

}
