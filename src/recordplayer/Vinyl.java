/* CLASS COMMENT: 
 * This file contains a class that extends from the
 * abstract Factory class.
 * 
 * It holds all the information regarding the 
 * Vinyl and contains draw, collision methods.*/
package recordplayer;

import java.awt.*;
import java.awt.geom.AffineTransform;

import ddf.minim.Minim;
import main.RoomObject;
import main.RoomPanel;

public class Vinyl extends RoomObject{
	private final String songInfo;
	private double spinRate;
	private final int index;
	private boolean inSleeve;
	private double minX, maxX, minY, maxY;

	public Vinyl(String songDetails, String filename, String songName, Minim minim, double x, double y, int index) {
		super(filename, songName, minim);
		xPos = x;
		yPos = y;
		spinRate = 45;
		this.inSleeve = true;
		this.index = index;
		songInfo = songDetails;		
	}

	public void setMinMax(double x, double bigX, double y, double bigY) {
		minX = x;
		maxX = bigX;
		minY = y;
		maxY = bigY;
	}

	public boolean clicked(double x, double y) {
		boolean clicked = x > (xPos - ((double) img.getWidth() / 2 * 0.3)) && x < (xPos + ((double) img.getWidth() / 2 * 0.3))
				&& y > (yPos - ((double) img.getHeight() / 2 * 0.2))
				&& y < (yPos + ((double) img.getHeight()) / 2 * 0.2);

		return clicked;
	}

	public void drawVinyl(Graphics2D g2) {
		AffineTransform at = g2.getTransform();

		if(RoomPanel.state >= RoomPanel.VINYL_SET) {
			if (RoomPanel.switchIsOn) {
				rotateVinyl(g2);
			} else {
				g2.translate(RoomPanel.W_WIDTH / 3 - 253, RoomPanel.W_HEIGHT / 2 + 86);
				g2.scale(0.3, 0.2);
				g2.drawImage(img, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
				g2.setTransform(at);
			}
		}
		else {
			if(this.inSleeve) {
				g2.translate( RoomPanel.W_WIDTH / 2 -450, RoomPanel.W_HEIGHT / 2 +30);
				g2.scale(0.3, 0.56);
				int constantY = -RoomPanel.HEIGHT / 2 - 335;
				g2.drawImage(img,  (int) xPos, constantY, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
				g2.setTransform(at);
				
			}else {
				g2.translate(xPos, yPos);
				g2.scale(0.3, 0.56);
				g2.drawImage(img, -(int) xPos, -(int) yPos, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
				g2.setTransform(at);
			}
			
		}
	}

	// Affine Transform reference: https://www.youtube.com/watch?v=vHfGiTFWoc4
	public void rotateVinyl(Graphics2D g2) {

		double translateX = RoomPanel.W_WIDTH / 3 - 253;
		double translateY = RoomPanel.W_HEIGHT / 2 + 83;
		double angle = (spinRate++) / 5;

		AffineTransform at = g2.getTransform();
		at.rotate(angle, img.getWidth() / 2, img.getHeight() / 2);
		g2.translate(translateX, translateY);
		g2.scale(0.8, 0.3);
		g2.drawImage(img, at, null);
		g2.setTransform(at);

	}

	public void setPos(double x, double y) {
		this.xPos = x;
		this.yPos = y;
	}

	public boolean hit(RecordPlayer rcPly) {
		return ((xPos >= rcPly.getX() + 200 && xPos <= rcPly.getX() + 348)
				&& (yPos >= rcPly.getY() + 18 && yPos <= rcPly.getY() + 70));
	}
	
	//SETTERS
	public void setInSleeve(boolean value) {
		this.inSleeve = value;
	}

	// GETTERS
	public double getX() {
		return this.xPos;
	}

	public double getY() {
		return this.yPos;
	}

	public int getIndex() {
		return this.index;
	}

	public boolean getInSleeve() {
		return this.inSleeve;
	}

	public String getSongInfo() {
		return this.songInfo;
	}

	@Override
	public void draw(Graphics2D g2) {
		drawVinyl(g2);
	}

	public boolean isSelected(double mouseX, double mouseY) {
		boolean value = (mouseX >= minX && mouseX <= maxX) && (mouseY >= minY && mouseY <= maxY);

		return value;

	}
}
