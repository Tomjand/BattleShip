package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
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

	private Board board;

	@BeforeEach
	public void setUp()
	{
		board = new Board();
	}

	@Test
	public void shouldAddSubmarineOnField() throws IllegalMoveException
	{
		//when
		board.addShip(2, 2, new Submarine(WarShip.Orientation.HORIZONTAL));

		//then
		final Field field = board.getField(2, 2);
		assertEquals(State.OCCUPIED, field.getState());
	}
	//@Test
	//public void shouldAddSubmarineOnField()
	//{
	//	//when
	//	board.addShip(10 , 10, new Submarine(WarShip.Orientation.HORIZONTAL));
	//
	//	//then
	//	final Field field = board.getField(10, 10);
	//	assertEquals(State.OCCUPIED,field.getState());
	//}


	@Test
	public void shouldAddDestroyerOnFields() throws IllegalMoveException
	{

		//when
		board.addShip(7, 7, new Destroyer(WarShip.Orientation.HORIZONTAL));

		//then
		final Field field = board.getField(8, 7);
		assertEquals(State.OCCUPIED, field.getState());
	}

	@Test
	public void shouldNotBeAbleToGetOutside() throws IllegalMoveException
	{
		//when
		final Exception exception = assertThrows(IllegalMoveException.class,
				() -> board.addShip(9, 0, new Destroyer(WarShip.Orientation.HORIZONTAL)));

		final String expectedMessage = "Ship is out of the board";
		final String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);

	}

	@Test
	public void shouldNotBeAbleToBeInContact() throws IllegalMoveException
	{
		//when
		board.addShip(1, 1, new Destroyer(WarShip.Orientation.HORIZONTAL));
		//when
		assertThrows(IllegalMoveException.class,
				() -> board.addShip(3, 1, new Destroyer(WarShip.Orientation.HORIZONTAL)));
	}


	@Test
	public void shouldNotToBeAbleAddFiveSubmarine() throws Exception
	{
		//given

		board.addShip(1, 1, new Submarine(WarShip.Orientation.HORIZONTAL));
		board.addShip(3, 3, new Submarine(WarShip.Orientation.HORIZONTAL));
		board.addShip(5, 5, new Submarine(WarShip.Orientation.HORIZONTAL));
		board.addShip(7, 7, new Submarine(WarShip.Orientation.HORIZONTAL));
		//when
		final Exception exception = assertThrows(IllegalMoveException.class,
				() -> board.addShip(9, 9, new Submarine(WarShip.Orientation.HORIZONTAL)));

		//then
		final String expectedMessage = "You can't add more than 4 Submarine";
		final String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void shouldNotBeAbleToAddTwoBattleships() throws Exception
	{
		board.addShip(1, 1, new BattleShip(WarShip.Orientation.HORIZONTAL));
		//given

		//when
		final Exception exception = assertThrows(IllegalMoveException.class,
				() -> board.addShip(1, 8, new BattleShip(WarShip.Orientation.HORIZONTAL)));
		//then
		final String expectedMessage = "You can't add more than 1 BattleShip";
		final String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void shouldMarkMiss() throws Exception
	{
		//when
		board.shot(1, 1);

		//then
		assertEquals(State.MISS, board.getField(1, 1).getState());
	}

	@Test
	public void shouldMarkHit() throws Exception
	{
		//given
		board.addShip(1, 1, new Destroyer(WarShip.Orientation.HORIZONTAL));
		//when
		board.shot(1, 1);

		//then
		assertEquals(State.HIT, board.getField(1, 1).getState());
	}

	@Test
	public void shouldMarkSunk() throws Exception
	{
		//given
		board.addShip(1, 1, new Destroyer(WarShip.Orientation.HORIZONTAL));
		board.shot(1, 1);
		//when
		board.shot(2, 1);

		//then
		assertEquals(State.SUNK, board.getField(1, 1).getState());
		assertEquals(State.SUNK, board.getField(2, 1).getState());
	}

	@Test
	public void shouldDecreaseShipsOnBoard() throws Exception
	{
		//given
		board.addShip(1, 1, new Submarine(WarShip.Orientation.HORIZONTAL));

		//when
		board.shot(1, 1);

		//then
		assertEquals(0, board.getShipsCount());
	}

	@Test
	public void shouldNotBeAbleToShootTwice() throws Exception
	{
		//given
		board.shot(1, 1);
		//when

		final Exception exception = assertThrows(IllegalMoveException.class,
				() -> board.shot(1, 1));

		//then
		final String expectedMessage = "You can't shoot twice in the same field";
		final String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void shouldHaveAllShipsGenerated() throws Exception
	{
		//when
		board.fillBoard();

		//then
		assertEquals(10, board.getShipsCount());
	}


	@Test
	public void shouldFailToAddShipOutsideRangeX() throws Exception
	{

		//when
		board.addShip(-1, 0, new Submarine(WarShip.Orientation.HORIZONTAL));

		//then
		assertThrows(IllegalMoveException.class, () -> board.addShip(-1, 0, new Submarine(WarShip.Orientation.HORIZONTAL)));
	}

	@Test
	public void shouldFailToAddShipOutsideRangeY() throws Exception
	{


		//when
		board.addShip(0, -1, new Submarine(WarShip.Orientation.HORIZONTAL));

		//then
		assertThrows(IllegalMoveException.class, () -> board.addShip(0, -1, new Submarine(WarShip.Orientation.HORIZONTAL)));

	}

}

