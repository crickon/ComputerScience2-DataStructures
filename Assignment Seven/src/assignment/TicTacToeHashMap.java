package assignment;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Scanner;

public class TicTacToeHashMap
{
	private HashMap<String, Boolean> map;
	private final int CAP = (int) Math.pow(2, 8);
	private final int numWinners = 1400;
	private final float resizeFactor = (float) numWinners / CAP;

	TicTacToeHashMap()
	{
		this.map = new HashMap<String, Boolean>(CAP, resizeFactor);
	}

	private int capacity() throws NoSuchFieldException, IllegalAccessException
	{
		// had to break out the good-ole java docs to figure this one out.
		// Basically accessing a private field that I shouldn't have access to
		// :P
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] table = (Object[]) tableField.get(map);
		return table == null ? 0 : table.length;
	}

	public HashMap.Entry[] getTable() throws NoSuchFieldException, IllegalAccessException
	{
		/*
		 * "table" field is the array of HashMap Entries (LinkedLists)
		 * http://resources.mpi-inf.mpg.de/d5/teaching/ss05/is05/javadoc/java/
		 * util/ HashMap.html
		 */
		Field tableField = map.getClass().getDeclaredField("table");
		tableField.setAccessible(true);
		HashMap.Entry[] table = (HashMap.Entry[]) tableField.get(map);
		return table;
	}

	public HashMap.Entry getNext(HashMap.Entry entry)
	{
		/*
		 * HashMap entries are structured like LinkedLists with the "next" field
		 * being the pointer to the next entry in the chain.
		 * 
		 * http://resources.mpi-inf.mpg.de/d5/teaching/ss05/is05/javadoc/java/
		 * util/ HashMap.Entry.html
		 */
		try
		{
			Field entryField = entry.getClass().getDeclaredField("next");
			entryField.setAccessible(true);
			HashMap.Entry nextNode = (HashMap.Entry) entryField.get(entry);
			return nextNode;
		}
		catch (NoSuchFieldException | IllegalAccessException e)
		{
			return null;
		}
	}

	/**
	 * Recursive method for determining the chain length: 0 for empty space, 1
	 * for single entry, 2+ for chain
	 * 
	 * @param node
	 *            root Entry node of the chain
	 * @return
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	public int chainLength(HashMap.Entry node)
	{
		if (node == null)
			return 0;
		return chainLength(getNext(node)) + 1;
	}

	private static void log(Object o)
	{
		System.out.println(o.toString());
	}

	public static void main(String[] args)
			throws java.io.FileNotFoundException, NoSuchFieldException, IllegalAccessException
	{

		TicTacToeHashMap m = new TicTacToeHashMap();
		log("Initial HashMap Capacity = " + m.CAP);

		Scanner winnersInput = new Scanner(new File("TicTacToeWinners.txt"));
		int count = 0;
		while (winnersInput.hasNextLine())
		{
			String line = winnersInput.nextLine();
			m.map.put(line, true);
		}
		winnersInput.close();

		log("Capacity with winners = " + m.capacity());
		analyze(m);
	}

	private static void analyze(TicTacToeHashMap m) throws NoSuchFieldException, IllegalAccessException
	{
		int wastedSpaces = 0;
		int numCollisions = 0;
		int numEntries = 0;
		int numChains = 0;
		int maxChain = 0;
		int chainSum = 0;

		HashMap.Entry[] table = m.getTable();
		for (int i = 0; i < table.length; i++)
		{
			int chainLength = m.chainLength(table[i]);
			numEntries += chainLength;
			if (chainLength > 1)
			{
				numCollisions += chainLength - 1;
				chainSum += chainLength - 1;
				numChains++;
			}
			if (chainLength == 0)
				wastedSpaces++;
			if (chainLength > maxChain)
				maxChain = chainLength;
		}
		log("wasted spaces = " + wastedSpaces);
		log("size of array = " + table.length);
		// number of entries stored in the table
		log("number of entries = " + numEntries);
		// load factor
		log("load factor = " + numCollisions * 1.0 / table.length);
		// number of entries in each quadrant
		// number of collisions in each tenth
		// avg chain length (not including 1)
		log("avg chain length = " + chainSum * 1.0 / numChains + ", " + chainSum + "/" + numChains);
		// maximum chain length
		log("longest chain length = " + maxChain);

	}

}