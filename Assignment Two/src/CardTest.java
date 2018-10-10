public class CardTest {
	public static void main(String args[]) {
		try {
			Deck deck = new Deck();
			Deck[] hands = deck.deal(2,2);
			
			for (Deck d : hands) {
				System.out.println(d.toString());
			}
			System.out.println(deck);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
