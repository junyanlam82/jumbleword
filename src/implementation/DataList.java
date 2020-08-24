package implementation;

import jwinterface.ListInterface;
import java.util.Random;



public class DataList<T> implements ListInterface<T> {

  private T[] array;
  private int length;
  private static final int DEFAULT_CAPACITY = 5;

  public DataList() {
    this(DEFAULT_CAPACITY);
  }

  public DataList(int initialCapacity) {
    length = 0;
    array = (T[]) new Object[initialCapacity];
  }

  public boolean add(T newEntry) {
      if(!isArrayFull()){
        array[length] = newEntry;
        length++;
      }
      else{
        expandArray(array.length + 10);
        array[length] = newEntry;
        length++;
      }
    
    return true;
  }
    private void expandArray(int newLength){
       //create a new array
       T[] newArray = (T[]) new Object[newLength];

       //copy all entries from the old to the new
       for(int i = 0; i < array.length; i++){
               newArray[i] = array[i];
       }

       //let the array pointer pointing to the new array
       array = newArray;
    }
     public void shuffle() {
        T[] shuffledArray = (T[]) new Object[length];
       //Generate random number first
        int[] index= new int[length];
        Random rand = new Random();
        
        for(int j=0;j<index.length;j++){
            index[j] = j;
        }
        for(int i=0;i<index.length;i++){
            int randomIndexToSwap = rand.nextInt(index.length);
            int temp = index[randomIndexToSwap];
            index[randomIndexToSwap]=index[i];
            index[i] = temp;
        }
        
        for(int i=0;i<shuffledArray.length;i++){
            shuffledArray[i] = this.array[index[i]];
        }
        
        array=shuffledArray; 
    }
     
  @Override
  public boolean add(int newPosition, T newEntry) {
    boolean isSuccessful = true;

    if ((newPosition >= 1) && (newPosition <= length + 1)) {
      if (!isArrayFull()) {
        makeRoom(newPosition);
        array[newPosition - 1] = newEntry;
        length++;
      }
    } else {
      isSuccessful = false;
    }

    return isSuccessful;
  }
   
  @Override
  public T remove(int givenPosition) {
    T result = null;

    if ((givenPosition >= 1) && (givenPosition <= length)) {
      result = array[givenPosition - 1];

      if (givenPosition < length) {
        removeGap(givenPosition);
      }

      length--;
    }

    return result;
  }

  @Override
  public void clear() {
    length = 0;
  }

  @Override
  public boolean replace(int givenPosition, T newEntry) {
    boolean isSuccessful = true;

    if ((givenPosition >= 1) && (givenPosition <= length)) {
      array[givenPosition - 1] = newEntry;
    } else {
      isSuccessful = false;
    }

    return isSuccessful;
  }

  @Override
  public T getEntry(int givenPosition) {
    T result = null;

    if ((givenPosition >= 1) && (givenPosition <= length)) {
      result = array[givenPosition - 1];
    }

    return result;
  }

  public boolean contains(T anEntry) {
    boolean found = false;
    for (int index = 0; !found && (index < length); index++) {
      if (anEntry.equals(array[index])) {
        found = true;
      }
    }

    return found;
  }
  
  public int getLength() {
    return length;
  }

  public boolean isEmpty() {
    return length == 0;
  }

  public boolean isFull() {
    return false;
  }

  @Override
  public String toString() {
    String outputStr = "";
    for (int index = 0; index < length; ++index) {
      outputStr += array[index] + "\n";
    }

    return outputStr;
  }

  private boolean isArrayFull() {
    return length == array.length;
  }

  /**
   * Task: Makes room for a new entry at newPosition. Precondition: 1 <=
   * newPosition <= length + 1; length is array's
 length before addition.
   */
  private void makeRoom(int newPosition) {
    int newIndex = newPosition - 1;
    int lastIndex = length - 1;

    // move each entry to next higher index, starting at end of
    // array and continuing until the entry at newIndex is moved
    for (int index = lastIndex; index >= newIndex; index--) {
      array[index + 1] = array[index];
    }
  }

  /**
   * Task: Shifts entries that are beyond the entry to be removed to the next
   * lower position. Precondition: array is not empty; 1 <= givenPosition <
 length; length is array's length before removal.
   */
  private void removeGap(int givenPosition) {
    // move each entry to next lower position starting at entry after the
    // one removed and continuing until end of array
    int removedIndex = givenPosition - 1;
    int lastIndex = length - 1;

    for (int index = removedIndex; index < lastIndex; index++) {
      array[index] = array[index + 1];
    }
  }

    

}
