import java.util.ArrayList;

public class CardTest {

	public static void main(String[] args) {
		ArrayList<Long> differences = new ArrayList<Long>();

		Deck d = new Deck();
		int trials = 1000;
		d.deal(2, 2);
		for (int i = 0; i < trials; i++) {
			d.shuffle();
			long t1 = System.nanoTime();
			d.selectionSort();
			long t2 = System.nanoTime();
			differences.add(t2-t1);
		}
		long sum = differences.get(0);
		long min = differences.get(0);
		long max = differences.get(0);
		for (int i = 1; i < differences.size(); i++){
			sum += differences.get(i);
			sum /= 2.0;
			if (differences.get(i) < min)
				min = differences.get(i);
			if (differences.get(i) > max)
				max = differences.get(i);
		}
		
		System.out.println(String.format("Average: %d, Min: %d, Max: %d", sum, min, max));
	}

}
