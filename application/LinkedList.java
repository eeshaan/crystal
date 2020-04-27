package application;

public class LinkedList implements LinkedListADT<Assignment> {

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

}
