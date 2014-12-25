package decorator;

public abstract class Message
{
	public static void main(String[] args)
	{
		Message message = new BoldMessage(new ItalicMessage(new NakedMessage("Coucou !")));
		System.out.println(message);
	}
}

class NakedMessage extends Message
{
	private String message;
	
	public NakedMessage(String message)
	{
		this.message = message;
	}
	
	@Override
	public String toString()
	{
		return message;
	}
}

abstract class MessageDecorator extends Message
{
	private Message message;
	
	public MessageDecorator(Message message)
	{
		this.message = message;
	}
	
	@Override
	public String toString()
	{
		return message.toString();
	}
}

class BoldMessage extends MessageDecorator
{
	public BoldMessage(Message message)
	{
		super(message);
	}

	@Override
	public String toString()
	{
		return "<B>" + super.toString() + "</B>";
	}
}

class ItalicMessage extends MessageDecorator
{
	public ItalicMessage(Message message)
	{
		super(message);
	}

	@Override
	public String toString()
	{
		return "<I>" + super.toString() + "</I>";
	}
}

class UnderlinedMessage extends MessageDecorator
{
	public UnderlinedMessage(Message message)
	{
		super(message);
	}

	@Override
	public String toString()
	{
		return "<U>" + super.toString() + "</U>";
	}
}
