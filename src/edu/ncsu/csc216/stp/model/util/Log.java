/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * provides basic method for custom array list 

 * @param <E> type of element added in list 
 * @author Matthew Williams
 */
public class Log<E> implements ILog<E> {
	
	/** array list of type e**/

	private E[] log; 
	
	/** size of array **/ 
	private int size; 
	
	
	
	
	/** initial capacity of list **/  
	private static final int INIT_CAPACITY = 10; 
	
	/** capacity of current array **/
	private int capacity; 
	
	
	/**
	 * constructor
	 */
	@SuppressWarnings("unchecked")
	public Log() {
		this.size = 0; 
		this.capacity = INIT_CAPACITY;
		this.log = (E[]) new Object[INIT_CAPACITY];
	}
	
	
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(E element) throws NullPointerException{
		
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		 
		size += 1; 

		
		if (size >= capacity) {
			capacity *= 2; 
			E[] temp = log; 
			log = (E[]) new Object[capacity];
			
			for (int i = 0; i < size(); i++) {
				log[i] = temp[i];
			}
			
			
			
		}
			
		
		
		for (int i = 0; i < size; i++) {
			if (log[i] == null) {
				log[i] = element;  
				break; 
			}
			}	
		
		
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
		
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		return log[idx];		
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
		
	}
	

}
