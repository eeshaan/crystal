package application;

public interface PriorityQueueADT<T> {
	
	  public void insert(T newObject);

	  public T removeBest();
	  
	  public T peekBest();

	  public int size();

	  public boolean isEmpty();
}
