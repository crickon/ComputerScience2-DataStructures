import java.util.Comparator;

public class CardComparator implements Comparator {
	private boolean ascending;

	public CardComparator() {
		this(true);
	}

	public CardComparator(boolean ascending) {
		this.ascending = ascending;
	}

	public int compare(Object one, Object two) {
		if (one instanceof Card && two instanceof Card) {
			Card first = (Card) one;
			Card second = (Card) two;

			if (ascending) {
				if (first.getRank() > second.getRank())
					return 1;
				else if (first.getRank() < second.getRank())
					return -1;
			} else {
				if (first.getRank() < second.getRank())
					return 1;
				else if (first.getRank() > second.getRank())
					return -1;
			}
			return 0;
		} else
			return 0;
	}
}
