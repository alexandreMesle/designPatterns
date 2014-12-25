package iterator;

import java.util.Iterator;
import java.util.Vector;

public class TableauCreux<T> implements Iterable<T>
{
	private Vector<T> tableauCreux;
	
	public TableauCreux(int size)
	{
		tableauCreux = new Vector<>(size);
		for (int i = 0 ; i < size ; i++)
			tableauCreux.add(null);
	}

	public T get(int i)
	{
		return tableauCreux.get(i);
	}
	
	public void set(int i, T item)
	{
		tableauCreux.set(i, item);
	}

	@Override
	public java.util.Iterator<T> iterator()
	{
		return new TableauCreuxIterator(tableauCreux.iterator());
	}

	private class TableauCreuxIterator implements Iterator<T>
	{
		private java.util.Iterator<T> iterator;
		private T item = null;
		
		TableauCreuxIterator (Iterator<T> iterator)
		{
			this.iterator = iterator;
		}
		
		@Override
		public boolean hasNext()
		{
			while (item == null && iterator.hasNext())
				item = iterator.next();
			return item != null;
		}

		@Override
		public T next()
		{
			T result = item;
			item = null;
			return result;
		}

		@Override
		public void remove()
		{
			iterator.remove();
		}
	}
	
	@Override
	public String toString()
	{
		String res = "[ ";
		for (T item : this)
			res += item + " ";
		return res + "]";
	}
	
	public static void main(String[] args)
	{
		TableauCreux<Integer> tc = new TableauCreux<>(10);
		tc.set(6, 5);
		tc.set(2, 90);
		System.out.println(tc);
	}
}
