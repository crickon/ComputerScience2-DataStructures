package testers;

import assignment.Message;
import assignment.MessagePriorityQueue;

public class QueueTest
{
	public static void main(String... args)
	{
		MessagePriorityQueue pq = new MessagePriorityQueue(10000);
		Message msg = new Message("test", (int) (Math.random() * 5));
		System.out.println(String.format("Empty peek returns null = %b", pq.peek() == null));
		pq.add(msg);
		System.out.println(String.format("Contains = %b, Prio = %d, Q = %d", pq.contains(msg), msg.getPriority(),
				pq.queueContaining(msg)));
		System.out.println(arrayStr(pq.sizes()));
		System.out.println(pq.poll().getMessage());
		System.out.println(String.format("Contains = %b, Prio = %d, Q = %d", pq.contains(msg), msg.getPriority(),
				pq.queueContaining(msg)));
	}

	private static String arrayStr(int[] array)
	{
		String str = "[";
		for (Object o : array)
			str += o.toString() + ", ";
		if (str.length() > 1)
			str = str.substring(0, str.lastIndexOf(','));
		str += "]";
		return str;
	}
}
