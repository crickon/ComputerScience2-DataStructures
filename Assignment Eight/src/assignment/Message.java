package assignment;

public class Message implements Comparable<Message>
{
	private String msg;
	private int priority;
	private long arrival;

	public Message(String msg, int priority)
	{
		this.msg = msg;
		this.priority = priority;
	}

	public String getMessage()
	{
		return this.msg;
	}

	public int getPriority()
	{
		return this.priority;
	}

	public long getArrivalTime()
	{
		return this.arrival;
	}

	public void setArrivalTime(long nanotime)
	{
		this.arrival = nanotime;
	}
	
	public long diff(long other)
	{
		return other - this.arrival;
	}

	@Override
	public int compareTo(Message o)
	{
		return o.priority - this.priority;
		/*
		 * if (this.priority < o.priority) return 1; if (this.priority >
		 * o.priority) return -1; return 0;
		 */
	}
	
	public boolean equals(Message o)
	{
		if (this.msg.equals(o.msg) && this.priority == o.priority)
			return true;
		return false;
	}

}
