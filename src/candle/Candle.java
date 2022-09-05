/* CLASS COMMENT: 
 * This file contains a class that creates a 
 * Candle object,.
 * 
 * It contains draw methods and collision methods.
 * This class extends from the Factory abstract class
 * RoomObject*/
package candle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import ddf.minim.Minim;
import main.RoomObject;
import main.RoomPanel;
import processing.core.PApplet;
import util.ImageLoader;

public class Candle extends RoomObject {
	public static final int CANDLE_CLEAN = 0;
	public static final int CANDLE_BURN = 1;
	public static final int CANDLE_BLOWNOUT = 2;

	private final float xStart;
	private float xSeed;
	private float ySeed;
	private final PApplet pa;
	
	public long time =0;

	private final BufferedImage wickClean;
	private final BufferedImage wickBurnt;
	private final BufferedImage flame;
	private final Ellipse2D.Double spark;
	private final int sparkX;
	private final int sparkY;

	public static int state = 0;

	public Candle(String filename, String soundName, Minim minim) {
		super(filename, soundName, minim);
		wickClean = ImageLoader.loadImage("../assets/candle/wickNormal.png");
		wickBurnt = ImageLoader.loadImage("../assets/candle/wickBurnt.png");
		flame = ImageLoader.loadImage("../assets/candle/fireLarge.png");
		spark = new Ellipse2D.Double(0, 0, 2, 2);
		sparkX = RoomPanel.W_WIDTH / 2 + 235;
		sparkY = RoomPanel.W_HEIGHT / 2 + 195;

		// perlin noise
		xStart = random(10);
		xSeed = xStart;
		ySeed = random(10);
		pa = new PApplet();
		
	}

	private float random(int i) {
		return (float) (Math.random() *i);
	}

	private void drawSmoke(Graphics2D g2) {
		float noiseFactor;
		
		for (int y = sparkY-5; y <= RoomPanel.W_HEIGHT / 2 + 195; y += 5) {
			ySeed += 0.1;
			xSeed = xStart;
			for (int x = sparkX; x <= RoomPanel.W_WIDTH / 2 + 240; x += 5) {
				xSeed += 0.1;
				noiseFactor = pa.noise(xSeed, ySeed);
				AffineTransform at = g2.getTransform();
				g2.translate(x, y);
				g2.rotate(noiseFactor * Math.toRadians(-90));
				g2.setColor(new Color(0, 0, 0, 150));
				g2.drawLine(0, 0, 5, 0);
				g2.setTransform(at);
			}
		}

	}

	private void drawSparks(Graphics2D g2, int x, int y) {
		AffineTransform at = g2.getTransform();
		g2.translate(x, y);
		g2.setColor(Color.YELLOW);
		g2.fill(spark);
		g2.setTransform(at);

		x += 2;
		y += 2;
		if (y <= sparkY) {
			drawSparks(g2, x, y);
			drawSparks(g2, -x, y);
		}
//		
	}

	private void drawFlame(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH / 2 + 232, RoomPanel.W_HEIGHT / 2 + 187);
		g2.scale(0.01, 0.03);
		g2.drawImage(flame, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);
	}

	private void drawWick(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH / 2 + 235, RoomPanel.W_HEIGHT / 2 + 200);
		g2.scale(0.007, 0.02);
		if (state == CANDLE_CLEAN) {
			g2.drawImage(wickClean, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		} else {
			g2.drawImage(wickBurnt, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		}
		g2.setTransform(at);
	}

	private void drawBase(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(RoomPanel.W_WIDTH / 2 + 200, RoomPanel.W_HEIGHT / 2 + 200);
		g2.scale(0.06, 0.1);
		g2.drawImage(img, 0, 0, RoomPanel.W_WIDTH, RoomPanel.W_HEIGHT, null);
		g2.setTransform(at);
	}

	@Override
	public void draw(Graphics2D g2) {
		drawBase(g2);
		drawWick(g2);
		if (state == CANDLE_BURN) {
			drawFlame(g2);
			drawSparks(g2, sparkX, sparkY - 15);
		} else if (state == CANDLE_BLOWNOUT) {
			drawSmoke(g2);
		}

	}

}
