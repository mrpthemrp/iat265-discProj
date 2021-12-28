/* CLASS COMMENT: 
 * This file contains a class that holds all the
 * information for the time of day in the program.
 * 
 * It also holds information for the filter of the room
 * depending on the time of day.*/
package background;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.RoomPanel;
import util.ImageLoader;

public class TimeOfDay {
	private int timeState;
	private RoomPanel pnl;
	BufferedImage skyStatus, skyFilter;

	public TimeOfDay(RoomPanel rmPnl) {
		pnl = rmPnl;
		updateTimeOfDay();
	}

	public void updateTimeOfDay() {
		timeState = pnl.windowState;
		if (timeState == RoomPanel.SUNRISE) {
			skyStatus = ImageLoader.loadImage("assets/sky/sunrise.png");
			skyFilter = ImageLoader.loadImage("assets/skyFilter/sunriseFilter.png");
		} else if (timeState == RoomPanel.MORNING) {
			skyStatus = ImageLoader.loadImage("assets/sky/morning.png");
			skyFilter = ImageLoader.loadImage("assets/skyFilter/morningFilter.png");
		} else if (timeState == RoomPanel.AFTERNOON) {
			skyStatus = ImageLoader.loadImage("assets/sky/afternoon.png");
			skyFilter = ImageLoader.loadImage("assets/skyFilter/afternoonFilter.png");
		} else if (timeState == RoomPanel.SUNSET) {
			skyStatus = ImageLoader.loadImage("assets/sky/sunset.png");
			skyFilter = ImageLoader.loadImage("assets/skyFilter/sunsetFilter.png");
		} else if (timeState == RoomPanel.EVENING) {
			skyStatus = ImageLoader.loadImage("assets/sky/evening.png");
			skyFilter = ImageLoader.loadImage("assets/skyFilter/eveningFilter.png");
		}
	}

	public void drawSky(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH / 2 + 10, RoomPanel.W_HEIGHT / 2 - 310);
		g2.scale(0.43, 0.34);
		g2.drawImage(skyStatus, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);

	}

	public void drawFilter(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(0, 0);
		g2.drawImage(skyFilter, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);
	}
}
