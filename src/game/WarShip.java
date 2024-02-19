package src.game;

public abstract class WarShip implements Ship
{
	private Orientation orientation;
	private int hits;
	private final Field[] occupied;

	public WarShip()
	{
		occupied = new Field[this.getDecksCount()];
	}

	@Override
	public boolean isSunk()
	{
		return hits == this.getDecksCount();
	}

	@Override
	public void hit()
	{
		hits++;
		if (isSunk())
		{
			for (final Field field : occupied)
			{
				field.setState(State.SUNK);
			}
		}
	}

	enum Orientation
	{
		HORIZONTAL, VERTICAL
	}

	public void setOnField(final Field field, final int deckNumber){
		field.setShip(this);
		field.setState(State.OCCUPIED);
		occupied[deckNumber] = field;
	}
}

class Submarine extends WarShip
{
	@Override
	public int getDecksCount()
	{
		return 1;
	}
}

class Destroyer extends WarShip
{
	@Override
	public int getDecksCount()
	{
		return 2;
	}
}

class Cruiser extends WarShip
{
	@Override
	public int getDecksCount()
	{
		return 3;
	}
}

class BattleShip extends WarShip
{
	@Override
	public int getDecksCount()
	{
		return 4;
	}
}

