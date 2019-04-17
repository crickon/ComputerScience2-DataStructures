/**
 * NVCC Assignment Nine: "Eight Queens"
 * 
 * @author Matthew "crickon" Grillo
 */
package assignment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that Displays all solutions to the Eight Queens problem
 */
public class DisplayManager
{
	public final static int BOARD_SIZE = 8;

	public final static int TOP_SIZE = 100;
	public final static int BUTTONS_HEIGHT = 50;
	public final static int SOLUTION_PANEL_SIZE = 70;
	public final static int SOLUTION_SIZE = BOARD_SIZE * SOLUTION_PANEL_SIZE;
	public final static int FRAME_HEIGHT = TOP_SIZE + SOLUTION_SIZE + BUTTONS_HEIGHT;
	public final static int FRAME_WIDTH = SOLUTION_SIZE;

	public final static Color ONE = Color.white;
	public final static Color TWO = Color.gray;

	private ArrayList<ArrayList<Vector2i>> boardList;
	private int current = -1;

	private JFrame frame;
	private JLabel title;
	private SolutionDisplay solution;
	private JPanel buttons;
	private GridBagConstraints gbc;

	/**
	 * Constructor sets the list of solutions to an empty list of 2D array's and
	 * creates the JFrame and initial graphics
	 */
	public DisplayManager()
	{
		this.boardList = new ArrayList<ArrayList<Vector2i>>();
		setFrame();
	}

	/**
	 * Method to set up the initial Java graphics for the Eight Queens visuals.
	 */
	private void setFrame()
	{
		this.frame = new JFrame("Eight Queens Solutions Visualized by Matthew Grillo");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();

		this.title = new JLabel();
		title.setPreferredSize(new Dimension(TOP_SIZE, FRAME_WIDTH));
		title.setFont(new Font("Arial", Font.BOLD, 50));
		title.setText("Solution #0");
		gbc.gridx = 0;
		gbc.gridy = 0;
		frame.add(title, gbc);

		this.solution = new SolutionDisplay();
		solution.setPreferredSize(new Dimension(SOLUTION_SIZE, SOLUTION_SIZE));
		solution.setMinimumSize(new Dimension(SOLUTION_SIZE, SOLUTION_SIZE));
		solution.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
		gbc.gridy = 1;
		frame.add(solution, gbc);

		this.buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		JButton next = new JButton("Next Solution");
		next.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				displayNext();
			}
		});
		JButton prev = new JButton("Previous Solution");
		prev.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				displayPrevious();
			}
		});
		buttons.add(prev);
		buttons.add(next);
		gbc.gridy = 2;
		frame.add(buttons, gbc);

		frame.setVisible(true);
	}

	/**
	 * Method to display the next solution in the List.
	 */
	public void displayNext()
	{
		if (boardList.isEmpty())
			return;
		if (current >= boardList.size() - 1)
			return;
		current += 1;
		title.setText("Solution #" + (current + 1));
		solution.setSolution(boardList.get(current));
	}

	/**
	 * Method to display the previous solution in the List.
	 */
	public void displayPrevious()
	{
		if (boardList.isEmpty())
			return;
		if (current <= 0)
			return;
		current -= 1;
		title.setText("Solution #" + (current + 1));
		solution.setSolution(boardList.get(current));
	}

	/**
	 * Method to add a solution to the List of solutions
	 * 
	 * @param queens
	 *            List of Queen positions
	 */
	public void addSolution(ArrayList<Vector2i> queens)
	{
		boardList.add(new ArrayList<>(queens));
	}

	/**
	 * Nested class to handle the solution display
	 */
	private class SolutionDisplay extends JPanel
	{
		private ChessSquarePanel[][] board;

		public SolutionDisplay()
		{
			setBoard();
		}

		/**
		 * Initial board setup. The board is a 2D array of ChessSquarePanels,
		 * each space on the board is represented by a ChessSquarePanel from the
		 * single solution display.
		 */
		private void setBoard()
		{
			board = new ChessSquarePanel[BOARD_SIZE][BOARD_SIZE];
			boolean add = false;
			for (int x = 0; x < BOARD_SIZE; x++)
			{
				for (int y = 0; y < BOARD_SIZE; y++)
				{
					int num = (x * BOARD_SIZE) + y + (add ? 1 : 0);
					if (num % 2 == 0)
						board[x][y] = new ChessSquarePanel(ONE, false, SOLUTION_PANEL_SIZE);
					else
						board[x][y] = new ChessSquarePanel(TWO, false, SOLUTION_PANEL_SIZE);
					this.add(board[x][y]);
				}
				add = !add;
			}
		}

		/**
		 * Set the display to a solution given by the list of spaces on the
		 * board that contain a queen.
		 * 
		 * @param queens
		 *            List of spaces on the board that contain a Queen
		 */
		public void setSolution(ArrayList<Vector2i> queens)
		{
			for (int r = 0; r < BOARD_SIZE; r++)
				for (int c = 0; c < BOARD_SIZE; c++)
					for (Vector2i queen : queens)
						if (queen.getX() == r && queen.getY() == c)
						{
							board[r][c].setQueen(true);
							break;
						}
						else
							board[r][c].setQueen(false);

			this.repaint();
		}
	}
}
