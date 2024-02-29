package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.game.BattleShip;
import src.game.Board;
import src.game.Destroyer;
import src.game.Field;
import src.game.IllegalMoveException;
import src.game.State;
import src.game.Submarine;
import src.game.WarShip;


public class BoardTest
{

	@Test
	public void shouldAddSubmarineOnField() throws Exception
	{
		//given
		final Board board = new Board();

		//when
		board.addShip(10 , 10, new Submarine(WarShip.Orientation.HORIZONTAL));

		//then
		final Field field = board.getField(10, 10);
		assertEquals(State.OCCUPIED,field.getState());
	}

	@Test
	public void shouldAddDestroyerOnField() throws Exception
	{
		//given
		final Board board = new Board();

		//when
		board.addShip(7 , 7, new Destroyer(WarShip.Orientation.HORIZONTAL));

		//then
		final Field field = board.getField(7, 7);
		assertEquals(State.OCCUPIED,field.getState());
	}



	@Test
	public void shouldNotToBeAbleAddFiveSubmarine() throws Exception
	{
		//given
		final Board board = new Board();
		board.addShip(0 , 0, new Submarine(WarShip.Orientation.HORIZONTAL));
		board.addShip(3 , 1, new Submarine(WarShip.Orientation.HORIZONTAL));
		board.addShip(0 , 5, new Submarine(WarShip.Orientation.HORIZONTAL));
		board.addShip(1 , 4, new Submarine(WarShip.Orientation.HORIZONTAL));
		//when
		final Exception exception = assertThrows(IllegalMoveException.class,
				() -> board.addShip(2, 2, new Submarine(WarShip.Orientation.HORIZONTAL)));

		//then
		final String expectedMessage = "You can't add more than 4 Submarine";
		final String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void shouldNotBeAbleToAddTwoBattleships() throws Exception
	{
		//given
		final Board board = new Board();
		board.addShip(0 , 0, new BattleShip(WarShip.Orientation.HORIZONTAL));
		//when
		final Exception exception = assertThrows(IllegalMoveException.class,
				() -> board.addShip(4, 2, new BattleShip(WarShip.Orientation.HORIZONTAL)));
		//then
		final String expectedMessage = "You can't add more than 1 BattleShip";
		final String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}



	@Test
	public void shouldFailToAddShipOutsideRangeX() throws Exception
	{
		//given
		final Board board = new Board();

		//when
		board.addShip(-1 , 0, new Submarine(WarShip.Orientation.HORIZONTAL));

		//then
		assertThrows(IllegalMoveException.class, () -> board.addShip(-1 , 0, new Submarine(WarShip.Orientation.HORIZONTAL)));
	}

	@Test
	public void shouldFailToAddShipOutsideRangeY() throws Exception
	{
		//given
		final Board board = new Board();

		//when
		board.addShip(0 , -1, new Submarine(WarShip.Orientation.HORIZONTAL));

		//then
		assertThrows(IllegalMoveException.class, () -> board.addShip(0 , -1, new Submarine(WarShip.Orientation.HORIZONTAL)));

	}




}

