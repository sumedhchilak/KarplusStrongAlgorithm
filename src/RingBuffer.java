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
import java.util.NoSuchElementException;
public class RingBuffer {
	// An instance array is created called elements of the Ringbuffer
	private double[]elements;
	// An instance variable of first index is created to keep track of the ringbuffer array
	private int firstIndex;
	// An instance variable of the last index is created
	private int lastIndex;
	// An instance variable of size is created to keep track of the number of elements in the ringbuffer array
	private int size;
	// The constructor initializes the instance variables
	public RingBuffer(int capacity) {
		elements = new double[capacity];
		firstIndex = 0;
		lastIndex = 0;
		size = 0;
	}
	// This method returns the size
	// The size is incremented if the enqueue method is called
	// The size is decremented if the dequeue method is called
	public int size() {
		return size;
	}
	// If size is of the ringbuffer array is 0, then true is returned else false
	public boolean isEmpty() {
		if(size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	// If the size of the array is equivalent to the length of the array, then true is returned
	// for whether the array is full
	public boolean isFull() {
		if(size() == elements.length) {
			return true;
		}
		else {
			return false;
		}
	}
	// This method adds an element to the end of the array
	public void enqueue(double x) {
		if(isFull()) {
			throw new IllegalStateException();
		}
		// If the last index reaches the length of the array, then it reaches the bounds an is set back to 0
		if(lastIndex == elements.length) {
			lastIndex = 0;
		}
		// Since an element is added to the end of the array the size is incremented
		size++;
		// The double passed as a parameter is stored in the last index of the array
		elements[lastIndex] = x;
		// The last index of the array is incremented to keep the index at the right location
		lastIndex++;
	}
	// This method removes the element in the front of the array
	public double dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		// The element in the first index of the array is stored in a placeholder
		double placeHolder = elements[firstIndex];
		// The size is decremented since removing the element in the front makes the array size smalled
		size--;
		// The firstIndex is incremented as the index moves ahead since the front element is removed
		firstIndex++;
		// The front element of the array which was stored in a placeholder is returned
		// If the first index reaches the element arrays length, it is reset back to 0 as it reaches the bounds
		if(firstIndex == elements.length) {
			firstIndex = 0;
		}
		return placeHolder;
	}
	// This method returns the current element at the firstIndex of the array
	public double peek()throws NoSuchElementException{
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return elements[firstIndex];
	}
	// This method returns the toString of the current array
	public String toString() {
		String s = "[";
		// An index variable stores the first index
		int index = firstIndex;
		// The forloop goes through the size of the array minus 1
		for(int i = 0; i<size()-1; i++) {
			// if the index reaches the end of the element array, it goes back to the beginning index
			if(index == elements.length) {
				index = 0;
			}
			// The string concatenates each index of the element array
			s += elements[index] + ", ";
			// The index is incremented
			index++;
		}
		// (fence-post)If the size of the array is greater than 0, the last index of the array is concatenated
		if(size()>0) {
			s += elements[lastIndex-1];
		}
		// the toString is returned
		return s + "]";
	}
}