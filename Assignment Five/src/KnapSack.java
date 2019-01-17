import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * NVCC Assignment Five
 * 
 * @author Matthew Grillo
 * @date January 16, 2019
 */
public class KnapSack
{
	private static final String OUTPUT_NAME = "knapsack.txt";
	/**
	 * Keyboard input
	 */
	private static Scanner kb;
	/**
	 * Input from text file via Scanner reader.
	 */
	private static Scanner input;
	/**
	 * PrintWriter to write to the output file
	 */
	private static PrintWriter output;
	/**
	 * Boolean to continue asking user for input file if the previously
	 * specified File does not exist
	 */
	private static boolean loop;

	/**
	 * Main method to start the Knapsack project.
	 * 
	 * @param args
	 *            Include original file name to skip user submission
	 */
	public static void main(String[] args)
	{
		kb = new Scanner(System.in);
		output = createWriter(OUTPUT_NAME);
		loop = true;

		String fileName = "";
		if (args.length == 0)
			System.out.println("Enter a file name: ");
		fileName = ((args.length > 0) ? args[0] : kb.next());

		File file;
		while (loop)
			try
			{
				file = new File(fileName);
				input = new Scanner(file);
				loop = false;
			}
			catch (FileNotFoundException e)
			{
				System.out.printf("Could not find %s, please enter a different file name:\n", fileName);
				fileName = kb.next();
			}

		while (input.hasNext())
		{
			runKnapsack(input.next());
		}

		output.close();
	}

	/**
	 * After the original file is parsed, this method is run for each of the
	 * given knapsack files.
	 * 
	 * @param fileName
	 *            String name of knapsack file
	 */
	public static void runKnapsack(String fileName)
	{
		try
		{
			File file = new File(fileName);
			Scanner knapScanner = new Scanner(file);

			int limit = knapScanner.nextInt();
			ArrayList<Integer> data = new ArrayList<Integer>();
			while (knapScanner.hasNextInt())
			{
				data.add(knapScanner.nextInt());
			}
			int[] ints = new int[data.size()];
			for (int i = 0; i < data.size(); i++)
				ints[i] = data.get(i);
			String list = data.toString();
			list = list.substring(list.indexOf('[') + 1, list.indexOf(']'));

			data = new ArrayList<Integer>();
			int sum = KnapSackMethods.knapsackSum(ints, ints.length - 1, limit, data);

			String str = String.format("%s  \t%d  \t%s", fileName, limit, list);
			System.out.println(str);
			output.println(str);
			output.println(""); // blank line
			for (int i : data)
				output.println(i + " pound watermelon");
			if (data.size() == 0)
				output.println("No possible watermelons");
			output.println("\n"); // ended with another 2 blank lines
		}
		catch (Exception e)
		{
			System.out.println("Skipping " + fileName);
		}
	}

	/**
	 * Method from Assignment One for easily creating an output PrintWriter
	 * 
	 * @param path file name or path, and extension for the output
	 * @return PrintWriter object for fileName specified
	 */
	private static PrintWriter createWriter(String path)
	{
		Writer writer = null;
		try
		{
			writer = new FileWriter(path, false);
		}
		catch (IOException e)
		{
			System.out.println("Unable to create file writer");
		}
		return new PrintWriter(writer);
	}
}
