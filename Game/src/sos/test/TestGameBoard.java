package sos.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sos.GUI;
import sos.GameBoard;

class TestGameBoard {
	private GameBoard gameBoard;
	private GUI gameGUI;
	@BeforeEach
	void setUp() throws Exception {
		gameGUI = new GUI();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEmptyBoard() {
		gameBoard = new GameBoard(gameGUI);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testNonEmptyBoard() {
		gameBoard = new GameBoard(gameGUI);
		gameBoard.setMoveAt("S", 0, 0);
		gameBoard.setMoveAt("O", 3, 4);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
