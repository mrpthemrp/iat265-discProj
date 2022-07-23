/* CLASS COMMENT: 
 * This file contains a class that extends from the
 * abstract Factory class.
 * 
 * It holds all the information regarding the 
 * PlayerBit and contains draw, collision methods.*/
package recordplayer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import ddf.minim.Minim;
import main.RoomObject;
import main.RoomPanel;

public class PlayerBit extends RoomObject{

	public PlayerBit(String filename,String soundName, Minim minim) 
	{
		super(filename, soundName, minim);
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH/4 +36, RoomPanel.W_HEIGHT/2 +148);
		g2.scale(0.013,0.013);

		g2.drawImage(img, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);
		
	}

}
