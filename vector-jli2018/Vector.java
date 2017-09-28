import java.util.Iterator;
import java.lang.Iterable;

/**
 * Vector Class
 *
 * @author 	Jessica Li
 * @version 1 - 11/02/15
 */
public class Vector<E> implements Iterable<E>
{
	private Object[] data;
	private int capacity;
	private int size;

	/**
	 * Default constructor
	 * Calls capacity constructor with capacity 10
	 */
	public Vector()
	{
		this( 10 );
	}

	/**
	 * Constructor that takes in capacity. Throws exception if capacity is not greater than 0.
	 *
	 * @param initCapacity		the length of the array data
	 */
	public Vector( int initCapacity )
	{
		if ( initCapacity <= 0)
			throw new IllegalArgumentException( "Capacity must be greater than 0. ");

		capacity = initCapacity;
		data = new Object[capacity];
		size = 0;
	}

	/**
	 * Copy constructor. Throws exception if parameter is null.
	 * Creates new array and copies data from other Vector into this one. 
	 *
	 * @param other		the Vector from which data is to be copied
	 */
	public Vector( Vector<E> other )
	{
		if ( other == null )
			throw new IllegalArgumentException( "The vector parameter must not be null. " );

		size = other.size;
		capacity = other.capacity;
		data = new Object[capacity];
		for ( int i = 0; i < other.size(); i++ )
		{
			data[i] = other.get(i); 						//could use other.data[i];
		}
	}

	/**
	 * Adds to the end of the data array. 
	 * Calls the index add with index size.
	 *
	 * @param toAdd		the element to be added to the array data
	 */
	public void add( E toAdd )
	{
		add( size, toAdd );
	}

	/**
	 * Adds toAdd to index and shifts following elements down one spot.
	 * Throws exception if the index given in greater than size or less than 0. 
	 * Uses backwards loop to shift elements down. Calls increaseCapacity() if size of array equals capacity. 
	 *
	 * @param index 	the index where toAdd will be placed
	 * @param toAdd		the element to be added to the array data
	 */
	public void add(int index, E toAdd)
	{
		if ( index > size || index < 0 )
			throw new IndexOutOfBoundsException( "The index must be less than or equal to size and greater than 0. ");

		for ( int i = size; i > index; i-- )
		{
			data[i] = data[i-1]; 
		}

		data[index] = toAdd;
		size++;

		if ( size == capacity )
			increaseCapacity();

	}

	/**
	 * Doubles the capacity of the vector. Creates a new array of double capacity but same size,
	 * copies elements into new array, sets arrays equal.
	 *
	 */
	private void increaseCapacity()
	{
		//related to add(), once size/capacity reaches certain fill, increase
		capacity *= 2;
		Object[] data2 = new Object[capacity]; 
		for ( int i = 0; i < size; i++ )
		{
			data2[i] = data[i];
		}

		data = data2;

	}

	/**
	 * Returns item at specified index. 
	 * Thows exception if index is greater than or equal to size or if index is less than 0.
	 * 
	 * @param index 	the index of the element to be returned
	 * @return 			the element at index
	 */
	@SuppressWarnings("unchecked")
	public E get(int index)
	{
		//used by copy constructor
		if ( index >= size || index < 0 )
			throw new IndexOutOfBoundsException( "The index must be less than size and greater than 0. ");

		return (E) data[index];
	}

	/**
	 * Removes and returns item at specified index, shifts following items down one spot.
	 * Thows exception if index is greater than or equal to size or if index is less than 0.
	 * Stores the element to be removed, uses loop to shift elements down, returns stored element. 
	 *
	 * @param index 	the index of the element to be removed
	 * @return 			the removed element 
	 */
	@SuppressWarnings("unchecked")
	public E remove(int index)
	{
		//used by obj remove()

		if ( index >= size || index < 0 )
			throw new IndexOutOfBoundsException( "The index must be less than size and greater than 0. ");

		E store = (E) data[index];
		size--;
		for ( int i = index; i < size; i++ )
		{
			data[i] = data[i+1];
		}

		return store;
	}

