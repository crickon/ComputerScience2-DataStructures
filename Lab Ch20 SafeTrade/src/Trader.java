import java.util.LinkedList;

public class Trader implements Comparable<Trader> {

	private Brokerage brokerage;
	private String name;
	private String pswd;

	private TraderWindow myWindow;

	private LinkedList<String> inbox;

	public Trader(Brokerage brokerage, String name, String pswd) {
		this.brokerage = brokerage;
		this.name = name;
		this.pswd = pswd;
	}

	/**
	 * Compares this trader to another by comparing their screen names case
	 * blind.
	 */
	public int compareTo(Trader other) {
		return this.name.compareToIgnoreCase(other.getName());
	}

	/**
	 * Indicates whether some other trader is "equal to" this one, based on
	 * comparing their screen names case blind. This method will throw a
	 * ClassCastException if other is not an instance of Trader.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Trader)) {
			throw new ClassCastException();
		}
		Trader other = (Trader) obj;
		return this.name.equalsIgnoreCase(other.getName());

	}

	public String getName() {
		return name;

	}

	public String getPassword() {
		return pswd;
	}

	/**
	 * Requests a quote for a given stock symbol from the brokerage by calling
	 * brokerage's getQuote.
	 */
	public void getQuote(String symbol) {
		brokerage.getQuote(symbol, this);
	}

	public boolean hasMessages() {
		return inbox.isEmpty();
	}

	/**
	 * Creates a new TraderWindow for this trader and saves a reference to it in
	 * myWindow. Removes and displays all the messages, if any, from this
	 * trader's mailbox by calling myWindow.show(msg) for each message.
	 */
	public void openWindow() {
		this.myWindow = new TraderWindow(this);
		for (String s : inbox) {
			myWindow.showMessage(s);
		}
		inbox.clear();
	}

	public void placeOrder(TradeOrder order) {
		// TODO
	}

	public void quit() {
		// TODO
	}

	/**
	 * Adds msg to this trader's mailbox and displays all messages. If this
	 * trader is logged in (myWindow is not null) removes and shows all the
	 * messages in the mailbox by calling myWindow.showMessage(msg) for each msg
	 * in the mailbox.
	 */
	public void receiveMessage(String msg) {
		inbox.add(msg);
		if (myWindow != null) {
			for (String s : inbox) {
				myWindow.showMessage(s);
			}
			inbox.clear();
		}
	}

}
