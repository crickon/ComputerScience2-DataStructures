import java.util.LinkedList;
import java.util.Queue;

public class ProductionLine
{
	private Queue<Disk> input;
	private Queue<Tower> output;
	private Tower robotArm;

	public ProductionLine()
	{
		this.input = new LinkedList<Disk>();
		this.output = new LinkedList<Tower>();
		this.robotArm = new Tower();
	}

	public void addDisk(Disk disk)
	{
		input.add(disk);
	}

	public void unloadRobot()
	{
		robotArm.flip();
		output.add(robotArm);
		robotArm = new Tower();
	}

	public void process()
	{
		while (input.isEmpty() == false)
		{
			if (robotArm.isEmpty())
				robotArm.add(input.poll());

			else
			{
				Disk disk = input.poll();
				if (robotArm.peek().compareTo(disk) > 0)
					unloadRobot();
				robotArm.add(disk);
			}
		}
	}

	public Tower removeTower()
	{
		if (output.isEmpty() == false)
		{
			return output.poll();
		}
		throw new IllegalStateException("The output queue is empty");
	}

	public boolean hasOutput()
	{
		return !output.isEmpty();
	}

}