	/**
	 * Removes first instance of specified object.
	 * Uses contains() to check of object exists in array. If it does, calls previous remove method
	 * with index of the obj, which is found by calling indexOf().
	 *
	 * @param obj 		the element to be located and removed
	 * @return 			true if obj is found and removed, false if obj is not found
	 *
	 */
	public boolean remove(E obj)
	{
		//related to contains(obj): false - return false
		//related to indexOf(obj): use to find index of object
		//related to previous remove: find index of obj^ and call remove^^

		if ( !contains(obj) )
			return false;
		else
		{
			remove( indexOf(obj) );
			return true;
		}

	}

	/**
	 * Replaces object at specified location.
	 * Thows exception if index is greater than or equal to size or if index is less than 0.
	 * Stores the element, sets spot to obj, returns stored element. 
	 *
	 * @param index 	the index of the element to be changed
	 * @param obj 		the value that will replace the current element
	 * @return 			the replaced element
	 */
	@SuppressWarnings("unchecked")
	public E set(int index, E obj)
	{
		if ( index >= size || index < 0 )
			throw new IndexOutOfBoundsException( "The index must be less than size and greater than 0. ");

		E store = (E) data[index];
		data[index] = obj;
		return store;
	}

	/**
	 * Returns the number of items in the vector.
	 *
	 * @return 		the size of the vector
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Returns the capacity of the vector.
	 *
	 * @return 		the capacity of the vector
	 */
	public int capacity()
	{
		return capacity;
	}


	/**
	 * Removes all items from the vector.
	 * Sets data to a new array of the same capacity, sets size to 0. 
	 *
	 *
	 */
	public void clear()
	{
		//does this change the size to 0 or set all to null?
		data = new Object[capacity];
		size = 0;
	}

	/**
	 * Returns whether or not the vector is empty
	 * Uses if statement to check is size is 0. If so, returns true. If else, returns false. 
	 *
	 *
	 * @return 		whether the vector is empty or not
	 */
	public boolean isEmpty()
	{
		if ( size == 0 )
			return true;
		return false;
	}

	/**
	 * Returns whether or not the vector contains specified object.
	 * Calls indexOf() to find if vector contains obj or not. 
	 *
	 * @param obj 		the object to be found
	 * @return 			whether data contains obj or not
	 */
	public boolean contains(E obj)
	{
		//related to remove(obj)
		//uses indexOf

		if ( indexOf(obj) == -1 )
			return false;
		return true;
	}

	/**
	 * Returns index of the first instance of specified object. Returns -1 is object is not found. 
	 * For loop traverses array. If either the element or obj is null, checks equality using ==. 
	 * Otherwise, checks equality using .equals(). 
	 *
	 * @param obj 		the element to be located
	 * @return 			the index of obj. -1 if obj is not found. 
	 */
	public int indexOf(E obj)
	{
		//related to remove(obj)

		//can also do two for loops, one for null and one not
		for ( int i = 0; i < size; i++ )
		{
			if ( data[i] == null || obj == null)
			{
				if ( data[i] == obj )
					return i;
			}

			else if ( data[i].equals(obj) )
				return i;
		}
		return -1;

	}


	/**
	 * Returns a string representation of the Vector. 
	 * Stores elements in array to String toReturn using for loop. Stores nulls as well. 
	 *
	 * @return 		a string containing a representation of the information in data
	 */
	public String toString() 
	{
		String toReturn = "";
		for ( int i = 0; i < size; i++ )
		{
			if ( data[i] == null )
				toReturn += "null" + "\t";
			else
				toReturn += data[i].toString() + "\t";
		}

		return toReturn;
	}


	/**
	 * Creates and returns new VectorIterator created using this vector.
	 * 
	 * @return 		new VectorIterator created with this Vector
	 */
	public Iterator<E> iterator()
	{
		return new VectorIterator<E>( this );
	}


}


/* Checks: 
	-adds null •
	-removes null •
	-indexoutofbounds exceptions •
	-adding of wrong type •
	-copy constructor - does it create a completely new Vector? Null param? •
*/