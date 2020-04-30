package application;

import java.util.Iterator;

public class LinkedList implements LinkedListADT<Assignment>, Iterable<Assignment> {

  private class Node {
    private Assignment assignment;
    private Node next;

    public Node(Assignment assignment) {
      this.assignment = assignment;
      next = null;
    }
  }

  private Node head;
  private int size;

  @Override
  public void insert(Assignment data) {
    Node newNode = new Node(data);

    Node current = head;
    if (current == null) {
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

  @Override
  public Assignment remove(String name) {
    Node current = head;
    while (current != null) {
      if (current.assignment.getAssignmentName().equals(name) && current == head) {
        Assignment rem = head.assignment;
        head = head.next;
        size--;
        return rem;
      } else if (current.next != null && current.next.assignment.getAssignmentName().equals(name)) {
        Assignment rem = current.next.assignment;
        current.next = current.next.next;
        size--;
        return rem;
      }

      current = current.next;
    }

    return null;
  }

  @Override
  public Assignment get(String name) {
    Node current = head;
    while (current != null) {
      if (current.assignment.getAssignmentName().equals(name))
        return current.assignment;

      current = current.next;
    }

    return null;
  }

  @Override
  public boolean contains(String name) {
    Node current = head;
    while (current != null) {
      if (current.assignment.getAssignmentName().equals(name))
        return true;

      current = current.next;
    }

    return false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<Assignment> iterator() {
    return new ListIter(head);
  }

  @Override
  public String toString() {
    String res = "";

    Node current = head;
    while (current != null) {
      res += current.assignment.toString() + "  ";

      current = current.next;
    }

    return res;
  }

  private class ListIter implements Iterator<Assignment> {
    private Node node;

    public ListIter(Node head) {
      this.node = head;
    }

    @Override
    public boolean hasNext() {
      return node != null;
    }

    @Override
    public Assignment next() {
      if (node == null)
        return null;

      Assignment assignment = node.assignment;
      node = node.next;
      return assignment;
    }
  }

}
