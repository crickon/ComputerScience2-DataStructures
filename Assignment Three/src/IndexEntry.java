import java.util.TreeSet;

public class IndexEntry
{
	private String word;
	private TreeSet<Integer> lines;

	/**
	 * take a given word, store it and initialize the list of numbers to an
	 * empty list
	 * 
	 * @param word
	 *            word to be stored
	 */
	public IndexEntry(String word)
	{
		this.word = word;
		this.lines = new TreeSet<Integer>();
	}

	public void add(int num)
	{
		if (lines.contains(num))
			return;
		lines.add(num);
	}

	public String getWord()
	{
		return this.word;
	}

	public String toString()
	{
		String str = this.word.toUpperCase();
		for (int line : this.lines)
			str += String.format(" %d,", line);
		str = str.substring(0, str.length() - 1);
		return str;
	}
}
