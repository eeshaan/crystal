package application;

public interface QueueADT<T> {

  public void enqueue(T data);

  public T dequeue();

  public int size();

  public boolean contains(T data);
}
