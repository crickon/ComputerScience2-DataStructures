
public class CardTest {
	public static void main(String args[]) {
		try {
			new Card ("Diamonds", "Ace");
			new Card (0,13);
			new Card ("testing", "strings");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
