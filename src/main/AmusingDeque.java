package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The deque228 implementation
 * 
 * @param <E> The type of data to be used in this List.
 * @author Lewis Sheaffer
 */
public class AmusingDeque<E> implements Deque<E> {
	private ArrayList<E> DequeList;

	/**
	 * Constructor for a new Deque object.
	 */
	public AmusingDeque() {
		DequeList = new ArrayList<E>();
	}

	/**
	 * Adds all of the elements in the specified collection to this collection
	 * (optional operation).
	 * 
	 * @param c The collection of elements to be added to this collection.
	 * @return True if this operation is performed as expected.
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return DequeList.addAll(c);
	}

	/**
	 * Removes all of the elements from this collection (optional operation).
	 */
	@Override
	public void clear() {
		DequeList.clear();
	}

	/**
	 * Returns true if this collection contains all of the elements in the specified
	 * collection.
	 * 
	 * @param c The collection of elements for which this method will check to see
	 *          if the deque contains.
	 * @return True if this operation is performed as expected.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return DequeList.containsAll(c);
	}

	/**
	 * Returns true if this collection contains no elements.
	 * 
	 * @return True if this collection contains no elements.
	 */
	@Override
	public boolean isEmpty() {
		return DequeList.isEmpty();
	}

	/**
	 * Removes all of this collection's elements that are also contained in the
	 * specified collection (optional operation).
	 * 
	 * @param c The collection of elements which will all be removed from this
	 *          deque.
	 * @return True if this operation is performed as expected.
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return DequeList.removeAll(c);
	}

	/**
	 * Retains only the elements in this collection that are contained in the
	 * specified collection (optional operation).
	 * 
	 * @param c The collection of elements which will all be retained in this deque.
	 * @return True if this operation is performed as expected.
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return DequeList.retainAll(c);
	}

	/**
	 * Returns an array containing all of the elements in this collection.
	 * 
	 * @return An array containing all of the elements in this collection.
	 */
	@Override
	public Object[] toArray() {
		return DequeList.toArray();
	}

	/**
	 * Returns an array containing all of the elements in this collection; the
	 * runtime type of the returned array is that of the specified array.
	 * 
	 * @param a The array for which the elements of this deque will be returned in.
	 * @return An array containing the elements of this deque.
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return DequeList.toArray(a);
	}

	/**
	 * Inserts the specified element into the queue represented by this deque (in
	 * other words, at the tail of this deque) if it is possible to do so
	 * immediately without violating capacity restrictions, returning true upon
	 * success and throwing an IllegalStateException if no space is currently
	 * available.
	 * 
	 * @param The element to be added to this deque.
	 * @return True if the element e is added successfully to this deque.
	 */
	@Override
	public boolean add(E e) {
		return DequeList.add(e);
	}

	/**
	 * Inserts the specified element at the front of this deque if it is possible to
	 * do so immediately without violating capacity restrictions, throwing an
	 * IllegalStateException if no space is currently available.
	 * 
	 * @param e The elements to be added to the front of this deque.
	 */
	@Override
	public void addFirst(E e) {
		DequeList.add(0, e);
	}

	/**
	 * Inserts the specified element at the end of this deque if it is possible to
	 * do so immediately without violating capacity restrictions, throwing an
	 * IllegalStateException if no space is currently available.
	 * 
	 * @param e The element to be added to the end of this deque.
	 */
	@Override
	public void addLast(E e) {
		DequeList.add(e);
	}

	/**
	 * Returns true if this deque contains the specified element.
	 * 
	 * @param o The Object for which this deque will be checked to see if it
	 *          contains.
	 * @return True if this deque contains the specified object.
	 */
	@Override
	public boolean contains(Object o) {
		return DequeList.contains(o);
	}

	/**
	 * Returns an iterator over the elements in this deque in reverse sequential
	 * order. The elements will be returned in order from last (tail) to first
	 * (head).
	 * 
	 * @return An iterator over the elements in this deque in reverse sequential
	 *         order.
	 */
	@Override
	public Iterator<E> descendingIterator() {
		ArrayList<E> reverseDeque = new ArrayList<E>();
		for(E entry : DequeList) {
			reverseDeque.add(entry);
		}
		return reverseDeque.iterator();
	}

