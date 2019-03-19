package assignment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings(
{ "unchecked", "rawtypes" })
public class MessagePriorityQueue
{
	/**
	 * nanoseconds to represent a "minute"
	 */
	public static final long minute = 100000l;
	public static final int numQueues = 5;
	public static int threshold = 1000000;

	private ArrayList<Queue> queues;

	public MessagePriorityQueue(int threshold)
	{
		queues = new ArrayList<Queue>(numQueues);
		for (int i = 0; i < numQueues; i++)
			queues.add(new LinkedList<Message>());
		this.threshold = threshold;
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
		for (Queue q : queues)
			if (!q.isEmpty())
				return false;
		return true;
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
		analyze(1000);
		analyze(10000);
		analyze(100000);
		analyze(1000000);
		//analyze(10000000);
		//analyze(1000000000);
	}

	private static void analyze(int threshold) throws InterruptedException
	{
		long start = System.nanoTime();
		MessagePriorityQueue pq = new MessagePriorityQueue(threshold);
		long[] waitTimes = new long[MessagePriorityQueue.numQueues];
		int[] prioCounts = new int[MessagePriorityQueue.numQueues];
		addMessage(pq);
		boolean reached = false;
		while (!pq.isEmpty())
		{
			if (pq.checkTime())
			{
				Message msg = pq.poll();
				long current = System.nanoTime();
				int prio = msg.getPriority();
				prioCounts[prio]++;
				if (waitTimes[prio] > 1)
					waitTimes[prio] = (waitTimes[prio] + msg.diff(current)) / 2;
				else
					waitTimes[prio] = msg.diff(current);
			}
			if (pq.size() > MessagePriorityQueue.threshold)
				reached = true;
			if (!reached)
				addMessage(pq);
			//System.out.println(pq.size());
			Thread.sleep(pq.minute / 1000000);
		}
		long end = System.nanoTime();
		printAnalysis(end - start, waitTimes, prioCounts);
	}

	private static void printAnalysis(long time, long[] waitTimes, int[] prioCounts)
	{
		double totalCount = 0;
		for (int i : prioCounts)
			totalCount += i;
		System.out.println(String.format("Queue threshold of %d messages analyzed in %f \"real seconds\"", MessagePriorityQueue.threshold, (double)time/1000000000l));
		for (int i = 0; i < MessagePriorityQueue.numQueues; i++)
			System.out.println(
					String.format("Priority %d: %d message events (%.1f%%), avg of %f minutes", i, prioCounts[i],
							prioCounts[i] / totalCount * 100, (double) waitTimes[i] / MessagePriorityQueue.minute));
		System.out.println();
	}

	private static void addMessage(MessagePriorityQueue pq)
	{
		int rand = (int) (Math.random() * 5);
		pq.add(new Message("", rand));
	}
}
