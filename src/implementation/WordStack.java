package implementation;


import jwinterface.StackInterface;

/**
 * WordStack.java A class that implements the ADT array by using an expandable
 array.
 *
 * @author Frank M. Carrano
 * @version 2.0
 */
public class WordStack<T> implements StackInterface<T> {

  private T[] array;
  private int topIndex; // index of top entry
  private static final int DEFAULT_CAPACITY = 15;

  public WordStack() {
    this(DEFAULT_CAPACITY);
  }

  public WordStack(int initialCapacity) {
    array = (T[]) new Object[initialCapacity];
    topIndex = -1;
  }

  public void push(T newEntry) {
    topIndex++;

    if (topIndex < array.length) {
      array[topIndex] = newEntry;
    }
  }

  public T peek() {
    T top = null;

    if (!isEmpty()) {
      top = array[topIndex];
    }

    return top;
  } 

  public T pop() {
    T top = null;

    if (!isEmpty()) {
      top = array[topIndex];
      array[topIndex] = null;
      topIndex--;
    } // end if

    return top;
  } 
    public int getLength(){
    return array.length;
    }
  public boolean isEmpty() {
    return topIndex < 0;
  } 

  public void clear() {
    topIndex = -1;
  } 
  
} 
