package ch25;

import java.util.Scanner;

public class HelloWorld
{
	public static void main(String... args)
	{
		System.out.println("Hello World");
		
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter two numbers: ");
		int one = kb.nextInt();
		int two = kb.nextInt();
		int pow = one;
		for (int i = 1; i < two; i++)
			pow *= one;
		
		System.out.println(pow);
	}
}
