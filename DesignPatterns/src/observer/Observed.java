package observer;

public interface Observed<T extends Observer>
{
	public void add(T observer);
	public void remove(T Observer);
}
