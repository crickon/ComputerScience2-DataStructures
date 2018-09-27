import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReadingTwo {
	private final static String outputPath = "output.txt";

	private static PrintWriter output;
	private static Scanner scanner;

	private static ArrayList<String> insertions;
	private static ArrayList<String> words;

	public static void main(String[] args) {
		output = createWriter(outputPath);

		scanner = new Scanner(System.in);

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
			String story = getStory(args[2]);
			madlibInput(args[2], story);
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

	private static void madlibInput(String path, String story) {

		words = new ArrayList<String>();
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
	}

	private static void madlibFile(String path, String story) {
		BufferedReader reader4 = null;
		try {
			reader4 = createReader(path);
		} catch (FileNotFoundException e) {
			System.out.println("Part3b: Unable to Open File");
			System.exit(1);
			output.close();
		}

		words = new ArrayList<String>();
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

	private static BufferedReader createReader(String path) throws FileNotFoundException {
		return new BufferedReader(new FileReader(path));
	}

}