	/**
	 * Retrieves, but does not remove, the head of the queue represented by this
	 * deque (in other words, the first element of this deque).
	 * 
	 * @return The head of the queue represented by this deque.
	 */
	@Override
	public E element() {
		return DequeList.get(0);
	}

	/**
	 * Retrieves, but does not remove, the first element of this deque.
	 * 
	 * @return The first element of this deque.
	 */
	@Override
	public E getFirst() {
		return DequeList.get(0);
	}

	/**
	 * Retrieves, but does not remove, the last element of this deque.
	 * 
	 * @return The last element of this deque.
	 */
	@Override
	public E getLast() {
		return DequeList.get(size() - 1);
	}

	/**
	 * Returns an iterator over the elements in this deque in proper sequence. The
	 * elements will be returned in order from first (head) to last (tail).
	 * 
	 * @return An iterator over the elements in this deque in proper sequence.
	 */
	@Override
	public Iterator<E> iterator() {
		return DequeList.iterator();
	}

	/**
	 * Inserts the specified element into the queue represented by this deque (in
	 * other words, at the tail of this deque) if it is possible to do so
	 * immediately without violating capacity restrictions, returning true upon
	 * success and false if no space is currently available.
	 * 
	 * @param e The element to be inserted into the queue represented by this deque.
	 * @return True upon the success of this operation.
	 */
	@Override
	public boolean offer(E e) {
		return DequeList.add(e);
	}

	/**
	 * Inserts the specified element at the front of this deque unless it would
	 * violate capacity restrictions. When using a capacity-restricted deque, this
	 * method is generally preferable to the addFirst(E) method, which can fail to
	 * insert an element only by throwing an exception.
	 * 
	 * @param e The element to be inserted in the front of this deque.
	 * @return True if this operation is performed successfully. 
	 */
	@Override
	public boolean offerFirst(E e) {
		DequeList.add(0, e);
		return true;
	}

	/**
	 * Inserts the specified element at the end of this deque unless it would
	 * violate capacity restrictions. When using a capacity-restricted deque, this
	 * method is generally preferable to the addLast(E) method, which can fail to
	 * insert an element only by throwing an exception.
	 * 
	 * @param e The element to add
	 * @returns True if the element was added to thid deque
	 * @throws ClassCastException       if the class of the specified element
	 *                                  prevents it from being added to this deque
	 * @throws NullPointerException     if the specified element is null and this
	 *                                  deque does not permit null elements
	 * @throws IllegalArgumentException if some property of the specified element
	 *                                  prevents it from being added to this deque
	 */
	@Override
	public boolean offerLast(E e) {
		return DequeList.add(e);
	}

