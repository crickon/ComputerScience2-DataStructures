package phun;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequence
{
	// Save data for indexes to prevent unneeded extra recursion
	private static String[][] data;

	public static String longestSub(String s1, String s2)
	{
		char[] arr1 = s1.toCharArray();
		char[] arr2 = s2.toCharArray();
		data = new String[s1.length()][s2.length()];

		return subRec(arr1, 0, arr2, 0);
	}

	private static String subRec(char[] s1, int n, char[] s2, int m)
	{
		if (n > s1.length - 1 || m > s2.length - 1)
			return "";
		if (data[n][m] != null)
			return data[n][m];
		if (s1[n] == s2[m])
			return s1[n] + subRec(s1, n + 1, s2, m + 1);
		else
		{
			String right = subRec(s1, n + 1, s2, m);
			String left = subRec(s1, n, s2, m + 1);
			data[n][m] = right.length() > left.length() ? right : left;
			return data[n][m];
		}
	}

	public static void main(String... args)
	{
		Map<String, String> answerKey = new HashMap<String, String>();
		answerKey.put(longestSub("ABAZDC", "BACBAD"), "ABAD");
		answerKey.put(longestSub("AGGTAB", "GXTXAYB"), "GTAB");
		answerKey.put(longestSub("ABBA", "ABCABA"), "ABBA");

		int i = 1;
		for (String key : answerKey.keySet())
		{
			System.out
					.println(String.format("Test %d = %b", i, key.equals(answerKey.get(key))));
			System.out.println(key + ", " + answerKey.get(key));
			i++;
		}
	}
}
