
/**
 * NVCC Assignment Four
 * 
 * @author Matthew Grillo
 */
import java.util.ArrayList;
import java.util.Stack;

public class Tower extends Stack<Disk>
{
	/**
	 * Creates a Default Tower object and initializes the super class
	 */
	public Tower()
	{
		super();
	}

	/**
	 * Reverses the order of the Disks in this Tower object
	 */
	public void flip()
	{
		ArrayList<Disk> disks = new ArrayList<Disk>(super.size());
		while (super.isEmpty() == false)
			disks.add(super.pop());

		for (Disk d : disks)
			super.push(d);
	}

	/**
	 * Returns a String representation of this Tower by popping all the disks
	 * onto a temporary Tower and then pushing them all back onto the Stack.
	 * 
	 * @return a String representation of this Tower.
	 */
	public String toString()
	{
		String str = "";
		Tower temp = new Tower();
		while (!this.isEmpty())
		{
			Disk disk = this.pop();
			for (int i = 0; i < disk.getRadius(); i++)
				str += "*";
			str += " " + disk.toString() + "\n";
			temp.push(disk);
		}
		while (!temp.isEmpty())
		{
			this.push(temp.pop());
		}
		return str;
	}
}
