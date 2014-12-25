package decorator;

abstract class AbstractGlace
{
	private AbstractGlace glace = null;
	
	public AbstractGlace(AbstractGlace glace)
	{
		this.glace = glace;
	}

	public abstract double getPrix();
	public abstract String getLib();
	
	public double getAddition()
	{
		return getPrix() + ((glace != null) ? glace.getAddition() : 0);
	}

	@Override
	public String toString()
	{
		return ((glace != null) ? glace + "\n": "") + 
				"* " + getLib() + " : " + getPrix();
	}
}

class BouleGlace extends AbstractGlace
{
	public enum Parfum {CHOCOLAT, VANILLE, FRAISE};
	private int nbBoules;
	private Parfum parfum;
	
	public BouleGlace(AbstractGlace glace, int nbBoules, Parfum parfum)
	{
		super(glace);
		this.nbBoules = nbBoules;
		this.parfum = parfum;
	}

	@Override
	public double getPrix()
	{
		return nbBoules;
	}
	
	@Override
	public String getLib()
	{
		return nbBoules + " boule(s) parfum " + parfum;
	}
}

class Chantilly extends AbstractGlace
{

	public Chantilly(AbstractGlace glace)
	{
		super(glace);
	}

	@Override
	public double getPrix()
	{
		return 0.5;
	}
	
	@Override
	public String getLib()
	{
		return "Chantilly";
	}
}

class Nappage extends AbstractGlace
{
	private Parfum parfum;
	public enum Parfum {CHOCOLAT, CARAMEL};
	
	public Nappage(AbstractGlace glace, Parfum parfum)
	{
		super(glace);
		this.parfum = parfum;
	}
	
	@Override
	public double getPrix()
	{
		return 0.75;
	}
	
	@Override
	public String getLib()
	{
		return "Nappage " + parfum;
	}
}

public class Glace
{
	public static void main(String[] args)
	{
		AbstractGlace glace = new Nappage (new Chantilly(
				new BouleGlace(new BouleGlace(null, 1, BouleGlace.Parfum.CHOCOLAT), 2, BouleGlace.Parfum.VANILLE))
			, Nappage.Parfum.CARAMEL);
		System.out.println(glace);
		System.out.println("total : " + glace.getAddition());
	}
}
