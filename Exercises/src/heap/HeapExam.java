/**
 * Final Exam Prep: Heap Methods
 * @author Matthew "crickon" Grillo
 */
package heap;

public class HeapExam
{
	/**
	 * Turn a Tree into a Heap
	 * 
	 * @param root
	 *            Node at the start of the Tree
	 * @param numNodes
	 *            The number of nodes in the Tree
	 * @return Heap object array
	 */
	public Object[] toArray(TreeNode root, int numNodes)
	{
		Object[] array = new Object[numNodes + 1];
		toArray(root, array, 1);
		return array;
	}

	/**
	 * Recursive helper to turn a Tree into a Heap
	 * 
	 * @param node
	 *            Current node to be processed in the tree
	 * @param values
	 *            heap object array
	 * @param i
	 *            index to place the object at this node into the array
	 */
	private void toArray(TreeNode node, Object[] values, int i)
	{
		if (node != null)
		{
			values[i] = node.getValue();
			toArray(node.getLeft(), values, i * 2);
			toArray(node.getRight(), values, i * 2 + 1);
		}
	}
}
