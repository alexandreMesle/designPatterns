package factory;

class Client
{
	public Commande createCommande()
	{
		return new Commande();
	}

	class Commande
	{
		private Commande()
		{}
	}
}

public class Factory
{
	public static void main(String[] args)
	{
		Client c = new Client();
		@SuppressWarnings("unused")
		Client.Commande cm = c.createCommande();
	}
}
