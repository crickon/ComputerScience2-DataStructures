import java.util.Scanner;

public class Lab1 {
	/**
	 * @author Matthew "crickon" Grillo
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter two numbers to find the sum: ");
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		
		System.out.printf("The sum of %d and %d is %d!", x, y, sum(x,y));
		
		scanner.close();
	}

	/**
	 * Method to find the sum of two numbers
	 * @param x number 1
	 * @param y number 2
	 * @return the sum of x and y
	 */
	public static int sum(int x, int y) {
		return x + y;
	}
}

//Thank you for helping me with GitHub - AnneMarie