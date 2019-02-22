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
	private int numCollisions = 0;

	private int[] quadrant = new int[4];
	private int oneFourth = hashArraySize / 4;

	private int[] tenth = new int[10];
	private int oneTenth = hashArraySize / 10;

	private HashNode[] winners;

	public TTT_HC()
	{
		winners = new HashNode[hashArraySize];
		setWinners();
		analyzeHashArray();
	}

	public TTT_HC(int hashArraySize)
	{
		this.hashArraySize = hashArraySize;
		winners = new HashNode[hashArraySize];
		setWinners();
		analyzeHashArray();
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
				addFourth(hash);
				if (winners[hash] == null)
					winners[hash] = hashObj;
				else
				{
					HashNode lastNode = winners[hash];
					while (lastNode.hasNext())
						lastNode = lastNode.getNext();
					lastNode.setNext(hashObj);
					numCollisions++;
					addTenth(hash);
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

	private void addFourth(int hash)
	{
		for (int i = 1; i <= this.quadrant.length; i++)
			if (hash >= this.oneFourth * (i - 1) && hash < this.oneFourth * i)
				this.quadrant[i - 1]++;
	}

	private void addTenth(int hash)
	{
		for (int i = 1; i <= this.tenth.length; i++)
			if (hash >= this.oneTenth * (i - 1) && hash < this.oneTenth * i)
				this.tenth[i - 1]++;
	}

	private void analyzeHashArray()
	{
		int numSpaces = 0;
		int numChains = 0;
		int largestChain = 0;
		int chainSum = 0;
		int cl = 0;
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
			}
			else if (winners[i] == null)
				numSpaces++;
		}

		// count empty spaces
		log("wasted spaces = " + numSpaces + ", " + (int) (numSpaces * 1.0 / this.hashArraySize * 100) + "%");
		// size of the array
		log("size of array = " + this.hashArraySize);
		// loadfactor (collisions/size)
		log("load factor = " + this.numCollisions * 1.0 / this.hashArraySize);
		// number of collisions
		log("number of collisions = " + this.numCollisions);
		// number of chains
		log("number of chains = " + numChains);
		// max chain length
		log("largest chain length = " + largestChain);
		// average chain length
		log("average chain length = " + chainSum*1.0 / numChains);

		// number of entries in each quarter of the array
		String quadStr = "";
		for (int i : this.quadrant)
			quadStr += i + ", ";
		quadStr = quadStr.substring(0, quadStr.length() - 2);
		log("number of entries per quadrant = " + quadStr);

		// number of collisions in each tenth of the array
		String tenStr = "";
		for (int i : this.tenth)
			tenStr += i + ", ";
		tenStr = tenStr.substring(0, tenStr.length() - 2);
		log("number of collisions per tenth = " + tenStr);
		log("");
	}
	
	public boolean isWin(String board)
	{
		int hash = tttHashCode(board);
		if (winners[hash].contains(board))
			return true;
		return false;
	}

	private void log(Object o)
	{
		System.out.println(o.toString());
	}

	public static void main(String... args)
	{
		// finding the sweet spot to not waste any space on the array:
		
		// new TTT_HC((int) Math.pow(3, 4));
		// new TTT_HC((int) Math.pow(3, 5));
		// new TTT_HC((int) Math.pow(3, 5)-15);
		// new TTT_HC((int) Math.pow(3, 5)-16);
		new TTT_HC();
	}

}
