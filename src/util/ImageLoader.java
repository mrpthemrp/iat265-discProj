/* CLASS COMMENT: 
 * This file contains a helper file for loading
 * BufferedImages that was taken from the week 10 lab.*/

package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

	// method to load images
	public static BufferedImage loadImage(String imgFile) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(imgFile));
		} catch (IOException e) {
			System.out.println("Oops, something wrong with your image file");
		}
		return img;
	}
}

