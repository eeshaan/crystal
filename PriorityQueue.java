package application;

/**
 * 
 * PriorityQueue - Priority Queue that contains Assignments
 * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani
 */
public class PriorityQueue implements PriorityQueueADT<Assignment> {

  private static final int INITIAL_CAPACITY = 20; // initial capacity of this waiting process queue
  private Assignment[] data; // max heap-array storing the data (oversize array)
  private int size; // number of CustomProcesses stored in this WaitingProcessQueue

  /**
   * PriorityQueue - constructor for this class
   */
  public PriorityQueue() {
    data = new Assignment[INITIAL_CAPACITY]; // Assignment array is initialized
  }

  /**
   * size - gets the size of the array
   * @return int - the size of the array
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * isEmpty - checks if the priority queue is empty
   * @return true if the queue is empty and false otherwise
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * insert - inserts an Assignment into the Assignment array
   */
  @Override
  public void insert(Assignment newObject) {
    // If array is full, doubles the size of the array with a shadow array
    if (size == data.length) {
      Assignment[] rep = new Assignment[size * 2]; // Creates shadow array
      for (int i = 0; i < size; i++) // Adds elements to shadow array
        rep[i] = data[i];
      data = rep; // Copies shadow array to data
    }

    data[size] = newObject; // Adds assignment to next free spot
    maxHeapPercolateUp(size); // Organizes the max-priority heap
    size++; // increments size
  }

  /**
   * removeBest - removes an Assignment from the queue with the highest priority
   * @return Assignment that is removed
   */
  @Override
  public Assignment removeBest() {
    size--; // decrements size

    Assignment temp = data[0]; // Gets the assignment to be removed
    
    // Replaces removed assignment with one with lowest priority
    data[0] = data[size];
    data[size] = null;

    maxHeapPercolateDown(0); // Organizes the max-priority heap
    
    return temp; // returns removed assignment
  }

  /**
   * remove - removes the assignment with the given name in parameter
   * @param name - name of assignment to be removed
   * @return the removed Assignment
   */
  public Assignment remove(String name) {
    int i; // index of removed assignment
    
    // Traverses queue for assignment
    for (i = 0; i < data.length; i++) {
      if (data[i].getAssignmentName().equals(name))
        break;
    }

    Assignment temp = data[i]; // Gets the assignment to be removed
    
    // replaces removed assignment with one with the lowest priority
    data[i] = data[size];
    data[size] = null;
    
    maxHeapPercolateDown(i); // Organizes the max-priority heap
    
    return temp; // Returns the removed assignment
  }

  /**
   * peekBest - gets the assignment with the highest priority
   * @return Assignment with highest priority
   */
  @Override
  public Assignment peekBest() {
    return data[0];
  }

  /**
   * maxHeapPercolateUp - sorts the max-priority heap by moving the last assignment
   *    to its correct position
   * @param index - index of assignment to be sorted into the array
   */
  private void maxHeapPercolateUp(int index) {
    if (index == 0 || data[index].compareTo(data[(index - 1) / 2]) <= 0) {
      // Base case: when the index is the first one, or it is smaller than its parent value
      return;
    } else if (data[index].compareTo(data[(index - 1) / 2]) > 0) {
      // Swaps the values of the current index and the index of its parent value
      Assignment temp = data[index];
      data[index] = data[(index - 1) / 2];
      data[(index - 1) / 2] = temp;

      // Recursive call on the new location of the new Assignment object
      maxHeapPercolateUp((index - 1) / 2);
    }
  }

  /**
   * maxHeapPercolateDown - sorts the max-priority heap by moving the first assignment
   *    to its correct position
   * @param index - index of assignment to be sorted into the array
   */
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

  /**
   * toString - gets the assignments
   * @return String List of assignments 
   */
  @Override
  public String toString() {
    String res = ""; // String of assignments
    
    // Traverses array to get the assignment names to add to res
    for (int i = 0; i < size; i++)
      res += data[i].getAssignmentName() + " ";

    return size == 0 ? " " : res; // returns String of assignments
  }
}
