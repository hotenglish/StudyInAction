package scjp_book;

interface DoRound {
	public int getRound();
}

interface DoValue {

	public int getValue(int i);
}

public abstract class DoMath implements DoValue {

	public int getValue(int i) {
		return 0;
	}
}
