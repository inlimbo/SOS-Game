package sos;

public class SOSGame {

	public static void main(String[] args) {
		GUI myGUI = new GUI();
		GameBoard myGameBoard = new GameBoard(myGUI);
		GameManager gameManager = new GameManager(myGUI, myGameBoard);
		myGameBoard.setGameManager(gameManager);
	}

}