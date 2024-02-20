package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.game.Board;

public class BoardTest
{
	@Test
	public void testFillBoard()throws Exception
	{
		//given
		Board board = new Board();
		//when
		board.fillBoard();
		//then
		assertNotNull(board);
	}
}