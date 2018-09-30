
public enum Rank {

	Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King;

	public static final Rank[] RANKS = { Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King };

	public static int getInt(Rank rank) {
		switch (rank) {
		case Ace:
			return 1;
		case Two:
			return 2;
		case Three:
			return 3;
		case Four:
			return 4;
		case Five:
			return 5;
		case Six:
			return 6;
		case Seven:
			return 7;
		case Eight:
			return 8;
		case Nine:
			return 9;
		case Ten:
			return 10;
		case Jack:
			return 11;
		case Queen:
			return 12;
		case King:
			return 13;
		default:
			return -1;
		}
	}

	public static Rank rankFromInt(int rank) {
		switch (rank) {
		case 1:
			return Ace;
		case 2:
			return Two;
		case 3:
			return Three;
		case 4:
			return Four;
		case 5:
			return Five;
		case 6:
			return Six;
		case 7:
			return Seven;
		case 8:
			return Eight;
		case 9:
			return Nine;
		case 10:
			return Ten;
		case 11:
			return Jack;
		case 12:
			return Queen;
		case 13:
			return King;
		default:
			return null;
		}
	}

	public static Rank getRank(int rank) {
		return null;
	}
}
