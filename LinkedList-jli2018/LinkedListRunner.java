import java.util.Iterator;

/**
 * LinkedListRunner class
 *
 * @author 		Jessica Li
 * @version 	11/29/15 (1.0)
 */
public class LinkedListRunner
{
	/**
	 * Runner for LinkedList class. Tests some, not all, functions of LinkedList. 
	 *
	 * @param args 		main method parameter
	 */ 
	public static void main( String[] args )
	{
		LinkedList<String> list = new LinkedList<String>();
		//ListNode<String> ln1 = new ListNode<String>( "item1" );
		//ListNode<String> ln2 = new ListNode<String>( "item2" );
		list.add( "a" );
		list.add( "b" );
		list.add( "c" );
		list.addLast( "aa" );
		
		System.out.println( list.remove( 2 ) );			//why does this not work??? same for get and set
		//return item, not listnode
		
		System.out.println( list );
		list.set(2, "cc");
		//list.clear();
		System.out.println( list );

		//this manual for each loop works
		Iterator<String> iter = list.iterator();
		while ( iter.hasNext() )
			System.out.println( iter.next() );

		System.out.println();

		//must print String!
		for ( String s : list )
			System.out.println( s );

		/*
		Stack stack = new LinkedList<String>(); 
		System.out.println( stack.isEmpty() );
		stack.push( "a" );
		stack.push( "b" );
		stack.push( "c" );
		stack.push( "d" );
		stack.push( "e" );
		stack.push( "f" );
		stack.pop();
		stack.pop();
		//System.out.println( stack.peek() );
		//System.out.println( stack );

		System.out.println();

		//queue prints backward because I'm adding to the front and removing from the end
		Queue qu = new LinkedList<String>(); 
		qu.offer( "a" );
		qu.offer( "b" );
		qu.offer( "c" );
		qu.offer( "d" );
		qu.offer( "e" );
		qu.offer( "f" );
		qu.poll();
		qu.poll();
		System.out.println( qu.peek() );
		System.out.println( qu );
		System.out.println( qu.isEmpty() );
		*/



	}
}