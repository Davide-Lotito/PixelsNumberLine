/**
 * 
 */
package pixelsLine;

import java.awt.Color;
import java.util.Comparator;
import java.util.Map.Entry;

/**
 * An easy comparator, to compare two color
 * @author {Davide Lotito Pio}
 *
 */
public class ComparatorColor implements Comparator<Entry<Color, Integer>> {

	@Override
	public int compare(Entry<Color, Integer> o1, Entry<Color, Integer> o2) {
		// TODO Auto-generated method stub
		int s = o1.getValue() - o2.getValue();
		return s;
	}

}
