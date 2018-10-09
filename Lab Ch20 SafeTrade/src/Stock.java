import java.util.PriorityQueue;

/**
 * Represents a stock in the SafeTrade project
 * 
 * @author matthewgrillo
 *
 */
public class Stock {
	private String symbol;
	private String name;
	private double price;
	private double lowPrice;
	private double highPrice;
	private double lastPrice;
	
	private PriorityQueue<TradeOrder> buyOrders;
	private PriorityQueue<TradeOrder> sellOrders;

	private int day;

	/**
	 * Constructs a new stock with a given symbol, company name, and starting
	 * price. Sets low price, high price, and last price to the same opening
	 * price. Sets "day" volume to zero. Initializes a priority qieue for sell
	 * orders to an empty PriorityQueue with a PriceComparator configured for
	 * comparing orders in ascending order; initializes a priority qieue for buy
	 * orders to an empty PriorityQueue with a PriceComparator configured for
	 * comparing orders in descending order.
	 */
	public Stock(String symbol, String name, double price) {
		this.symbol = symbol;
		this.name = name;
		this.price = price;
		
		buyOrders = new PriorityQueue<TradeOrder>(new PriceComparator(true));
		sellOrders = new PriorityQueue<TradeOrder>(new PriceComparator(false));
	}

	/**
	 * Returns a quote string for this stock. The quote includes: the company
	 * name for this stock; the stock symbol; last sale price; the lowest and
	 * highest day prices; the lowest price in a sell order (or "market") and
	 * the number of shares in it (or "none" if there are no sell orders); the
	 * highest price in a buy order (or "market") and the number of shares in it
	 * (or "none" if there are no buy orders).
	 * 
	 * Giggle.com (GGGL) Price: 10.00 hi: 10.00 lo: 10.00 vol: 0 Ask: 12.75
	 * size: 300 Bid: 12.00 size: 500
	 */
	public String getQuote() {
		double asking = sellOrders.peek().getPrice();
		double bidding = buyOrders.peek().getPrice();
		int sizeSell = sellOrders.peek().getShares();
		int sizeBid = buyOrders.peek().getShares();
		String quote = String.format("%s (%s) Price: %d hi: %d lo: %d Ask: %d size: %d Bid: %d size: %d", name,
				symbol, price, highPrice, lowPrice, asking, sizeSell, bidding, sizeBid);
		return quote;
	}

	/**
	 * Places a trading order for this stock. Adds the order to the appropriate
	 * priority queue depending on whether this is a buy or sell order. Notifies
	 * the trader who placed the order that the order has been placed, by
	 * sending a message to that trader.
	 */
	public void placeOrder(TradeOrder order) {
		if (order.isBuy()) {
			buyOrders.add(order);
		}else {
			sellOrders.add(order);
		}
		//order.getTrader().sendmessage?
		// TODO
	}

}
