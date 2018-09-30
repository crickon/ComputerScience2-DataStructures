/**
 * NVCC Assignment Two: Sorting
 * 
 * @author matthew grillo (Github: "crickon")
 * @version 1.0
 */
public class Card implements Comparable<Card> {
	// Private Constants
	private static final String SUIT_NUM_ERROR = "Suit must be between 0 and 4";
	private static final String RANK_NUM_ERROR = "Rank must be between 1 and 13";
	private static final String SUIT_STR_ERROR = "The Suit \"%s\" does not exist";
	private static final String RANK_STR_ERROR = "The Rank \"%s\" does not exist";

	// Fields
	private Suit suit = null;
	private Rank rank = null;

	// Constructors
	public Card() {
		this(0, 0);
	}

	public Card(int suit, int rank) {
		this.suit = Suit.suitFromInt(suit);
		this.rank = Rank.rankFromInt(rank);

		if (this.suit == null) {
			throw new IllegalArgumentException(SUIT_NUM_ERROR);
		} else if (this.rank == null) {
			throw new IllegalArgumentException(RANK_NUM_ERROR);
		}
	}

	public Card(String suit, String rank) {
		try {
			this.suit = Suit.valueOf(suit);
		} catch (Throwable t) {
			throw new IllegalArgumentException(String.format(SUIT_STR_ERROR, suit));
		}
		try {
			this.rank = Rank.valueOf(rank);
		} catch (Throwable t) {
			throw new IllegalArgumentException(String.format(RANK_STR_ERROR, rank));
		}
	}

	public Card(String suit, int rank) {
		try {
			this.suit = Suit.valueOf(suit);
		} catch (Throwable t) {
			throw new IllegalArgumentException(String.format(SUIT_STR_ERROR, suit));
		}

		this.rank = Rank.rankFromInt(rank);

		if (this.rank == null) {
			throw new IllegalArgumentException(RANK_NUM_ERROR);
		}
	}

	public Card(int suit, String rank) {
		this.suit = Suit.suitFromInt(suit);
		try {
			this.rank = Rank.valueOf(rank);
		} catch (Throwable t) {
			throw new IllegalArgumentException(String.format(RANK_STR_ERROR, rank));
		}

		if (this.suit == null) {
			throw new IllegalArgumentException(SUIT_NUM_ERROR);
		}
	}

	// Getters
	public String getSuit() {
		return suit.toString();
	}

	public int getRank() {
		return Rank.getInt(rank);
	}

	// Methods
	public String toString() {
		return rank.toString() + " of " + suit.toString();
	}

	public String getRankStr() {
		return rank.toString();
	}

	public int getSuitInt() {
		return Suit.getInt(suit);
	}

	public int compareTo(Card other) {
		// check for equality first
		if (this.toString().equals(other.toString()))
			return 0;

		if (this.getSuitInt() > other.getSuitInt())
			return 1;
		else if (this.getSuitInt() < other.getSuitInt())
			return -1;
		else {
			if (this.getRank() > other.getRank())
				return 1;
			else if (this.getRank() < other.getRank())
				return -1;
		}
		
		return 0; // the method would never reach this point. :( look at how lonely this return is
	}

	public boolean equals(Card other) {
		return this.toString().equals(other.toString());
	}

}
