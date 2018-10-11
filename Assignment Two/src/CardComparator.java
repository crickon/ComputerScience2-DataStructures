import java.util.Comparator;

public class CardComparator implements Comparator {

	public int compare(Object one, Object two) {
		if (one instanceof Card && two instanceof Card) {
			Card first = (Card) one;
			Card second = (Card) two;

			if (first.getRank() > second.getRank())
				return 1;
			else if (first.getRank() < second.getRank())
				return -1;
			return 0;
		} else
			return 0;
	}
}
