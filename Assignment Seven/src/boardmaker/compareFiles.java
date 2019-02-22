package boardmaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class compareFiles
{
	private static String[] files;
	private static Scanner[] readers;
	
	public static void main (String[] args)
	{
		//files = new String[]{"boardLists/winningBoards.txt", "TicTacToeWinners.txt"};
		files = args;
		System.out.println(filesList());
		readers = new Scanner[files.length];
		for (int i = 0; i < files.length; i++)
		{
			try
			{
				readers[i] = new Scanner(new File(files[i]));
			}
			catch (FileNotFoundException e)
			{
				System.out.println(String.format("Could not find %s, quitting.", files[i]));
				System.exit(-1);
			}
		}
		boolean same = true;
		while (files.length != 0 && hasNext())
		{
			String[] lines = new String[files.length];
			for (int i = 0; i < files.length; i++)
				lines[i] = readers[i].nextLine();
			for (int i = 1; i < files.length; i++)
				same = same && lines[i-1].equals(lines[i]);
				
		}
		System.out.printf("The files: %s %s the same", filesList(), (same ? "are" : "are not"));
	}

	private static String filesList()
	{
		String str = "";
		for (String s : files)
			str += s + ", ";
		if (str.length() > 2)
			str = str.substring(0, str.length()-2);
		return str;
	}

	private static boolean hasNext()
	{
		boolean hasNext = true;
		for (Scanner reader : readers)
			hasNext = hasNext && reader.hasNext();
		return hasNext;
	}
	
}
