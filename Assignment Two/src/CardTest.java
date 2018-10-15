
public class CardTest {

	public static void main(String[] args) {
		Deck d = new Deck();
		Deck[] hands = d.deal(4, 13);
		for (Deck hand : hands)
			System.out.println(hand);
	}

}
