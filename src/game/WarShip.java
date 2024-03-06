package src.game;

public abstract class WarShip implements Ship
{
	public enum Orientation
	{
		HORIZONTAL, VERTICAL
	}
	private Orientation orientation;
	private int hits;
	private final Field[] occupied;

	public WarShip(final Orientation orientation)
	{
		this.orientation = orientation;
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
			for (int i = 0; i < occupied.length; i++)
			{
				occupied[i].setState(State.SUNK);
			}
		}
	}

	public Orientation getOrientation()
	{
		return orientation;
	}

	public void setOrientation(final Orientation orientation)
	{
		this.orientation = orientation;
	}



	public void setOnField(final Field field, final int deckNumber){
		field.setShip(this);
		field.setState(State.OCCUPIED);
		occupied[deckNumber] = field;
	}
}

class Cruiser extends WarShip
{
	public Cruiser(final Orientation orientation)
	{
		super(orientation);
	}

	@Override
	public int getDecksCount()
	{
		return 3;
	}
}

