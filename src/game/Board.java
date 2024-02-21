package src.game;

import static src.game.State.*;

public class Board
{
	public static final int BOARD_SIZE = 10;
	private final Field[][] fields = new Field[BOARD_SIZE][BOARD_SIZE];

	private int shipsCount;

	public Board(){
		//if (BOARD_SIZE < 0 || BOARD_SIZE > 10)
		//{
		//	throw new IllegalArgumentException("the range of the board size must be between 1 and 10");
		//}
		for(int x = 0; x < BOARD_SIZE; x++){
			for(int y = 0; y < BOARD_SIZE; y++){
				fields[x][y] = new Field(x, y, EMPTY);
			}
		}
	}

	public void fillBoard()
	{
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			for (int j = 0; j < BOARD_SIZE; j++)
			{
				fields[i][j].setState(getRandomValue(Math.random()));
			}
		}
	}

	public void printBoard()
	{
		printLetters();
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			System.out.print(i + 1);
			if (i < 9)
			{
				System.out.print(' ');
			}
			for (int j = 0; j < BOARD_SIZE; j++)
			{
				System.out.print(fields[i][j].StateToChar());
			}
			System.out.print('\n');
		}
	}

	private static void printLetters()
	{
		System.out.print("  ");
		for (int i = 0; i < BOARD_SIZE   ; i++)
		{
			System.out.print((char) ('A' + i));
		}
		System.out.print('\n');
	}

	private State getRandomValue(final double random)
	{
		return (random < 0.2) ? HIT : ((random > 0.8) ? EMPTY : MISS);
	}


	public void addShip(final int x, final int y, final Submarine submarine) throws IllegalMoveException
	{
		if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE)
		{
			throw new IllegalMoveException("the range of the Ship position must be between 1 and 10");
		}

		shipsCount++;

		submarine.setOnField(fields[x][y], 0);
	}

	public int getShipsCount()
	{
		return shipsCount;
	}

	public Field getField(final int x, final int y)
	{
		return fields[x][y];
	}
}
