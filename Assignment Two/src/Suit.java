
public enum Suit {

	clubs, diamonds, hearts, spades;

	/**
	 * A static array of Suit values useful in loops
	 */
	public static final Suit[] SUITS = { clubs, diamonds, hearts, spades };

	/**
	 * Suit method to determine the integer value of a given Suit
	 * @param suit A Suit value
	 * @return An integer value of that suit
	 */
	public static int getInt(Suit suit) {
		switch (suit) {
		case clubs:
			return 0;
		case diamonds:
			return 1;
		case hearts:
			return 2;
		case spades:
			return 3;
		default:
			return -1;
		}
	}

	/**
	 * Suit method to determine the Suit value of a given integer
	 * @param suit A Suit integer
	 * @return A Suit value of that integer
	 */
	public static Suit suitFromInt(int suit) {
		switch (suit) {
		case 0:
			return clubs;
		case 1:
			return diamonds;
		case 2:
			return hearts;
		case 3:
			return spades;
		default:
			return null;
		}
	}
}
