import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException;

/**
 * VectorIterator Class
 *
 * @author 	Jessica Li
 * @version 1 - 11/02/15
 */
public class VectorIterator<E> implements Iterator<E>
{
	private Vector<E> vector;
	private int index;

	/**
	 * Constructor for VectorIterator.
	 * 
	 * @param v 	vector that class field vector is set to
	 */
	public VectorIterator( Vector<E> v )
	{
		vector = v;
		index = 0;
	}

	/**
	 * Returns whether or not the index has reached the end of the vector. 
	 *
	 * @return		true if index is less than size, otherwise false
	 */
	public boolean hasNext()
	{
		if ( index < vector.size() )
			return true;
		else
			return false;
	}

	/**
	 * Returns the next element in the class field vector. If hasNext() returns false, 
	 * throws exception, as there are no more elements left to return. 
	 * Increments index. 
	 *
	 * @return 		next element in vector
	 */
	public E next()
	{
		if ( !hasNext() )
			throw new NoSuchElementException( "There are no more elements left in the vector." );
		E store = vector.get( index );
		index++;
		return store;
	}

	/**
	 * Not sure what this remove() method does. 
	 */
	public void remove()
	{
		//what does this do???
	}

}