import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
	private boolean ascending;

	public CardComparator() {
		this(true);
	}

	public CardComparator(boolean ascending) {
		this.ascending = ascending;
	}

	public int compare(Card one, Card two) {
		if (ascending) {
			if (one.getRank() > two.getRank())
				return 1;
			else if (one.getRank() < two.getRank())
				return -1;
		} else {
			if (one.getRank() < two.getRank())
				return 1;
			else if (one.getRank() > two.getRank())
				return -1;
		}
		return 0;
	}
}
