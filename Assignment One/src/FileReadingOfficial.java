import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * NVCC Assignment One: File Reading and Writing
 * 
 * @author matthewgrillo (Github: "crickon")
 * @version 1.0
 */
public class FileReadingOfficial {
	/**
	 * Output path and file name constant
	 */
	public final static String outputPath = "output.txt";

	/**
	 * Output PrintWriter object for writing to the output file
	 */
	private static PrintWriter output;
	
	/**
	 * List of words needed to be filled into a story.
	 * Object is created after the story text file is read
	 */
	private static ArrayList<String> insertions;

	/**
	 * Main method that runs when the jar is executed
	 * 
	 * @param args
	 *            Command Line arguments
	 */
	public static void main(String[] args) {
		output = createWriter(outputPath);

		if (args.length < 1) {
			// no args
			output.println("Insufficient Arguments");
		}
		if (args.length >= 1) {
			// One Argument - Check for equal parenthesis from arg1
			checkParenthesis(args[0]);
		}
		if (args.length >= 2) {
			// Two Arguments - run 1 and check for equality
			compareFiles(args[0], args[1]);
		}
		if (args.length == 3) {
			// Three Args - run 1 & 2 and run madlibs with user input
			madlibInput(getStory(args[2]));
		}
		if (args.length == 4) {
			// Four Args - run 1 & 2 and run madlibs without user input
			String story = getStory(args[2]);
			madlibFile(args[3], story);
		}
		if (args.length > 4) {
			output.println("Too many arguments");
		}
		output.close();
	}


	/**
	 * Check for an equal amount of open parenthesis to close parenthesis of a
	 * text file and print the result to the output file
	 * 
	 * @param path
	 *            Path to the text file that is to be checked
	 */
	private static void checkParenthesis(String path) {
		BufferedReader reader1 = null;
		try {
			reader1 = createReader(path);
		} catch (FileNotFoundException e) {
			output.println("Part1: Unable to Open File");
			System.exit(1);
			output.close();
		}

		// Check if the braces are balanced
		int openP = 0;
		int closeP = 0;

		String line;
		try {
			line = reader1.readLine();
			while (line != null) {
				char[] chars = line.toCharArray();
				for (char c : chars) {
					if (c == '{')
						openP++;
					else if (c == '}')
						closeP++;
				}
				line = reader1.readLine();
			}
		} catch (IOException e) {
			System.out.println("Part1: IOException");
			output.close();
		}

		boolean balanced = openP == closeP;
		System.out.printf("Part1 Parenthesis Balanced: %b (%d open, %d close)\n\n", balanced, openP, closeP);

		// Print a line into the output file with result of braces check
		output.println(balanced ? "Braces Balanced" : "Braces Not Balanced");
		// Display a blank line in the output
		output.println();
	}

	/**
	 * Compare two text files and print the result to the output file
	 * 
	 * @param pathOne
	 *            Path to the first text file that is to be compared
	 * @param pathTwo
	 *            Path to the second text file that is to be compared
	 */
	private static void compareFiles(String pathOne, String pathTwo) {
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		try {
			reader1 = createReader(pathOne);
			reader2 = createReader(pathTwo);
		} catch (FileNotFoundException e) {
			output.println("Part2: Unable to Open File");
			System.exit(1);
			output.close();
		}

		// compare the files.
		boolean identical = true;
		try {
			String line1 = reader1.readLine();
			String line2 = reader2.readLine();

			while (line1 != null || line2 != null) {
				if (!line1.equals(line2))
					identical = false;

				line1 = reader1.readLine();
				line2 = reader2.readLine();
			}
		} catch (IOException e) {
			System.out.println("Part2: Comparing Exception");
			System.exit(1);
			output.close();
		}
		output.println(identical ? "Files Identical" : "Files not Identical");
		output.println();
		System.out.printf("Part2 Identical: %b \n\n", identical);
	}

