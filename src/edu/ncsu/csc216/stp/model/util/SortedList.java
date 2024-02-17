/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;


/**
 * class for sorted lists 
 * @param <E> type of element the array contains 
 * @author Matthew Williams 
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	
	

	/** front of list node **/
	private ListNode front; 
	
	/** size of array **/
	private int size; 
	
	/**
	 * constructor
	 */
	public SortedList() {
		this.front = null; 
		this.size = 0; 
	}
	

	/**
	 * Adds the element to the list in sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	@Override
	public void add(E element) throws NullPointerException, IllegalArgumentException{
		
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		int c = 0; 
		
		while (c < size()) {
			
			if (element.equals(get(c))) {
				throw new IllegalArgumentException("Cannot add duplicate element.");
			}
			
			c += 1;
		}
		
		ListNode newNode = new ListNode(element);
		newNode.data = element; 
		ListNode current;
		
		if (front == null) {
			front = newNode; 
		}
		
		else if (element.compareTo(front.data) < 0) {
			front = new ListNode (element, front);
		}
		
	
		
		
		else {current = front; 
			while (current.next != null && current.next.data.compareTo(element) < 0) {
				current = current.next; 
			}
			
			current.next = new ListNode(element, current.next);
			}


		size += 1;
		}
		


	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E remove(int idx) throws IndexOutOfBoundsException {
		
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E value = null;
		if (idx == 0) {
			value = front.data;
			front = front.next;
		}
		else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			value = current.next.data;
			current.next = current.next.next; 
		}
		size--;
		return value; 
	}

	/**
	 * Returns true if the element is in the list.
	 * @param element element to search for
	 * @return true if element is found
	 */
	@Override
	public boolean contains(E element) {
		int current = 0; 
		ListNode f = front;
	
		
		while (current != size()) {
			
			if (f.data.equals(element)) {
				return true; 
			}
			f = f.next;  
			
			
			
			current += 1; 
			
		}
		return false;
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
		
		int current = 0; 
		ListNode f = front;
	
		
		while (current != idx) {
			f = f.next; 
			current += 1; 
			
		}
		return f.data;
	}

	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}


	
	/**
	 * class for Listnodes 
	 * @author Matthew Williams 
	 */
	private class ListNode {
		
		/** data of list node **/ 
		public E data; 
		/** data value of next list node **/ 
		public ListNode next;
		
		/**
		 * constructor
		 * @param data of list node 
		 */
		public ListNode(E data) {
			this.data = data;  
		}
		
		/**
		 * constructor
		 * @param date of list node 
		 * @param next data of next list node 
		 */
		public ListNode(E date, ListNode next) {
			this.data = date; 
			this.next = next; 
		}
	}




	


}
