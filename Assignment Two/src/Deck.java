import java.util.Random;

public class Deck
{
	// Constants
	private static int DECKSIZE = 52;

	// Fields
	private Card[] deck;
	private int topCard;

	// Constructors
	/**
	 * Default constructor that creates a sorted and full deck of cards
	 */
	public Deck()
	{
		this(true);
	}

	/**
	 * Constructor that creates a full deck of cards, either sorted or shuffled
	 * 
	 * @param sorted
	 *            True if the deck is to be sorted, false if the deck is to be
	 *            shuffled
	 */
	public Deck(boolean sorted)
	{
		fillDeck();
		if (sorted == false)
			this.shuffle();
		topCard = DECKSIZE - 1; // the last card in deck
	}

	/**
	 * Constructor that creates a deck of cards based on the array of cards
	 * given
	 * 
	 * @param cards
	 *            An array of cards to be in the deck.
	 */
	public Deck(Card[] cards)
	{
		this.deck = new Card[DECKSIZE];
		for (int i = 0; i < cards.length; i++)
		{
			this.deck[i] = cards[i];
		}
		topCard = cards.length - 1;
	}

	// Methods
	private void fillDeck()
	{
		this.deck = new Card[DECKSIZE];
		int count = 0;
		for (Suit suit : Suit.SUITS)
		{
			for (Rank rank : Rank.RANKS)
			{
				this.deck[count] = new Card(suit.toString(), rank.toString());
				count++;
			}
		}
	}

	/**
	 * Method that mixes up the deck so that it is not sorted. O(n). Goes
	 * through every position of the Array of Cards and sets the card somewhere
	 * else randomly in the deck.
	 */
	public void shuffle()
	{
		Random rand = new Random();
		for (int i = 0; i <= topCard; i++)
		{
			int r = rand.nextInt(topCard + 1);
			Card temp = deck[r];
			deck[r] = deck[i];
			deck[i] = temp;
		}
	}

	/**
	 * Create a String of every card separated by a new line, unless the deck is
	 * full. If the deck is full then each suit will have a column filled with
	 * the order of the ranks as they appear in the deck.
	 * 
	 * @return String
	 */
	public String toString()
	{
		String str = "";
		if (topCard == DECKSIZE - 1)
		{
			Card[][] bySuit = new Card[Suit.SUITS.length][Rank.RANKS.length];
			int[] suitCounts = new int[Suit.SUITS.length];
			for (int i = 0; i <= topCard; i++)
			{
				int suit = deck[i].getSuitInt();
				bySuit[suit][suitCounts[suit]] = deck[i];
				suitCounts[suit]++;
			}
			for (int j = 0; j < bySuit[0].length; j++)
			{
				for (int i = 0; i < bySuit.length; i++)
				{
					String ext = "";
					if (i == 0 || i == 2)
						ext = "\t\t";
					else if (i == 1)
						ext = " \t";
					else if (i == 3)
						ext = "\n";
					str += bySuit[i][j].toString() + ext;
				}
			}

			return str;
		}
		for (Card c : deck)
			if (c != null && c.toString() != null)
				str += c.toString() + "\n";
		return str;
	}

	/**
	 * Method to determine if two Decks are equal to each other
	 * 
	 * @param other
	 *            Another Deck Object
	 * @return boolean value of the Decks' equality
	 */
	public boolean equals(Deck other)
	{
		if (this.topCard != other.topCard)
			return false;
		for (int i = 0; i <= topCard; i++)
			if (this.deck[i].compareTo(other.deck[i]) != 0)
				return false;

		return true;
	}

	/**
	 * Creates a deck for each hand with the number of cards per hand and
	 * removes the cards from the original deck.
	 * 
	 * @param hands
	 *            number of hands (as decks) to be dealt to
	 * @param cards
	 *            number of cards per hand
	 * @return Array of Decks containing the hands that were drawn
	 */
	public Deck[] deal(int hands, int cards)
	{
		if (hands * cards > topCard + 1)
			return null;

		Deck[] handDeck = new Deck[hands];
		Card[] temp = new Card[cards];
		for (int i = 0; i < hands; i++)
		{
			for (int j = 0; j < cards; j++)
			{
				temp[j] = this.deck[topCard];
				this.deck[topCard] = null;
				topCard--;
			}
			handDeck[i] = new Deck(temp);
			temp = new Card[cards];
		}
		return handDeck;
	}

	/**
	 * Function that picks a random card, removes it from the deck, then returns
	 * the Card
	 * 
	 * @return The Card picked at random
	 */
	public Card pick()
	{
		if (topCard <= 0)
			return null;
		Random random = new Random();
		int rand = random.nextInt(topCard + 1);

		Card pick = deck[rand];

		for (int i = rand; i < topCard; i++)
		{
			deck[i] = deck[i++];
		}
		deck[topCard] = null;
		topCard--;

		return pick;
	}

	/**
	 * Method to perform a SelectionSort on the Deck of cards
	 */
	public void selectionSort()
	{
		for (int n = topCard + 1; n > 1; n--)
		{
			Card greatest = deck[0];
			int greatIndex = 0;

			for (int i = 1; i < n; i++)
			{
				if (deck[i].compareTo(greatest) > 0)
				{
					greatest = deck[i];
					greatIndex = i;
				}
			}

			Card temp = deck[n - 1];
			deck[n - 1] = deck[greatIndex];
			deck[greatIndex] = temp;
		}
	}

	/**
	 * Method to perform a MergeSort on the Deck of cards
	 */
	public void mergeSort()
	{
		split(0, deck.length - 1);
	}

	private void split(int low, int high)
	{
		if (low < high)
		{
			int mid = (low + high) / 2;
			split(low, mid);
			split(mid + 1, high);
			merge(low, mid, high);
		}
	}

	private void merge(int low, int mid, int high)
	{
		Card[] temp = new Card[high + 1];
		for (int a = low; a <= high; a++)
			temp[a] = deck[a];
		int i = low;
		int j = mid + 1;
		int k = low;
		
		while (i < mid && j < high){
			if (deck[i].compareTo(deck[j]) <= 0) {
                deck[k] = temp[i];
                i++;
            } else {
                deck[k] = temp[j];
                j++;
            }
            k++;
		}
		while (i <= mid) {
            deck[k] = temp[i];
            k++;
            i++;
        }
	}

	public int getTopCard()
	{
		return this.topCard;
	}
}
