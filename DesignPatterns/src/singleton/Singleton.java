package singleton;

public class Singleton
{
	private static Singleton singleton;
	
	private Singleton()
	{}
	
	public static Singleton getSingleton()
	{
		if(singleton == null)
			singleton = new Singleton();
		return singleton;		
	}
	
	public static void main(String[] args)
	{
		Singleton s1 = getSingleton(), s2 = getSingleton();
		System.out.println(s1 == s2);
	}

}
