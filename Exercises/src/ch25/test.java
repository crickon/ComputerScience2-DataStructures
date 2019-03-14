package ch25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class test
{
	private static String path = "src/ch25/words.txt";

	public static void main(String... args)
	{
		File file = new File(path);
		try
		{
			Scanner scanner = new Scanner(file);
			read(scanner);

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public static void read(Scanner scanner)
	{
		Map<String, Integer> map = new TreeMap<String, Integer>();
		String bigWord = "";
		Integer bigNum = 0;
		while (scanner.hasNextLine())
		{
			String word = scanner.nextLine();
			int temp = 1;
			if (map.containsKey(word))
				temp += map.get(word);
			map.put(word, temp);
			if (temp > bigNum)
			{
				bigWord = word;
				bigNum = temp;
			}
		}
		System.out.println(bigWord + ", " + bigNum);
	}
}
