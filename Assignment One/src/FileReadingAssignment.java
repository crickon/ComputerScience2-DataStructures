import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class FileReadingAssignment {
	private static boolean printTrace = false;

	public static void main(String[] args) {
		/*
		 * Part 1
		 */
		// Take in a file name and create File
		String path1 = JOptionPane.showInputDialog("Insert a file name with extension: ");

		// Create BufferedReader for file reading
		BufferedReader reader1 = null;
		try {
			reader1 = createReader(path1);
		} catch (FileNotFoundException e) {
			if (printTrace)
				e.printStackTrace();
			System.out.println("Part1: File not Found: " + path1);
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
		System.out.printf("Part1 Parenthesis Balanced: %b (%d open, %d close)\n", balanced, openP, closeP);

		// Load or Create output file and create PrintWriter
		String outputpath = "output.txt";

		PrintWriter output = createWriter(outputpath);// new
														// PrintWriter(writer);

		// Print a line into the output file with result of braces check
		output.println(balanced ? "Braces Balanced" : "Braces Not Balanced");
		output.println();

		/*
		 * Part2
		 */
		// Take in a second filename
		String path2 = JOptionPane.showInputDialog("Insert another file name with extension: ");

		// Create Scanner for file reading
		BufferedReader reader2 = null;
		try {
			reader2 = createReader(path2);
		} catch (FileNotFoundException e) {
			if (printTrace)
				e.printStackTrace();
			System.out.println("Part2: Unable to Open File - " + path2);
			System.exit(1);
		}
		
		//Reset reader1
		try {
			reader1 = createReader(path1);
		} catch (FileNotFoundException e) {
			if (printTrace)
				e.printStackTrace();
			System.out.println("Part2: Unable to Open File - " + path1);
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
		}catch(IOException e) {
			if (printTrace)
				e.printStackTrace();
			System.out.println("Part2: Comparing Exception");
			System.exit(1);
		}
		
		output.println(identical ? "Files Identical" : "Files not Identical");
		output.println();
		System.out.printf("Part2 Identical = %b \n", identical);
		// Close Reader and Writer (last)
		output.close();
	}

	private static PrintWriter createWriter(String outputpath) {
		Writer writer = null;
		try {
			writer = new FileWriter(outputpath, false);
		} catch (IOException e) {
			if (printTrace)
				e.printStackTrace();
			System.out.println("Part1.5: Unable to create output writer");
			System.exit(1);
		}
		return new PrintWriter(writer);
	}

	private static BufferedReader createReader(String path) throws FileNotFoundException {
		return new BufferedReader(new FileReader(path));
	}
}
