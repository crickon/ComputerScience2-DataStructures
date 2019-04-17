/**
 * NVCC Assignment Nine: "Eight Queens"
 * 
 * @author Matthew "crickon" Grillo
 */
package assignment;

/**
 * Data Structure to store a vector that consists of two integers. Helpful with
 * 2D array positioning.
 *
 */
public class Vector2i
{
	private int x;
	private int y;

	/**
	 * Construct a Vector storing two integer values
	 * 
	 * @param x
	 *            first integer value
	 * @param y
	 *            second integer value
	 */
	public Vector2i(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Get a String representation for this Vector
	 */
	public String toString()
	{
		return "[" + x + "," + y + "]";
	}

	/**
	 * Getter for the first integer value
	 * 
	 * @return first integer value
	 */
	public int getX()
	{
		return this.x;
	}

	/**
	 * Getter for the second integer value
	 * 
	 * @return second integer value
	 */
	public int getY()
	{
		return this.y;
	}
}