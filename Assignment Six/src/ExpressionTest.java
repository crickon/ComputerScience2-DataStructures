import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class ExpressionTest
{
	public static final String OUTPUT_PATH = "myAnswers.txt";
	public static final String DEFAULT_INPUT = "postFixExpressions.txt";
	public static final String INSERT_FILENAME = "Input a file name: ";
	public static final String NOT_FOUND = "File was not found";

	private static PrintWriter output;
	private static Scanner input;
	private static Scanner kb;

	private static boolean loop = true;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		output = createWriter(OUTPUT_PATH);
		kb = new Scanner(System.in);
		try
		{
			if (args.length == 0)
				input = new Scanner(new File(DEFAULT_INPUT));
			else
				input = new Scanner(new File(args[0]));
		}
		catch (FileNotFoundException e)
		{
			findInput();
		}
		while (input.hasNextLine())
		{
			String notation = input.nextLine();
			String[] strarr = notation.split(" ");
			ExpressionTree tree = new ExpressionTree(strarr);
			output.println(tree.evalTree() + "\n" + tree.toPrefixNotation() + "\n" + tree.toInfixNotation() + "\n"
					+ tree.toPostfixNotation() + "\n" + tree.postfixEval(strarr) + "\n" + "\n");
		}

		// close output to print to the file.
		output.close();
	}

	private static void findInput()
	{
		while (loop)
		{
			System.out.println(INSERT_FILENAME);
			try
			{
				input = new Scanner(new File(kb.next()));
				loop = false;
			}
			catch (FileNotFoundException e)
			{
				System.out.println(NOT_FOUND);
			}
		}
	}

	/**
	 * Method from Assignment One for easily creating an output PrintWriter
	 * 
	 * @param path
	 *            file name or path, and extension for the output
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
