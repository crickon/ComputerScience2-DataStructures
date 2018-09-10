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
		
		System.out.printf("The sum of %d and %d is %d! \n", x, y, sum(x,y));
		System.out.printf("The difference between %d and %d is %d! \n", x, y, difference(x,y));
		System.out.printf("The product of %d and %d is %d! \n", x, y, product(x,y));
		System.out.printf("The quotient of %d and %d is %.2f! \n", x, y, quotient(x,y));
		System.out.printf("The modulus of %d and %d is %d! \n", x, y, modulus(x,y));
		
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

//Thank you for helping me with GitHub - AnneMarie

	/**
	 * Method to find the difference of two numbers
	 * @param x number 1
	 * @param y number 2
	 * @return the difference between x and y
	 */
	public static int difference (int x, int y) {
		return x-y;
	}
	
	/**
	 * Method to find the product of two numbers
	 * @param x number 1
	 * @param y number 2
	 * @return the product of x and y
	 */
	public static int product (int x, int y) {
		return x*y;
	}
	
	/**
	 * Method to find the quotient of two numbers
	 * @param x number 1
	 * @param y number 2
	 * @return the quotient of x and y
	 */
	public static double quotient (int x, int y) {
		return x*1.0 / y*1.0;
	}
	
	/**
	 * Method to find the modulus of two numbers
	 * @param x number 1
	 * @param y number 2
	 * @return the modulus of x and y
	 */
	public static int modulus (int x, int y) {
		return x%y;
	}
}

