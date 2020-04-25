package application;

public interface LinkedListADT<T> {
	
	public void insert();
	
	public T remove();
	
	public T get();
	
	public boolean contains(T data);
	
	public boolean size();
}