	/**
	 * Read a story text file and create a String to be filled in from. Also
	 * creates an ArrayList of words that need to be replaced in the story.
	 * 
	 * @param path
	 *            Path to the text file that contains a mad lib story
	 * @return A String containing the full story from the text file
	 */
	private static String getStory(String path) {
		BufferedReader reader3 = null;
		try {
			reader3 = createReader(path);
		} catch (IOException e) {
			System.out.println("Part3a: Unable to Open File");
			System.exit(1);
			output.close();
		}

		// loop through reader for brackets
		insertions = new ArrayList<String>();
		String singleStringStory = "";
		try {
			boolean record = false;
			String line = reader3.readLine();

			String temp = "";
			while (line != null) {
				char[] chars = line.toCharArray();
				for (char c : chars) {
					if (record) {
						temp += c;
					} else {
						singleStringStory += c;
					}
					if (c == '<')
						record = true;
					else if (c == '>') {
						record = false;
						insertions.add(temp.substring(0, temp.length() - 1));
						temp = "";
					}
				}
				singleStringStory += "\n";
				line = reader3.readLine();
			}
			// System.out.println(insertions.toString());

		} catch (IOException e) {
			System.exit(1);
			output.close();
		}

		return singleStringStory;
	}

	/**
	 * Get input from the user for each word that is to be replaced in the
	 * story. Once collected, the story is filled in and printed to the output
	 * file.
	 * 
	 * @param story
	 *            A Single string story that has already been processed from a
	 *            file with the insertions needed
	 */
	private static void madlibInput(String story) {
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<String> words = new ArrayList<String>();
		for (String insert : insertions) {
			System.out.println(String.format("insert a(n) %s:", insert));
			words.add(scanner.nextLine());
		}

		String[] partsOfStory = story.split("<");

		String newStory = "";
		for (int i = 0; i < words.size(); i++) {
			newStory += partsOfStory[i] + words.get(i);
		}
		// System.out.println(newStory);
		output.println(newStory);
		scanner.close();
	}

	/**
	 * Instead of receiving input from the user, the user inserts a fourth command line argument 
	 * where the words are drawn from to fill out the story and print to the output file
	 * 
	 * @param path
	 *            Path to the words text file
	 * @param story
	 *            A Single string story that has already been processed from a
	 *            file with the insertions needed
	 */
	private static void madlibFile(String path, String story) {
		BufferedReader reader4 = null;
		try {
			reader4 = createReader(path);
		} catch (FileNotFoundException e) {
			System.out.println("Part3b: Unable to Open File");
			System.exit(1);
			output.close();
		}

		ArrayList<String> words = new ArrayList<String>();
		try {
			String temp = reader4.readLine();

			while (temp != null) {
				words.add(temp);
				temp = reader4.readLine();
			}
		} catch (IOException e) {
			System.exit(1);
			output.close();
		}

		String[] partsOfStory = story.split("<");

		String newStory = "";
		for (int i = 0; i < insertions.size(); i++) {
			if (words.size() <= i) {
				newStory += partsOfStory[i] + "<" + insertions.get(i) + ">";
			} else {
				newStory += partsOfStory[i] + words.get(i);
			}

		}
		// System.out.println(newStory);
		output.println(newStory);
	}

	/**
	 * Helper method to create a PrintWriter for the output file, or any other
	 * file that needs to be written to.
	 * 
	 * @param path
	 *            Path where the file is to be stored and the name of the file
	 * @return PrintWriter object that can be printed to
	 */
	private static PrintWriter createWriter(String path) {
		Writer writer = null;
		try {
			writer = new FileWriter(path, false);
		} catch (IOException e) {
			System.out.println("Unable to create output file writer");
			System.exit(1);
			output.close();
		}
		return new PrintWriter(writer);
	}

	/**
	 * Helper method to create a BufferedReader to read the text within a file.
	 * 
	 * @param path
	 *            Path where the file is located
	 * @return BufferedReader object that can be used to read the text
	 * @throws FileNotFoundException
	 *             if the file does not exist
	 */
	private static BufferedReader createReader(String path) throws FileNotFoundException {
		return new BufferedReader(new FileReader(path));
	}

}
