import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

/**
 * NVCC Assignment Three
 * 
 * @author Matthew Grillo (@Crickon)
 *
 */
public class IndexMaker
{
	private static String input;
	private static String output;
	private static BufferedReader inputReader;
	private static PrintWriter outputWriter;

	private static DocumentIndex doc;

	/**
	 * Accept the input file and output files as arguments (in that order). If
	 * they are not supplied, ask the user for the names of the input/output
	 * files. If the output filename is not specified, the file name should be
	 * the input file name concatenated to the word "index", with the same
	 * extension.
	 * 
	 * @param args
	 *            Input and Output file paths
	 */
	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		switch (args.length)
		{
			case 2:
				input = args[0];
				output = args[1];
				break;
			case 1:
				input = args[0];
				int sub = input.lastIndexOf(".");
				if (sub == -1)
					sub = input.length();
				output = input.substring(0, sub) + "index" + input.substring(sub);
				break;
			default:
				System.out.println("Enter input and output path files");
				input = kb.next();
				output = kb.next();
				break;
		}
		try
		{
			inputReader = createReader(input);
			outputWriter = createWriter(output);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		doc = new DocumentIndex();

		String temp;
		try
		{
			int line = 1;
			temp = inputReader.readLine();
			while (temp != null)
			{
				doc.addAllWords(temp, line);
				line++;
				temp = inputReader.readLine();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		outputWriter.print(doc.toString());
		outputWriter.close();
	}

	private static BufferedReader createReader(String path) throws FileNotFoundException
	{
		return new BufferedReader(new FileReader(path));
	}

	private static PrintWriter createWriter(String path)
	{
		Writer writer = null;
		try
		{
			writer = new FileWriter(path, false);
		}
		catch (IOException e)
		{
			System.out.println("Unable to create output file writer");
			outputWriter.close();
			System.exit(1);
		}
		return new PrintWriter(writer);
	}
}
