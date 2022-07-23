/* CLASS COMMENT: 
 * This file contains a class that extends from the
 * abstract Factory class. It is also the base for
 * the decorator Pattern.
 * 
 * It holds all the information regarding the 
 * RecordPlayer and contains draw, collision methods.*/
package recordplayer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import ddf.minim.Minim;
import main.RoomObject;
import main.RoomPanel;
import util.ImageLoader;

public class RecordPlayer extends RoomObject implements DeviceAttachments {
	private BufferedImage cover, coverOpen;

	public RecordPlayer(String filename, String soundName, Minim minim) {
		super(filename, soundName, minim);
		cover = ImageLoader.loadImage("assets/recordPlayer/recordCap.png");
		coverOpen = ImageLoader.loadImage("assets/recordPlayer/recordCapOpen.png");
	}

	public void drawCover(Graphics2D g2) {
		if (RoomPanel.state == RoomPanel.RECORD_CLOSE) {
			AffineTransform at = g2.getTransform();
			g2.translate(RoomPanel.W_WIDTH / 4 - 235, RoomPanel.W_HEIGHT / 2 + 7);
			g2.scale(0.49, 0.72);
			g2.drawImage(cover, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT / 2, null);
			g2.setTransform(at);
		} else {
			AffineTransform at = g2.getTransform();
			g2.translate(RoomPanel.W_WIDTH / 4 - 235, RoomPanel.W_HEIGHT / 2 - 136);
			g2.scale(0.49, 0.72);
			g2.drawImage(coverOpen, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT / 2, null);
			g2.setTransform(at);
		}

	}

	@Override
	public void drawAttachments(Graphics2D g2) {
		draw(g2);
		drawCover(g2);
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH / 4 - 305, RoomPanel.W_HEIGHT / 2 + 100);
		xPos = RoomPanel.W_WIDTH / 4 - 305;
		yPos = RoomPanel.W_HEIGHT / 2 + 100;
		g2.scale(0.6, 0.8);
		g2.drawImage(img, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT / 2, null);
		g2.setTransform(at);
	}

	public boolean hit(RoomObject obj) {
		return ((obj.xPos >= getX() + 200 && obj.xPos <= getX() + 348)
				&& (obj.yPos >= getY() + 18 && obj.yPos <= getY() + 70));
	}

	public boolean capHitClose(double mouseX, double mouseY) {
		boolean returnValue = false;
		if ((mouseX >= 85 && mouseX <= 700) 
				&& (mouseY >= 480 && mouseY <= 580)) {
			returnValue = true;
		}

		return returnValue;
	}
	
	public boolean capHitOpen(double mouseX, double mouseY) {
		boolean returnValue = false;
		if ((mouseX >= 84 && mouseX <= 700) 
				&& (mouseY >= 210 && mouseY <= 305)) {
			returnValue = true;
		}

		return returnValue;
	}

	@Override
	public boolean hit(double mouseX, double mouseY) {
		// TODO Auto-generated method stub
		return false;
	}
}
