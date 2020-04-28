package application;

/**
 * Filename: HashTableADT.java Project: p3-2020 Course: cs400 Authors: Debra Deppeler
 * 
 * May use any of these Java's built-in Java collection types: Arrays, List, ArrayList, LinkedList,
 * Stack, Queue (interface), PriorityQueue, Deque
 * 
 * May not use HashTable, TreeMap, HashMap, etc. May not add any public members to ADT or your
 * implementation.
 *
 * DO NOT EDIT THIS INTERFACE
 */
public interface HashTableADT<K, V> {

  // Returns the load factor threshold that was
  // passed into the constructor when creating
  // the instance of the HashTable.
  // When the current load factor is greater than or
  // equal to the specified load factor threshold,
  // the table is resized and elements are rehashed.
  public double getLoadFactorThreshold();

  // Returns the current load factor for this hash table
  // load factor = number of items / current table size
  public double getLoadFactor();

  // Return the current Capacity (table size)
  // of the hash table array.
  //
  // The initial capacity must be a positive integer, 1 or greater
  // and is specified in the constructor.
  //
  // REQUIRED: When the load factor threshold is reached,
  // the capacity must increase to: 2 * capacity + 1
  //
  // Once increased, the capacity never decreases
  public int getCapacity();

}
