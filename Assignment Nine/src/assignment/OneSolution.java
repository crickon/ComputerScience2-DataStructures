/**
 * NVCC Assignment Nine: "Eight Queens"
 * 
 * @author Matthew "crickon" Grillo
 */
package assignment;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class that displays a singular solution to the Eight Queens problem that I
 * found from my research online.
 *
 */
public class OneSolution
{
	public final static int BOARD_SIZE = 8;
	public final static int PANEL_SIZE = 100;
	public final static int FRAME_SIZE = BOARD_SIZE * PANEL_SIZE;

	public final static Color ONE = Color.white;
	public final static Color TWO = Color.gray;

	private static JFrame frame;
	private static ChessSquarePanel[][] board;

	/**
	 * Run method
	 * 
	 * @param args
	 *            Command Line Arguments (None required)
	 */
	public static void main(String... args)
	{
		setBoard();
		setFrame();
		setSolution();
	}

	/**
	 * Helper method to setup the 2D ChessSquarePanel board array. Also sets the
	 * appropriate color to make the board checkered.
	 */
	private static void setBoard()
	{
		board = new ChessSquarePanel[BOARD_SIZE][BOARD_SIZE];
		boolean add = false;
		for (int x = 0; x < BOARD_SIZE; x++)
		{
			for (int y = 0; y < BOARD_SIZE; y++)
			{
				int num = (x * BOARD_SIZE) + y + (add ? 1 : 0);
				if (num % 2 == 0)
					board[x][y] = new ChessSquarePanel(ONE, false, PANEL_SIZE);
				else
					board[x][y] = new ChessSquarePanel(TWO, false, PANEL_SIZE);
			}
			add = !add;
		}
	}

	/**
	 * Helper method to set up the JFrame for visualizing the Eight Queens
	 * Solution
	 */
	private static void setFrame()
	{
		frame = new JFrame("One Solution Graphic");
		frame.setSize(FRAME_SIZE, FRAME_SIZE);
		frame.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

		for (JPanel[] arr : board)
			for (JPanel panel : arr)
				frame.add(panel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Helper method to set the spaces on the board for the solution manually
	 */
	private static void setSolution()
	{
		ArrayList<ChessSquarePanel> queens = new ArrayList<ChessSquarePanel>(8);
		queens.add(board[0][3]);
		queens.add(board[1][6]);
		queens.add(board[2][2]);
		queens.add(board[3][7]);
		queens.add(board[4][1]);
		queens.add(board[5][4]);
		queens.add(board[6][0]);
		queens.add(board[7][5]);
		for (ChessSquarePanel panel : queens)
			panel.setQueen(true);
	}
}
