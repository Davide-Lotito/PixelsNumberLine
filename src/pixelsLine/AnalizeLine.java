/**
 * 
 */
package pixelsLine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Map.Entry;
import java.awt.image.*;

/**
 * The main class. 
 * @author {Davide Lotito Pio}
 *
 */
public class AnalizeLine {

	/*
	 * contains the number of equal pixels, in each row
	 */
	static ArrayList<Entry<Color, Integer>> results = new ArrayList<Entry<Color, Integer>>();
	/**
	 * The image dimensions
	 */
	static int w,h;
	/**
	 * The reference at the image, to analize
	 */
	static BufferedImage image;

	static Line[] righe;
	static Thread[] threads;
	
	
	public static void main(String[] args) throws InterruptedException {

		checkMain(args);

		/**
		 * scans each line, based on height, of the image
		 * and create an arrayList of color for each line
		 */
		for(int i=0; i<h; i++) {
			ArrayList<Color> arrayColor = new ArrayList<Color>();
			for (int x = 0; x < w; x++) {
				int pixel = image.getRGB(x,i);
				Color mycolor = new Color(pixel);
				arrayColor.add(mycolor);
			}	
			//as many threads as there are lines
			righe[i] = new Line(arrayColor);
			threads[i] = new Thread(righe[i]);
		}

		/*start the threads*/
		for(int i=0; i<h; i++) {
			threads[i].start(); //Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
		}

		for(int i=0; i<h; i++) {
			threads[i].join(); // Waits for this thread to die.
			results.add(i, righe[i].getCount());
		}

		finalStamp();

	}

	/**
	 * The finalStamp of the program
	 */
	private static void finalStamp() {
		System.out.println("\nThe image is:"+h+"x"+w+"\n");
		for(int i=0; i<h; i++) {
			System.out.println("Number of equal pixels in the row :[" + i +"]\t");
			System.out.println(stampEntry(results.get(i))+"\n");
		}
		System.out.println("In the image, the row that contains more equal pixels "
				+ "has " + stampEntry(Collections.max(results, new ComparatorColor())) + " equal pixels.");
	}

	/**
	 * The initial operations of the program
	 * @param args
	 */
	private static void checkMain(String[] args){
		System.out.println("Overview:\nThis simple program uses threads to calculate the "
				+ "number of equal pixels in a row. It shows the value for each "
				+ "row and the maximum value of the whole image");
		System.out.println("@author: Davide Pio Lotito");
		if(args.length==0) {
			System.err.println("be used: java pixelsNumberLine file-name-image");
			System.exit(1);
		}if(args[0].equals("--help")) {
			System.out.println("--help:\nTo calculate the number of equal pixels in a row."
					+ " It shows the value for each "
					+ "row and the maximum value of the whole image");

			Scanner scanner = new Scanner(System.in);
			boolean condition = true;
			while(condition) {
				System.out.println("Now you can enter the path/name of the image\n");
				//get the next line of user input.
				String command = scanner.nextLine();
				if(command.equals("")) {
					System.err.println("be used: java pixelsNumberLine file-name-image");
					System.exit(1);
				}
				getImage(command);
				scanner.close();
				condition = false;
			}
		} else {
			getImage(args[0]);
		}
	}
	
	/**
	 * To get the image.
	 * @param args
	 */
	private static void getImage(String args) {
		image = IO.read(args);
		w = image.getWidth();
		h = image.getHeight ();
		righe = new Line[h];
		threads = new Thread[h];
	}
	
	/**
	 * A better stamp
	 * @param o
	 * @return
	 */
	private static String stampEntry(Entry<Color, Integer> o) {
		String s = "Color: " + o.getKey().toString().replaceFirst("j.*or", "") + "- Number of equal pixels: " + o.getValue();
		return s;
	}
	
}
