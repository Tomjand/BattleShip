package src.game;

public class Field
{
	private final int x;
	private final int y;
	private State state;
	private Ship ship;

	public Field(final int x, final int y, final State state)
	{
		this.x = x;
		this.y = y;
		this.state = state;
	}

	public char StateToChar()
	{
		switch (this.state)
		{
			case EMPTY:
				return '.';
			case SUNK:
				return 'X';
			case HIT:
				return 'O';
			case MISS:
				return '-';
			default:
				return ' ';
		}
	}

	public void setShip(final Ship ship)
	{
		this.ship = ship;
	}

	public void setState(final State state)
	{
		this.state = state;
	}

	public State getState()
	{
		return state;
	}

}
