package ch25;

public class sumString
{
	public static void main (String[] args)
	{
		byte b1 = 0x00;
		byte b2 = 0x01;
		byte b3 = 0x02;
		int result = b3 | (b2 << 8) | (b1 << 16);
		int result1 = b1 | (b2 << 8) | (b3 << 16);
		int result2 = b2 | (b3 << 8) | (b1 << 16);
		System.out.println(result);
		System.out.println(result1);
		System.out.println(result2);
	}
}
