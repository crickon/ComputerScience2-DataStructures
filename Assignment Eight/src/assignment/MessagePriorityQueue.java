package assignment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MessagePriorityQueue
{
	/**
	 * nanoseconds to represent a "minute"
	 */
	static final long minute = 10000l;
	static final int numQueues = 5;
	private ArrayList<Queue> queues;

	public MessagePriorityQueue()
	{
		queues = new ArrayList<Queue>(numQueues);
		for (int i = 0; i < numQueues; i++)
			queues.add(new LinkedList<Message>());
	}

	public void add(Message msg)
	{
		msg.setArrivalTime(System.nanoTime());
		queues.get(msg.getPriority()).add(msg);
	}

	public boolean remove(Message msg)
	{
		boolean removed = false;
		for (Queue q : queues)
			removed = q.remove(msg) || removed;
		return removed;
	}

	public boolean contains(Message msg)
	{
		boolean contains = false;
		for (Queue q : queues)
			contains = contains || q.contains(msg);
		return contains;
	}

	public Message peek()
	{
		for (Queue q : queues)
		{
			Message msg = (Message) q.peek();
			if (msg != null)
				return msg;
		}
		return null;
	}

	public int size()
	{
		int size = 0;
		for (Queue q : queues)
			size += q.size();
		return size;
	}

	public int[] sizes()
	{
		int[] sizes = new int[queues.size()];
		for (int i = 0; i < queues.size(); i++)
			sizes[i] = queues.get(i).size();
		return sizes;
	}

	public int queueContaining(Message msg)
	{
		for (int i = 0; i < queues.size(); i++)
		{
			Queue q = queues.get(i);
			if (q.contains(msg))
				return i;
		}
		return -1;
	}

	public Message poll()
	{
		for (Queue q : queues)
		{
			Message msg = (Message) q.poll();
			if (msg != null)
				return msg;
		}
		return null;
	}

	public boolean isEmpty()
	{
		boolean empty = true;
		for (Queue q : queues)
			empty = q.isEmpty() && empty;
		return empty;
	}

	public double getTime()
	{
		long current = System.nanoTime();
		Message top = this.peek();
		long arrival = top.getArrivalTime();
		return (double) (current - arrival) / this.minute;
	}

	public boolean checkTime()
	{
		if (this.getTime() >= 4)
			return true;
		return false;
	}

	public static void main(String... args) throws InterruptedException
	{
		MessagePriorityQueue pq = new MessagePriorityQueue();
		long[] waitTimes = new long[pq.numQueues];
		addMessages(pq, 10);
		int threshold = 100000;
		boolean reached = false;
		while (!pq.isEmpty())
		{
			// System.out.println(pq.getTime() + ", " + pq.checkTime());
			if (pq.checkTime())
			{
				Message msg = pq.poll();
				long current = System.nanoTime();
				int prio = msg.getPriority();
				if (waitTimes[prio] > 1)
				{
					waitTimes[prio] = (waitTimes[prio] + msg.diff(current)) / 2;
				}
				else
				{
					waitTimes[prio] = msg.diff(current);
				}
			}
			if (pq.size() > threshold)
				reached = true;
			if (!reached)
				addMessages(pq, 1);
			System.out.println(pq.size());
			Thread.sleep(pq.minute / 1000000);
		}
		printTimes(waitTimes);
		System.out.println(arrayStr(waitTimes));
	}

	private static void printTimes(long[] waitTimes)
	{
		String str = "[";
		for (long o : waitTimes)
			str += (double) o / MessagePriorityQueue.minute + ", ";
		if (str.length() > 1)
			str = str.substring(0, str.lastIndexOf(','));
		str += "]";
		System.out.println(str);
	}

	private static void addMessages(MessagePriorityQueue pq, int i)
	{
		for (int j = 0; j < i; j++)
		{
			int rand = (int) (Math.random() * 5);
			pq.add(new Message("", rand));
		}
	}

	private static String arrayStr(long[] array)
	{
		String str = "[";
		for (long o : array)
			str += o + ", ";
		if (str.length() > 1)
			str = str.substring(0, str.lastIndexOf(','));
		str += "]";
		return str;
	}
}
