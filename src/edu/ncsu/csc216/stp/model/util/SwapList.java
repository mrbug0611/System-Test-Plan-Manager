/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;


/**
 * class for swapping elements array in java 
 * @param <E> type of element array will be composed of 
 * @author Matthew Williams 
 */
public class SwapList<E> implements ISwapList<E> {
	
	/** initial capacity of list **/ 
	private static final int INIT_CAPACITY = 10; 
	
	/** size of list **/
	 
	private int size; 
	
	/** array of elements **/ 
	private E[] list; 
	
	/** capacity of current array **/
	private int capacity; 
	
	/**
	 * constructor 
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		this.capacity = INIT_CAPACITY; 
		this.size = 0; 
		this.list = (E[]) new Object[INIT_CAPACITY];

		
	}
	
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(E element) throws NullPointerException, IllegalArgumentException {
		
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		 
		
		if (size == list.length) {
			capacity = list.length; 
			
			capacity *= 2;
			
			E[] newList = (E[]) new Object[capacity];
			
			for (int i = 0; i < list.length; i++) {
				newList[i] = list[i];
			}
			list = newList;
		}
			
		size += 1; 
		
		
		for (int i = 0; i < size; i++) {
			if (list[i] == null) {
				list[i] = element;  
				break; 
			}
			}	
	}
	
	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@SuppressWarnings("unchecked")

	@Override
	public E remove(int idx) throws IndexOutOfBoundsException {
		if (idx < 0 || idx >= size()){
			throw new IndexOutOfBoundsException("Invalid index.");

		}
		
		E removedItem =  list[idx];
		
		if (idx < size) {
			E[] newList = (E[]) new Object[list.length];
			
			for (int i = 0; i < idx; i++) {
				newList[i] = list[i];
			}
	
			
	
			for (int i = idx; i < size; i++) {
				newList[i] = list[i + 1];
			}
			
			list = newList; }
		else {
			list[idx] = null;
		}
		
		 
	
		size -= 1; 
		return removedItem;	}
	
	/**
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveUp(int idx) throws IndexOutOfBoundsException {
		
		if (idx < 0 || idx >= size()){
			throw new IndexOutOfBoundsException("Invalid index.");

		}
		
		if (idx != 0) {
		
		E current = get(idx);
		E temp = get(idx - 1);
		
		list[idx] = temp; 
		list[idx - 1] = current; }
	}
	
	/**
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveDown(int idx) throws IndexOutOfBoundsException{
		
		if (size() == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");

		}
		
		if (idx < 0 || idx >= size()){
			throw new IndexOutOfBoundsException("Invalid index.");

		}
		
		if (idx < size() - 1) {
			E current = get(idx);
			E temp = get(idx + 1);
			
			list[idx] = temp; 
			list[idx + 1] = current; 
			}
	}
	/**
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void moveToFront(int idx) throws IndexOutOfBoundsException {
		
		if (size() == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");

		}
		
		if (idx < 0 || idx >= size()){
			throw new IndexOutOfBoundsException("Invalid index.");

		}
		
		E current = remove(idx);
		E[] temp = list; 
		list = (E[]) new Object[capacity];
		add(current);
		
		for (int i = 1; i < size; i++) {
			list[i] = temp[i - 1];
		}
		
		
	}
	
	/**
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToBack(int idx) throws IndexOutOfBoundsException {
		
		if (size() == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");

		}
		
		if (idx < 0 || idx >= size()){
			throw new IndexOutOfBoundsException("Invalid index.");

		}
		
		E current = remove(idx);
		add(current);
		
	}
	
	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E get(int idx) throws IndexOutOfBoundsException {
		if (idx < 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		if (idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");

		}
		return list[idx];		}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

}
