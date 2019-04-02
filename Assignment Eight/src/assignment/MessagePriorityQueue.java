/**
 * NVCC Assignment Eight: "Priority Message Queue"
 * @author Matthew "crickon" Grillo
 */
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
	 * Literal for the number of queues in this priority queue
	 */
	public static final int numQueues = 5;
	/**
	 * Because the test method exponentially increases the size of the Queue,
	 * have a threshold that once reached the method will know to stop adding
	 * messages.
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
	 * Method to check if the Priority Queue contains a Message
	 * 
	 * @param msg
	 *            Message to be checked
	 * @return if the Priority Queue contains the Message
	 */
	public boolean contains(Message msg)
	{
		for (Queue q : queues)
			if (q.contains(msg))
				return true;
		return false;
	}

	/**
	 * Method to look at the Message at the front of the queue, but not remove
	 * it. Returns null if the queue is empty
	 * 
	 * @return Message at the front of the queue
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
	 * Method to get the size of the Priority Queue
	 * 
	 * @return size of the Priority Queue
	 */
	public int size()
	{
		int size = 0;
		for (Queue q : queues)
			size += q.size();
		return size;
	}

	/**
	 * Method to get the size for each Priority
	 * 
	 * @return Array of sizes
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
	 * Method to get and remove the Message in the front of the queue. Returns
	 * null if the queue is empty
	 * 
	 * @return Message in the front of the queue
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
	 * Determine if the Priority Queue is empty (contains no Messages)
	 * 
	 * @return if the Priority Queue is empty
	 */
	public boolean isEmpty()
	{
		for (Queue q : queues)
			if (!q.isEmpty())
				return false;
		return true;
	}

	/**
	 * Return the time in "minutes" of how long the Message at the front of the
	 * Queue has been in the Queue
	 * 
	 * @return how long the Message at the front of the queue has been in the
	 *         Queue
	 */
	public double getTime()
	{
		long current = System.nanoTime();
		Message top = this.peek();
		long arrival = top.getArrivalTime();
		return (double) (current - arrival) / this.minute;
	}

	/**
	 * Determine if the Message has been process for 4 "minutes" or not
	 * 
	 * @return if the Time of the Message in the front of the queue is or has
	 *         exceeded 4 minutes
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
	 * Helper method to analyze a PriorityQueue with the given threshold
	 * 
	 * @param threshold
	 *            Threshold of the PriorityQueue
	 * @throws InterruptedException
	 *             if the Thread is interrupted during sleep
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
	 * Helper method to print the analysis of the PriorityQueue
	 * 
	 * @param time
	 *            Time in nanoseconds that it took to process the PriorityQueue
	 * @param waitTimes
	 *            the average Message processing time for each priority in the
	 *            Queue
	 * @param prioCounts
	 *            the count for the number of Messages of each priority that
	 *            went into the Queue
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
	 * Helper method to add a Message of random priority to a PriorityQueue
	 * 
	 * @param pq
	 *            PriorityQueue object
	 */
	private static void addMessage(MessagePriorityQueue pq)
	{
		int rand = (int) (Math.random() * 5);
		pq.add(new Message("", rand));
	}
}
