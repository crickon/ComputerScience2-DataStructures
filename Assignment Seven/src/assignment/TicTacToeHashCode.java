package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * NVCC Assignment Seven: "TicTacToe Hash Function"
 * 
 * @author Matthew "crickon" Grillo
 *
 */
public class TicTacToeHashCode extends Board
{
	private static final String testFile = "TTT_Tests.txt";
	private static final String KELLY_WINNERS = "TicTacToeWinners.txt";
	private static final String MY_WINNERS = "boardLists/winningBoards.txt";
	private static final String ALL = "TTT_all.txt";

	private static boolean random = false;

	private final int[] powersOf3 =
	{ 1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683 };
	private final int maxPow = powersOf3.length - 1;
	private final int boardSize = 3;
	private final int boardStringLength = 9;

	private boolean[] winners;
	// True if the hash string that maps to this index is a winner,
	// false otherwise

	private static final String DEFAULT_TITLE = "Tic Tac Toe";

	/**
	 * Default constructor to initialize JFrame with default window title, then
	 * call the other constructor
	 */
	TicTacToeHashCode()
	{
		this(DEFAULT_TITLE);
	}

	/**
	 * Constructor to create Board Object, initialize winners look up table, and
	 * set winning hash values
	 * 
	 * @param s
	 *            JFrame window title
	 */
	TicTacToeHashCode(String s)
	{
		super(s);
		// win lookup table by the board's hashcode as an index.
		winners = new boolean[powersOf3[maxPow]];
		setWinners();
		System.out.println("Wasted spaces in the array = " + wastedSpace());
	}

	/**
	 * helper method to initialize winning hash values
	 */
	private void setWinners()
	{
		try
		{
			Scanner trueTacs = new Scanner(new File(KELLY_WINNERS));
			while (trueTacs.hasNextLine())
			{
				String ttt = trueTacs.nextLine();
				int hash = myHashCode(ttt);
				winners[hash] = true;
			}
			trueTacs.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println(KELLY_WINNERS + " not found");
			System.exit(-1);
		}
	}

	@Override
	public int myHashCode()
	{
		int hash = 0;
		try
		{
			for (int r = 0; r < boardSize; r++)
				for (int c = 0; c < boardSize; c++)
				{
					int n = ((r * boardSize) + (c));
					hash += myCharValue(super.charAt(r, c)) * powersOf3[n];
				}
		}
		catch (NullPointerException e)
		{
		}
		return hash;
	}

	/**
	 * HashCode parser that takes in a board string instead of using the super
	 * class's
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

	@Override
	public boolean isWin(String s)
	{
		return winners[myHashCode(s)];

	}

	@Override
	public boolean isWin()
	{
		return winners[this.myHashCode(super.getBoardString())];
	}

	/**
	 * Function to determine the number of wasted spaces in the lookup table's
	 * array
	 * 
	 * @return number of wasted spaces
	 */
	public int wastedSpace()
	{
		int space = 0;
		for (int i = 0; i < this.winners.length; i++)
		{
			if (this.winners[i] == false)
				space++;
		}
		return space;
	}

	/**
	 * helper method to set the super class's TicTacToe visuals. (board,
	 * hashcode and whether the board is a valid win.)
	 * 
	 * @param board
	 *            String representation of a board
	 */
	public void display(String board)
	{
		super.show(board);
		super.setHashCodeLabel(myHashCode(board));
		super.setWinnerLabel(TicTacToe.isWinString(board));
	}

	/**
	 * Main run method for Part 1 of the assignment. Create TicTacToeHashCode
	 * Object, read in test file and display the valid winning boards for 4
	 * seconds (4000ms) each.
	 * 
	 * @param args
	 *            Command Line Arguments
	 * @throws InterruptedException
	 *             if the Thread is interrupted
	 */
	public static void main(String... args) throws InterruptedException
	{
		TicTacToeHashCode board = new TicTacToeHashCode();

		// for each winning board from test file, display for 4 seconds
		try
		{
			Scanner testInput = new Scanner(new File(testFile));
			while (testInput.hasNextLine())
			{
				String line = testInput.nextLine();
				if (board.isWin(line))
				{
					board.display(line);
					System.out.println(line);
					Thread.sleep(4000);
				}
			}

		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not find testFile: " + testFile);
			System.exit(-1);
		}

		while (random)
		{
			board.displayRandomString();
			Thread.sleep(4000);
		}
	}

}
