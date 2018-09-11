import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class FileReadingAssignment {
	private static boolean printTrace = false;
	
	public static void main(String[] args) {
		//Take in a file name and create File
		String filename = JOptionPane.showInputDialog("Insert a file name with extension: ");
		File file = new File(filename);
		
		//Create Scanner for file reading
		Scanner textReader = null;
		try {
			textReader = new Scanner(file);
		} catch (FileNotFoundException e) {
			if (printTrace) e.printStackTrace();
			System.out.println("Part1: Unable to Open File");
			System.exit(1);
		}
		
		//Check if the braces are balanced (if '{' == '}')
		int openP = 0;
		int closeP = 0;
		
		while (textReader.hasNext()) {
			String str = textReader.next();
			char[] chars = str.toCharArray();
			for (char c : chars) {
				if (c == '{') openP ++;
				else if (c == '}') closeP ++;
			}
		}
		
		boolean balanced = openP == closeP;
		System.out.printf("Parenthesis Balanced: %b (%d:%d)", balanced, openP, closeP);
		
		//Load or Create output file and create PrintWriter
		String outputpath = "output.txt";
		Writer writer = null;
		try {
			writer = new FileWriter(outputpath, false);
		} catch (IOException e) {
			if (printTrace) e.printStackTrace();
			System.out.println("Part2: Unable to create output writer");
			System.exit(1);
		}

		PrintWriter output = new PrintWriter(writer);
		
		//Print a line into the output file with result of braces check
		output.println(balanced ? "Braces Balanced" : "Braces Not Balanced");
		
		textReader.close();
		output.close();
	}
}
