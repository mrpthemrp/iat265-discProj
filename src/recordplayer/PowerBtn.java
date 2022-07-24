/* CLASS COMMENT: 
 * This file contains a class that 
 * is called as a Decorator.
 * 
 * It draws the power button on the record player.*/
package recordplayer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import ddf.minim.Minim;
import main.RoomPanel;

public class PowerBtn extends Attachments implements DeviceAttachments {
	public PowerBtn(DeviceAttachments dev, String filename, String sound, Minim minim) {
		super(dev, filename, sound, minim);
	}

	@Override
	public void drawAttachments(Graphics2D g2) {
		super.drawAttachments(g2);
		drawObject(g2);

	}

	@Override
	public boolean hit(double mouseX, double mouseY) {
		boolean returnValue = (mouseX >= 145 && mouseX <= 175)
                && (mouseY >= 595 && mouseY <= 610);
        return returnValue;
	}

	@Override
	public void drawObject(Graphics2D g2) {
		if (!RoomPanel.switchIsOn) {
			// off
			AffineTransform at = g2.getTransform();
			g2.translate(RoomPanel.W_WIDTH / 10 + 51, RoomPanel.W_HEIGHT / 2 + 258);
			g2.scale(0.015, 0.02);
			g2.drawImage(img, 0, 0, -RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
			g2.setTransform(at);
		}
		else {
			//on
			AffineTransform at = g2.getTransform();
			g2.translate(RoomPanel.W_WIDTH/10 +16 , RoomPanel.W_HEIGHT/2 + 258);
			g2.scale(0.015, 0.02);
			g2.drawImage(img,0,0,RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
			g2.setTransform(at);
		}


		

	}

}
