/* CLASS COMMENT: 
 * This file contains a class that holds all 
 * the information pertaining to the instruction screens.
 * 
 * This class contains methods for drawing the appropriate screens 
 * based on the main panel's state.*/
package background;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.RoomPanel;
import util.ImageLoader;

public class Screens {
	private BufferedImage welcome, instruct, refresh, exit;
	
	private boolean selectedExit;

	public Screens() {
		welcome = ImageLoader.loadImage("assets/screens/welcomeScreen.png");
		instruct = ImageLoader.loadImage("assets/screens/instructionsScreen.png");
		refresh = ImageLoader.loadImage("assets/screens/restartScreen.png");
		exit = ImageLoader.loadImage("assets/screens/exitScreen.png");
		selectedExit = false;
	}

	public void drawScreen(Graphics2D g2) {
		BufferedImage img = welcome;
		if (RoomPanel.state == RoomPanel.START) {
			img = welcome;
		}
		if (RoomPanel.state == RoomPanel.BEGIN) {
			img = instruct;
		}
		if (RoomPanel.state == RoomPanel.REPLAY) {
			img = refresh;
		}
		if(selectedExit) {
			img = exit;
		}

		AffineTransform at = g2.getTransform();
		g2.drawImage(img, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);

	}

	// HIT BUTTON CHECK
	public boolean hitStartBtn(double x, double y) {
		return ((x >= 515 && x <= 750) && (y >= 475 && y <= 520));
	}

	public boolean hitBeginBtn(double x, double y) {
		return ((x >= 515 && x <= 750) && (y >= 475 && y <= 575));
	}

	public boolean hitReplayBtn(double x, double y) {
		return ((x >= 397 && x < 594) && (y >= 418 && y <= 494));
	}

	public boolean hitExitBtn(double x, double y) {
		return ((x >= 670 && x <= 860) && (y >= 419 && y <= 490));
	}
	
	public void setExitStatus(boolean status) {
		this.selectedExit = status;
	}
	
	public boolean getExit() {
		return this.selectedExit;
	}
}
