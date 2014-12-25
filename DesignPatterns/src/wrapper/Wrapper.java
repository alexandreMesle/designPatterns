package wrapper;

import java.util.Iterator;
import java.util.Vector;

class Matrice<T extends Anneau<T>> implements Anneau<Matrice<T>>, Iterable<T>
{
	private Vector<Vector<T>> matrice;
	private int nbL, nbC;
	
	public Matrice(int nbL, int nbC, T init)
	{
		this.nbL = nbL;
		this.nbC = nbC;
		matrice = new Vector<Vector<T>>(nbL);
		for(int i = 0 ; i < nbL ; i++)
		{
			matrice.add(new Vector<T>(nbC));
			for (int j = 0 ; j < nbC ; j++)
				matrice.get(i).add(init);
		}
	}
	
	public Matrice(int nbL, int nbC)
	{
		this(nbL, nbC, null);
	}
	
	public T get(int i, int j)
	{
		return matrice.get(i).get(j);
	}
	
	public void set(int i, int j, T item)
	{
		if (item != null)
			matrice.get(i).set(j, item);
		else
			set(i, j, get(0, 0).zero());
	}

	@Override
	public Matrice<T> add(Matrice<T> other)
	{
		if (nbC != other.nbC && nbL != other.nbL)
			throw new java.lang.IllegalArgumentException();
		Matrice<T> result = new Matrice<T>(nbL, other.nbC, null);
		for(int i = 0 ; i < nbL ; i++)
			for(int j = 0 ; j < nbC ; j++)
				result.set(i, j, get(i, j).add(other.get(i, j)));
		return result;
	}

	@Override
	public Matrice<T> mul(Matrice<T> other)
	{
		if (nbC != other.nbL)
			throw new java.lang.IllegalArgumentException();
		T zero = get(0, 0).zero();
		Matrice<T> result = new Matrice<T>(nbL, other.nbC, zero);
		for(int i = 0 ; i < nbL ; i++)
			for(int j = 0 ; j < other.nbC ; j++)
				for(int k = 0 ; k < nbC ; k++)
					result.set(i, j, result.get(i, j).
							add(get(i, k).mul(other.get(k, j))));
		return result;
	}

	@Override
	public Matrice<T> zero()
	{
		return new Matrice<T>(nbL, nbC, get(0, 0).zero());
	}

	@Override
	public Matrice<T> un()
	{
		Matrice<T> un = new Matrice<T>(nbL, nbL, get(0, 0).zero());
		for (int i = 0 ; i < nbL ; i++)
			un.set(i, i, get(0, 0).un());
		return un;
	}
	
	@Override
	public String toString()
	{
		String res = "";
		for(Vector<T> vector : matrice)
		{
			res += "[ ";
			for(T item : vector)
				res += item + " ";
			res += "]\n";
		}
		return res;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new IterateurMatrice<T>(matrice.iterator());
	}
}

public class Wrapper
{
	public static void main(String[] args)
	{
		Matrice<IntegerTraffique> 
			m = new Matrice<IntegerTraffique>(2, 2, new IntegerTraffique(2)), 
			un = m.un(), zero = m.zero(); 
		System.out.println(m);
		System.out.println(un);
		System.out.println(zero);
		System.out.println(m.add(un));
		System.out.println(m.mul(m));
	}
}
