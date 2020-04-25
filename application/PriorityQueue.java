package application;

public class PriorityQueue implements PriorityQueueADT {
	private static final int INITIAL_CAPACITY = 20; // the initial capacity of this
	  // waiting process queue
	  private Assignment[] data; // min heap-array storing the CustomProcesses
	  // inserted in this WaitingProcessQueue.
	  // data is an oversize array
	  private int size; // number of CustomProcesses stored in this WaitingProcessQueue

	  /**
	   * Creates a new WaitingProcessQueue object and initializes its fields.
	   */
	  public PriorityQueue() {
	    data = new Assignment[INITIAL_CAPACITY];
	  }

	  /**
	   * Accessor method for the size of the WaitingProcessQueue object.
	   * 
	   * @return size
	   */
	  //@Override
	  public int size() {
	    return size;
	  }

	  /**
	   * Checks whether the WaitingProcessQueue is empty.
	   * 
	   * @return true if the size is zero and false otherwise
	   */
	  //@Override
	  public boolean isEmpty() {
	    return size == 0;
	  }

	  /**
	   * Inserts a new CustomProcess into the WaitingProcessQueue's data field.
	   */
	  //@Override
	  public void insert(Assignment newObject) {
	    if (size == data.length) {
	      // If the data field is full, copies the current array over to an oversized array of two times
	      // its current size
	      Assignment[] rep = new Assignment[size * 2];
	      for (int i = 0; i < size; i++)
	        rep[i] = data[i];

	      data = rep;
	    }

	    data[size] = newObject; // Sets the next null index to the new CustomProcess
	    minHeapPercolateUp(size); // Sorts order of elements to ensure that it is organized like a min
	                              // priority heap
	    size++;
	  }

	  /**
	   * Removes the first element from the WaitingProcessQueue's data field, aka the CustomProcess with
	   * the lowest value and therefore of highest priority.
	   * 
	   * @return the CustomProcess object from the first element of the data field
	   */
	  //@Override
	  public Assignment removeBest() {
	    size--;

	    // Store the CustomProcess from index 0 of the data field, replace the value of index zero with
	    // the last non-null element of the array and sets that index to null
	    Assignment temp = data[0];
	    data[0] = data[size];
	    data[size] = null;

	    minHeapPercolateDown(0); // Sorts order of elements to ensure that it is organized like a min
	                             // priority heap
	    return temp;
	  }

	  /**
	   * Returns the CustomProcess from the first element of the WaitingProcessQueue's data field, aka
	   * the CustomProcess with the lowest value and therefore of highest priority.
	   * 
	   * @return the CustomProcess object from the first element of the data field
	   */
	  //@Override
	  public Assignment peekBest() {
	    return data[0];
	  }

	  /**
	   * Recursive private helper method for sorting the data field after inserting a new CustomProcess
	   * object.
	   * 
	   * @param index - current index of the new CustomProcess object
	   */
	  private void minHeapPercolateUp(int index) {
	    if (index == 0 || data[index].compareTo(data[(index - 1) / 2]) >= 0) {
	      // Base case: when the index is the first index, or it is larger than its parent value
	      return;
	    } else if (data[index].compareTo(data[(index - 1) / 2]) < 0) {
	      // Swaps the values of the current index and the index of its parent value
	      Assignment temp = data[index];
	      data[index] = data[(index - 1) / 2];
	      data[(index - 1) / 2] = temp;

	      minHeapPercolateUp((index - 1) / 2); // Recursive call on the new location of the new
	                                           // CustomProcess object
	    }
	  }

	  /**
	   * Recursive private helper method for sorting the data field after removing the CustomProcess
	   * object with the highest priority.
	   * 
	   * @param index - the current index of the new first element/old last element
	   */
	  private void minHeapPercolateDown(int index) {
	    if (index >= size / 2) {
	      // Base case: the index does not have any children indices
	      return;
	    } else if (data[index * 2 + 1] != null && data[index].compareTo(data[index * 2 + 1]) > 0) {
	      // If the first child of the current index is less than it

	      // Checks which is lower, the first or second child index
	      int repInd;
	      if (data[index * 2 + 2] != null && data[index * 2 + 1].compareTo(data[index * 2 + 2]) > 0)
	        repInd = index * 2 + 2;
	      else
	        repInd = index * 2 + 1;

	      // Swaps the data and calls minHeapPercolateDown() on the index that the data from the current
	      // index was swapped to
	      Assignment temp = data[index];
	      data[index] = data[repInd];
	      data[repInd] = temp;
	      minHeapPercolateDown(repInd);
	    } else if (data[index * 2 + 2] != null && data[index].compareTo(data[index * 2 + 2]) > 0) {
	      // If the current index is greater than the second child index and smaller than the first

	      int repInd = index * 2 + 2;

	      // Swaps the data and calls minHeapPercolateDown() on the index that the data from the current
	      // index was swapped to
	      Assignment temp = data[index];
	      data[index] = data[repInd];
	      data[repInd] = temp;
	      minHeapPercolateDown(repInd);
	    }
	  }

	  /**
	   * Creates a String representation of this WaitingProcessQueue object.
	   * 
	   * @return a String of all CustomProcess objects in the WaitingProcessQueue object in order
	   */
	  @Override
	  public String toString() {
	    String res = "";
	    for (int i = 0; i < size; i++)
	      res += data[i].toString() + " ";

	    return size == 0 ? " " : res;
	  }
}
