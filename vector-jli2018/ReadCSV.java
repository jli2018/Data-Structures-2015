import java.io.*;
import java.util.Scanner;

/*To use this example program, run it in the same folder as a .csv file 
named Sheet1.csv */

public class ReadCSV
{
	public static void main (String args[])
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
		
		while( input.hasNextLine() )
		{
			System.out.println(input.nextLine());
		}
	}



}