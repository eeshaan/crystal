package application;

public class Queue implements QueueADT<Assignment> {

	private class Node {
		private Assignment assignment;
		private Node next;
		
		public Node(Assignment assignment) {
			this.assignment = assignment;
			next = null;
		}
	}
	
	private Node head;
	private Node tail;
	
	@Override
	public void enqueue(Assignment data) {
		if(tail == null) {
			head = new Node(data);
			tail = head;
		} 
		else {
			tail.next = new Node(data);
			tail = tail.next;
		}
	}

	@Override
	public Assignment dequeue() {
		Assignment data = head.assignment;
		head = head.next;
		return data;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(Assignment data) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
