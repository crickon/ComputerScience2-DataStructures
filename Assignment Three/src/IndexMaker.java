import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class IndexMaker
{
	private static String input;
	private static String output;
	private static BufferedReader inputReader;
	private static PrintWriter outputWriter;

	private static DocumentIndex doc;

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
				output = input + "output";
			default:
				System.out.println("Enter input and output path files");
				input = kb.next();
				output = kb.next();
		}
		try
		{
			inputReader = createReader(input);
			outputWriter = createWriter(output);
		}
		catch (FileNotFoundException e)
		{
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
