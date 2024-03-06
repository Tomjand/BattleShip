package src.game;

import static src.game.State.EMPTY;
import static src.game.State.HIT;
import static src.game.State.MISS;
import static src.game.State.OCCUPIED;

import java.util.HashMap;
import java.util.Map;
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
	Map<Class<? extends Ship>, Integer> myMap = new HashMap<>();
	private int shipsCount;
	private int submarineCount;
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
		final int decksCount = ship.getDecksCount();
		if (numberOfShipsByDeck[decksCount - 1] == getTotalCountOfShips(decksCount))
		{
			throw new IllegalMoveException(
					"You can't add more than " + getTotalCountOfShips(decksCount) + " " + ship.getClass().getSimpleName());
		}

		//if (submarineCount == 4)
		//{
		//	throw new IllegalMoveException("You can't add more than 4 Submarine");
		//}

		if (!isValidPositionOnBoard(x) || !isValidPositionOnBoard(y))
		{
			throw new IllegalMoveException("the range of the Ship position must be between 0 and " + BOARD_SIZE);
		}

		final Field[] field = new Field[decksCount];
		int xToSet = x;
		int yToSet = y;
		for (int i = 0; i < decksCount; i++)
		{
			if (ship.getOrientation() == WarShip.Orientation.HORIZONTAL)
			{
				xToSet = x + i;
			}
			else
			{
				yToSet = y + i;
			}

			if (isOutside(xToSet, yToSet))
			{
				throw new IllegalMoveException("Ship is out of the board");
			}
			field[i] = fields[xToSet][yToSet];
			if (isFieldOccupied(field[i])){
				throw new IllegalMoveException("Field is occupied");
			}
		}

		for (int i = 0; i < decksCount; ++i)
		{
			ship.setOnField(field[i], i);
		}
		shipsCount++;
		numberOfShipsByDeck[decksCount - 1]++;
	}

	private boolean isFieldOccupied(final Field field)
	{
		for (int y= field.getY()-1; y <= field.getY()+1; y++ )
			for (int x= field.getX()-1; x <= field.getX() +1; x++ )
			{
				if(isOutside(x,y)){
					continue;
				}

				if(fields[x][y].getState() != EMPTY){
					return true;
				}

			}

		return false;
	}

	private void addShip2(final Ship ship)
	{
		//myMap.put(ship.getClass().getName(), myMap.get(ship.getClass()) + 1);
	}

	private boolean isValidPositionOnBoard(final int position)
	{
		return position >= 0 && position < BOARD_SIZE;
	}

	private boolean isOutside(final int x, final int y)
	{
		return x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE;
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

	public void shot(final int x, final int y) throws IllegalMoveException
	{
		final Field field = getField(x,y);

		if(field.getState() == MISS || field.getState() == HIT || field.getState() == State.SUNK)
		{
			throw new IllegalMoveException("You can't shoot twice in the same field");
		}

		if (field.getState() == EMPTY)
		{
			field.setState(MISS);
		}
		else if (field.getState() == OCCUPIED)
		{
			field.setState(State.HIT);
			field.getShip().hit();
			if (field.getShip().isSunk())
			{
				shipsCount--;
			}
		}

	}
}
