package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * NVCC Assignment Seven: "TicTacToe Hash Function" part 2
 * 
 * @author Matthew "crickon" Grillo
 *
 */
public class TTT_HC
{
	private final String KELLY_WINNERS = "TicTacToeWinners.txt";
	private final String MY_WINNERS = "boardLists/winningBoards.txt";
	public final static int numWinners = 1400;

	private final int[] powersOf3 =
	{ 1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683 };
	private final int boardStringLength = 9;

	/**
	 * The "Best" hash array size for zero wasted spaces, whilst having the
	 * lowest possible chain length is 354.
	 */
	private int hashArraySize = 354;

	private HashNode[] winners;

	/**
	 * Default constructor to initialize winners array of LinkedLists and set
	 * the winning values from a File of String boards.
	 */
	public TTT_HC()
	{
		winners = new HashNode[hashArraySize];
		setWinners();
	}

	/**
	 * Constructor to initialize winners array of LinkedLists with a given
	 * capacity and set the winning values from a File of String boards.
	 * 
	 * @param hashArraySize
	 *            capacity of winners array
	 */
	public TTT_HC(int hashArraySize)
	{
		this.hashArraySize = hashArraySize;
		winners = new HashNode[hashArraySize];
		setWinners();
	}

	/**
	 * Helper method to set the winners array with winning Tic Tac Toe values
	 */
	private void setWinners()
	{
		try
		{
			Scanner trueTacs = new Scanner(new File(KELLY_WINNERS));
			while (trueTacs.hasNextLine())
			{
				String ttt = trueTacs.nextLine();
				int hash = tttHashCode(ttt);
				HashNode hashObj = new HashNode(hash, ttt, null);
				if (winners[hash] == null)
					winners[hash] = hashObj;
				else
				{
					hashObj.setNext(winners[hash]);
					winners[hash] = hashObj;
				}
			}
			trueTacs.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println(KELLY_WINNERS + " not found");
			System.exit(-1);
		}
	}

	/**
	 * HashCode parser that takes in a TTT board String and returns the hash
	 * code value for an index in the winners array.
	 * 
	 * @param s
	 *            String representation of TTT board
	 * @return hash code index
	 */
	private int tttHashCode(String s)
	{
		// condense the hashcode to fit in a smaller array using modulus
		// remainder will always be < hashArraySize
		return myHashCode(s) % this.hashArraySize;
	}

	/**
	 * HashCode parser that takes in a TTT board String and returns a unique
	 * hash code for that board.
	 * 
	 * @param s
	 *            String representation of TTT board
	 * @return unique hash code
	 */
	private int myHashCode(String s)
	{
		int hash = 0;
		for (int i = 0; i < boardStringLength && i < s.length(); i++)
		{
			hash += myCharValue(s.charAt(i)) * powersOf3[i];
		}
		return hash;
	}

	/**
	 * Helper method to return unique char values for tiles on TicTacToe string
	 * representations
	 * 
	 * @param c
	 *            char representation of a TicTacToe board tile
	 * @return unique char/tile value
	 */
	private int myCharValue(char c)
	{
		switch (c)
		{
			case 'x':
			case 'X':
				return 1;
			case 'o':
			case 'O':
				return 2;
			default:
				return 0;

		}
	}

	/**
	 * Method to determine if a given String representation of a TTT board is a
	 * valid winning board
	 * 
	 * @param board String representation of a TTT board
	 * @return Board is a valid winning board
	 */
	public boolean isWin(String board)
	{
		int hash = tttHashCode(board);
		if (winners[hash].contains(board))
			return true;
		return false;
	}

