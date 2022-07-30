package sos;

import java.util.Vector;
import java.awt.Color;

import javax.swing.JTable;

public class BaseGameLogic {
	
	protected GUI gameGUI;
	protected GameBoard gameBoard;
	protected boolean bluePlayerTurn = true;
	protected boolean redPlayerTurn = false;
	protected boolean gameOver = false;
	
	public BaseGameLogic() {
		
	}
	
	public String getCurrentPlayerSymbol() {
		if(bluePlayerTurn) {
			return gameGUI.getBluePlayerSymbol();
		}
		else {
			return gameGUI.getRedPlayerSymbol();
		}
	}
	
	public Color getPlayerTurnColor() {
		if(bluePlayerTurn) {
			return Color.BLUE;
		}
		else {
			return Color.RED;
		}
	}
	
	public String getPlayerTurn() {
		if(bluePlayerTurn) {
			return "Blue";
		}
		else {
			return "Red";
		}
	}
	
	public void setGameBoard(GameBoard myGameBoard) {
		gameBoard = myGameBoard;
	}
	
	public void setGameGUI(GUI myGameGUI) {
		gameGUI = myGameGUI;
	}
	
	public void setGameOver(Boolean game) {
		gameOver = game;
	}
	
	public void setDefaultPlayerTurn() {
		bluePlayerTurn = true;
		redPlayerTurn = false;
	}
	
	public Vector<Line> checkForWinner(int xPos, int yPos) {
		String currentSymbol = gameBoard.getSymbolAtPos(xPos, yPos);
		return checkForWinnerUsingSymbol(xPos, yPos, currentSymbol);
	}
	
	public Vector<Line> checkForWinnerUsingSymbol(int xPos, int yPos, String symbol) {
		String currentSymbol = symbol;
		String altSymbol = currentSymbol == "S" ? "O" : "S";
		Vector<Line> winningLines = new Vector<Line>();
		
		//Checks at most 2 positions over from your symbol, in all directions, if you have made a winning move
		if(currentSymbol == "S") {
			for(int x = -1; x <= 1; x++) {
				for(int y = -1; y <= 1; y++) {
					if(x == 0 && y == 0) {
						continue;
					}
					if(gameBoard.getSymbolAtPos(xPos + x, yPos + y) == altSymbol) {
						if(gameBoard.getSymbolAtPos(xPos + x*2, yPos + y*2) == currentSymbol) {
							winningLines.add(new Line(yPos, xPos, yPos + y*2, xPos + x*2, getPlayerTurnColor()));
						}
					}
				}
			}
		}
		//Checks on either sides of your symbol, in all directions, if you have made a winning move
		else {
			for(int x = -1; x <= 0; x++) {
				for(int y = -1; y <= 1; y++) {
					if(x == 0 && y == 0) {
						continue;
					}
					if(x == 0 && y == 1) {
						continue;
					}
					if(gameBoard.getSymbolAtPos(xPos + x, yPos + y) == altSymbol) {
						if(gameBoard.getSymbolAtPos(xPos - x, yPos - y) == altSymbol) {
							winningLines.add(new Line(yPos - y, xPos - x, yPos + y, xPos + x, getPlayerTurnColor()));
						}
					}
				}
			}
		}
		return winningLines;
	}
	
	public Boolean isGameOver() {
		return gameOver;
	}
	
	public void makeMove(int x, int y) {
		throw new UnsupportedOperationException();
	}
	
	public void makeMove(int x, int y, String symbol) {
		throw new UnsupportedOperationException();
	}
	
	public void makePlayerTurn() {
		if(bluePlayerTurn) {
			bluePlayerTurn = false;
			redPlayerTurn = true;
			gameGUI.setPlayerTurn("Red");
		}
		else {
			redPlayerTurn = false;
			bluePlayerTurn = true;
			gameGUI.setPlayerTurn("Blue");
		}
	}
	
	public void resetGame() {
		bluePlayerTurn = true;
		redPlayerTurn = false;
		gameOver = false;
	}

}