public class CardTest
{

	public static void main(String[] args)
	{
		//constructorTest();
		sortingTest();
	}

	private static void constructorTest()
	{
		
	}

	private static void sortingTest()
	{
		String str = "";
		Deck sorted = new Deck(true);
		Deck shuffled = new Deck(false);
		shuffled.selectionSort();
		str += "Selection Sort - " + (sorted.equals(shuffled) ? "works" : "doesnt work") + "\n";

		shuffled = new Deck(false);
		shuffled.mergeSort();
		str += "Merge Sort - " + (sorted.equals(shuffled) ? "works" : "doesnt work") + "\n";
		System.out.print(str);
	}
}
