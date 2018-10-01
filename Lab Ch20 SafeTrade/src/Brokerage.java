import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author matthewgrillo
 *
 *         Represents a brokerage.
 */
public class Brokerage implements Login {
	// Private Constants
	private static final int NAME_MAX = 10;
	private static final int NAME_MIN = 4;
	private static final int PASSWORD_MAX = 10;
	private static final int PASSWORD_MIN = 2;

	private StockExchange exchange;

	private TreeMap<String, Trader> activeTraders;
	private TreeSet<Trader> loggedTraders;

	/**
	 * Constructs new brokerage affiliated with a given stock exchange.
	 * Initializes the map of traders to an empty map (a TreeMap<String,
	 * Trader>), keyed by trader's name; initializes the set of active
	 * (logged-in) traders to an empty set (a TreeSet).
	 */
	public Brokerage(StockExchange exchange) {
		this.exchange = exchange;

		activeTraders = new TreeMap<String, Trader>();
	}

	/**
	 * Tries to register a new trader with a given screen name and password. If
	 * successful, creates a Trader object for this trader and adds this trader
	 * to the map of all traders (using the screen name as the key).
	 */
	public int addUser(String name, String password) {
		if (activeTraders.containsKey(name))
			return -3;
		else if (name.length() > NAME_MAX || name.length() < NAME_MIN)
			return -1;
		else if (password.length() > PASSWORD_MAX || password.length() < PASSWORD_MIN)
			return -2;

		Trader user = new Trader(this, name, password);
		activeTraders.put(name, user);

		return 0;
	}

	/**
	 * Tries to login a trader with a given screen name and password. If no
	 * messages are waiting for the trader, sends a "Welcome to SafeTrade!"
	 * message to the trader. Opens a dialog window for the trader by calling
	 * trader's openWindow() method. Adds the trader to the set of all logged-in
	 * traders.
	 */
	public int login(String name, String password) {
		if (!activeTraders.containsKey(name))
			return -1;
		Trader user = activeTraders.get(name);
		if (!user.getPassword().equals(password))
			return -2;
		else if (loggedTraders.contains(user))
			return -3;

		user.openWindow();
		loggedTraders.add(user);

		return 0;
	}

	/**
	 * Removes a specified trader from the set of logged-in traders.
	 */
	public void logout(Trader trader) {
		if (loggedTraders.contains(trader))
			loggedTraders.remove(trader);
	}

	/**
	 * Requests a quote for a given stock from the stock exachange and passes it
	 * along to the trader by calling trader's receiveMessage method.
	 */
	public void getQuote(String symbol, Trader trader) {
		//TODO
	}

	/**
	 * Places an order at the stock exchange.
	 */
	public void placeOrder(TradeOrder order) {
		//TODO
	}
}
