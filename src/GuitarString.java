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
public class GuitarString {
	// The instance variable for numCapacity is created
	private int numCapacity;
	// the sample rate constant
	private static final int SAMPLE_RATE = 44100;
	// the energy decay factor
	private static final double ENERGY_DECAY_FACTOR = 0.994;
	// The random shift constant
	private static final double RANDOM_SHIFT = 0.5;
	// The constant for dividing the summation of the removed sample and the new sample
	private static final double AVERAGE = 2.0;
	// object of type RingBuffer created
	private RingBuffer elements;
	// The instance variable for counting the number of tics
	private int ticCount = 0;
	// This constructor receives the frequency as a parameter, which is used to calculate the N capacity stored
	// in the numCapacity variable
	public GuitarString(double frequency) {
		numCapacity = (int) Math.round(SAMPLE_RATE/frequency);
		// the elements object is created as a new RingBuffer with the numCapacity passed
		elements = new RingBuffer(numCapacity);
		// This for loop assigns default 0's to the buffer 
		for(int i = 0; i<numCapacity; i++) {
			elements.enqueue(0.0);
		}
	}
	// This constructor receives the double array init as a parameter
	public GuitarString(double[]init) {
		// numCapacity stores the length of the double array init
		numCapacity = init.length;
		// The elements object is created as a new RingBuffer with the numCapacity passed
		elements = new RingBuffer(numCapacity);
		// The contents of the buffer are initialized with the contents of the double array
		for(int i = 0; i<numCapacity; i++) {
			elements.enqueue(init[i]);
		}
	}
	// This method sets the buffer to white noise
	public void pluck() {
		// The forloop goes through the buffer with each element storing a random number between -0.5 to 0.5
		for(int i = 0; i<numCapacity; i++) {
			elements.dequeue();
			double num = Math.random()-RANDOM_SHIFT;
			elements.enqueue(num);
		}
	}
	// This method advances the simulation one at a time
	public void tic() {
		// from element removed is stored in the double removedSample
		double removedSample = elements.dequeue();
		// The new front sample after the old one was removed is stored in the double front sample
		double  frontSample = elements.peek();
		// The double newSample stores the average of the removed sample and the front sample multiplied by the
		// energy decay factor.
		double newSample = ((removedSample + frontSample)/AVERAGE) * ENERGY_DECAY_FACTOR;
		// The new sample is added to the buffer
		elements.enqueue(newSample);
		// Keeps track of how many times tic is called
		ticCount++;
	}
	// This method returns the current sample
	public double sample() {
		return elements.peek();
	}
	// This method returns the ticCount
	public int time() {
		return ticCount;
	}
}