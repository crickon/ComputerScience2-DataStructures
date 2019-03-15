package testers;

import assignment.Message;

public class MessageTest
{
	public static void main(String... args)
	{
		Message low = new Message("Low Priority", 4);
		low.setArrivalTime(System.nanoTime());
		double seconds = ((double)low.getArrivalTime() / 1000000000);
		Message high = new Message("High Priority", 0);
		high.setArrivalTime(System.nanoTime());
		System.out.println(String.format("Low Priority = %d", low.getPriority()));
		System.out.println(String.format("High Priority = %d", high.getPriority()));
		System.out.println(String.format("Low arrival = %d, %f", low.getArrivalTime(), seconds));
		System.out.println(String.format("High arrival = %d", high.getArrivalTime()));
		System.out.println(String.format("Comparable Test: low < high = %b", low.compareTo(high) < 0));
		System.out.println(String.format("Comparable Test: high > low = %b", high.compareTo(low) > 0));
	}
}
