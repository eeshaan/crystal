package application;

/**
 * 
 * PQueueADT -  interface for a queue
 * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani (2020)

 * @param <T>
 */
public interface QueueADT<T> {

  /**
   * enqueue - adds an object to the queue
   * @param data - the object added to the queue
   */
  public void enqueue(T data);

  /**
   * dequeue - removes the first object in the queue
   * @return the object removed from the queue
   */
  public T dequeue();

  /**
   * size - gets the size of the queue
   * @return the number of elements in the queue (int)
   */
  public int size();

  /**
   * contains - checks if the object is in the queue
   * @param data - the object checked if in the queue
   * @return true if the object is in the queue and false otherwise
   */
  public boolean contains(T data);
}
