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
		//to przenisec do enum dodac metode zwaracjaca konkretne znaki
		switch (this.state)
		{
			case EMPTY:
				return ' ';
			case SUNK:
				return 'X';
			case HIT:
				return 'O';
			case MISS:
				return '!';
			case OCCUPIED:
				return ' ';
			default:
				return '?';
		}
	}

	public State getState()
	{
		return state;
	}

	public void setState(final State state)
	{
		this.state = state;
	}

	public int getY()
	{
		return y;
	}

	public int getX()
	{
		return x;
	}

	public Ship getShip()
	{
		return ship;
	}

	public void setShip(final Ship ship)
	{
		this.ship = ship;
	}
}
