package src.game;

public class BattleShip extends WarShip
{
	public BattleShip(final Orientation orientation){
		super(orientation);
	}
	@Override
	public int getDecksCount()
	{
		return 4;
	}
}
