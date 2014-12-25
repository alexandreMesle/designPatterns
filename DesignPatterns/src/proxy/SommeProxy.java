package proxy;

class Somme
{
	private long n, sum = 0;
	private boolean done = false;
	
	public Somme(long n)
	{
		this.n = n;
	}
	
	public long getSomme()
	{
		if (!done)
			for (long i = 1 ; i <= n ; i++)
				sum += i;
		done = true;
		return sum;
	}
}


public class SommeProxy
{
	public static void main(String[] args)
	{
		Somme s = new Somme(1000000000), t = new Somme(500000000);
		System.out.println(s.getSomme());
		System.out.println(t.getSomme());
		System.out.println(s.getSomme());
		System.out.println(t.getSomme());
	}

}
