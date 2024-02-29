package src.game;

public class Destroyer extends WarShip
{
	public Destroyer(final Orientation orientation)
	{
		super(orientation);
	}
	@Override
	public int getDecksCount()
	{
		return 2;
	}
}
