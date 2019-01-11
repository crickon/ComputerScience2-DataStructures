/**
 * Area fill functions for the game of Hex
 */

public class HexBoard extends CharMatrix
{
	private final int MIN = 0;
	private int rows;
	private int cols;

	public HexBoard(int rows, int cols)
	{
		super(rows, cols);
		this.rows = rows;
		this.cols = cols;
	}

	public boolean isBlack(int r, int c)
	{
		return super.charAt(r, c) == 'B';
	}

	public boolean isWhite(int r, int c)
	{
		return super.charAt(r, c) == '.';
	}

	public boolean isGray(int r, int c)
	{
		return super.charAt(r, c) == 'G';
	}

	public void setBlack(int r, int c)
	{
		super.setCharAt(r, c, 'B');
	}

	public void setWhite(int r, int c)
	{
		super.setCharAt(r, c, '.');
	}

	public void setGray(int r, int c)
	{
		super.setCharAt(r, c, 'G');
	}

	/**
	 * Returns true if there is a contiguous chain of black stones that starts
	 * in col 0 and ends in the last column of the board; otherwise returns
	 * false.
	 */
	public boolean blackHasWon()
	{
		boolean won = false;
		for (int c = 0; c < cols; c++)
		{
			areaFill(c, 0);
		}
		for (int r = 0; r < rows; r++)
		{
			if (this.isGray(r, cols - 1))
				won = true;
		}
		System.out.println(this.toString());
		for (int c = 0; c < cols; c++)
			for (int r = 0; r < rows; r++)
				if (this.isGray(r, c))
					this.setBlack(r, c);
		return won;
	}

	/**
	 * Fills the contiguous area that contains r,c with gray color. Does nothing
	 * if r, c is out of bounds or is not black.
	 */
	public void areaFill(int r, int c)
	{
		if (this.isInBounds(r, c))
		{
			if (this.isBlack(r, c))
			{
				this.setGray(r, c);
				areaFill(r - 1, c - 1);
				areaFill(r - 1, c);
				areaFill(r, c - 1);
				areaFill(r, c + 1);
				areaFill(r + 1, c);
				areaFill(r + 1, c + 1);
			}
		}
	}

	public String toString()
	{
		String s = "";

		for (int r = 0; r < numRows(); r++)
		{
			for (int c = 0; c < numCols(); c++)
			{
				if (isBlack(r, c))
					s += 'B';
				else if (isWhite(r, c))
					s += 'W';
				else if (isGray(r, c))
					s += 'X';
				else
					s += '.';
			}
			s += '\n';
		}
		return s;
	}

	// ****************************************************************

	private boolean isInBounds(int r, int c)
	{
		return r >= MIN && c >= MIN && r < rows && c < cols;
	}
}
