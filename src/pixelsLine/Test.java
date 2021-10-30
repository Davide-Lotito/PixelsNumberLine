package pixelsLine;
/**
 * A simple test.
 * @author {Davide Lotito Pio}
 *
 */
public class Test {

	public static void main(String[] args) throws InterruptedException {
		/**
		 * If there are not arguments, use a default image! Only to show the program
		 */
		if(args.length==0) args = new String[] {"./Images/image.jpg"};
		AnalizeLine.main(args) ;
	}
}