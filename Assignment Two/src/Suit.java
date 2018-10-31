
public enum Suit
{

	CLUBS, DIAMONDS, HEARTS, SPADES;

	private static final int MIN = 0;
	private static final int MAX = 3;
	/**
	 * A static array of Suit values useful in loops
	 */
	public static final Suit[] SUITS =
	{ CLUBS, DIAMONDS, HEARTS, SPADES };

	/**
	 * Suit method to determine the integer value of a given Suit
	 * 
	 * @param suit
	 *            A Suit value
	 * @return An integer value of that suit
	 */
	public static int getInt(Suit suit)
	{
		for (int i = 0; i < SUITS.length; i++)
		{
			if (SUITS[i].equals(suit))
				return i;
		}
		return -1;
	}

	/**
	 * Suit method to determine the Suit value of a given integer
	 * 
	 * @param suit
	 *            A Suit integer
	 * @return A Suit value of that integer
	 */
	public static Suit suitFromInt(int suit)
	{
		if (suit < MIN || suit > MAX)
			return null;
		return SUITS[suit];
	}
}
