package application;

public class PriorityQueue implements PriorityQueueADT<Assignment> {
	private static final int INITIAL_CAPACITY = 20; // the initial capacity of this
	  // waiting process queue
	  private Assignment[] data; // min heap-array storing the 
	  // data is an oversize array
	  private int size; // number of CustomProcesses stored in this WaitingProcessQueue

	  
	  public PriorityQueue() {
	    data = new Assignment[INITIAL_CAPACITY];
	  }

	  @Override
	  public int size() {
	    return size;
	  }

	  @Override
	  public boolean isEmpty() {
	    return size == 0;
	  }

	  @Override
	  public void insert(Assignment newObject) {
	    if (size == data.length) {
	      // If the data field is full, copies the current array over to an oversized array of two times
	      // its current size
	      Assignment[] rep = new Assignment[size * 2];
	      for (int i = 0; i < size; i++)
	        rep[i] = data[i];

	      data = rep;
	    }

	    data[size] = newObject; // Sets the next null index to the new Assignment
	    maxHeapPercolateUp(size); // Sorts order of elements to ensure that it is organized like a max
	                              // priority heap
	    size++;
	  }

	  @Override
	  public Assignment removeBest() {
	    size--;

	    // Store the Assignment from index 0 of the data field, replace the value of index zero with
	    // the last non-null element of the array and sets that index to null
	    Assignment temp = data[0];
	    data[0] = data[size];
	    data[size] = null;

	    maxHeapPercolateDown(0); // Sorts order of elements to ensure that it is organized like a min
	                             // priority heap
	    return temp;
	  }
	  
	  public Assignment remove(String name) {
		  int i;
		  for(i = 0; i < data.length; i++) {
			  if(data[i].getAssignmentName().equals(name)) {
				  break;
			  }
		  }
		  
		  Assignment temp = data[i];
		  data[i] = data[size];
		  data[size] = null;
		  maxHeapPercolateDown(i); // Sorts order of elements to ensure that it is organized like a min
		                             // priority heap
		  return temp;
	  }

	  @Override
	  public Assignment peekBest() {
	    return data[0];
	  }

	  
	  private void maxHeapPercolateUp(int index) {
	    if (index == 0 || data[index].compareTo(data[(index - 1) / 2]) <= 0) {
	      // Base case: when the index is the first index, or it is smaller than its parent value
	      return;
	    } else if (data[index].compareTo(data[(index - 1) / 2]) > 0) {
	      // Swaps the values of the current index and the index of its parent value
	      Assignment temp = data[index];
	      data[index] = data[(index - 1) / 2];
	      data[(index - 1) / 2] = temp;

	      maxHeapPercolateUp((index - 1) / 2); // Recursive call on the new location of the new
	                                           // Assignment object
	    }
	  }

	  private void maxHeapPercolateDown(int index) {
	    if (index >= size / 2) {
	      // Base case: the index does not have any children indices
	      return;
	    } else if (data[index * 2 + 1] != null && data[index].compareTo(data[index * 2 + 1]) < 0) {
	      // If the first child of the current index is greater than it

	      // Checks which is greater, the first or second child index
	      int repInd;
	      if (data[index * 2 + 2] != null && data[index * 2 + 1].compareTo(data[index * 2 + 2]) < 0)
	        repInd = index * 2 + 2;
	      else
	        repInd = index * 2 + 1;

	      // Swaps the data and calls maxHeapPercolateDown() on the index that the data from the current
	      // index was swapped to
	      Assignment temp = data[index];
	      data[index] = data[repInd];
	      data[repInd] = temp;
	      maxHeapPercolateDown(repInd);
	    } else if (data[index * 2 + 2] != null && data[index].compareTo(data[index * 2 + 2]) < 0) {
	      // If the current index is smaller than the second child index and greater than the first

	      int repInd = index * 2 + 2;

	      // Swaps the data and calls maxHeapPercolateDown() on the index that the data from the current
	      // index was swapped to
	      Assignment temp = data[index];
	      data[index] = data[repInd];
	      data[repInd] = temp;
	      maxHeapPercolateDown(repInd);
	    }
	  }

	  @Override
	  public String toString() {
	    String res = "";
	    for (int i = 0; i < size; i++)
	      res += data[i].toString() + " ";

	    return size == 0 ? " " : res;
	  }
}