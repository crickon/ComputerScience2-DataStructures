
/**
 * NVCC Assignment Four
 * 
 * @author Matthew Grillo
 */
import java.util.LinkedList;
import java.util.Queue;

public class ProductionLine
{
	/**
	 * Input Queue of Disks on the ProductionLine implemented as a LinkedList.
	 */
	private Queue<Disk> input;
	/**
	 * Output Queue of Towers from the ProductionLine implemented as a
	 * LinkedList.
	 */
	private Queue<Tower> output;
	/**
	 * Temporary Tower to add Disks from the input queue before unloading onto
	 * the output Queue.
	 */
	private Tower robotArm;

	/**
	 * Default Constructor that initializes the input and output Queues as well
	 * as the robotArm Tower.
	 */
	public ProductionLine()
	{
		this.input = new LinkedList<Disk>();
		this.output = new LinkedList<Tower>();
		this.robotArm = new Tower();
	}

	/**
	 * Adds a Disk to the input queue
	 * 
	 * @param disk
	 *            Disk object to be added
	 * @throws IllegalArgumentException if the Disk object is null
	 */
	public void addDisk(Disk disk)
	{
		if (disk != null)
			input.add(disk);
		else
			throw new IllegalArgumentException("Attempted to add a null Disk");
	}

	/**
	 * Flips the robotArm Tower, adds to the output Queue and initializes
	 * robotArm as a new Tower.
	 */
	public void unloadRobot()
	{
		robotArm.flip();
		output.add(robotArm);
		robotArm = new Tower();
	}

	/**
	 * Processes the input Disks to determine if they should be stacked on the
	 * robotArm, or if the robotArm should be unloaded. Loops until the input
	 * Queue is empty. Disks will be stacked only if their radius' are equal or
	 * greater than the topmost Disk on the robotArm Tower. The robotArm will be
	 * unloaded when a Disk is not greater than the topmost Disk.
	 */
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
		//final unload of what is left on the arm
		if (!robotArm.isEmpty())
			unloadRobot();
	}

	/**
	 * Removes a Tower from the front of the Queue and returns it
	 * 
	 * @return Tower object from the output Queue
	 * @throws IllegalStateException
	 *             if the output Queue is empty
	 */
	public Tower removeTower()
	{
		if (hasOutput())
		{
			return output.poll();
		}
		throw new IllegalStateException("The output queue is empty");
	}

	/**
	 * Checks if the output Queue is empty
	 * 
	 * @return whether the output Queue is empty
	 */
	public boolean hasOutput()
	{
		return !output.isEmpty();
	}

}
