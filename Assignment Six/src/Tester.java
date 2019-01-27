public class Tester
{
	private static ExpressionTree pf;
	
	public static void main(String[] args)
	{
		String postfixExample = 
				// "24+97-/a";
				// "/***35+";
				"33+44+*55+66+**";
		char[] arr = postfixExample.toCharArray();
		String[] strarray = new String[arr.length];
		for (int i = 0; i < arr.length; i++)
		{
			strarray[i] = arr[i] + "";
		}
		
		pf = new ExpressionTree(null);
		TreeNode root = pf.buildTree(strarray);
		
		log(pf.evalTree() + " = " + pf.postfixEval(strarray));
		log(pf.toPrefixNotation());
		log(pf.toInfixNotation());
		log(pf.toPostfixNotation());
	}
	
	public static void log(Object o)
	{
		System.out.println(o.toString());
	}
}
