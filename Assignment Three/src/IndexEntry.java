import java.util.TreeSet;

/**
 * NVCC Assignment Three
 * 
 * @author Matthew Grillo (@Crickon)
 *
 */
public class IndexEntry
{
	private String word;
	private TreeSet<Integer> lines;

	/**
	 * Constructor. Takes a given word, stores it and initialize the list of
	 * numbers to an empty list.
	 * 
	 * @param word
	 *            word to be stored
	 */
	public IndexEntry(String word)
	{
		this.word = word;
		this.lines = new TreeSet<Integer>();
	}

	/**
	 * Method that appends num to the list of numbers, but only if it is not
	 * already in that list.
	 * 
	 * @param num
	 *            line number
	 */
	public void add(int num)
	{
		if (lines.contains(num))
			return;
		lines.add(num);
	}

	/**
	 * Word Getter
	 * 
	 * @return IndexEntry's word.
	 */
	public String getWord()
	{
		return this.word;
	}

	/**
	 * Returns a string representation of this IndexEntry in the format used in
	 * each line of the output file.
	 * 
	 * @return toString of the IndexEntry
	 */
	public String toString()
	{
		String str = this.word.toUpperCase();
		for (int line : this.lines)
			str += String.format(" %d,", line);
		str = str.substring(0, str.length() - 1);
		return str;
	}
}
