package sos;

import java.util.Vector;

import javax.swing.JTable;

public class GeneralGame extends BaseGameLogic{
	
	private int bluePlayerScore;
	private int redPlayerScore;
	
	public GeneralGame() {
		super();
	}
	public void makeMove(int x, int y) {
		 
		 String symbol = getCurrentPlayerSymbol();
		 makeMove(x, y, symbol);
	}
	
	public void makeMove(int x, int y, String symbol) {
		 
		 gameBoard.setMoveAt(symbol, x, y);
		 Vector<Line> winningLines = checkForWinner(x, y);
		 
		 if(winningLines.size() > 0) {
			 gameBoard.drawLines(winningLines);
			 if(getPlayerTurn() == "Blue") {
				 bluePlayerScore += winningLines.size();
			 }
			 else {
				 redPlayerScore += winningLines.size();
			 }
		 }
		 
		 makePlayerTurn();
		 
		 if(gameBoard.isGameBoardFull()) {
			 if(bluePlayerScore > redPlayerScore)
			 {
				 gameGUI.setPlayerWinMessage("Blue player wins!");
			 }
			 else if(redPlayerScore > bluePlayerScore) {
				 gameGUI.setPlayerWinMessage("Red player wins!");
			 }
			 else {
				 gameGUI.setPlayerWinMessage("Draw");
			 }
			 gameOver = true;
			 setDefaultPlayerTurn();
			 return;
		 }
	}


}