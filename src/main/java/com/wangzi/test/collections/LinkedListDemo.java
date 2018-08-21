package com.wangzi.test.collections;

import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedListDemo<E> 
	extends AbstractSequentialList<E> 
	implements List<E>, Deque<E>, Cloneable, java.io.Serializable {

	private static final long serialVersionUID = -7078306271919550054L;
	
	transient int size = 0;

    /**
     * Pointer to first node.
     * Invariant: (first == null && last == null) ||
     *            (first.prev == null && first.item != null)
     */
    transient Node<E> first;

    /**
     * Pointer to last node.
     * Invariant: (first == null && last == null) ||
     *            (last.next == null && last.item != null)
     */
    transient Node<E> last;

    /**
     * Constructs an empty list.
     */
    public LinkedListDemo() {
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param  c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public LinkedListDemo(Collection<? extends E> c) {
        this();
        addAll(c);
    }
    
    /**
     * Links e as first element.
     */
    private void linkFirst(E e) {
    	final Node<E> f= first;
    	final Node<E> newNode = new Node<>(null, e, f);
    	first = newNode;
    	if (f == null)
    		last = newNode;
    	else
    		f.prev = newNode;
    	size++;
    	modCount++;
    }
    
    /**
     * Links e as last element.
     */
    void linkLast(E e) {
    	final Node<E> l = last;
    	final Node<E> newNode = new Node<>(l, e, null);
    	last = newNode;
    	if(l == null)
    		last = newNode;
    	else
    		l.next = newNode;
    	size++;
    	modCount++;
    }
    
    /**
     * Unlinks non-null first node f.
     */
    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        modCount++;
        return element;
    }
    /**
     * Unlinks non-null last node l.
     */
    private E unlinkLast(Node<E> l){
    	final E element = l.item;
    	final Node<E> next = l.next;
    	l.item = null;
    	l.next = null; // help GC
    	last = next;
    	if(last == null)
    		first = null;
    	else
    		next.prev = null;
    	size--;
    	modCount++;
    	return element;
    }
    private static class Node<E>{
    	E item;
    	Node<E> next;
    	Node<E> prev;
    	Node(Node<E> prev, E element, Node<E> next) {
    		this.item = element;
    		this.next = next;
    		this.prev = prev;
    	}
    }
	@Override
	public void addFirst(E e) {
		
		
	}

	@Override
	public void addLast(E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean offerFirst(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerLast(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peekFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peekLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void push(E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
