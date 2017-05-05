package textgen;

import java.util.AbstractList;
import java.util.Collection;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size=0;
		head=new LLNode<E>(null);
		tail=new LLNode<E>(null);
		setHeadTail();
	}
	
	public MyLinkedList(E element){
		this();
		add(element);
	}
	
	public MyLinkedList(Collection<E> c){
		this();
		for(E element : c) add(element);
	}	

	private void setHeadTail(){
		head.setNext(tail);
		tail.setPrev(head);
	}
	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) throws NullPointerException
	{
		if(element.equals(null)){throw new NullPointerException("Cannot add null pointers.");}
		LLNode<E> newLast=new LLNode<E>(element);
		LLNode<E> oldLast = tail.getPrev();
		oldLast.setNext(newLast);
		newLast.setPrev(oldLast);
		newLast.setNext(tail);
		tail.setPrev(newLast);
		size++;
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		if(index>(size-1) || index<0) throw new IndexOutOfBoundsException("index "+index+" is greater than size "+this.size);

		return getNode(index).getData();
	}
	
	private LLNode<E> countFromFront(int index){
		LLNode<E> result=head;
		for(int count=0;count<=index;count++){
			result=result.getNext();
		}
		return result;
	}

	private LLNode<E> countFromBack(int index){
		LLNode<E> result=tail;
		for(int count=size-1;count>=index;count--){
			result=result.getPrev();
		}
		return result;
	}
	

	private LLNode<E> getNode(int index){
		LLNode<E> temp=null;
		if(index<(size-index)){
			temp=countFromFront(index);
		}else{temp=countFromBack(index);}
		
		return temp;
		
	}
	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(element.equals(null)){throw new NullPointerException("Cannot add null pointers.");}
		if(index>(size-1) || index<0) throw new IndexOutOfBoundsException("index "+index+" is greater than size "+this.size);
		LLNode<E> oldIndex=this.getNode(index);
		LLNode<E> newIndex=new LLNode<E>(element);
		newIndex.setPrev(oldIndex.getPrev());
		newIndex.setNext(oldIndex);
		oldIndex.setPrev(newIndex);
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(index>(size-1) || index<0) throw new IndexOutOfBoundsException("index "+index+" is greater than size "+this.size);
		LLNode<E> oldIndex=this.getNode(index);
		LLNode<E> oldNext=oldIndex.getNext();
		LLNode<E> oldPrev=oldIndex.getPrev();
		oldNext.setPrev(oldPrev);
		oldPrev.setNext(oldNext);
		size--;
		return oldIndex.getData();
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(element.equals(null)){throw new NullPointerException("Cannot add null pointers.");}
		if(index>(size-1) || index<0) throw new IndexOutOfBoundsException("index "+index+" is greater than size "+this.size);
		getNode(index).setData(element);
		
		return null;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	
	// E.g. you might want to add another constructor
	public void setData(E e){
		this.data=e;
	}
	
	public E getData(){
		return this.data;
	}
	
	public LLNode<E> getPrev() {
		return prev;
	}


	public void setPrev(LLNode<E> prev) {
		this.prev = prev;
	}


	public LLNode<E> getNext() {
		return next;
	}


	public void setNext(LLNode<E> next) {
		this.next = next;
	}


	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
