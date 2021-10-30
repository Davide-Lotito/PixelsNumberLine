/*
 * 
 */
package pixelsLine;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Only for the input of the Image
 * @author {Davide Lotito Pio}
 *
 */

public class IO {
	
	/**
	 * get the file
	 * @param fileName
	 * @return
	 */
	public static BufferedImage read(String fileName) {
		try {
			File f = new File(fileName);
			return ImageIO.read(f);
		} catch(IOException e) {
			System.err.println("Error in the file reading!");
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;

	}
}