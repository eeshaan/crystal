package application;

/**
 * 
 * PriorityQueueADT -  interface for a priority queue
 * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani (2020)
 * @param <T>
 */
public interface PriorityQueueADT<T> {

  /**
   * insert - inserts an object
   * @param newObject - object to be inserted
   */
  public void insert(T newObject);

  /**
   * removeBest - removes the object with highest priority
   * @return bestObject - object with highest priority
   */
  public T removeBest();

  /**
   * peekBest - peeks at the queue
   * @return object with the highest priority
   */
  public T peekBest();

  /**
   * size - gets the size of the queue
   * @return the size of the queue
   */
  public int size();

  /**
   * isEmpty - checks if the queue is empty
   * @return true if the queue is empty and false otherwise
   */
  public boolean isEmpty();
}