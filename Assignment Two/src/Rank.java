
public enum Rank {

	ace, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king;

	/**
	 * A static array of Rank values useful in loops
	 */
	public static final Rank[] RANKS = { ace, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king };

	/**
	 * Rank method to determine the integer value of a given Rank
	 * @param rank A Rank value
	 * @return An integer value of that rank
	 */
	public static int getInt(Rank rank) {
		switch (rank) {
		case ace:
			return 1;
		case two:
			return 2;
		case three:
			return 3;
		case four:
			return 4;
		case five:
			return 5;
		case six:
			return 6;
		case seven:
			return 7;
		case eight:
			return 8;
		case nine:
			return 9;
		case ten:
			return 10;
		case jack:
			return 11;
		case queen:
			return 12;
		case king:
			return 13;
		default:
			return -1;
		}
	}

	/**
	 * Rank method to determine the Rank value of a given integer
	 * @param rank A Rank integer
	 * @return A Rank value of that integer
	 */
	public static Rank rankFromInt(int rank) {
		switch (rank) {
		case 1:
			return ace;
		case 2:
			return two;
		case 3:
			return three;
		case 4:
			return four;
		case 5:
			return five;
		case 6:
			return six;
		case 7:
			return seven;
		case 8:
			return eight;
		case 9:
			return nine;
		case 10:
			return ten;
		case 11:
			return jack;
		case 12:
			return queen;
		case 13:
			return king;
		default:
			return null;
		}
	}
}
