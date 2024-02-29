package src.game;

public class Submarine extends WarShip
{
	public Submarine(final Orientation orientation)
	{
		super(orientation.VERTICAL);
	}
	@Override
	public int getDecksCount()
	{
		return 1;
	}
}
