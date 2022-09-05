/* CLASS COMMENT: 
 * This file contains a class that creates a 
 * Lighter object,.
 * 
 * It contains draw methods and collision methods.
 * This class extends from the Factory abstract class
 * RoomObject*/
package candle;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import ddf.minim.Minim;
import main.RoomObject;
import main.RoomPanel;
import util.ImageLoader;

public class Lighter extends RoomObject {
	BufferedImage top, lightOn;
	private boolean  on;

	public Lighter(String filename, String soundName, Minim minim) {
		super(filename, soundName, minim);
		top = ImageLoader.loadImage("../assets/candle/lightTop.png");
		lightOn = ImageLoader.loadImage("../assets/candle/lightOn.png");
		on = false;
	}

	@Override
	public void draw(Graphics2D g2) {
		drawBody(g2);

	}

	private void drawBody(Graphics2D g2) {

		BufferedImage temp = img;
		if (!on) {
			AffineTransform at = g2.getTransform();
			g2.translate(RoomPanel.W_WIDTH / 2 + 200, RoomPanel.W_HEIGHT / 2 + 100);
			xPos = RoomPanel.W_WIDTH / 2 + 225;
			yPos = RoomPanel.W_HEIGHT / 2 + 130;
			g2.scale(0.03, 0.07);
			g2.drawImage(temp, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
			g2.setTransform(at);
		} else {
			temp = lightOn;
			AffineTransform at = g2.getTransform();
			g2.translate(xPos, yPos);
			g2.scale(0.03, 0.09);
			g2.drawImage(temp, -(int) xPos, -(int) yPos, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
			g2.setTransform(at);
		}
	}

	public void setOn(boolean status) {
		this.on = status;
	}

	public boolean getOn() {
		return this.on;
	}

	public boolean hit(double x, double y) {
        return x >= (835) && x < (870)
                && (y > 420 && y < 483);
    }
	
	public boolean hitCandle() {
        return xPos > 830 && xPos < 880 && yPos > 550 && yPos < 590;
    }

	public boolean startPos() {
        return xPos > 833 && xPos < 867 && yPos > 436 && yPos < 481;
    }
	
	public void setPos(double mouseX, double mouseY) {
		this.xPos = mouseX;
		this.yPos = mouseY;
		
	}
}
