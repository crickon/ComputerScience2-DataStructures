package assignment;

/**
 * LinkedList-type structure for hashing Tic-Tac-Toe (TTT) boards. This Object
 * is For handling hash collisions via chaining.
 * 
 * @author Matthew "crickon" Grillo 
 *
 */
public class HashNode
{
	private int hash;
	private String board;
	private HashNode next;

	/**
	 * Constructor to initialize a HashNode object
	 * 
	 * @param hash
	 *            unique hash (or maybe not) for TTT Board
	 * @param board
	 *            String representation of TTT board
	 * @param next
	 *            The following node in the LinkedList; set as null for root.
	 */
	public HashNode(int hash, String board, HashNode next)
	{
		this.hash = hash;
		this.board = board;
		this.next = next;
	}

	/**
	 * getter for returning this HashNode's hash value
	 * 
	 * @return int hash value
	 */
	public int getHash()
	{
		return this.hash;
	}

	/**
	 * getter for returning this HashNode's TTT board representation
	 * 
	 * @return TTT board String
	 */
	public String getBoardString()
	{
		return this.board;
	}

	/**
	 * getter for returning the next HashNode in the LinkedList
	 * 
	 * @return next HashNode object
	 */
	public HashNode getNext()
	{
		return this.next;
	}

	/**
	 * setter to set the next HashNode in the LinkedList
	 * 
	 * @param node
	 *            HashNode object to be added to the LinkedList
	 */
	public void setNext(HashNode node)
	{
		this.next = node;
	}

	/**
	 * boolean function to determine if this HashNode is at the end of the
	 * LinkedList
	 * 
	 * @return next HashNode is not null
	 */
	public boolean hasNext()
	{
		return this.next != null;
	}

	/**
	 * Recursive method to determine if a TTT board's String is within this
	 * LinkedList, starting at this node.
	 * 
	 * @param board
	 *            Board to be found
	 * @return LinkedList contains board String, starting at this node.
	 */
	public boolean contains(String board)
	{
		if (this.board.equals(board))
			return true;
		if (this.next == null)
			return false;
		return this.next.contains(board);
	}

	/**
	 * Recursive method to determine the length of the LinkedList, starting at
	 * this node.
	 * 
	 * @return length of LinkedList, starting at this node.
	 */
	public int length()
	{
		if (next == null)
			return 1;
		return next.length() + 1;
	}
}
