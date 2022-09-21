import java.util.*;
public class DSAQueue implements Iterable
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

	public void enqueue(Object value)
	{
		list.insertLast(value);
	}

	public Object dequeue()
	{
		return list.removeFirst();
	}

	public Object peek()
	{
		return list.peekFirst();
	}
}
