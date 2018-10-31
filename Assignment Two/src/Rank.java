
public enum Rank
{

	ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

	private static final int MIN = 1;
	private static final int MAX = 13;
	/**
	 * A static array of Rank values useful in loops
	 */
	public static final Rank[] RANKS =
	{ ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING };

	/**
	 * Rank method to determine the integer value of a given Rank
	 * 
	 * @param rank
	 *            A Rank value
	 * @return An integer value of that rank
	 */
	public static int getInt(Rank rank)
	{
		for (int i = 0; i < RANKS.length; i++)
		{
			if (RANKS[i].equals(rank))
				return i + 1;
		}
		return -1;
	}

	/**
	 * Rank method to determine the Rank value of a given integer
	 * 
	 * @param rank
	 *            A Rank integer
	 * @return A Rank value of that integer
	 */
	public static Rank rankFromInt(int rank)
	{
		if (rank < MIN || rank > MAX)
			return null;
		return RANKS[rank - 1];
	}
}
