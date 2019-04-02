package heap;

public class HeapExam
{
	public Object[] toArray(TreeNode root, int numNodes)
	{
		Object[] array = new Object[numNodes + 1];
		toArray(root, array, 1);
		return array;
	}

	private void toArray(TreeNode root, Object[] values, int i)
	{
		if (root != null)
		{
			values[i] = root.getValue();
			if (root.getLeft() != null)
				toArray(root.getLeft(), values, i * 2);
			if (root.getRight() != null)
				toArray(root.getRight(), values, i * 2 + 1);
		}
	}
}
