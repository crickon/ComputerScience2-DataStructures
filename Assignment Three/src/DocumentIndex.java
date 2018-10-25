import java.util.TreeMap;

public class DocumentIndex
{
	private TreeMap<String, IndexEntry> list;

	public DocumentIndex()
	{
		list = new TreeMap<String, IndexEntry>();
	}

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

	public void addAllWords(String str, int num)
	{
		String[] words = str.split(" ");
		for (String word : words)
		{
			if (!word.equals(""))
				addWord(word.toUpperCase(), num);
		}
	}

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
