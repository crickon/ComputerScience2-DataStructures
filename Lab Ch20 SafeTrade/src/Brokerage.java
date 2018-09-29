
public class Brokerage implements Login {
	private StockExchange exchange;
	
	public Brokerage(StockExchange exchange) {
		this.exchange = exchange;
	}
	
	public int addUser(String name, String password) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void	getQuote(String symbol, Trader trader) {
		
	}

	public int login(String name, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void	logout(Trader trader) {
		
	}
	public void	placeOrder(TradeOrder order) {
		
	}
}
