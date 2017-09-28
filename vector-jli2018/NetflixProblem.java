import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Vector;

/**
 * NetflixProblem Class.
 *
 * @author Jessica Li
 * @version 1 - 11/09/15
 */
public class NetflixProblem
{

	Vector<Vector<Integer>> people;
	//array is initialized in read() method. First line of csv file, stores show names. 
	String[] showNames;

	/**
	 * Constructor for NetFlixProblem class. 
	 * Initializes Vector of Vectors containing all the people and their ratings. Populates people by callign read(). 
	 * Changes the rating system of the vectors by calling changeRatings(). 
	 */
	public NetflixProblem()
	{
		//the people vector will store the person's name, and the nested vectors will store each persons ratings for all the shows
		//
		people = new Vector<Vector<Integer>>(43);
		//size is number of lines in file
		read();
		//should i change ratings here?
		changeRatings();
		
	}

	/** 
	 * Trasfers data from Excel spreadsheet to Vector people, line by line. 
	 * Given code returns an indivudual line. 
	 * Written code:
	 * int index keeps track of where in Vector people to add new data. Starts at -1 to take care of first line, containing the show names,
	 * which are stored in class field array showNames. 
	 * Stores line of data in an array. Transfers the values from array to a corresponding Vector in the people Vector using a for loop. 
	 */ 
	public void read()
	{
		String pathname = "Sheet1.csv";
		File file = new File(pathname);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + pathname );
			System.exit(1);
		}
		
		int index = -1;
		while( input.hasNextLine() )
		{
			if ( index == -1 )
			{
				String line = input.nextLine();
				showNames = line.split( "," );
			}

			else
			{
				//size!
				people.add( new Vector<Integer>(78) );
				String line = input.nextLine();
				String[] lineValues = line.split( "," );
				for ( int i = 0; i < lineValues.length; i++ )
				{
					people.get(index).add( Integer.parseInt( lineValues[i] ) ); 
				}
			}

			index++;
		}
	}

	/**
	 * Changes the ratings in the vectors from 1-5 to -5 to +5. Makes future calculations easier. 
	 * Nested for loop and multiple if statements change the individual values. 
	 */ 
	public void changeRatings()
	{
		for ( int i = 0; i < people.size(); i++ )
		{
			for ( int j = 0; j < people.get(i).size(); j++ )
			{
				//auto(un)boxes for me??
				if ( people.get(i).get(j) == 1 )
					people.get(i).set( j, -5 );
				if ( people.get(i).get(j) == 2 )
					people.get(i).set( j, -3 );	
				if ( people.get(i).get(j) == 3 )
					people.get(i).set( j, 1 );	
				if ( people.get(i).get(j) == 4 )
					people.get(i).set( j, 3 );	
				if ( people.get(i).get(j) == 5 )
					people.get(i).set( j, 5 );	
			}
		}

		/* how does this work?
		for ( Vector<Integer> pp : people )
		{
			for ( int r : pp )
			{
				if ( r == 1 )
					//r = -5; 		???????? can I do this?
			}
		}*/
	}



	/** 
	 * Finds the similarity score for two people. 
	 * Variable score stores similarity score. For loop traces through tv shows. 
	 * Multiplies each person's rating for the show, adds product to score. Returns score. 
	 *
	 * @param p1	the first person for whom the similarity score is to be found
	 * @param p2	the second person for whom the similarity score is to be found
	 * @return 		the similarity score for person 1 and person 2
	 */ 
	public int getSimilarityScore( int p1, int p2 )
	{
		int score = 0;
		for ( int i = 0; i < people.get(0).size(); i++ )
		{
			score += people.get(p1).get(i) * people.get(p2).get(i);
		}
		return score;
	}

	/**
	 * Rates an individual tv show. 
	 * Variable score stores rating/score. For loop traverses through all the people. 
	 * If the person is pp, skips pp. Multiplies the person's rating by the two people's s
	 * imilarity score to get a weighted number. Adds to score. Returns score. 
	 *
	 * @param pp 	person who we are rating shows for - exclude this person from calculations
	 * @param tv 	the tv show to be rated
	 * @return 		weighted rating for this tv show
	 */
	public Integer rateShow( int pp, int tv )
	{
		if ( people.get(pp).get(tv) != 0 )
			return null;

		int score = 0;
		for ( int i = 0; i < pp; i++ )
		{
			if ( i == pp )
				continue;
			score += people.get(i).get(tv) * getSimilarityScore( pp, i );
		}
		return score;
	}

	/**
	 * Method to find final ratings of shows for a person and return the 3 shows with the highest ratings--the best recommendations.
	 * Vector showScores stores final ratings. For loop adds ratings to Vector using rateShow() method.
	 * String top3 stores names of the top 3 shows to be recommended. 3 iteration for loop calls getTop() method for position of the highest score,
	 * adds show at that position to top3. Sets the show score at that position to null in order for the next iterations to find the next highest score. 
	 * If getTop() returns -1, meaning all shows have been watched, top3 is returned, as there is nothing left to recommend. Therefore, if top3 is empty,
	 * that means the user has slready watched all the shows listed in the csv document. 
	 * Returns top3. 
	 *
	 * @param pp 	the person who is to be recommended a show
	 * @return 		the name of the recommended TV show 
	 */
	public String findRecommendations( int pp )
	{
		Vector<Integer> showScores = new Vector<Integer>( people.get(0).size() ); 
		for ( int i = 0; i < people.get(0).size(); i++ )
		{
			showScores.add( rateShow( pp, i ) );
		}

		String top3 = "";
		
		for ( int i = 0; i < 3; i++ )
		{
			int currTop = getTop( showScores );
			if ( currTop == -1 )
				return top3;
			top3 += showNames[ currTop ] + "     ";
			showScores.set( currTop, null );
		}

		return top3;

		//for testing purposes, prints out all the values of showScores
		//for ( int i = 0; i < showScores.size(); i++ )
		//{
		//	System.out.println( showScores.get(i) );
		//} 

	}

	/** 
	 * Helper method to find the highest score of a Vector of scores. 
	 * While loop makes sure starting value toRecommend is not null. Returns -1 if all values in Vector are null,
	 * meaning the person has watched all the shows. 
	 * For loop traverses through Vector to find any higher values than the preset toRecommend. Sets toRecommend to 
	 * the position of a higher value. 
	 * Returns toRecommend. 
	 *
	 * @param showScores	the Vector containing all the TV show scores
	 * @return 		the position of the highest score
	 */
	public int getTop( Vector<Integer> showScores )
	{
		int toRecommend = 0;
		while ( showScores.get(toRecommend) == null )
		{
			toRecommend++;
			//if user has watched all shows
			if ( toRecommend == showScores.size() )
				return -1;	
		}

		for ( int i = toRecommend+1; i < showScores.size(); i++ )
		{

			if ( showScores.get(i) != null && showScores.get(i) > showScores.get( toRecommend ) )
				toRecommend = i; 
		}

		return toRecommend;
	}

	/**
	 * Runner main method. 
	 * Creates new Netflix Problem. Stores recommendations for person #__ in String recommendations. 
	 * If recommendations does not hold anything, prints that the user has watched all the shows. Otherwise, prints recommendations. 
	 * 
	 * @param args 		main method parameter
	 */ 
	public static void main( String[] args )
	{
		NetflixProblem np = new NetflixProblem(); 
		String recommendations = np.findRecommendations( 11 );
		if ( recommendations.length() == 0 )
			System.out.println( "This user has watched all the shows. ");
		else
			System.out.println( recommendations );

	}


}