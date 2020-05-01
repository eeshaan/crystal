package application;

/**
 * 
 * LinkedListADT - interface for a LinkedList
 * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani
 * @param <T>
 */
public interface LinkedListADT<T> {

  /**
   * insert - inserts an object into the LinkedList
   * @param data - object inserted into the LinkedList
   */
  public void insert(T data);

  /**
   * remove - removes an object from the LinkedList
   * @param name - the name of the object removed from the LinkedList
   * @return object removed from the LinkedList
   */
  public T remove(String name);

  /**
   * get - gets the object with the name provided in the LinkedList
   * @param name - the name of the object to be found
   * @return object with the provided name
   */
  public T get(String name);

  /**
   * contains - checks if an object with the name provided is in the LinkedList
   * @param name - the name of the object to be found
   * @return true if the object is in the LinkedList and false otherwise
   */
  public boolean contains(String name);

  /**
   * size - gets the size of the LinkedList
   * @return the number of objects in the LinkedList
   */
  public int size();
}