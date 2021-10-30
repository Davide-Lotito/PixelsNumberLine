package pixelsLine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * One line of the image is treated. 
 * Using a method of Collections calculates the frequency 
 * with which the pixels appears and the maximum count is taken
 *  
 * @author {Davide Lotito Pio}
 *
 */

public class Line implements Runnable {

	/**
	 * Contains the colors in a line
	 */
	private ArrayList<Color> arrayColor;
	/**
	 * Contains the frequency
	 */
	//private ArrayList<Integer> countArray;
	private HashMap<Color, Integer> countArray;

	public Line(ArrayList<Color> arrayColor) {
		this.arrayColor = arrayColor;
		countArray = new HashMap<Color, Integer>();
	}

	public Entry<Color, Integer> getCount() {
		Set<Entry<Color,Integer>> convertMap = countArray.entrySet(); // to convert a map in a set 
		return Collections.max(convertMap, new ComparatorColor());
	}

	@Override
	public void run() {
		for (Color color : arrayColor) {
			Integer frequency = countArray.get(color); // so i can check with null (object)
			if (frequency == null) {
				countArray.put(color, 1);
			} else {
				countArray.put(color, frequency+1);
			}
		}
	}

}
