package ch23;

public class Exercises
{
	public static void main (String[] args)
	{
		int x = 0;
		for (int i = 0; i < 10; i++)
		{
			x = x + 5;
		}
		log(x + "");
	}
	
	private static void log(String str){
		System.out.println(str);
	}	
}
