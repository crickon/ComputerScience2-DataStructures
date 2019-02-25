package assignment;

public class TTTHashString
{
	private static final int boardStringLength = 9;

	private static final int[] powersOf3 =
	{ 1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683 };

	private String str;

	public TTTHashString(String str)
	{
		this.str = str;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		for (int i = 0; i < boardStringLength && i < str.length(); i++)
		{
			hash += myCharValue(str.charAt(i)) * powersOf3[i];
		}
		return hash;
	}

	private int myCharValue(char c)
	{
		switch (c)
		{
			case 'x':
			case 'X':
				return 1;
			case 'o':
			case 'O':
				return 2;
			default:
				return 0;
		}
	}
}
