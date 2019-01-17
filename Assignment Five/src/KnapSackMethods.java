import java.util.ArrayList;
import java.util.List;

/**
 * NVCC Assignment Five
 */
@SuppressWarnings("unused")
public class KnapSackMethods
{
	/**
	 * Knapsack recursion for part A.
	 * <p>
	 * Find sum of the greatest combination of numbers that are under a given
	 * limit
	 * 
	 * @param w
	 *            int array that contains n positive integers (n <= w.length).
	 * @param n
	 *            points to the position in the array
	 * @param limit
	 *            numerical limit for the sum
	 * @return the sum of some of these integers such that it has the largest
	 *         possible value without exceeding limit.
	 */
	public static int knapsackSum(int[] w, int n, int limit)
	{
		if (n < 0)
			return 0;
		else
		{
			int excluding = knapsackSum(w, n - 1, limit);
			int including = knapsackSum(w, n - 1, limit - w[n]) + w[n];
			return (excluding > including || including > limit) ? excluding : including;
		}
	}

	/**
	 * Knapsack recursion for part B.
	 * <p>
	 * Find sum of the greatest combination of numbers that are under a given
	 * limit, and create a list of the combination of integers.
	 * 
	 * @param w
	 *            int array that contains n positive integers (n <= w.length).
	 * @param n
	 *            points to the position in the array
	 * @param limit
	 *            numerical limit for the sum
	 * @param list
	 *            List object to track which numbers in w have been added to the
	 *            sum
	 * @return the sum of some of these integers such that it has the largest
	 *         possible value without exceeding limit.
	 */
	public static int knapsackSum(int[] w, int n, int limit, List<Integer> list)
	{
		if (n < 0)
			return 0;
		else
		{
			ArrayList<Integer> exclu = new ArrayList<Integer>();
			int excluding = knapsackSum(w, n - 1, limit, exclu);

			ArrayList<Integer> inclu = new ArrayList<Integer>();
			int including = knapsackSum(w, n - 1, limit - w[n], inclu) + w[n];

			if (excluding > including || including > limit)
			{
				for (int i : exclu)
					list.add(i);
				return excluding;
			}
			else
			{
				for (int i : inclu)
					list.add(i);
				list.add(w[n]);
				return including;
			}
		}
	}
}
