package src.game;

import java.util.Scanner;

public class Main
{
	public static void main(final String[] args) throws IllegalMoveException
	{
		final Board board = new Board();
		board.fillBoard();


		final Scanner scanner = new Scanner(System.in);

		while(board.getShipsCount() > 0){
			board.printBoard();
			final String move = scanner.nextLine();
			final int y = move.charAt(0) - 'A';
			final int x = move.charAt(1) - '0';

			try
			{
				board.shot(x, y);
			}
			catch (final IllegalMoveException e)
			{
				System.out.println(e.getMessage());
			}


		}
	}
	
}
