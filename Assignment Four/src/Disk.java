
public class Disk implements Comparable<Disk>
{
	private int radius;

	public Disk(int radius)
	{
		this.radius = radius;
	}

	public int compareTo(Disk other)
	{
		return this.radius - other.radius;
	}

	public boolean equals(Object other)
	{
		if (other instanceof Disk)
		{
			return this.getRadius() == ((Disk) other).getRadius();
		}
		return false;
	}

	public String toString()
	{
		return this.radius + "";
	}

	public int getRadius()
	{
		return this.radius;
	}
}
