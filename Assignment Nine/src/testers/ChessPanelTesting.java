package testers;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import assignment.ChessSquarePanel;

public class ChessPanelTesting
{
	public static void main(String... args)
	{
		JFrame frame = new JFrame("Panel test");
		ChessSquarePanel panel = new ChessSquarePanel(Color.gray, true);
		frame.setLayout(new FlowLayout());
		frame.setSize(1080, 1080);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
