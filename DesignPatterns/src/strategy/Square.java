package strategy;

public abstract class Square
{
	public enum Algo {DIRECT, SUM};
	
	public abstract long square(long n);
	
	public static Square getSquare(Algo algo)
	{
		switch (algo)
		{
			case DIRECT : return new DirectSquare();
			case SUM : return new SumSquare();
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		Square direct = getSquare(Algo.DIRECT), sum = getSquare(Algo.SUM);
		for (int i = 1 ; i <= 10 ; i++)
			System.out.println(direct.square(i) + " == " + sum.square(i));
	}
}

class DirectSquare extends Square
{
	@Override
	public long square(long n)
	{
		return n * n;
	}
}

class SumSquare extends Square
{
	@Override
	public long square(long n)
	{
		if (n == 0)
			return 0;
		else
			return 2*n - 1 + square(n - 1);
	}
}