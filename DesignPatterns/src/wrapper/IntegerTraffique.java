package wrapper;

interface Anneau<T>
{
	public T zero();
	public T un();
	public T add(T other);
	public T mul(T other);
}

public class IntegerTraffique implements Anneau<IntegerTraffique>
{
	private Integer integer;
	
	public IntegerTraffique(Integer integer)
	{
		this.integer = integer;
	}
	
	public Integer get()
	{
		return integer;
	}
	
	@Override
	public IntegerTraffique zero()
	{
		return new IntegerTraffique(0);
	}

	@Override
	public IntegerTraffique un()
	{
		return new IntegerTraffique(1);
	}

	@Override
	public IntegerTraffique add(IntegerTraffique other)
	{
		return new IntegerTraffique(get() + other.get());
	}

	@Override
	public IntegerTraffique mul(IntegerTraffique other)
	{
		return new IntegerTraffique(get() * other.get());
	}
	
	@Override
	public String toString()
	{
		return integer.toString();
	}
}

