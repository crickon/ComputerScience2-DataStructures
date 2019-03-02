package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TTT_HC
{
	private final String KELLY_WINNERS = "TicTacToeWinners.txt";
	private final String MY_WINNERS = "boardLists/winningBoards.txt";

	private final int[] powersOf3 =
	{ 1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683 };
	private final int boardStringLength = 9;

	// 3^4 has no wasted space, but long chain lengths
	// 3^5 has 15 wasted spaces, but shorter chain lengths
	// 3^5-16 has 0 wasted spaces and shorter chain length than 3^5.. perfect
	private int hashArraySize = powersOf3[5] - 16;

	private HashNode[] winners;

	public TTT_HC()
	{
		winners = new HashNode[hashArraySize];
		setWinners();
	}

	public TTT_HC(int hashArraySize)
	{
		this.hashArraySize = hashArraySize;
		winners = new HashNode[hashArraySize];
		setWinners();
	}

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

	private int tttHashCode(String ttt)
	{
		// condense the hashcode to fit in a smaller array using modulus
		// remainder will always be < hashArraySize
		return myHashCode(ttt) % this.hashArraySize;
	}

	private int myHashCode(String s)
	{
		int hash = 0;
		for (int i = 0; i < boardStringLength && i < s.length(); i++)
		{
			hash += myCharValue(s.charAt(i)) * powersOf3[i];
		}
		return hash;
	}

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

	public boolean isWin(String board)
	{
		int hash = tttHashCode(board);
		if (winners[hash].contains(board))
			return true;
		return false;
	}

	public void analyzeHashArray()
	{
		int numSpaces = 0;
		int numChains = 0;
		int largestChain = 0;
		int chainSum = 0;
		int cl = 0;
		int numCollisions = 0;
		int numEntries = 0;
		for (int i = 0; i < this.winners.length; i++)
		{
			if (winners[i] != null)
			{
				HashNode currentNode = winners[i];
				int chainLength = currentNode.length();
				numChains++;
				chainSum += chainLength;
				if (chainLength > largestChain)
					largestChain = chainLength;
				if (chainLength > 1)
					numCollisions += chainLength - 1;
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

	private int[] determineQuadrants()
	{
		int[] quadrants = new int[4];
		int oneFourth = hashArraySize / 4;// fix integer division

		for (int i = 0; i < 4; i++)
		{
			int numEntries = 0;
			for (int j = oneFourth * i; j < oneFourth * (i + 1) && j < hashArraySize; j++)
			{
				if (this.winners[j] != null)
					numEntries += this.winners[j].length();
			}
			quadrants[i] = numEntries;
		}
		return quadrants;

	}

	private int[] determineTenths()
	{
		int[] tenths = new int[10];
		int oneTenth = hashArraySize / 10;// i hate integer division.....

		for (int i = 0; i < 10; i++)
		{
			int numCollisions = 0;
			for (int j = oneTenth * i; j < oneTenth * (i + 1); j++)
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
		// finding the sweet spot to not waste any space on the array:

		// new TTT_HC((int) Math.pow(3, 4));
		// new TTT_HC((int) Math.pow(3, 5));
		// new TTT_HC((int) Math.pow(3, 5)-15);
		// new TTT_HC((int) Math.pow(3, 5)-16);
		new TTT_HC().analyzeHashArray();
	}

	private void log(Object o)
	{
		System.out.println(o.toString());
	}
}
