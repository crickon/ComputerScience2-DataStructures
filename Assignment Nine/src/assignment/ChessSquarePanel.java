/**
 * NVCC Assignment Nine: "Eight Queens"
 * 
 * @author Matthew "crickon" Grillo
 */
package assignment;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * JPanel graphical representation of an Eight Queens chess board position AKA
 * the space on the board.
 */
@SuppressWarnings("serial")
public class ChessSquarePanel extends JPanel
{
	public final String STYLE = "Arial";
	public final int EMPHASIS = Font.PLAIN;
	public final String QUEEN = "Q";

	public int size = 100;
	private Color background;
	private boolean queen;

	/**
	 * Constructor to set the background color of the panel, and whether or not
	 * the panel should display a queen.
	 * 
	 * @param background
	 *            Color to set the JPanel background to
	 * @param queen
	 *            boolean value of if this panel should display a queen
	 */
	public ChessSquarePanel(Color background, boolean queen)
	{
		this.background = background;
		this.queen = queen;
	}

	/**
	 * Chained constructor with an added parameter for setting the size of the
	 * JPanel
	 * 
	 * @param background
	 *            Color to set the JPanel background to
	 * @param queen
	 *            boolean value of if this panel should display a queen
	 * @param size
	 *            size of the JPanel in pixels
	 */
	public ChessSquarePanel(Color background, boolean queen, int size)
	{
		this(background, queen);
		this.size = size;
	}

	/**
	 * Repainter method for the JPanel. Sets the background color to the
	 * background field value and draws a Q if the panel should display a queen.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(background);
		if (queen)
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setFont(new Font(STYLE, EMPHASIS, size));
			int x = g2d.getFont().getSize();
			int y = g2d.getFontMetrics().stringWidth(QUEEN);
			g2d.drawString(QUEEN, (int) (y - y * .85), (int) (x - x * .15));
		}
	}

	/**
	 * Setter for this whether this panel should display a queen, then repaints
	 * the panel.
	 * 
	 * @param queen
	 *            if the panel should display a queen
	 */
	public void setQueen(boolean queen)
	{
		this.queen = queen;
		repaint();
	}

}
