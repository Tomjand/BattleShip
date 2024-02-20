package src.game;

public class Main
{
	public static void main(final String[] args)
	{
		final State[][] state = new State[10][10];
		final Board board = new Board();
		board.fillBoard();
		board.printBoard();
		board.printBoard();
	}
	
}
