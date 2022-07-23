/* CLASS COMMENT: 
 * This file contains an Interface
 * that serves as the interface for the decorator pattern.*/
package recordplayer;

import java.awt.Graphics2D;

public interface DeviceAttachments {
	void drawAttachments(Graphics2D g2);
	boolean hit (double mouseX, double mouseY);
}
