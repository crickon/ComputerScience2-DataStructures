package assignment;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class TicTacToeMyHashMap
{
	private HashMap<TTTHashString, Boolean> map;
	private final int CAP = (int) Math.pow(2, 8);
	private final int numWinners = 1400;
	private final float resizeFactor = (float) numWinners / CAP;

	TicTacToeMyHashMap()
	{
		this.map = new HashMap<TTTHashString, Boolean>(CAP, resizeFactor);
	}

	private int capacity() throws NoSuchFieldException, IllegalAccessException
	{
		// had to break out the good-ole java docs to figure this one out.
		// Basically accessing a private field that I shouldn't have access to
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

	public static HashMap.Entry getNext(HashMap.Entry entry)
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
	public static int chainLength(HashMap.Entry node)
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

		TicTacToeMyHashMap m = new TicTacToeMyHashMap();
		log("Initial HashMap Capacity = " + m.CAP);

		Scanner winnersInput = new Scanner(new File("TicTacToeWinners.txt"));
		int count = 0;
		while (winnersInput.hasNextLine())
		{
			TTTHashString line = new TTTHashString(winnersInput.nextLine());
			m.map.put(line, true);
		}
		winnersInput.close();

		log("Capacity with winners = " + m.capacity());
		analyze(m);
	}

	private static void analyze(TicTacToeMyHashMap m) throws NoSuchFieldException, IllegalAccessException
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
			int chainLength = chainLength(table[i]);
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
		int[] quadrants = determineQuadrants(table);
		String quadStr = "";
		for (int i : quadrants)
			quadStr += i + ", ";
		log("number of entries per quadrant = " + quadStr.substring(0, quadStr.lastIndexOf(',')));
		// number of collisions in each tenth
		int[] tenths = determineTenths(table);
		String tenStr = "";
		for (int i : tenths)
			tenStr += i + ", ";
		log("number of collisions per tenth = " + tenStr.substring(0, tenStr.lastIndexOf(',')));
		// avg chain length (not including 1)
		log("avg chain length = " + chainSum * 1.0 / numChains + ", " + chainSum + "/" + numChains);
		// maximum chain length
		log("longest chain length = " + maxChain);

	}

	private static int[] determineQuadrants(Entry[] table)
	{
		int[] quadrants = new int[4];
		int len = table.length;
		int fourth = len / 4;
		for (int i = 0; i < 4; i++)
		{
			int numEntries = 0;
			for (int j = fourth * i; j < fourth * (i + 1) && j < len; j++)
			{
				numEntries += chainLength(table[j]);
			}
			quadrants[i] = numEntries;
		}
		return quadrants;
	}

	private static int[] determineTenths(Entry[] table)
	{
		int[] tenths = new int[10];
		int len = table.length;
		int tenth = len / 10;
		for (int i = 0; i < 10; i++)
		{
			int numCollisions = 0;
			for (int j = tenth * i; j < tenth * (i + 1) && j < len; j++)
			{
				int chainLen = chainLength(table[j]);
				if (chainLen > 1)
					numCollisions += chainLen - 1;
			}
			tenths[i] = numCollisions;
		}
		return tenths;
	}

}