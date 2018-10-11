public class CardTest {
	public static void main(String args[]) {
		try {
			Deck deck = new Deck();
			System.out.println(deck.toString());
			
			Deck deck2 = new Deck(false);
			System.out.println(deck2.toString());
			
			deck2.shuffle();
			System.out.println(deck2.toString());
			
			Deck[] hands = deck.deal(14, 4);
			
			for (Deck d : hands) {
				System.out.println(d.toString());
			}
			System.out.println(deck);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