	/**
	 * Retrieves, but does not remove, the head of the queue represented by this
	 * deque (in other words, the first element of this deque), or returns null if
	 * this deque is empty. This method is equivalent to peekFirst().
	 * 
	 * @return The head of the queue represented by this deque.
	 */
	@Override
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return DequeList.get(0);
	}

	/**
	 * Retrieves, but does not remove, the first element of this deque, or returns
	 * null if this deque is empty.
	 * 
	 * @return the first element of this deque, or null if this deque is empty.
	 */
	@Override
	public E peekFirst() {
		if (isEmpty()) {
			return null;
		}
		return DequeList.get(0);
	}

	/**
	 * Retrieves, but does not remove, the last element of this deque, or returns
	 * null if this deque is empty.
	 * 
	 * @return The last element of this deque, or null if the deque is empty.
	 */
	@Override
	public E peekLast() {
		if (isEmpty()) {
			return null;
		}
		return DequeList.get(size() - 1);
	}

	/**
	 * Retrieves and removes the head of the queue represented by this deque (in
	 * other words, the first element of this deque), or returns null if this deque
	 * is empty. This method is equivalent to pollFirst().
	 * 
	 * @return The head of the queue represented by this deque.
	 */
	@Override
	public E poll() {
		if (isEmpty()) {
			return null;
		}
		return DequeList.remove(0);
	}

	/**
	 * Retrieves and removes the first element of this deque, or returns null if
	 * this deque is empty.
	 * @return The first elements of this deque, or null if the deque is empty.
	 */
	@Override
	public E pollFirst() {
		if (isEmpty()) {
			return null;
		}
		return DequeList.remove(0);
	}

	/**
	 * Retrieves and removes the last element of this deque, or returns null if this
	 * deque is empty.
	 * 
	 * @return The last element of this deque, or null if the deque is empty.
	 */
	@Override
	public E pollLast() {
		if (isEmpty()) {
			return null;
		}
		return DequeList.remove(size() - 1);
	}

	/**
	 * Pops an element from the stack represented by this deque. In other words,
	 * removes and returns the first element of this deque.
	 * 
	 * @return The element popped from the stack represented by this deque.
	 */
	@Override
	public E pop() {
		return DequeList.remove(0);
	}

	/**
	 * Pushes an element onto the stack represented by this deque (in other words,
	 * at the head of this deque).
	 * 
	 * @param e The element to be added onto the stack represented by this deque.
	 */
	@Override
	public void push(E e) {
		DequeList.add(0, e);
	}

	/**
	 * Retrieves and removes the head of the queue represented by this deque (in
	 * other words, the first element of this deque). This method differs from poll
	 * only in that it throws an exception if this deque is empty.
	 * 
	 * @throws NoSuchElementException if this deque is empty.
	 * @return The head of the queue represented by this deque.
	 */
	@Override
	public E remove() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return DequeList.remove(0);
	}

	/**
	 * Removes the first occurrence of the specified element from this deque. If the
	 * deque does not contain the element, it is unchanged. More formally, removes
	 * the first element e such that (o==null ? e==null : o.equals(e)) (if such an
	 * element exists). Returns true if this deque contained the specified element
	 * (or equivalently, if this deque changed as a result of the call).
	 * 
	 * @param o The object to be removed from this deque.
	 * @return true if this deque contained the specified element
	 */
	@Override
	public boolean remove(Object o) {
		return remove(o);
	}

	/**
	 * Retrieves and removes the first element of this deque. This method differs
	 * from pollFirst only in that it throws an exception if this deque is empty.
	 * 
	 * @return The first element of this deque.
	 * @throws NoSuchElementException if this deque is empty.
	 */
	@Override
	public E removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return DequeList.remove(0);
	}

	/**
	 * Removes the first occurrence of the specified element from this deque. If the
	 * deque does not contain the element, it is unchanged. More formally, removes
	 * the first element e such that (o==null ? e==null : o.equals(e)) (if such an
	 * element exists). Returns true if this deque contained the specified element
	 * (or equivalently, if this deque changed as a result of the call).
	 * 
	 * @param o The object to be removed from this list.
	 * @return True if this deque is changed as a result of the call.
	 */
	@Override
	public boolean removeFirstOccurrence(Object o) {
		return DequeList.remove(o);
	}

	/**
	 * Retrieves and removes the last element of this deque. This method differs
	 * from pollLast only in that it throws an exception if this deque is empty.
	 * 
	 * @throws NoSuchElementException if this deque is empty
	 * @return The last element of this deque.
	 */
	@Override
	public E removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return DequeList.remove(size() - 1);
	}

	/**
	 * Removes the last occurrence of the specified element from this deque. If the
	 * deque does not contain the element, it is unchanged. More formally, removes
	 * the last element e such that (o==null ? e==null : o.equals(e)) (if such an
	 * element exists). Returns true if this deque contained the specified element
	 * (or equivalently, if this deque changed as a result of the call).
	 * 
	 * @param o The object for which the last occurrence will be removed from this
	 *          list.
	 * @return True if this deque changed as a result of the call.
	 */
	@Override
	public boolean removeLastOccurrence(Object o) {
		int tempSize = size();
		DequeList.remove(DequeList.lastIndexOf(o));
		return ((size() - tempSize) != 0);
	}

	/**
	 * Returns the number of elements in this deque.
	 * 
	 * @return The number of elements in this deque.
	 */
	@Override
	public int size() {
		return DequeList.size();
	}

}
