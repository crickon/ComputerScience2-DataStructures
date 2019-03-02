package assignment;

/**
 * String object with custom hashCode for TicTacToe boards to override java's
 * default hashCode method.
 * 
 * @author Matthew "crickon" Grillo
 *
 */
public class TTTHashString
{
	private static final int boardStringLength = 9;

	private static final int[] powersOf3 =
	{ 1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683 };

	private String str;

	/**
	 * Constructor to initialize and set board string
	 * 
	 * @param str
	 *            String representation of a TicTacToe board
	 */
	public TTTHashString(String str)
	{
		this.str = str;
	}

	/**
	 * Unique hashCode method from part 1 of the assignment
	 */
	@Override
	public int hashCode()
	{
		int hash = 0;
		for (int i = 0; i < boardStringLength && i < str.length(); i++)
		{
			hash += myCharValue(str.charAt(i)) * powersOf3[i];
		}
		return hash;
	}

	/**
	 * Helper method to evaluate TicTacToe tiles from their char value
	 * 
	 * @param c
	 *            TTT tile char value
	 * @return integer value of char
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
}
