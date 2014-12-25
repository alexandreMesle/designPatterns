package wrapper;

import java.util.Iterator;
import java.util.Vector;

public class IterateurMatrice<T> implements Iterator<T>
{
		private Iterator<Vector<T>> lines = null;
		private Iterator<T> line = null;
			
		public IterateurMatrice(Iterator<Vector<T>> iterator)
		{
			lines = iterator;
		}
		
		@Override
		public boolean hasNext()
		{
			while(line == null || !line.hasNext() && lines.hasNext())
				line = lines.next().iterator();
			return line.hasNext();
		}

		@Override
		public T next()
		{
			return line.next();
		}

		@Override
		public void remove()
		{
			line.remove();
		}

		public static void main(String[] args)
		{
			Matrice<IntegerTraffique> m = 
				new Matrice<IntegerTraffique>(2, 2, new IntegerTraffique(2));
			for (IntegerTraffique i : m.add(m.un()))
				System.out.print(i + " ");
		}
}
