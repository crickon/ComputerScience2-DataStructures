
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
		return false;

	}

	public String getName() {
		return name;

	}

	public String getPassword() {
		return pswd;
	}

	public void getQuote(String symbol) {

	}

	public boolean hasMessages() {
		return false;
	}

	public void openWindow() {

	}

	public void placeOrder(TradeOrder order) {

	}

	public void quit() {

	}

	public void receiveMessage(String msg) {

	}

}
