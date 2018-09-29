public class TicTacToe {
	private final static boolean dev = true;
	private static char[][] board;
	
	public static void main (String[] args) {
		board = new char[][] {{'-', '-', '-'},{'-', '-', '-'},{ '-', '-', '-'}};
		
		if (dev)
			printBoard();
		
		
	}
	
	private static void printBoard () {
		for (int r = 0; r < 3; r++) {
			System.out.print("|");
			for (char c : board[r]) {
				System.out.print(" " + c + " ");
			}
			System.out.print("|\n");
		}
		System.out.println();
	}
}
