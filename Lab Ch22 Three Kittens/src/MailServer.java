import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Represents an mechanism for queuing and dispatching messages. A MailServer is
 * a queue of messages and maintains a set of registered subscribers.
 * 
 * @author matthewgrillo
 *
 */
public class MailServer extends LinkedList<Message>
{
	private TreeSet<Actor> subscribers;

	public MailServer()
	{
		this.subscribers = new TreeSet<Actor>();
	}

	public void signUp(Actor actor)
	{
		if (subscribers.contains(actor))
			return;
		subscribers.add(actor);
	}

	public void dispatch(Message msg)
	{
		if (msg != null)
		{
			if (msg.getRecipient() == null)
			{
				for (Actor a : subscribers)
					if (!a.equals(msg.getSender()))
						a.receive(msg);
			}
			else
				msg.getRecipient().receive(msg);
		}
	}
}
