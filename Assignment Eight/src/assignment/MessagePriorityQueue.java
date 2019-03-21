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
	/**
	 * 
	 */
	public static final int numQueues = 5;
	/**
	 * 
	 */
	public static int threshold;

	/**
	 * List of Queues for each priority (0-4)
	 */
	private ArrayList<Queue> queues;

	/**
	 * PriorityQueue constructor with a default Message threshold of 10000
	 */
	public MessagePriorityQueue()
	{
		this(10000);
	}

	/**
	 * PriorityQueue constructor with a specific Message threshold
	 * 
	 * @param threshold
	 *            Message threshold
	 */
	public MessagePriorityQueue(int threshold)
	{
		queues = new ArrayList<Queue>(numQueues);
		for (int i = 0; i < numQueues; i++)
			queues.add(new LinkedList<Message>());
		this.threshold = threshold;
	}

	/**
	 * Add a Message into the priority queue
	 * 
	 * @param msg
	 *            Message to be added
	 */
	public void add(Message msg)
	{
		msg.setArrivalTime(System.nanoTime());
		queues.get(msg.getPriority()).add(msg);
	}

	/**
	 * 
	 * @return
	 */
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

	/**
	 * 
	 * @return
	 */
	public int size()
	{
		int size = 0;
		for (Queue q : queues)
			size += q.size();
		return size;
	}

	/**
	 * 
	 * @return
	 */
	public int[] sizes()
	{
		int[] sizes = new int[queues.size()];
		for (int i = 0; i < queues.size(); i++)
			sizes[i] = queues.get(i).size();
		return sizes;
	}

	/**
	 * Determine the index of the queue that contains a given Message. Returns
	 * -1 if the Message is not found.
	 * 
	 * @param msg
	 *            Message
	 * @return index of the queue in the list containing the Message
	 */
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

	/**
	 * 
	 * @return
	 */
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

	/**
	 * 
	 * @return
	 */
	public boolean isEmpty()
	{
		for (Queue q : queues)
			if (!q.isEmpty())
				return false;
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public double getTime()
	{
		long current = System.nanoTime();
		Message top = this.peek();
		long arrival = top.getArrivalTime();
		return (double) (current - arrival) / this.minute;
	}

	/**
	 * 
	 * @return
	 */
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
		// analyze(10000000);
		// analyze(1000000000);
	}

	/**
	 * 
	 * @param threshold
	 * @throws InterruptedException
	 */
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
			// System.out.println(pq.size());
			Thread.sleep(pq.minute / 1000000);
		}
		long end = System.nanoTime();
		printAnalysis(end - start, waitTimes, prioCounts);
	}

	/**
	 * 
	 * @param time
	 * @param waitTimes
	 * @param prioCounts
	 */
	private static void printAnalysis(long time, long[] waitTimes, int[] prioCounts)
	{
		double totalCount = 0;
		for (int i : prioCounts)
			totalCount += i;
		System.out.println(String.format("Queue threshold of %d messages analyzed in %f \"real seconds\"",
				MessagePriorityQueue.threshold, (double) time / 1000000000l));
		for (int i = 0; i < MessagePriorityQueue.numQueues; i++)
			System.out.println(
					String.format("Priority %d: %d message events (%.1f%%), avg of %f minutes", i, prioCounts[i],
							prioCounts[i] / totalCount * 100, (double) waitTimes[i] / MessagePriorityQueue.minute));
		System.out.println();
	}

	/**
	 * 
	 * @param pq
	 */
	private static void addMessage(MessagePriorityQueue pq)
	{
		int rand = (int) (Math.random() * 5);
		pq.add(new Message("", rand));
	}
}
