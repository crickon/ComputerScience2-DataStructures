import java.util.Arrays;

public class Tester
{
	public static void main(String[] args)
	{
		// testDisk();
		// testCompareable();

		production();
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
		for (int i = 0; i < 100; i++)
			line.addDisk(new Disk(((int) (Math.random() * 10)) + 1));
		line.process();

		while (line.hasOutput())
		{
			System.out.println(line.removeTower().toString());
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
