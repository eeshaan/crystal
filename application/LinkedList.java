package application;

public class LinkedList<T> implements LinkedListADT<T> {

	private class Node {
		private T data;
		private Node next;
		
		public Node(T data) {
			this.data = data;
			next = null;
		}
	}
	
	private Node head;
	private Node tail;
	private Node size;
	
	@Override
	public void insert(T data) {
		if(head == null) {
			head = new Node(data);
			tail = head;
		}
		else {
			tail.next = new Node(data);
			tail = tail.next;
		}
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(T data) {
		Node current = head;
		while(current != null) {
			current = current.next;
			
		}
		return false;
	}

	@Override
	public boolean size() {
		// TODO Auto-generated method stub
		return false;
	}

}
