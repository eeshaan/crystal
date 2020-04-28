package application;

public interface LinkedListADT<T> {
	
	public void insert(T data);
	
	public T remove(String name);
	
	public T get(String name);
	
	public boolean contains(String name);
	
	public int size();
}
