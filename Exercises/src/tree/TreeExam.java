package tree;

public class TreeExam
{
	/**
	 * Replaces the value in each node with the sum of the values in all the
	 * nodes of its subtree.
	 * 
	 * @param root
	 *            node at the beginning of the tree
	 */
	public static void accumulate(TreeNode root)
	{
		helper(root);
	}

	/**
	 * Recursive helper method for accumulating the Tree
	 * 
	 * @param node
	 *            current node index in the Tree
	 * @return current node's value
	 */
	public static int helper(TreeNode node)
	{
		if (node != null)
		{
			int val = (int) node.getValue();
			int newVal = 0;
			if (node.getLeft() != null)
				newVal += helper(node.getLeft()) + (int) node.getLeft().getValue();
			if (node.getRight() != null)
				newVal += helper(node.getRight()) + (int) node.getRight().getValue();
			node.setValue(newVal);
			return val;
		}
		return 0;
	}

	public static void main(String... args)
	{
		TreeNode root = new TreeNode(1);
		root.setLeft(new TreeNode(2));
		root.setRight(new TreeNode(2, new TreeNode(3), new TreeNode(3)));

		accumulate(root);

		System.out.println(root.getValue().toString());
		System.out.println(root.getRight().getValue().toString());
	}
}
