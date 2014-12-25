package observer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import iterator.TableauCreux;

interface IndexObserver extends Observer
{
	public void notify(int index);
}

class TableauxCreuxObserver<T> extends TableauCreux<T> implements Observed<IndexObserver>, IndexObserver
{
	private Set<IndexObserver> observers = new HashSet<>();
	
	public TableauxCreuxObserver(int size)
	{
		super(size);
	}

	public void notifyObserver(int index)
	{
		for(IndexObserver observer : observers)
			observer.notify(index);
	}

	@Override
	public void set(int i, T item) 
	{
		super.set(i, item);
		notifyObserver(i);
	};
	
	@Override
	public void notify(int index)
	{
		System.out.println("Value (index = " + index + " has changed to " + get(index));
	}
	
	@Override
	public void add(IndexObserver observer)
	{
		this.observers.add(observer);
	}

	@Override
	public void remove(IndexObserver observer)
	{
		this.observers.remove(observer);
	}
}

public class SurveillanceTableauCreux
{
	public static void main(String[] args)
	{
		final int SIZE = 10;
		TableauxCreuxObserver<Integer> tco = new TableauxCreuxObserver<Integer>(SIZE);
		tco.add(tco);
		Random r = new Random();
		for (int i = 1 ; i < SIZE ; i++)
			tco.set(Math.abs(r.nextInt())%SIZE, r.nextInt());
	}
}
