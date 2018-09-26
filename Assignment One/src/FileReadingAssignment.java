import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class FileReadingAssignment {
	//for printing Exception Traces if need be.
	private static boolean printTrace = false;
	
	private static Scanner scanner;
	private static PrintWriter output;
	private static String pathOne;
	
	private static String outputPath = "output.txt";

	public static void main(String[] args) {
		// Scanner for reading user input.
		scanner = new Scanner(System.in);
		
		// Writer to print to output file.
		output = createWriter(outputPath);
		
		partOne();
		partTwo();
		partThree();

		// Close Writer after use
		output.close();
	}

	private static void partOne() {
		// Get java or text file path from user
		System.out.println("Insert a file name with extension: ");
		pathOne = scanner.nextLine();

		// Create BufferedReader for file reading
		BufferedReader reader1 = null;
		try {
			reader1 = createReader(pathOne);
		} catch (FileNotFoundException e) {
			if (printTrace)
				e.printStackTrace();
			System.out.println("Part1: File not Found: " + pathOne);
			System.exit(1);
		}

		// Check if the braces are balanced (if '{' == '}')
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
			if (printTrace)
				e.printStackTrace();
			System.out.println("Part1: IOException");
		}

		boolean balanced = openP == closeP;
		System.out.printf("Part1 Parenthesis Balanced: %b (%d open, %d close)\n\n", balanced, openP, closeP);

		// Print a line into the output file with result of braces check
		output.println(balanced ? "Braces Balanced" : "Braces Not Balanced");
		output.println();
	}
	
	private static void partTwo() {
		// Get java or text file path from user
		System.out.println("Insert another file name with extension: ");
		String pathTwo = scanner.nextLine();

		// Create Scanner for file reading
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		try {
			reader1 = createReader(pathOne);
			reader2 = createReader(pathTwo);
		} catch (FileNotFoundException e) {
			if (printTrace)
				e.printStackTrace();
			System.out.println("Part2: Unable to Open File");
			System.exit(1);
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
			if (printTrace)
				e.printStackTrace();
			System.out.println("Part2: Comparing Exception");
			System.exit(1);
		}

		output.println(identical ? "Files Identical" : "Files not Identical");
		output.println();
		System.out.printf("Part2 Identical: %b \n\n", identical);
	}
	
	private static void partThree() {
		System.out.println("Insert the filename and extension for the storyboard: ");
		String path3 = scanner.nextLine();
		
		// open file
		BufferedReader reader3 = null;
		try {
			reader3 = createReader(path3);
		}catch (IOException e) {
			if (printTrace)
				e.printStackTrace();
			System.out.println("Part3: Unable to Open File - " + path3);
			System.exit(1);
		}
		
		//loop through reader for brackets
		try {
			String temp = reader3.readLine();
			while (temp != null) {
				if (temp.contains("<") && temp.contains(">")){
				}
				temp = reader3.readLine();
			}
			
		} catch (IOException e) {
			if (printTrace)
				e.printStackTrace();
			System.exit(1);
		}
	}

	// Helper Methods
	private static PrintWriter createWriter(String outputpath) {
		Writer writer = null;
		try {
			writer = new FileWriter(outputpath, false);
		} catch (IOException e) {
			if (printTrace)
				e.printStackTrace();
			System.out.println("Part0.5: Unable to create output writer");
			System.exit(1);
		}
		return new PrintWriter(writer);
	}

	private static BufferedReader createReader(String path) throws FileNotFoundException {
		return new BufferedReader(new FileReader(path));
	}
}
