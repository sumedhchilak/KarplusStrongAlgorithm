/**
 * CS312 Assignment 12. 
 *
 * On MY honor, Sumedh Chilakamarri, this programming assignment is MY own work
 * and I have not provided this code to any other student.
 *
 * Student name: Sumedh Chilakamarri
 * UTEID: ssc2536
 * email address: sumedh.chilak@utexas.edu
 * Grader name: Andrew
 * Number of slip days used on this assignment: 0
 *
 */
public class GuitarHero {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// the intro displayed
		show();
		// The play method is called and strings array is passed
		GuitarString[]strings = frequency();
		play(strings);
	}
	private static void show() {
		final double TEXT_POS_X = .4;
		final double TEXT_POS_Y = .5;
		final double POS_X1 = 0.4;
		final double POS_X2 = 0.5;
		final double POS_Y1 = 0.4;
		final double POS_Y2 = 0.3;
		String keyboard = "q 2 w e 4 r 5 t y 7 u 8 i 9 o p - [ = z x d c f v g b n j m k , . ; / ' ";
		StdDraw.text(TEXT_POS_X, TEXT_POS_Y, "Type a key to play a note!");
		StdDraw.text(POS_X1, POS_Y1, "List:");
		StdDraw.text(POS_X2, POS_Y2, keyboard);
	}
	private static GuitarString[] frequency() {
		// Constant for the number of strings
		final int NUM_STRINGS = 37;
		// The frequency constant
		final int FREQUENCY_CONSTANT = 440;
		// The power constant in the formula
		final double POWER_CONSTANT = 1.05956;
		// The constant for the frequency number which is subtracted in the power
		final int FREQ_NUM = 24;
		// the arrays string of type GuitarString of length 37
		GuitarString[] strings = new GuitarString[NUM_STRINGS];
		// This forloop goes through the array and calculates each frequency
		for(int i = 0; i<NUM_STRINGS; i++) {
			double frequency = (FREQUENCY_CONSTANT * Math.pow(POWER_CONSTANT, i - FREQ_NUM));
			// The Strings array is assigned the frequencies
			strings[i] = new GuitarString(frequency);
		}	
		return strings;
	}
	// compute the superposition of the samples
	private static double superposition(GuitarString[]strings) {
		double sample = 0.0;
		for(int i = 0; i<strings.length;i++) {
			sample += strings[i].sample();
		}
		// send the result to standard audio
		StdAudio.play(sample);
		return sample;
	}
	// advance the simulation of each guitar string by one step
	private static void advance(GuitarString[]strings) {
		for(int j = 0; j<strings.length; j++) {
			strings[j].tic();
		}
	}
	private static void play(GuitarString[]strings) {        // the main input loop
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		final double INCREMENT = 0.03;
		final double Y_POS = 0.1;
		double index = 0.0;
		while (true) {
			// check if the user has typed a key, and, if so, process it
			if (StdDraw.hasNextKeyTyped()) {
				// the user types this character
				char key = StdDraw.nextKeyTyped();
				// pluck the corresponding string
				int keyboardIndex = keyboard.indexOf(key);
				// The pluck method is called only is the key exists
				if(keyboardIndex != -1) {
					strings[keyboardIndex].pluck();
					// Every time a note is played, the key is displayed
					StdDraw.text(index, Y_POS, key+"");
					index += INCREMENT;
				}
			}
			superposition(strings);
			advance(strings);
		}   
	}	
}