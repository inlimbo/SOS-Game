package sos.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sos.GUI;
import sos.GameBoard;
import sos.GameManager;

class TestComputerPlayer {
	GUI gameGUI;
	GameBoard gameBoard;
	GameManager gameManager;
	@BeforeEach
	void setUp() throws Exception {
		gameGUI = new GUI();
		gameBoard = new GameBoard(gameGUI);
		gameManager = new GameManager(gameGUI, gameBoard);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testComputerWinningMove() {
		gameBoard.setMoveAt("S", 0, 0);
		gameBoard.setMoveAt("S", 0, 2);
		int[] move = gameManager.computerPlayer.getComputerMove();
		String symbol = gameManager.computerPlayer.getSymbolForMove();
		gameManager.currentGame.makeMove(move[0], move[1], symbol);
		assertEquals("O", gameBoard.getSymbolAtPos(0, 1));
	}

}
