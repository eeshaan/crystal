package application;

import java.util.Iterator;

/**
 * 
 * LinkedList - a LinkedList that contains Assignments
 * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani
 */
public class LinkedList implements LinkedListADT<Assignment>, Iterable<Assignment> {

  /**
   * 
   * Node - a node containing an Assignment and a reference to the next Node in the LinkedList
   * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani
   */
  private class Node {
    private Assignment assignment; // Assignment contained in the Node
    private Node next; // Reference to the next node in the LinkedList

    /**
     * Node - constructor for the Node class
     * @param assignment - Assignment to be stored in the Node
     */
    public Node(Assignment assignment) {
      this.assignment = assignment;
      next = null;
    }
  }

  private Node head; // First Node in the LinkedList
  private int size; // Size of the LinkedList

  /**
   * insert - inserts the Assignment as a Node into the LinkedList
   * @param data - Assignment object to be inserted into the LinkedList
   */
  @Override
  public void insert(Assignment data) {
    Node newNode = new Node(data); // Node containing Assignment

    Node current = head; // First node in the LinkedList
    
    if (current == null) { // If LinkedList is empty, set newNode as head
      head = new Node(data);
    } else {
      while (current != null) {
        if (current.assignment.getAssignmentName().equals(data.getAssignmentName())) {
          // if key exists already change the value associated with it
          current.assignment = data;
          break;
        }
        if (current.next == null) {
          // if key does not exist insert at the end of list
          current.next = newNode;
          size++;
          break;
        }

        current = current.next; // iterate through list
      }
    }
  }

  /**
   * remove - removed the Node with the Assignment with the name provided
   * @param name - name of the Assignment of the Node to be removed
   * @return Assignment - the Assignment with the name given and whose Node is removed
   */
  @Override
  public Assignment remove(String name) {
    Node current = head; // Head of the LinkedList
    
    while (current != null) { // If the LinkedList isn't empty
      if (current.assignment.getAssignmentName().equals(name) && current == head) {
        // If the Node is found and is the head, remove it and set the next node as the head
        Assignment rem = head.assignment;
        head = head.next;
        size--;
        return rem;
      } else if (current.next != null && current.next.assignment.getAssignmentName().equals(name)) {
        // If the Node is found in the middle of the LinkedList, remove it and set the previous node's
        // next node as the next node
        Assignment rem = current.next.assignment;
        current.next = current.next.next;
        size--;
        return rem;
      }

      current = current.next; // increments through the LinkedList
    }

    return null;
  }

  /**
   * get - gets the Assignment with the name provided
   * @param name - name of the assignment
   * @return Assignment - the assignment with the name provided
   */
  @Override
  public Assignment get(String name) {
    Node current = head; // Gets the head of the LinkedList
    
    // Traverses the LinkedList
    while (current != null) {
      if (current.assignment.getAssignmentName().equals(name))
        // If the Assignment is found, return it
        return current.assignment;

      current = current.next; // increments through the LinkedList
    }

    return null; // else return null if it's not found
  }

  /**
   * contains - checks if the Assignment with the name provided is in the LinkedList
   * @param name - name of the Assignment that is searched for
   * @return true if the Assignment is found and false otherwise
   */
  @Override
  public boolean contains(String name) {
    Node current = head; // Gets the head of the LinkedList
    
    // Traverses the LinkedList and checks if the Node's Assignment has the name
    while (current != null) {
      // if found, return true
      if (current.assignment.getAssignmentName().equals(name))
        return true;

      current = current.next; // increment through the LinkedList
    }

    return false; // if not found, return false
  }

  /**
   * size - gets the length of the LinkedList
   * @return the length of the LinkedList
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * iterator - Creates a ListIter instance with the head of the LinkedList
   * @return Iterator<Assignment> - Iterator of the Assignment
   */
  @Override
  public Iterator<Assignment> iterator() {
    return new ListIter(head);
  }

  /**
   * toString - gets a String of the names of the Assignments in the LinkedList
   * @return String of the names of all the Assignments in the LinkedList
   */
  @Override
  public String toString() {
    String res = ""; // String to be returned
    Node current = head; // Head of the LinkedList
    
    // Traverses through the LinkedList and concatenate the names of the Assignments
    // to the String
    while (current != null) {
      res += current.assignment.toString() + "  ";

      current = current.next;
    }

    return res; // returns the String
  }

  /**
   * 
   * ListIter - TODO Describe purpose of this user-defined type
   * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani
   */
  private class ListIter implements Iterator<Assignment> {
    private Node node; // Node instance used in the LinkedList

    /**
     * ListIter - constructor of the ListIter
     * @param head - head of the LinkedList
     */
    public ListIter(Node head) {
      this.node = head;
    }

    /**
     * hasNext - checks if there is a successor node
     * @return true if there exists a successor node and false otherwise
     */
    @Override
    public boolean hasNext() {
      return node != null;
    }

    /**
     * next - gets the successor node's Assignment
     * @return Assignment of the successor node
     */
    @Override
    public Assignment next() {
      // checks if the node provided is null
      if (node == null)
        return null;

      // Returns the successor node's Assignment
      Assignment assignment = node.assignment;
      node = node.next;
      return assignment;
    }
  }

}
