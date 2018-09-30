
public enum Suit {

	Spades, Hearts, Clubs, Diamonds;

	public static final Suit[] SUITS = { Spades, Hearts, Clubs, Diamonds };
	
	public static int getInt(Suit suit) {
		switch (suit) {
		case Spades:
			return 0;
		case Hearts:
			return 1;
		case Clubs:
			return 2;
		case Diamonds:
			return 3;
		default:
			return -1;
		}
	}

	public static Suit suitFromInt(int suit) {
		switch (suit) {
		case 0:
			return Spades;
		case 1:
			return Hearts;
		case 2:
			return Clubs;
		case 3:
			return Diamonds;
		default:
			return null;
		}
	}
}
