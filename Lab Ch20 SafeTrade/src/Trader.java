
public class Trader implements Comparable<Trader> {
	
	private Brokerage brokerage;
	private String name;
	private String pswd;

	public Trader(Brokerage brokerage, String name, String pswd) {
		this.brokerage = brokerage;
		this.name = name;
		this.pswd = pswd;
	}

	public int compareTo(Trader o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean equals(Object other) {
		//TODO
		return false;

	}

	public String getName() {
		return name;

	}

	public String getPassword() {
		return pswd;
	}

	public void getQuote(String symbol) {
		//TODO
	}

	public boolean hasMessages() {
		//TODO
		return false;
	}

	public void openWindow() {
		//TODO
	}

	public void placeOrder(TradeOrder order) {
		//TODO
	}

	public void quit() {
		//TODO
	}

	public void receiveMessage(String msg) {
		//TODO
	}

}
