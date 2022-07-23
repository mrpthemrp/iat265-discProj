/* CLASS COMMENT: 
 * This file contains a class that holds information on 
 * the background of the program. It holds the main background image.*/
package background;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import util.ImageLoader;
import main.RoomPanel;
public class Background {
	private BufferedImage img;
	
	public Background(String filename) {
		img = ImageLoader.loadImage(filename);
	}
	
	public void drawBackground(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.drawImage(img, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		
		g2.setTransform(at);
	}

}
