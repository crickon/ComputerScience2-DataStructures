package assignment;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Graphical representation of a TicTacToe board
 */
abstract class Board extends JFrame implements ActionListener
{

	private JButton buttons[][];
	private JLabel lblHashCode;
	private JLabel lblWinTitle;

	private String boardString = "";

	/**
	 * Create JFrame window with a given window title
	 * 
	 * @param title
	 *            JFrame window title
	 */
	public Board(String title)
	{
		super(title);
		setupFrame();
	}

	/**
	 * Set JLabel display of this board's hash code
	 * 
	 * @param hashcode
	 *            TTT board hash code
	 */
	public void setHashCodeLabel(int hashcode)
	{
		lblHashCode.setText("" + hashcode);
	}

	/**
	 * Set JLabel display of whether this board is a valid winning TTT board.
	 * 
	 * @param result
	 *            WinnerLabel for whether the board is a winning board
	 */
	public void setWinnerLabel(String result)
	{
		lblWinTitle.setText(result);
	}

	/**
	 * Set JLabel display of whether this board is a valid winning TTT board
	 * from a boolean value.
	 * 
	 * @param result
	 *            whether the board is a winning board as a boolean value
	 */
	public void setWinnerLabel(boolean result)
	{
		if (result)
			setWinnerLabel("Winner");
		else
			setWinnerLabel("Loser");
	}

	// required because of abstract method, but not used
	@Override
	public void actionPerformed(ActionEvent e)
	{
	}

	/**
	 * Set up the top panel of the JFrame's display with a flow layout. This
	 * panel contains the hashcode and isWin labels.
	 * 
	 * @return initialized upper JPanel
	 */
	JPanel setupPanelOne()
	{
		JPanel panel = new JPanel();
		JLabel lblHCTitle = new JLabel("Hash Code");
		;
		lblHashCode = new JLabel("" + myHashCode());
		lblWinTitle = new JLabel(""); // Will say either Winner or Loser
		setWinnerLabel(TicTacToe.isWin(boardString));
		panel.setLayout(new FlowLayout());
		panel.add(lblHCTitle);
		panel.add(lblHashCode);
		panel.add(lblWinTitle);
		return panel;
	}

	/**
	 * Set up the lower panel of the JFrame display with a GridLayout. This
	 * panel contains a 2D array of JButtons to represent the tiles on a
	 * TicTacToe board.
	 * 
	 * @return initialized lower JPanel
	 */
	private JPanel setupPanelTwo()
	{
		JButton b;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(TicTacToe.ROWS, TicTacToe.COLS));
		buttons = new JButton[TicTacToe.ROWS][TicTacToe.COLS];

		int count = 1;

		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++)
			{
				char ch = randomXO();
				b = new JButton("" + ch);
				boardString += ch;
				b.setActionCommand("" + r + ", " + c);
				b.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						JButton btn = (JButton) e.getSource();
						btn.setText("" + cycleValue(btn.getText().charAt(0)));
						resetBoardString();
						setHashCodeLabel(myHashCode());
						setWinnerLabel(isWin());

					}
				});
				panel.add(b);
				buttons[r][c] = b;
			}

		return panel;
	}

	/**
	 * Return a different TicTacToe value from 'x' to 'o' to ' '
	 * 
	 * @param ch
	 *            current tile value
	 * @return new tile value
	 */
	private static char cycleValue(char ch)
	{
		switch (ch)
		{
			case 'x':
				return 'o';
			case 'o':
				return ' ';
			default:
				return 'x';
		}
	}

	/**
	 * Creates functional JFrame with upper and lower JPanels initialized.
	 */
	private void setupFrame()
	{
		JPanel panel2 = new JPanel();

		// Setup Frame
		super.setSize(250, 200);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		// Setup Panels
		panel2 = setupPanelTwo(); // panelOne displays a value that requires
									// panelTwo to be ready
		super.add(setupPanelOne());
		super.add(panel2);

		super.setVisible(true);
	}

	/**
	 * Method to get a random TicTacToe tile represented as a char
	 * 
	 * @return random TicTacToe char
	 */
	private char randomXO()
	{
		int rnd = (int) (Math.random() * TicTacToe.CHAR_POSSIBILITIES);
		switch (rnd)
		{
			case 1:
				return 'x';
			case 2:
				return 'o';
			default:
				return ' ';
		}
	}

	/**
	 * Gets a unique hash code value for this TicTacToe board
	 * 
	 * @return unique hash code TTT value
	 */
	abstract int myHashCode();

	/**
	 * Method to determine if a given String representation of a TTT board is a
	 * valid winning board
	 * 
	 * @param s
	 *            String representation of a TTT board
	 * @return Board is a valid winning board
	 */
	abstract boolean isWin(String s);

	/**
	 * Method to determine if the current TTT board is a valid winning board
	 * 
	 * @return Board is a valid winning board
	 */
	abstract boolean isWin();

	/**
	 * Method to get the TTT tile character value at a given position in the 2D
	 * JButton array
	 * 
	 * @param row
	 *            2D TTT board row value
	 * @param col
	 *            2D TTT board column value
	 * @return TTT tile character value
	 */
	public char charAt(int row, int col)
	{
		String value = buttons[row][col].getText();
		if (value.length() > 0)
			return value.charAt(0);
		else
			return '*';
	}

	/**
	 * Method to get the TTT tile character value at a given position in a
	 * String representation of a TTT Board
	 * 
	 * @param row
	 *            2D TTT board row value
	 * @param col
	 *            2D TTT board column value
	 * @return TTT tile character value
	 */
	public char charAt(String s, int row, int col)
	{
		int pos = row * TicTacToe.COLS + col;
		if (s.length() >= pos)
			return s.charAt(pos);
		else
			return '*';
	}

	/**
	 * Method to display a String representation of a TTT board in the JFrame's
	 * 2D button array
	 * 
	 * @param s
	 *            String representation of a TTT board
	 */
	public void show(String s)
	{
		int pos = 0;
		String letter;
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++)
			{
				char ch = s.charAt(pos);
				switch (ch)
				{
					case '1':
						letter = "x";
						break;
					case '2':
						letter = "o";
						break;
					case '0':
						letter = " ";
						break;
					default:
						letter = "" + ch;
				}
				buttons[r][c].setText(letter);
				pos++;
			}
	}

	/**
	 * Set this Board's String representation based on the 2D array of buttons
	 */
	public void resetBoardString()
	{
		boardString = "";
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++)
			{
				boardString += buttons[r][c].getText();
			}
	}

	/**
	 * Set this Board's String representation based on a given String
	 * 
	 * @param s
	 *            this Board's new String representation
	 */
	public void setBoardString(String s)
	{
		boardString = s;
		show(s);
	}

	/**
	 * Getter to return this board's current boardString
	 * 
	 * @return this board's current boardString
	 */
	public String getBoardString()
	{
		return boardString;
	}

	/**
	 * Procedure to display a random TicTacToe Board in this JFrame, display
	 * it's proper hashcode and whether or not this TTT Board is a valid winning
	 * board
	 */
	public void displayRandomString()
	{
		String tempBoard = "";
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++)
			{
				char ch = randomXO();
				tempBoard += ch;
				buttons[r][c].setText("" + ch);
			}
		this.setBoardString(tempBoard);
		setHashCodeLabel(myHashCode());
		setWinnerLabel(isWin());
	}

}