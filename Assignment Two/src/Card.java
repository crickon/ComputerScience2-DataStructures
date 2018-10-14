/**
 * NVCC Assignment Two: Sorting
 * 
 * @author matthew grillo (Github: "crickon")
 * @version 1.0
 */
public class Card implements Comparable<Card> {
	// Private Constants
	private static final String SUIT_NUM_ERROR = "Suit must be between 0 and 3";
	private static final String RANK_NUM_ERROR = "Rank must be between 1 and 13";
	private static final String SUIT_STR_ERROR = "The Suit \"%s\" does not exist";
	private static final String RANK_STR_ERROR = "The Rank \"%s\" does not exist";

	// Fields
	private Suit suit = null;
	private Rank rank = null;

	// Constructors
	/**
	 * Default Constructor that creates a card with the value of Ace of Clubs
	 */
	public Card() {
		this(0, 1);
	}

	/** 
	 * Constructor that creates a Card based on integer inputs
	 * 
	 * @param suit The number (0-3) of the Card's suit
	 * @param rank The number (1-13) of the Card's rank
	 * 
	 * @throws IllegalArgumentException if the integer is not within the allowed range
	 */
	public Card(int suit, int rank) {
		this.suit = Suit.suitFromInt(suit);
		this.rank = Rank.rankFromInt(rank);

		if (this.suit == null) {
			throw new IllegalArgumentException(SUIT_NUM_ERROR);
		} else if (this.rank == null) {
			throw new IllegalArgumentException(RANK_NUM_ERROR);
		}
	}

	/**
	 * Constructor that creates a Card based on String inputs
	 * @param suit The name of the Card's suit
	 * @param rank The name of the Card's rank
	 * 
	 * @throws IllegalArgumentException if the String given does not match a Suit or Rank
	 */
	public Card(String suit, String rank) {
		try {
			this.suit = Suit.valueOf(suit.toLowerCase());
		} catch (Throwable t) {
			throw new IllegalArgumentException(String.format(SUIT_STR_ERROR, suit));
		}
		try {
			this.rank = Rank.valueOf(rank.toLowerCase());
		} catch (Throwable t) {
			throw new IllegalArgumentException(String.format(RANK_STR_ERROR, rank));
		}
	}

	/**
	 * Constructor that creates a Card based on A String suit and an Integer rank
	 * 
	 * @param suit The name of the Card's suit
	 * @param rank The number (1-13) of the Card's rank
	 * 
	 * @throws IllegalArgumentException if the String given does not match a Suit or if the rank is not within the range
	 */
	public Card(String suit, int rank) {
		try {
			this.suit = Suit.valueOf(suit.toLowerCase());
		} catch (Throwable t) {
			throw new IllegalArgumentException(String.format(SUIT_STR_ERROR, suit));
		}

		this.rank = Rank.rankFromInt(rank);

		if (this.rank == null) {
			throw new IllegalArgumentException(RANK_NUM_ERROR);
		}
	}

	/**
	 * Constructor that creates a Card based on an Integer suit and a String rank
	 * 
	 * @param suit The number (0-3) of the Card's suit
	 * @param rank The name of the Card's rank
	 * 
	 * @throws IllegalArgumentException if the suit is not within the range or if the String given does not match a rank
	 */
	public Card(int suit, String rank) {
		this.suit = Suit.suitFromInt(suit);
		try {
			this.rank = Rank.valueOf(rank.toLowerCase());
		} catch (Throwable t) {
			throw new IllegalArgumentException(String.format(RANK_STR_ERROR, rank));
		}

		if (this.suit == null) {
			throw new IllegalArgumentException(SUIT_NUM_ERROR);
		}
	}

	// Getters
	/**
	 * Method to get the Card's suit value
	 * @return The Card's suit as a String
	 */
	public String getSuit() {
		String str =  suit.toString();
		str = Character.toUpperCase(str.charAt(0)) + str.substring(1, str.length());
		return str;
	}

	/**
	 * Method to get the Card's rank value
	 * @return The Card's rank as an integer
	 */
	public int getRank() {
		return Rank.getInt(rank);
	}

	// Methods
	/**
	 * Method to print the Card's values in a String
	 * 
	 * @return A String consisting of the Card's rank and suit in this format: "[Rank] of [Suit]"
	 */
	public String toString() {
		String suit = this.suit.toString();
		suit = Character.toUpperCase(suit.charAt(0)) + suit.substring(1, suit.length());
		String rank = this.rank.toString();
		rank = Character.toUpperCase(rank.charAt(0)) + rank.substring(1, rank.length());
		return rank + " of " + suit;
	}

	/**
	 * Method to get the Card's rank value in a String
	 * @return The Card's rank as a String
	 */
	public String getRankStr() {
		String str =  rank.toString();
		str = Character.toUpperCase(str.charAt(0)) + str.substring(1, str.length());
		return str;
	}

	/**
	 * Method to get the Card's suit value as an integer
	 * @return The Card's suit as an integer
	 */
	public int getSuitInt() {
		return Suit.getInt(suit);
	}

	/**
	 * Method to compare this Card to another based on their Suits and Ranks.
	 * 
	 * @return 0 if the Cards are equal, -1 if this card is less than, 1 is this card is greater than
	 */
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
	
	/**
	 * Method to determine if this card equals another
	 * @param other Another Card object
	 * @return boolean value of the Cards' equality
	 */
	public boolean equals(Card other) {
		return (this.rank.equals(other.rank) && this.suit.equals(other.suit));
	}

}
