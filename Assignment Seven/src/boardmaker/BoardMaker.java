package boardmaker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import assignment.TicTacToe;

public class BoardMaker
{
	private final String folder = "boardLists/";
	private final String validFile = "validBoards.txt";
	private final String invalidFile = "invalidBoards.txt";
	private final String winnerFile = "winningBoards.txt";
	private final String loserFile = "nonwinningBoards.txt";
	private final String allFile = "allBoards.txt";

	private PrintWriter valid;
	private PrintWriter invalid;
	private PrintWriter winners;
	private PrintWriter losers;
	private PrintWriter all;

	public BoardMaker()
	{
		createWriters();
		bigBirdsNest();
		closeWriters();
	}

	private void createWriters()
	{
		valid = createWriter(folder + validFile);
		invalid = createWriter(folder + invalidFile);
		winners = createWriter(folder + winnerFile);
		losers = createWriter(folder + loserFile);
		this.all = createWriter(folder + allFile);
	}

	private void bigBirdsNest()
	{
		for (int a = 0; a < 3; a++)
			for (int b = 0; b < 3; b++)
				for (int c = 0; c < 3; c++)
					for (int d = 0; d < 3; d++)
						for (int e = 0; e < 3; e++)
							for (int f = 0; f < 3; f++)
								for (int g = 0; g < 3; g++)
									for (int h = 0; h < 3; h++)
										for (int i = 0; i < 3; i++)
										{
											String boardStr = "" + getChar(a) + getChar(b) + getChar(c) + getChar(d)
													+ getChar(e) + getChar(f) + getChar(g) + getChar(h) + getChar(i);
											char[][] board = TicTacToe.makeBoard(boardStr);
											
											all.println(boardStr);
											if (TicTacToe.valid(board))
											{
												valid.println(boardStr);
												if (TicTacToe.isWin(board))
													winners.println(boardStr);
												else
													losers.println(boardStr);
											}
											else
												invalid.println(boardStr);
										}
	}

	private char getChar(int i)
	{
		if (i == 0)
			return ' ';
		if (i == 1)
			return 'x';
		return 'o';
	}

	private void closeWriters()
	{
		valid.close();
		invalid.close();
		winners.close();
		losers.close();
		all.close();
	}

	private static PrintWriter createWriter(String path)
	{
		Writer writer = null;
		try
		{
			writer = new FileWriter(path, false);
		}
		catch (IOException e)
		{
			System.out.println("Unable to create file writer");
		}
		return new PrintWriter(writer);
	}

	public static void main(String[] args)
	{
		new BoardMaker();
	}
}
