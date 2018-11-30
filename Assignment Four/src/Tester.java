/**
 * NVCC Assignment Four
 * 
 * @author Matthew Grillo
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Tester
{
	public static void main(String[] args)
	{
		testDisk();
		testCompareable();

		production();
		
		testForExceptions();
	}

	private static void testDisk()
	{
		Disk one = new Disk(1);
		log("Equals : " + one.equals(one));
		log("getRadius : " + (one.getRadius() == 1));
	}

	private static void testCompareable()
	{
		Disk ten = new Disk(10);
		Disk five = new Disk(5);
		Disk one = new Disk(1);
		Disk seven = new Disk(7);

		Disk[] unsorted = new Disk[]
		{ ten, five, one, seven };

		Arrays.sort(unsorted);

		Disk[] sorted = new Disk[]
		{ one, five, seven, ten };

		boolean equals = true;
		for (int i = 0; i < unsorted.length && i < sorted.length; i++)
		{
			if (!unsorted[i].equals(sorted[i]))
			{
				equals = false;
				break;
			}
		}
		log("Comparable : " + equals);

	}

	private static void production()
	{
		ProductionLine line = new ProductionLine();
		ArrayList<Integer> numberLine = new ArrayList<Integer>();
		for (int i = 0; i < 15; i++)
		{
			int random = ((int) (Math.random() * 10)) + 1;
			numberLine.add(random);
			line.addDisk(new Disk(random));
		}
		log("Numbers in the order that they are generated and added to the queue: \n" + numberLine + "\n");
		line.process();

		while (line.hasOutput())
		{
			System.out.println(line.removeTower().toString());
		}
	}
	
	private static void testForExceptions()
	{
		try {
			Disk test = new Disk(0);
		}catch (IllegalArgumentException e) {
			log("Throws Exception on Disk(0)");
		}
		try {
			Disk test = new Disk(-1);
		}catch (IllegalArgumentException e) {
			log("Throws Exception on Disk(-1)");
		}
		
		ProductionLine line = new ProductionLine();
		line.addDisk(new Disk(1));
		line.process();
		Tower tower = line.removeTower();
		try {
			Tower test = line.removeTower();
		}catch (IllegalStateException e) {
			log("Throws Exception if the output queue is empty");
		}
	}

	public static void log(Object obj)
	{
		if (obj instanceof String)
			System.out.println((String) obj);
		else
			System.out.println(obj + "");
	}

	public static void line()
	{
		System.out.println();
	}
}
