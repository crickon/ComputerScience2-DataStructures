import java.util.TreeMap;

/**
 * NVCC Assignment Three
 * 
 * @author Matthew Grillo (@Crickon)
 *
 */
public class DocumentIndex
{
	private TreeMap<String, IndexEntry> list;

	/**
	 * Constructor that creates a list with a default capacity
	 */
	public DocumentIndex()
	{
		list = new TreeMap<String, IndexEntry>();
	}

	/**
	 * Method that adds num to the IndexEntry for word by calling that
	 * IndexEntry’s add(num) method. If word is not yet in the DocumentIndex,
	 * the method first creates a new IndexEntry for word and inserts it into
	 * this list in alphabetical order (ignoring the upper and lower case).
	 * 
	 * @param word
	 *            Word to be added, or added to.
	 * @param num
	 *            Line number
	 */
	public void addWord(String word, int num)
	{
		word = word.replaceAll("[^A-Za-z0-9]+", "");
		if (!list.containsKey(word))
		{
			IndexEntry entry = new IndexEntry(word);
			entry.add(num);
			list.put(word, entry);
			return;
		}
		IndexEntry entry = list.get(word);
		entry.add(num);
	}

	/**
	 * Method that extracts all the words from str and for each word calls
	 * addWord(word, num).
	 * 
	 * @param str
	 *            Sentence or phrase of word(s).
	 * @param num
	 *            line number
	 */
	public void addAllWords(String str, int num)
	{
		String[] words = str.split(" ");
		for (String word : words)
		{
			if (!word.equals(""))
				addWord(word.toUpperCase(), num);
		}
	}

	/**
	 * Method that creates the text output for the whole DocumentIndex. Loops
	 * through keySet and adds each line.
	 * 
	 * @return Output in correct format.
	 */
	public String toString()
	{
		String str = "";
		for (String key : list.keySet())
		{
			IndexEntry i = list.get(key);
			str += i.toString() + "\n";
		}
		return str;
	}
}
