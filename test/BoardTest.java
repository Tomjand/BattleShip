package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import src.game.Board;
import src.game.Field;
import src.game.IllegalMoveException;
import src.game.State;
import src.game.Submarine;



public class BoardTest
{
	private  Board board;
	@BeforeAll
	public void setUp() throws Exception
	{
		board = new Board();
	}
	@Test
	public void shouldAddSubmarineOnField() throws Exception
	{
		//given
		board.addShip(0 , 0, new Submarine());
		board.addShip(3 , 1, new Submarine());
		board.addShip(0 , 5, new Submarine());
		board.addShip(1 , 4, new Submarine());
		//when

		board.addShip(10 , 10, new Submarine());
		//then
		final Field field = board.getField(0, 0);
		assertEquals(State.OCCUPIED,field.getState());
	}

	@Test
	public void shouldNotToBeAbleAddFiveSubmarine() throws Exception
	{
		//given
		final Board board = new Board();
		//when

		board.addShip(0 , 0, new Submarine());
		//then

	}



	@Test
	public void shouldFailToAddShipOutsideRangeX() throws Exception
	{
		//given
		final Board board = new Board();
		//when

		board.addShip(-1 , 0, new Submarine());
		//then
		assertThrows(IllegalMoveException.class, () -> board.addShip(-1 , 0, new Submarine()));
	}

	@Test
	public void shouldFailToAddShipOutsideRangeY() throws Exception
	{
		//given
		final Board board = new Board();
		//when

		board.addShip(0 , -1, new Submarine());
		//then
		assertThrows(IllegalMoveException.class, () -> board.addShip(0 , -1, new Submarine()));

	}


}

