import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Tower extends Stack<Disk>
{
	public Tower()
	{
		super();
	}

	public void flip()
	{
		ArrayList<Disk> disks = new ArrayList<Disk>(super.size());
		while (super.isEmpty() == false)
			disks.add(super.pop());

		for (Disk d : disks)
			super.push(d);
	}

	public String toString()
	{
		String str = "";
		Iterator<Disk> iterator = super.iterator();
		while (iterator.hasNext())
		{
			Disk disk = iterator.next();
			for (int i = 0; i < disk.getRadius(); i++)
				str += "<>";
			
			str += disk.toString();
			str += "\n";
		}
		return str;
	}
}
