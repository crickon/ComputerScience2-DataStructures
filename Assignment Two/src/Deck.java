import java.util.Random;

public class Deck {
	// Constants
	private static int DECKSIZE = 52;

	// Fields
	private Card[] deck;
	private int topCard; // Literally never used and I don't see a point in it

	// Constructors
	/**
	 * Default constructor that creates a sorted and full deck of cards
	 */
	public Deck() {
		this(true);
	}

	/**
	 * Constructor that creates a full deck of cards, either sorted or shuffled
	 * 
	 * @param sorted True if the deck is to be sorted, false if the deck is to be
	 *               shuffled
	 */
	public Deck(boolean sorted) {
		setDeck();
		if (sorted == false)
			this.shuffle();
	}

	/**
	 * Constructor that creates a deck of cards based on the array of cards given
	 * 
	 * @param cards An array of cards to be in the deck.
	 */
	public Deck(Card[] cards) {
		this.deck = cards;
	}

	// Methods
	/**
	 * Methods that mixes up the deck so that it is not sorted. O(n). Goes through
	 * every position of the Array of Cards and sets the card somewhere else
	 * randomly in the deck.
	 */
	public void shuffle() {
		// Go through every position in the Array and set the value to a
		// different position
		Random rand = new Random();
		for (int i = 0; i < deck.length; i++) {
			int r = rand.nextInt(DECKSIZE);
			Card temp = deck[r];
			deck[r] = deck[i];
			deck[i] = temp;
		}
	}

	/**
	 * Create a String that separates the toString of every card by a new line if
	 * the Array is smaller than the max deck size (52). If the deck is the maximum
	 * size, then separate the toStrings into 4 columns using tab, with a new line
	 * between every 4 columns.
	 * 
	 * @return String
	 */
	public String toString() {
		String str = "";
		if (deck.length == DECKSIZE) {
			for (Rank rank : Rank.RANKS) {
				for (Suit suit : Suit.SUITS) {
					str += new Card(suit.toString(), rank.toString()).toString() + "\t";
				}
				str += "\n";
			}
			return str;
		}
		for (Card c : deck)
			if (c != null && c.toString() != null)
				str += c.toString() + "\n";
		return str;
	}

	/**
	 * Compares the toStrings of deck (which will be equal if the decks are equal).
	 * 
	 * @param other Another Deck Object
	 * @return boolean value of the Decks' equality
	 */
	public boolean equals(Deck other) {
		return this.toString().equals(other.toString());
	}

	/**
	 * Creates a deck for each hand with the number of cards per hand and removes
	 * the cards from the original deck.
	 * 
	 * @param hands number of hands (as decks) to be dealt to
	 * @param cards number of cards per hand
	 * @return Array of Decks containing the hands that were drawn
	 */
	public Deck[] deal(int hands, int cards) {
		int numCards = hands * cards;
		if (deck.length < numCards)
			return null;

		Deck[] retDeck = new Deck[hands];
		Card[] newDeck = new Card[deck.length-numCards];

		Card[] temp = new Card[cards];
		int index = 0;
		int hand = 0;
		int card = 0;
		for (int i = 0; i < deck.length; i++) {
			if (i <= numCards) {
				if (card == cards || i == numCards) {
					retDeck[hand] = new Deck(temp);
					temp = new Card[cards];
					hand++;
					card = 0;
				}
				temp[card] = deck[i];
				card++;
			}
			if (i >= numCards){
				newDeck[index] = deck[i];
				index++;
			}
		}
		this.deck = newDeck;
		return retDeck;
	}

	/**
	 * Function that picks a random card, removes it from the deck, then returns the
	 * Card
	 * 
	 * @return The Card picked at random
	 */
	public Card pick() {
		Card[] newDeck = new Card[deck.length - 1];
		Random rand = new Random();
		int r = rand.nextInt(deck.length);
		for (int i = 0, j = 0; i < deck.length; i++) {
			if (i != r) {
				newDeck[j] = deck[i];
				j++;
			}
		}
		Card retCard = deck[r];
		this.deck = newDeck;
		return retCard;
	}

	/**
	 * Method to perform a SelectionSort on the Deck of cards
	 */
	public void selectionSort() {
		for (int n = deck.length; n > 1; n--) {
			Card greatest = deck[0];
			int greatIndex = 0;

			for (int i = 1; i < n; i++) {
				if (deck[i].compareTo(greatest) > 0) {
					greatest = deck[i];
					greatIndex = i;
				}
			}

			Card temp = deck[n - 1];
			deck[n - 1] = deck[greatIndex];
			deck[greatIndex] = temp;
		}
	}

	private Card[] temp;

	/**
	 * Method to perform a MergeSort on the Deck of cards
	 */
	public void mergeSort() {
		int n = deck.length;
		temp = new Card[n];
		recursiveSort(this.deck, 0, n - 1);
	}

	private void recursiveSort(Card[] a, int from, int to) {
		if (to - from < 2) {
			if (to > from && a[to].compareTo(a[from]) < 0) {
				Card aTemp = a[to];
				a[to] = a[from];
				a[from] = aTemp;
			}
		} else {
			int middle = (from + to) / 2;
			recursiveSort(a, from, middle);
			recursiveSort(a, middle + 1, to);
			merge(a, from, middle, to);
		}
	}

	private void merge(Card[] a, int from, int middle, int to) {
		int i = from, j = middle + 1, k = from;

		while (i <= middle && j <= to) {
			if (a[i].compareTo(a[j]) < 0) {
				temp[k] = a[i];
				i++;
			} else {
				temp[k] = a[j];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			temp[k] = a[i];
			i++;
			k++;
		}
		while (j <= to) {
			temp[k] = a[j];
			j++;
			k++;
		}

		for (k = from; k <= to; k++) {
			a[k] = temp[k];
		}
	}

	// Non required methods
	private void setDeck() {
		this.deck = new Card[DECKSIZE];
		int count = 0;
		for (Suit suit : Suit.SUITS) {
			for (Rank rank : Rank.RANKS) {
				this.deck[count] = new Card(suit.toString(), rank.toString());
				count++;
			}
		}
	}
}
