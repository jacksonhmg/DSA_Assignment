/* TAKEN FROM PRACTICAL 6 WORK, ORIGINALLY FROM PRACTICAL 4 */


import java.util.*;

public class DSAStack implements Iterable
{
	private DSALinkedList list = new DSALinkedList();
	Iterator iList;
	
	public Iterator iterator()
	{
		return list.iterator();
	}

	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public void push(Object value)
	{
		list.insertFirst(value);
	}

	public Object pop()
	{
		return list.removeFirst();
	}

	public Object top()
	{
		return list.peekFirst();
	}


	public void display() {
		/* Function to display status of Circular Queue */
		iList = list.iterator();
		while(iList.hasNext() != false){
			System.out.println(iList.next());
		}
	  }



}
