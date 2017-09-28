/**
 * Runner for the Vector class. 
 *
 * @author Jessica Li
 * @version 1 - 11/02/15
 */ 
public class VectorRunner
{
	/**
	 * Tests the many methods of the Vector class (and VectorIterator). 
	 * 
	 * 
	 * @param args 		main method parameter
	 */
	@SuppressWarnings("unchecked")
	public static void main( String[] args )
	{
		Vector<String> v = new Vector<String>();

		v.add( "This" );
		v.add( "is" );
		v.add( "my" );
		v.add( "Vector" );
		v.add( "program." );
		//v.add( new Integer(9) );

		System.out.println( v );

		v.add( 2, "not" );
		//v.add( -5, "nott" );
		//v.add( 100, "nott" );
		//v.add( new Integer( 3 ) );

		System.out.println( v );

		v.add( null );
		System.out.println( v );

		v.remove( "not" );
		System.out.println( v );

		System.out.println( v.get(0) );

		v.set( 0, "Here" );
		System.out.println( v );

		//does not remove at index 2?
		//v.remove( new Integer(2) ); 

		System.out.println( v );

		System.out.println( v.indexOf( "Hello" )); 
		
		v.remove( null );
		System.out.println( v );

		Vector<String> ee1 = new Vector( null );

		//Iterator<String> iter = v.iterator();
		//while ( iter.hasNext() )
		//	System.out.println( iter.next() );


		for ( String s : v )
			System.out.println( s );

	}

}