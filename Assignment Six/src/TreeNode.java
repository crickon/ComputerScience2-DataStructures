public class TreeNode
{

	private Object value;
	private TreeNode right;
	private TreeNode left;

	public TreeNode(Object value)
	{
		this.value = value;
		this.right = null;
		this.left = null;
	}

	public TreeNode(Object value, TreeNode left, TreeNode right)
	{
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public Object getValue()
	{
		return this.value;
	}

	public TreeNode getLeft()
	{
		return this.left;
	}

	public TreeNode getRight()
	{
		return this.right;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public void setLeft(TreeNode node)
	{
		this.left = node;
	}

	public void setRight(TreeNode node)
	{
		this.right = node;
	}
}
