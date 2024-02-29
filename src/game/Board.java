package src.game;

import static src.game.State.EMPTY;
import static src.game.State.HIT;
import static src.game.State.MISS;
//statek - lista pól / typ statku - ilosc masztow
// kazde filed ma x,y, state, ship
// strzal w board - sprawdzenie czy trafiony, zatopiony, pudlo
// statek
// po trafieniu sprawdzamy ilosc zwracamy : trafiony lub zatopiony
// klasa GAME - tablica
// wystawic API rest 2 instancje gry do polaczenia

public class Board
{
	public static final int BOARD_SIZE = 10;
	public static final int SHIP_TYPES_COUNT = 4;
	private final Field[][] fields = new Field[BOARD_SIZE][BOARD_SIZE];

	private int shipsCount;
	private int numberOfShipsByDeck[] = new int[SHIP_TYPES_COUNT];

	public Board()
	{
		//if (BOARD_SIZE < 0 || BOARD_SIZE > 10)
		//{
		//	throw new IllegalArgumentException("the range of the board size must be between 1 and 10");
		//}
		for (int x = 0; x < BOARD_SIZE; x++)
		{
			for (int y = 0; y < BOARD_SIZE; y++)
			{
				fields[x][y] = new Field(x, y, EMPTY);
			}
		}
	}

	private static void printLetters()
	{
		System.out.print("  ");
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			System.out.print((char) ('A' + i));
		}
		System.out.print('\n');
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

	private State getRandomValue(final double random)
	{
		return (random < 0.2) ? HIT : ((random > 0.8) ? EMPTY : MISS);
	}


	public void addShip(final int x, final int y, final Ship ship) throws IllegalMoveException
	{
		if (numberOfShipsByDeck[ship.getDecksCount() - 1] == getTotalCountOfShips(ship.getDecksCount()))
		{
			throw new IllegalMoveException("You can't add more than " + getTotalCountOfShips(ship.getDecksCount()) + " " + ship.getClass().getSimpleName());
		}

		if (x < 0 || x > BOARD_SIZE || y < 0 || y > BOARD_SIZE)
		{
			throw new IllegalMoveException("the range of the Ship position must be between 1 and 10");
		}
		shipsCount++;
		numberOfShipsByDeck[ship.getDecksCount() - 1]++;
		//ship.setOnField(fields[x][y], 0);
	}

	private int getTotalCountOfShips(final int decksCount)
	{
		return SHIP_TYPES_COUNT - decksCount + 1;
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
