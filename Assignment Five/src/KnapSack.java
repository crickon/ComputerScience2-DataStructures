import java.util.ArrayList;
import java.util.List;

public class KnapSack
{
	public static void main(String[] args)
	{
		new KnapSack(new int[]
		{ 70, 10, 60, 65, 15, 20 }, 40);
		new KnapSack(new int[]
		{ 70, 10, 60, 65, 15, 20 }, 45);
		new KnapSack(new int[]
		{ 70, 10, 60, 65, 15, 20 }, 90);
	}

	public KnapSack(int[] w, int limit)
	{
		int n = w.length - 1;
		log("a: " + knapsackSum(w, n, limit));

		ArrayList<Integer> list = new ArrayList<Integer>();
		log("b: " + knapsackSum(w, n, limit, list) + " " + list.toString());
		log("");
	}

	public int knapsackSum(int[] w, int n, int limit)
	{
		if (n < 0)
			return 0;
		else
		{
			int excluding = knapsackSum(w, n - 1, limit);
			int including = knapsackSum(w, n - 1, limit - w[n]) + w[n];
			if (including > limit)
				return excluding;
			return (excluding > including) ? excluding : including;
		}
	}

	public int knapsackSum(int[] w, int n, int limit, List<Integer> list)
	{
		if (n < 0)
			return 0;
		else
		{

			ArrayList<Integer> exclu = new ArrayList<Integer>();
			int excluding = knapsackSum(w, n - 1, limit, exclu);

			ArrayList<Integer> inclu = new ArrayList<Integer>();
			int including = knapsackSum(w, n - 1, limit - w[n], inclu) + w[n];

			if (including > limit)
			{
				for (int i : exclu)
					list.add(i);
				return excluding;
			}
			
			if (including > excluding)
			{
				for (int i : inclu)
					list.add(i);
				list.add(w[n]);
				return including;
			}
			else
			{
				for (int i : exclu)
					list.add(i);
				return excluding;
			}
		}
	}

	private void log(String string)
	{
		System.out.println(string);
	}
}
