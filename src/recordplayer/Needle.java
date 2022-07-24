/* CLASS COMMENT: 
 * This file contains a class that extends from the
 * abstract Factory class.
 * 
 * It holds all the information regarding the 
 * Needle and contains draw, collision methods.*/
package recordplayer;

import java.awt.*;
import java.awt.geom.AffineTransform;

import ddf.minim.Minim;
import main.RoomObject;
import main.RoomPanel;

public class Needle extends RoomObject {

	public Needle(String filename, String soundName, Minim minim) {
		super(filename, soundName, minim);
	}

	public boolean clicked(double x, double y) {
		boolean clicked = (x >= 480 && x <= 545)
                && (y >= 310 && y <= 340);

        return clicked;
	}

	public void drawNeedleOnDisc(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH / 2 - 30, RoomPanel.W_HEIGHT / 2 + 88);
		xPos = RoomPanel.W_WIDTH / 2 - 30;
		yPos = RoomPanel.W_HEIGHT / 2 + 88;
		g2.scale(0.1, 0.15);

		// negative flips horizontally
		g2.drawImage(img, 0, 0, -RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);
	}

	public void drawNeedleOffDisc(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH / 2 - 18, RoomPanel.W_HEIGHT / 2 + 124);
		xPos = RoomPanel.W_WIDTH / 2 - 18;
		yPos = RoomPanel.W_HEIGHT / 2 + 124;
		g2.scale(0.1, 0.15);
		g2.rotate(-5);

		// negative flips horizontally
		g2.drawImage(img, 0, 0, -RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);
	}

	public double getX() {
		return this.xPos;
	}

	public double getY() {
		return this.yPos;
	}

	@Override
	public void draw(Graphics2D g2) {
		if(RoomPanel.state == RoomPanel.RECORD_CLOSE) {
			drawNeedleOnDisc(g2);
		}
		else {
			if(RoomPanel.needleIsSet) {
				drawNeedleOnDisc(g2);
			}
			else {
				drawNeedleOffDisc(g2);
			}
		}
		
		
	}
}
