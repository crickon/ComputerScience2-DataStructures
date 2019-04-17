/**
 * NVCC Assignment Nine: "Eight Queens"
 * 
 * @author Matthew "crickon" Grillo
 */
package assignment;

import java.util.ArrayList;

/**
 * Main Eight Queens class that contains the recursion to solve the Eight Queens
 * problem
 */
public class EightQueens
{
	public static final int BOARD_SIZE = 8;
	public static final int COMPLETE = 8;

	private DisplayManager display;

	/**
	 * Solve every 8 Queens solution
	 */
	public void solveQueens()
	{
		display = new DisplayManager();
		ArrayList<Vector2i> list = new ArrayList<Vector2i>(COMPLETE);
		addQueens(list);
	}

	private boolean aSolution = false;

	/**
	 * Recursive method to solve a solution for 8 Queens, then modified to solve
	 * every solution. Solutions are sent to the DisplayManager so their visual
	 * representation can be viewed.
	 * 
	 * @param queens
	 *            List of Queens (will never go above the size 8)
	 * @return if a solution was found
	 */
	public boolean addQueens(ArrayList<Vector2i> queens)
	{
		if (queens.size() == COMPLETE)
		{
			if (display != null)
				display.addSolution(queens);
			System.out.println(queens.toString());
			aSolution = true;
			return true;
		}
		int row = 0;
		int col = 0;
		if (!queens.isEmpty())
			row = queens.get(queens.size() - 1).getX() + 1;

		for (; row < BOARD_SIZE; row++)
		{
			for (; col < BOARD_SIZE; col++)
			{
				if (annemarieValid(row, col, queens))
				{
					queens.add(new Vector2i(row, col));
					if (addQueens(queens))
					{
						// return true;
					}
					else
					{
						// queens.remove(queens.size() - 1);
					}
					queens.remove(queens.size() - 1);
				}
			}
		}
		// return false;
		return aSolution;
	}

	/**
	 * Method to determine if a row, col position will not interfere with any
	 * positions in the List
	 * 
	 * @param row
	 *            row of potential Queen position
	 * @param col
	 *            column of potential Queen position
	 * @param queens
	 *            List of Queens already in the solution
	 * @return if the row, col position is valid to add a Queen to
	 */
	public boolean isValid(int row, int col, ArrayList<Vector2i> queens)
	{
		if (row >= BOARD_SIZE || col >= BOARD_SIZE)
			return false;
		if (queens.isEmpty())
			return true;

		boolean[][] board = new boolean[BOARD_SIZE][BOARD_SIZE];

		// check if rows or columns conflict with any of the current queens
		for (Vector2i queen : queens)
		{
			if (row == queen.getX() || col == queen.getY())
				return false;
			board[queen.getX()][queen.getY()] = true;
		}
		// check if diagonals from row and col contain a queen
		int dist = Math.min(row, col);
		int tempRow = row - dist;
		int tempCol = col - dist;
		while (tempRow < board.length && tempCol < board.length)
		{
			if (board[tempRow][tempCol])
				return false;
			tempRow++;
			tempCol++;
		}
		dist = Math.min(board.length - 1 - row, col);
		tempRow = row + dist;
		tempCol = col - dist;
		while (tempRow >= 0 && tempCol < board.length)
		{
			if (board[tempRow][tempCol])
				return false;
			tempRow--;
			tempCol++;
		}
		// if all tests are passed, then the position is valid for placement
		return true;
	}

	/**
	 * isValid method based on AnneMarie's isValid method, which is much more
	 * efficient than the one that I came up with. Creds to @annemariecabs
	 * 
	 * @param row
	 *            row of potential Queen position
	 * @param col
	 *            column of potential Queen position
	 * @param queens
	 *            List of Queens already in the solution
	 * @return if the row, col position is valid to add a Queen to
	 */
	public boolean annemarieValid(int row, int col, ArrayList<Vector2i> queens)
	{
		if (row >= BOARD_SIZE || col >= BOARD_SIZE)
			return false;
		for (Vector2i queen : queens)
		{
			if (row == queen.getX() || col == queen.getY())
				return false;
			if (queen.getX() - queen.getY() == row - col)
				return false;
			if (queen.getX() + queen.getY() == row + col)
				return false;
		}
		return true;
	}

	/**
	 * Run method to start the solving of the 8 Queens chess puzzle
	 * 
	 * @param args
	 *            Command Line Arguments (None required)
	 */
	public static void main(String... args)
	{
		EightQueens queens = new EightQueens();
		queens.solveQueens();
	}
}
