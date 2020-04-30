package application;
/**
 * HashTable.java in p3a_project
 *
 * Author: Ayaz Franz (acfranz@wisc.edu) Date: March 6, 2020
 *
 * Course: CS400 Semester: Spring 2020 Lecture: 001
 *
 * IDE: Eclipse IDE for Java Developers Version: 2019-09
 *
 */

import java.util.ArrayList;
import java.util.Iterator;

// TODO: describe the collision resolution scheme you have chosen
// I am using buckets. My buckets are linked lists
//
// TODO: explain your hashing algorithm here
// I use java's hashCode function.
//

/**
 * Defines the operations needed for the HashTable object.
 * 
 * @author acfranz
 *
 * @param <K> extends type Comparable<K> type of keys
 * @param <V> type of values
 */
public class HashTable<K, V> implements HashTableADT<K, V> {

  private ArrayList<LinkedNode> data; // ArrayList of buckets (linked lists)
  private double loadFactorThreshold; // threshold for load factor to resize
  private int capacity; // number of indices in data
  private int numKeys; // number of keys

  /**
   * Private inner class for LinkedNode objects. LinkedNode objects hold key value pairs and can be
   * linked.
   * 
   * @author acfranz
   *
   */
  private class LinkedNode {
    private K key;
    private V value;
    private LinkedNode next; // pointer to the next node

    /**
     * Constructor for LinkedNode objects setting their key and value fields appropriately and
     * leaving next to be null
     * 
     * @param key   - value of key
     * @param value - value of value
     */
    public LinkedNode(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  @Override
  public String toString() {
    String res = "";

    for (LinkedNode node : data) {
      while (node != null) {
        res += node.value + " ";
        
        node = node.next;
      }
    }
    return res;
  }

  /**
   * No argument constructor for HashTable object. Sets capacity to 11, loadFactorThreshold to 1.5,
   * and initializes all values in data to null.
   */
  public HashTable() {
    data = new ArrayList<>(11);
    loadFactorThreshold = 1.5;
    capacity = 11;
    for (int i = 0; i < capacity; i++)
      data.add(null);
  }

  /**
   * Constructor for HashTable object that takes user inputs. Sets the loadFactorThreshold and
   * capacity accordingly and initializes the all values in data to null.
   * 
   * @param initialCapacity     - initial capacity for HashTable
   * @param loadFactorThreshold - the loadFactor to resize at
   */
  public HashTable(int initialCapacity, double loadFactorThreshold) {
    this.loadFactorThreshold = loadFactorThreshold;
    data = new ArrayList<>(initialCapacity);
    capacity = initialCapacity;
    for (int i = 0; i < capacity; i++)
      data.add(null);
  }

  /**
   * Inserts a key value pair into the HashTable.
   * 
   * @param key   - key to be inserted
   * @param value - value associated with key
   * @throws IlleaglNullKeyException - when key is null
   */
  // @Override
  public void insert(K key, V value) {
    // if (key == null)
    // throw new IllegalNullKeyException("key is null");

    LinkedNode newNode = new LinkedNode(key, value);
    int hashIndex = Math.abs(key.hashCode() % capacity); // hash function

    LinkedNode node = data.get(hashIndex); // node at hash index
    if (node == null) {
      numKeys++;
      data.set(hashIndex, newNode);
    } else {
      while (node != null) {
        if (key.equals(node.key)) {
          // if key exists already change the value associated with it
          node.value = value;
          break;
        }
        if (node.next == null) {
          // if key does not exist insert at the end of list
          node.next = newNode;
          numKeys++;
          break;
        }

        node = node.next; // iterate through list
      }
    }

    double loadFactor = getLoadFactor();
    if (loadFactor > loadFactorThreshold)
      resize();
  }

  /**
   * Private helper method to help with resizing the HashTable.
   * 
   * @throws IllegalNullKeyException - if a null key is inserted
   */
  private void resize() {
    capacity = capacity * 2 + 1; // sets new capacity
    ArrayList<LinkedNode> temp = data;
    data = new ArrayList<>(capacity); // sets data to new capacity
    numKeys = 0; // clear all keys

    // set all indices to null
    for (int i = 0; i < capacity; i++)
      data.add(null);

    // inserts all nodes back into data
    for (LinkedNode node : temp)
      while (node != null) {
        insert(node.key, node.value);

        node = node.next; // iterate through list
      }
  }

  /**
   * Removes a key value pair from the HashTable. If the key was not in the HashTable, method
   * returns false.
   * 
   * @param key - key to be deleted
   * @return true if operation successful, false if key did not exist
   * @throws IllegalNullKeyException - when key is null
   */
  // @Override
  public boolean remove(K key) {
    // if (key == null)
    // throw new IllegalNullKeyException("key is null");

    // hash function
    int hashIndex = Math.abs(key.hashCode() % capacity);
    LinkedNode head = data.get(hashIndex);

    LinkedNode node = head;
    while (node != null) {
      if (node.key.equals(key) && node == head) {
        // key is in head node of the list
        data.set(hashIndex, node.next);
        numKeys--;
        return true;
      } else if (node.next != null && node.next.key.equals(key)) {
        // key is in a body node
        node.next = node.next.next;
        numKeys--;
        return true;
      }

      node = node.next; // iterate through list
    }

    return false;
  }

  /**
   * Get the value that is stored with the given key.
   * 
   * @param key - key associated with value to be retrieved
   * @return value associated with the key
   * @throws IllegalNullKeyException - when key is null
   * @throws KeyNotFoundException    - when key is not in HashTable
   */
  // @Override
  public V get(K key) {
    // if (key == null)
    // throw new IllegalNullKeyException("key is null");

    // hash function
    int hashIndex = Math.abs(key.hashCode() % capacity);
    LinkedNode node = data.get(hashIndex);

    while (node != null) {
      if (node.key.equals(key)) // found key
        return node.value;

      node = node.next; // iterate through list
    }
    return null;
    // throw new KeyNotFoundException("key did not exist in hashtable");
  }

  /**
   * Returns the number of keys in the HashTable.
   * 
   * @return numKeys field
   */
  // @Override
  public int numKeys() {
    return numKeys;
  }

  /**
   * Returns the loadFactorThreshold of the HashTable.
   * 
   * @return loadFactorThreshold field
   */
  @Override
  public double getLoadFactorThreshold() {
    return loadFactorThreshold;
  }

  /**
   * Calculates and returns the load factor of the threshold.
   * 
   * @return numKeys divided by capacity
   */
  @Override
  public double getLoadFactor() {
    double numKeys = numKeys();
    return numKeys / capacity;
  }

  /**
   * Getter method for the capacity.
   * 
   * @return capacity field
   */
  @Override
  public int getCapacity() {
    return capacity;
  }

  
  public Iterator<K> iterator() {
	    return new keyIter(this);
	}

	private class keyIter implements Iterator<K>{

	    private int current;
	    private ArrayList<K> keys;

	    public keyIter(HashTable<K, V> ht) {
	        current = 0;
	        keys = new ArrayList<>();

	        for (LinkedNode node : data) {
	              while (node != null) {
	                keys.add(node.key);

	                node = node.next;
	              }
	        }
	    }

	    @Override
	    public boolean hasNext() {
	        return current < keys.size();
	    }

	    @Override
	    public K next() {
	        if(current >= keys.size())
	            return null;

	        K key = keys.get(current);
	        current++;
	        return key;
	    }

	}
}
