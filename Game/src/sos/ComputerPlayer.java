package sos;

import java.util.Random;
import java.util.Vector;

public class ComputerPlayer extends BaseGameLogic{
	
	private int[] move = new int[2];
	private String symbol = "S";
	Random rand = new Random(System.currentTimeMillis());
	
	public ComputerPlayer() {
	}
	public int[] getComputerMove() {
		
		if(isWinningMove()) {
			return move;
		}
		else {
			makeRandomMove();
			return move;
		}
	}
	public void makeRandomMove() {
		int randX = rand.nextInt(gameBoard.getGameBoardSize());
		int randY = rand.nextInt(gameBoard.getGameBoardSize());
		int randSymbol = rand.nextInt(2) + 1;
		if(randSymbol == 1) {
			symbol = "S";
		}
		else {
			symbol = "O";
		}
		int count = 0;
		outerLoop:
		while(gameBoard.getSymbolAtPos(randX, randY) == "S" || gameBoard.getSymbolAtPos(randX, randY) == "O") {
			randX = rand.nextInt(gameBoard.getGameBoardSize());
			randY = rand.nextInt(gameBoard.getGameBoardSize());
			count ++;
			if(count > 20) {
				for(int x = 0; x < gameBoard.getGameBoardSize(); x++) {
					for(int y = 0; y < gameBoard.getGameBoardSize(); y++) {
						if(gameBoard.getSymbolAtPos(x, y) == "") {
							move[0] = x;
							move[1] = y;
							break outerLoop;
						}
					}
				}
			}
		}
		move[0] = randX;
		move[1] = randY;
		
	}
	public Boolean isWinningMove() {
		Vector<Line> winningLines;
		for(int x = 0; x < gameBoard.getGameBoardSize(); x++) {
			for(int y = 0; y < gameBoard.getGameBoardSize(); y++) {
				if(gameBoard.getSymbolAtPos(x, y) == "") {
					winningLines = checkForWinnerUsingSymbol(x, y, "S");
					if(winningLines.size() > 0) {
						move[0] = x;
						move[1] = y;
						symbol = "S";
						return true;
					}
					winningLines = checkForWinnerUsingSymbol(x, y, "O");
					if(winningLines.size() > 0) {
						move[0] = x;
						move[1] = y;
						symbol = "O";
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public String getSymbolForMove() {
		return symbol;
	}
}
