package ch25;

public class nano
{
	public static void main(String... args)
	{
		long begin = System.nanoTime();
		while (true)
		{
			long now = System.nanoTime();
			int leng = countDigit(now);
			System.out.println(leng);
		}
	}
	
	static int countDigit(long n) 
    { 
        int count = 0; 
        while (n != 0) { 
            n = n / 10; 
            ++count; 
        } 
        return count; 
    } 
}
