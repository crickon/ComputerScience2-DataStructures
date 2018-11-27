/**
 * NVCC Assignment Four
 * 
 * @author Matthew Grillo
 */
public class Disk implements Comparable<Disk>
{
	private int radius;

	/**
	 * Creates Disk object with radius
	 * @param radius Integer for the radius of this Disk
	 * @throws IllegalArgumentException if the radius is not greater than 0
	 */
	public Disk(int radius)
	{
		if (radius > 0)
			this.radius = radius;
		else
			throw new IllegalArgumentException("Radius must be greater than 0");
	}

	/**
	 * Compares the radius of this Disk and another
	 * @param other Another Disk object
	 */
	public int compareTo(Disk other)
	{
		return this.radius - other.radius;
	}

	/**
	 * Determines if this Disk is equal to another object
	 * @param other Another object
	 */
	public boolean equals(Object other)
	{
		if (other instanceof Disk)
		{
			return this.getRadius() == ((Disk) other).getRadius();
		}
		return false;
	}

	/**
	 * Overrides toString to return the radius of this Disk
	 */
	public String toString()
	{
		return this.radius + "";
	}

	/**
	 * Getter for the radius of this Disk
	 * @return Integer radius of the Disk
	 */
	public int getRadius()
	{
		return this.radius;
	}
}
