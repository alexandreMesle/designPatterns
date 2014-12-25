package observer;

import java.util.HashSet;
import java.util.Set;

interface Sommable<U>
{
	public U getZero();
	public U add(U item);
	public U sub(U item);
}

interface Valuable<U> extends Observed<ValueObserver<U>>
{
	public U getValue();
}

interface ValueObserver<U> extends Observer
{
	public void valueChanged(U oldValue, U newValue);
}

class Sum<T extends Valuable<U>, U extends Sommable<U>> implements Valuable<U>, ValueObserver<U>
{
	private U sum = null;
	private Set<ValueObserver<U>> observers = new HashSet<>();
	private Set<T> mySet = new HashSet<T>(); 
	
	public void put(T item)
	{
		mySet.add(item);
		item.add(this);
		item.add(new TestValueObserver<U>("put"));
		U oldValue = null;
		if (sum == null)
		{
			sum = item.getValue();
			oldValue = item.getValue().getZero();
		}
		else
		{
			oldValue = sum;
			sum = sum.add(item.getValue());
		}
		notifyObserver(oldValue, sum);
	}
	
	public void remove(T item)
	{
		if (mySet.remove(item))
		{
			item.remove(this);
			U oldValue = sum;
			sum = sum.sub(item.getValue());
			notifyObserver(oldValue, sum);

		}
	}

	public U getSum()
	{
		return sum;
	}

	@Override
	public U getValue()
	{
		return getSum();
	}
	
	@Override
	public void add(ValueObserver<U> observer)
	{
		observers.add(observer);
	}

	@Override
	public void remove(ValueObserver<U> observer)
	{
		observers.remove(observer);
	}

	@Override
	public void valueChanged(U oldValue, U newValue)
	{
		U formerValue = sum;
		sum = sum.add(newValue.sub(oldValue));
		notifyObserver(formerValue, sum);
	}

	public void notifyObserver(U oldValue, U newValue)
	{
		for (ValueObserver<U> observer : observers)
			observer.valueChanged(oldValue, newValue);
	}
	
	@Override
	public String toString()
	{
		return "" + getValue() + "(" + mySet + ")";
	}
}

class IntSommable implements Valuable<IntSommable>, Sommable<IntSommable>
{
	private int value = 0;
	private Set<ValueObserver<IntSommable>> observers = new HashSet<>();
	
	public IntSommable(int value)
	{
		setValue(value);
	}
	
	public void setValue(int value)
	{
		int oldValue = this.value;
		this.value = value;
		notifyObserver(oldValue);
	}
	
	@Override
	public void add(ValueObserver<IntSommable> observer)
	{
		observers.add(observer);
	}

	@Override
	public void remove(ValueObserver<IntSommable> observer)
	{
		observers.remove(observer);
	}

	private void notifyObserver(int oldValue)
	{
		for (ValueObserver<IntSommable> observer : observers)
			observer.valueChanged(new IntSommable(oldValue), this);
	}
	
	@Override
	public IntSommable getZero()
	{
		return new IntSommable(0);
	}

	@Override
	public IntSommable add(IntSommable item)
	{
		return new IntSommable(value + item.toInt());
	}

	@Override
	public IntSommable sub(IntSommable item)
	{
		return new IntSommable(value - item.toInt());
	}

	@Override
	public IntSommable getValue()
	{
		return this;
	}
	
	public int toInt()
	{
		return value;
	}

	@Override
	public String toString()
	{
		return "" + toInt();
	}
}

public class SommeTableau
{
	public static void main(String[] args)
	{
//		Sum<Sum<IntSommable, IntSommable>, IntSommable> sumOfSums = new Sum<>();
//		sumOfSums.add(new TestValueObserver<IntSommable>("sos"));
		for (int i = 1 ; i < 2 ; i++)
		{
			int total = 0;
			Sum<IntSommable, IntSommable> sum = new Sum<>();
			sum.add(new TestValueObserver<IntSommable>("sum"));
			System.out.println(total);
			System.out.println(sum);
			for (int j = 0 ; j < 5; j++)
			{
				IntSommable is = new IntSommable(j);
				is.add(new TestValueObserver<IntSommable>("is"));
				total += j;
				sum.put(is);
				is.setValue(j - 1);
				total -= 1;
				System.out.println("%%%%%%%%%");
				System.out.println(total);
				System.out.println(sum);
				System.out.println("%%%%%%%%%");
			}
			System.out.println(total);
			System.out.println(sum);
//			System.out.println(sumOfSums);
//			sumOfSums.put(sum);
//			System.out.println(sumOfSums);
		}
//		System.out.println(total + " = " + sumOfSums.getSum().toInt());
	}
}

class TestValueObserver<U> implements ValueObserver<U>
{
	private String nom;
	
	public TestValueObserver(String nom)
	{
		this.nom = nom;
	}
	
	@Override
	public void valueChanged(U oldValue, U newValue)
	{
		System.out.println("[" + nom + "] value has changed to " + oldValue + " to " + newValue);
	}
}
