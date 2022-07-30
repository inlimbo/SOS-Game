package sos.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sos.GUI;
import sos.GameBoard;
import sos.GameManager;

class TestPlayerMoves {
	private GUI gameGUI;
	private GameBoard gameBoard;
	private GameManager gameManager;
	
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
	void testMakeMove() {
		gameManager.handleMove(0, 0);
		assertEquals("S", gameBoard.getSymbolAtPos(0, 0));
	}

}
