import java.util.Comparator;

public class PriceComparator implements Comparator<TradeOrder> {

	private boolean ascendingFlag;

	/**
	 * Constructs a price comparator that compares two orders in ascending
	 * order. Sets the private boolean ascending flag to true.
	 */
	public PriceComparator() {
		this.ascendingFlag = true;
	}

	/**
	 * Constructs a price comparator that compares two orders in ascending or
	 * descending order. The order of comparison depends on the value of a given
	 * parameter. Sets the private boolean ascending flag to asc.
	 */
	public PriceComparator(boolean asc) {
		this.ascendingFlag = asc;
	}

	/**
	 * 0 if both orders are market orders; -1 if order1 is market and order2 is
	 * limit; 1 if order1 is limit and order2 is market; the difference in
	 * prices, rounded to the nearest cent, if both order1 and order2 are limit
	 * orders. In the latter case, the difference returned is cents1 - cents2 or
	 * cents2 - cents1, depending on whether this is an ascending or descending
	 * comparator (ascending is true or false).
	 */
	public int compare(TradeOrder o1, TradeOrder o2) {
		if (o1.isMarket() && o2.isMarket())
			return 0;
		if (o1.isLimit() && o2.isMarket())
			return 1;
		if (o1.isMarket() && o2.isLimit())
			return -1;
		if (o1.isLimit() && o2.isLimit()) {
			int onePrice = (int) Math.round(o1.getPrice());
			int twoPrice = (int) Math.round(o2.getPrice());
			// Compare in ascending order
			if (ascendingFlag) {
				return onePrice-twoPrice;
			}
			// Compare in descending order
			else {
				return twoPrice-onePrice;
			}
		}
		return 0;
	}
}
