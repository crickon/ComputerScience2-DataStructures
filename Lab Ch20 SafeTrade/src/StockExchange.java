import java.util.HashMap;

/**
 * Represents a stock exchange. A StockExchange keeps a HashMap of stocks, keyed
 * by a stock symbol. It has methods to list a new stock, request a quote for a
 * given stock symbol, and to place a specified trade order.
 * 
 * @author matthewgrillo
 *
 */
public class StockExchange {
	private HashMap<String, Stock> stocks;

	/**
	 * Constructs a new stock exchange object. Initializes listed stocks to an
	 * empty map (a HashMap).
	 */
	public StockExchange() {
		stocks = new HashMap<String, Stock>();
	}

	/**
	 * Returns a quote for a given stock.
	 */
	public String getQuote(String symbol) {
		//TODO
		return symbol;
	}

	/**
	 * Adds a new stock with given parameters to the listed stocks.
	 */
	public void listStock(String symbol, String name, double price) {
		Stock stock = new Stock(symbol, name, price);
		stocks.put(symbol, stock);
	}

	/**
	 * Places a trade order by calling stock.placeOrder for the stock specified by the stock symbol in the trade order.
	 */
	public void placeOrder(TradeOrder order) {
		//TODO
	}
}
