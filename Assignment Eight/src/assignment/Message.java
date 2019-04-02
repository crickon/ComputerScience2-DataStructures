/**
 * NVCC Assignment Eight: "Priority Message Queue"
 * @author Matthew "crickon" Grillo
 */
package assignment;

public class Message implements Comparable<Message>
{
	private String msg;
	private int priority;
	private long arrival;

	/**
	 * Construct a message object with a priority
	 * 
	 * @param msg
	 *            Text to be attached to the Message
	 * @param priority
	 *            Priority of the Message for processing
	 */
	public Message(String msg, int priority)
	{
		this.msg = msg;
		this.priority = priority;
	}

	/**
	 * Getter for the text attached to the Message
	 * 
	 * @return text attached to the Message
	 */
	public String getMessage()
	{
		return this.msg;
	}

	/**
	 * Getter for this Message's priority
	 * 
	 * @return this Message's priority
	 */
	public int getPriority()
	{
		return this.priority;
	}

	/**
	 * Getter for the time that this Message arrived in queue
	 * 
	 * @return time that this Message arrived in queue
	 */
	public long getArrivalTime()
	{
		return this.arrival;
	}

	/**
	 * Set the time that this Message arrived in queue
	 * 
	 * @param nanotime
	 *            time that this Message arrived in queue
	 */
	public void setArrivalTime(long nanotime)
	{
		this.arrival = nanotime;
	}

	/**
	 * Get the difference in time from this Message's arrival in queue to
	 * another
	 * 
	 * @param other
	 *            another nano time
	 * @return the difference in time
	 */
	public long diff(long other)
	{
		return other - this.arrival;
	}

	/**
	 * Compare this Message to another based on the difference in priority
	 */
	@Override
	public int compareTo(Message o)
	{
		return this.priority - o.priority;
		/*
		 * if (this.priority < o.priority) return 1; if (this.priority >
		 * o.priority) return -1; return 0;
		 */
	}

	/**
	 * Determine if this Message equals another. Messages are equal when the
	 * text attached and the priority are the same.
	 * 
	 * @param o
	 *            another Message
	 * @return if this Message equals another
	 */
	public boolean equals(Message o)
	{
		if (this.msg.equals(o.msg) && this.priority == o.priority)
			return true;
		return false;
	}
}