	/**
	 * Method to analyze the winners array post-process.
	 */
	public void analyzeHashArray()
	{
		int numSpaces = 0;
		int numChains = 0;
		int largestChain = 0;
		int chainSum = 0;
		int numCollisions = 0;
		int numEntries = 0;
		for (int i = 0; i < this.winners.length; i++)
		{
			if (winners[i] != null)
			{
				HashNode currentNode = winners[i];
				int chainLength = currentNode.length();
				if (chainLength > largestChain)
					largestChain = chainLength;
				if (chainLength > 1){
					numCollisions += chainLength - 1;
					chainSum += chainLength;
					numChains++;
				}
				numEntries += chainLength;
			}
			else if (winners[i] == null)
				numSpaces++;
		}

		// count empty spaces
		log("wasted spaces = " + numSpaces + ", " + (int) (numSpaces * 1.0 / this.hashArraySize * 100) + "%");
		// size of the array
		log("size of array = " + this.hashArraySize);
		log("num of entries = " + numEntries);
		// loadfactor (collisions/size)
		log("load factor = " + numCollisions * 1.0 / this.hashArraySize);
		// number of collisions
		log("number of collisions = " + numCollisions);
		// number of chains
		log("number of chains = " + numChains);
		// max chain length
		log("largest chain length = " + largestChain);
		// average chain length
		log("average chain length = " + chainSum * 1.0 / numChains);

		// number of entries in each quarter of the array
		String quadStr = "";
		for (int i : this.determineQuadrants())
			quadStr += i + ", ";
		quadStr = quadStr.substring(0, quadStr.length() - 2);
		log("number of entries per quadrant = " + quadStr);

		// number of collisions in each tenth of the array
		String tenStr = "";
		int tencol = 0;
		for (int i : this.determineTenths())
			tenStr += i + ", ";
		tenStr = tenStr.substring(0, tenStr.length() - 2);
		log("number of collisions per tenth = " + tenStr);
		log("");
	}

	/**
	 * Method to count the number of wasted spaces in the winners array
	 * 
	 * @return number of wasted spaces
	 */
	public int countSpaces()
	{
		int count = 0;
		for (int i = 0; i < this.winners.length; i++)
			if (winners[i] == null)
				count++;
		return count;
	}

	/**
	 * Method to determine the average chain length of the winners array
	 * 
	 * @return average chain length as a double
	 */
	public double averageChainLength()
	{
		int numChains = 0;
		int chainSum = 0;
		for (int i = 0; i < this.winners.length; i++)
		{
			if (winners[i] != null)
			{
				int chainLength = winners[i].length();
				if (chainLength > 1){
					chainSum += chainLength;
					numChains++;
				}
			}
		}
		return chainSum * 1.0 / numChains;
	}

	/**
	 * Helper method to determine how many entries exist in every quadrant of
	 * the array's size
	 * 
	 * @return the number of entries in each quadrant stored as an array
	 */
	private int[] determineQuadrants()
	{
		int len = this.winners.length;
		double oneFourth = len * 1.0 / 4;
		double[] quadVals =
		{ 0.0, oneFourth * 1, oneFourth * 2, oneFourth * 3, oneFourth * 4 };
		int[] quadrants = new int[4];

		for (int i = 0; i < quadVals.length - 1; i++)
		{
			int numEntries = 0;
			for (int j = (int) Math.round(quadVals[i]); j < Math.round(quadVals[i + 1]) && j < len; j++)
			{
				if (this.winners[j] != null)
					numEntries += this.winners[j].length();
			}
			quadrants[i] = numEntries;
		}
		return quadrants;

	}

	/**
	 * Helper method to determine how many collisions occur in every tenth of
	 * the array's size
	 * 
	 * @return the number of collisions in each tenth stored as an array
	 */
	private int[] determineTenths()
	{
		int len = this.winners.length;
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
				if (this.winners[j] != null)
				{
					int chainLen = this.winners[j].length();
					if (chainLen > 1)
						numCollisions += chainLen - 1;
				}
			}
			tenths[i] = numCollisions;
		}
		return tenths;
	}

	public static void main(String... args)
	{
		new TTT_HC().analyzeHashArray();
		bestArraySize();
	}

	/**
	 * Analyze every possible array size to find the best array size having zero
	 * wasted spaces and lowest average chain length in the array.
	 */
	public static void bestArraySize()
	{
		int bestSize = Integer.MAX_VALUE;
		double bestAverage = Double.MAX_VALUE;
		for (int i = 1; i < TTT_HC.numWinners; i++)
		{
			TTT_HC temp = new TTT_HC(i);
			if (temp.countSpaces() == 0)
			{
				double avg = temp.averageChainLength();
				if (avg < bestAverage)
				{
					bestAverage = avg;
					bestSize = i;
				}
			}
		}
		log("The best array size is " + bestSize + ", with an avg chain length of " + bestAverage);
	}

	/**
	 * Because System.out.println() is too long
	 * 
	 * @param o
	 *            Object to be printed in the console
	 */
	public static void log(Object o)
	{
		System.out.println(o.toString());
	}
}
