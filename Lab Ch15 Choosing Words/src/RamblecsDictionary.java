import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class RamblecsDictionary {
	public static void main(String[] args) {
		String inputpath = "words.txt";
		File file = new File(inputpath);
		Scanner textReader = null;
		try {
			textReader = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String outputpath = "output.txt";
		Writer writer = null;
		try {
			writer = new FileWriter(outputpath, true);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		PrintWriter output = new PrintWriter(writer);

		while (textReader.hasNextLine()) {
			String line = textReader.nextLine();
			if (line.length() >= 3 && line.length() <= 5) {
				line = line.toUpperCase();
				output.println(line);
			}
		}

		textReader.close();
		output.close();
	}
}