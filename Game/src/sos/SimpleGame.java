package sos;

import java.util.Vector;

import javax.swing.JTable;

public class SimpleGame extends BaseGameLogic {
	
	private Boolean isWinner = false;
	
	public SimpleGame() {
		super();
	}
	
	public void makeMove(int x, int y) {

		 String symbol = getCurrentPlayerSymbol();
		 makeMove(x, y, symbol);
		 
	}
	
	public void makeMove(int x, int y, String symbol) {
		 if(isWinner()) {
			 return;
		 }
		 gameBoard.setMoveAt(symbol, x, y);
		 Vector<Line> winningLines = checkForWinner(x, y);

		 if(winningLines.size() > 0) {
			 gameBoard.drawLines(winningLines);
			 isWinner = true;
			 gameOver = true;
			 gameGUI.setPlayerWinMessage(getPlayerTurn() + " player wins!");
			 setDefaultPlayerTurn();
			 setGameOver(true);
			 
			 gameBoard.repaintGameTable();
			 
			 return;
		 }
		 
		 makePlayerTurn();
		 
		 if(gameBoard.isGameBoardFull() && !isWinner()) {
			 gameGUI.setPlayerWinMessage("Draw!");
			 setDefaultPlayerTurn();
			 setGameOver(true);
			 return;
		 }
		 
	}
	
	public Boolean isWinner() {
		return isWinner;
	}
	public void setIsWinner(Boolean winner) {
		if(winner) {
			isWinner = true;
		}
		else {
			isWinner = false;
		}
	}

	
}