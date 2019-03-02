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

	/**
	 * Default constructor to initialize the HashMap and "loadfactor"
	 */
	TicTacToeMyHashMap()
	{
		this.map = new HashMap<TTTHashString, Boolean>(CAP, resizeFactor);
	}

	/**
	 * Provided HashMap capacity method to determine the size of the HashMap
	 * using java reflection. Not to be confused with the number of entries in
	 * the HashMap.
	 * 
	 * @return size of the HashMap table
	 * @throws NoSuchFieldException
	 *             thrown if the "table" field is not found
	 * @throws IllegalAccessException
	 *             thrown if the field is inaccessible
	 */
	private int capacity() throws NoSuchFieldException, IllegalAccessException
	{
		// had to break out the good-ole java docs to figure this one out.
		// Basically accessing a private field that I shouldn't have access to
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] table = (Object[]) tableField.get(map);
		return table == null ? 0 : table.length;
	}

	/**
	 * Method to get the HashMap's array of LinkedLists (stored as HashMap.Entry
	 * objects) using the same reflection as the capacity method.
	 * 
	 * @return HashMap's stored data array
	 * @throws NoSuchFieldException
	 *             thrown if the "table" field is not found
	 * @throws IllegalAccessException
	 *             thrown if the field is inaccessible
	 */
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

	public static int countTreeNodes = 0;

	/**
	 * Method to get the next node in the LinkedList using reflection.
	 * 
	 * @param entry
	 *            A node from HashMap's table
	 * @return The next node in the LinkedList or null if there is no child
	 *         node.
	 */
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
			countTreeNodes++;
			// System.out.println(entry.getClass().getName());
		}
		return null;
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

	/**
	 * Helper method for printing to the console easier
	 * 
	 * @param o
	 *            Object to print its String value to the console (Typically a
	 *            String).
	 */
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
		int numWinners = 0;
		while (winnersInput.hasNextLine())
		{
			TTTHashString line = new TTTHashString(winnersInput.nextLine());
			m.map.put(line, true);
			numWinners++;
		}
		winnersInput.close();

		log("Capacity with winners = " + m.capacity());
		log("number of winners added = " + numWinners);
		analyze(m);
	}

	/**
	 * Helper method to analyze the HashMap post-process.
	 * 
	 * @param m
	 *            TicTacToeHashMap object
	 * @throws NoSuchFieldException
	 *             Thrown if the HashMap table is not found
	 * @throws IllegalAccessException
	 *             Thrown if the HashMap table is inaccessible
	 */
	private static void analyze(TicTacToeMyHashMap m) throws IllegalAccessException, NoSuchFieldException
	{
		int wastedSpaces = 0;
		int numCollisions = 0;
		int numChains = 0;
		int maxChain = 0;
		int chainSum = 0;

		HashMap.Entry[] table = m.getTable();
		for (int i = 0; i < table.length; i++)
		{
			int chainLength = chainLength(table[i]);
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
		log("number of entries = " + m.map.size());
		// load factor
		log("load factor = " + numCollisions * 1.0 / table.length);
		log("number of collisions = " + numCollisions);
		log("number of chains = " + numChains);
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
		log("number of uncounted treeNodes = " + countTreeNodes);
	}

	/**
	 * Helper method to determine how many entries exist in every quadrant of
	 * the HashMap's capacity
	 * 
	 * @param table
	 *            HashMap's stored data array
	 * @return the number of entries in each quadrant stored as an array
	 */
	private static int[] determineQuadrants(Entry[] table)
	{
		int len = table.length;
		double oneFourth = len * 1.0 / 4;
		double[] quadVals =
		{ 0.0, oneFourth * 1, oneFourth * 2, oneFourth * 3, oneFourth * 4 };
		int[] quadrants = new int[4];
		for (int i = 0; i < quadVals.length - 1; i++)
		{
			int numEntries = 0;
			for (int j = (int) Math.round(quadVals[i]); j < Math.round(quadVals[i + 1]) && j < len; j++)
			{
				numEntries += chainLength(table[j]);
			}
			quadrants[i] = numEntries;
		}
		return quadrants;
	}

	/**
	 * Helper method to determine how many collisions occur in every tenth of
	 * the HashMap's capacity
	 * 
	 * @param table
	 *            HashMap's stored data array
	 * @return the number of collisions in each tenth stored as an array
	 */
	private static int[] determineTenths(Entry[] table)
	{
		int len = table.length;
		double oneTenth = len * 1.0 / 10;
		double[] tenthVals =
		{ 0.0, oneTenth * 1, oneTenth * 2, oneTenth * 3, oneTenth * 4, oneTenth * 5, oneTenth * 6, oneTenth * 7,
				oneTenth * 8, oneTenth * 9, oneTenth * 10 };
		int[] tenths = new int[10];
		for (int i = 0; i < tenthVals.length - 1; i++)
		{
			int numCollisions = 0;
			for (int j = (int) Math.round(tenthVals[i]); j < Math.round(tenthVals[i + 1]) && j < len; j++)
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