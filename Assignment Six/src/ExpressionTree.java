
/**
 * Assignment Six: PostFix Notation
 * Author: Matthew "crickon" Grillo
 * Date: January 31, 2019
 */

import java.util.Stack;

public class ExpressionTree extends TreeNode implements Expressions
{
	/**
	 * Exception message when an operator does not exist
	 */
	public static final String INVALID_OP = "Operator %s does not exist";

	/**
	 * Super Constructor to initialize root TreeNode with a value
	 * 
	 * @param value
	 *            Root Object
	 */
	public ExpressionTree(Object value)
	{
		super(value);
	}

	/**
	 * Super Constructor to initialize root TreeNode with a value, and child
	 * nodes
	 * 
	 * @param value
	 *            Root Object
	 * @param left
	 *            Left child TreeNode
	 * @param right
	 *            Right child TreeNode
	 */
	public ExpressionTree(Object value, TreeNode left, TreeNode right)
	{
		super(value, left, right);
	}

	/**
	 * Constructor to create an ExpressionTree and build the tree from String
	 * array
	 * 
	 * @param arr
	 *            String array in postfix Notation
	 */
	public ExpressionTree(String[] arr)
	{
		super(null);
		buildTree(arr);
	}

	/**
	 * Function that takes in an array of strings in postfix notation order,
	 * then builds and returns an expression tree of type TreeNode.
	 * 
	 * @param exp
	 *            Array of strings in postfix order
	 * @return expression tree TreeNode root
	 */
	public TreeNode buildTree(String[] exp)
	{
		Stack<TreeNode> tree = new Stack<TreeNode>();

		// Assume that every expression will be either a number or an operator
		// (as a string)
		for (String s : exp)
		{
			s = s.trim(); // just in case, ya never know
			TreeNode left = null;
			TreeNode right = null;
			if (isOperator(s))
			{
				if (!tree.isEmpty())
					right = tree.pop();
				if (!tree.isEmpty())
				{
					left = tree.pop();
					TreeNode node = new TreeNode(s, left, right);
					tree.push(node);
				}
			}
			else
			{
				// only add integers
				try
				{
					Integer.parseInt(s);
					TreeNode node = new TreeNode(s);
					tree.push(node);
				}
				catch (NumberFormatException e)
				{
				}

			}
		}
		TreeNode root = new TreeNode(null);
		if (!tree.isEmpty())
			root = tree.pop();
		super.setValue(root.getValue());
		super.setLeft(root.getLeft());
		super.setRight(root.getRight());

		return this;
	}

	/**
	 * Function that will evaluate the expression tree and return the answer.
	 * 
	 * @return evaluation of the tree
	 */
	public int evalTree()
	{
		// if its an operator, evaluate with left and right
		if (this.getValue() != null)
			return recursiveEval(this);
		return 0;
	}

	private int recursiveEval(TreeNode node)
	{
		if (isOperator(node.getValue().toString()))
		{
			int leftVal = recursiveEval(node.getLeft());
			int rightVal = recursiveEval(node.getRight());

			return operate(node.getValue().toString(), leftVal, rightVal);
		}
		else
		{
			// return numerical value
			return Integer.parseInt(node.getValue().toString());
		}
	}

	/**
	 * recursive conversion method that traverses the expression tree and
	 * returns a string in prefix notation
	 */
	public String toPrefixNotation()
	{
		return prefixRecursive(this);
	}

	private String prefixRecursive(TreeNode node)
	{
		String str = "";
		str += node.getValue().toString();

		if (node.getLeft() != null)
			str += " " + prefixRecursive(node.getLeft());

		if (node.getRight() != null)
			str += " " + prefixRecursive(node.getRight());
		return str;
	}

	/**
	 * recursive conversion methods that traverses the expression tree and
	 * returns a string in infix notation
	 */
	public String toInfixNotation()
	{
		return infixRecursive(this);
	}

	private String infixRecursive(TreeNode node)
	{
		String str = "";
		if (node.getLeft() != null)
		{
			if (node.getLeft().getLeft() != null)
				str += "(" + infixRecursive(node.getLeft()) + ")";
			else
				str += infixRecursive(node.getLeft()) + " ";

		}
		str += node.getValue().toString();

		if (node.getRight() != null)
		{
			if (node.getRight().getRight() != null)
				str += "(" + infixRecursive(node.getRight()) + ")";
			else
				str += " " + infixRecursive(node.getRight());
		}

		return str;
	}

	/**
	 * recursive conversion methods that traverses the expression tree and
	 * returns a string in postfix notation wowzer
	 */
	public String toPostfixNotation()
	{
		return postfixRecursive(this);
	}

	private String postfixRecursive(TreeNode node)
	{
		String str = "";
		if (node.getLeft() != null)
			str += postfixRecursive(node.getLeft()) + " ";

		if (node.getRight() != null)
			str += postfixRecursive(node.getRight()) + " ";

		str += node.getValue().toString();
		return str;
	}

	/**
	 * function that will take in an array of strings in post-fix order and,
	 * using a stack, evaluate the expression
	 */
	public int postfixEval(String[] exp)
	{
		Stack<Integer> stack = new Stack<Integer>();
		for (String s : exp)
			if (isOperator(s))
			{
				int left = 0;
				int right = 0;
				if (!stack.isEmpty())
					right = stack.pop().intValue();
				if (!stack.isEmpty())
				{
					left = stack.pop().intValue();
					stack.push(operate(s, left, right));
				}
			}
			else
				try
				{
					stack.push(Integer.parseInt(s));
				}
				catch (NumberFormatException e)
				{
				}

		if (!stack.isEmpty())
			return stack.pop().intValue();
		return 0;
	}

	/**
	 * function to determine is a string is an operator
	 * 
	 * @param str
	 *            arithmetic operator as a string
	 * @return true/false
	 */
	private boolean isOperator(String str)
	{
		return str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*");
	}

	/**
	 * Function to perform arithmetic on two integers
	 * 
	 * @param operator
	 *            valid operator string
	 * @param one
	 *            integer before operator
	 * @param two
	 *            integer after operator
	 * @return int value post-operation
	 */
	private int operate(String operator, int one, int two)
	{
		if (operator.equals("+"))
			return one + two;
		if (operator.equals("-"))
			return one - two;
		if (operator.equals("/"))
			return one / two;
		if (operator.equals("*"))
			return one * two;
		// should never reach this point, but returning 0 doesn't feel right
		throw new IllegalArgumentException(String.format(INVALID_OP, operator));
	}

}
