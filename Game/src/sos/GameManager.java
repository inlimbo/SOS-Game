package sos;

import java.util.function.Consumer;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class GameManager {
	GUI gui;
	PrintWriter recordFile;
	private SimpleGame simpleGame = new SimpleGame();
	private GeneralGame generalGame = new GeneralGame();
	public ComputerPlayer computerPlayer = new ComputerPlayer();
	public BaseGameLogic currentGame = simpleGame;
	private Boolean isBluePlayerComputer = false;
	private Boolean isRedPlayerComputer = false;
	private Boolean recordGame = false;
	private GameBoard gameBoard;
	
	public GameManager(GUI gui, GameBoard gameBoard) {
		this.gui = gui;
		this.gameBoard = gameBoard;
		
		setupGUIListeners();
		simpleGame.setGameBoard(gameBoard);
		generalGame.setGameBoard(gameBoard);
		simpleGame.setGameGUI(gui);
		generalGame.setGameGUI(gui);
		computerPlayer.setGameBoard(gameBoard);
		computerPlayer.setGameGUI(gui);
		
	}
	public void setupGUIListeners() {
		gui.setupButtonListeners(new Consumer<String>() {
			@Override
			public void accept(String event) {
				if(event.equals("SIMPLE_GAME")) {
					newGame();
					currentGame = simpleGame;
				}
				
				if(event.equals("GENERAL_GAME")) {
					newGame();
					
					currentGame = generalGame;
				}
				
				if(event.equals("NEW_GAME")) {
					newGame();
				}
				
				if(event.equals("COMPUTER_BLUE_PLAYER")) {
					isBluePlayerComputer = true;
					if(isComputersTurn()) {
						handleComputerMove();
					}
				}
				
				if(event.equals("COMPUTER_RED_PLAYER")) {
					isRedPlayerComputer = true;
					if(isComputersTurn()) {
						handleComputerMove();
					}
					
				}
				
				if(event.equals("HUMAN_BLUE_PLAYER")) {
					isBluePlayerComputer = false;
				}
				
				if(event.equals("HUMAN_RED_PLAYER")) {
					isRedPlayerComputer = false;
				}
				
				if(event.equals("BOARD_SIZE")) {
					int size = gui.getSelectedBoardSize();
					gameBoard.resizeBoard(size);
					newGame();
				}
				
				if(event.equals("RECORD_GAME")) {
					if(gui.isRecording()) {
						setUpRecording();
						recordGame = true;
					}
					else {
						if(recordGame) {
							closeRecording();
						}
						recordGame = false;
					}
				}
				
			}			
		});
	}
	
	public void handleMove(int x, int y) {
		if(recordGame) {
			recordGameMove(x, y);
		}
		currentGame.makeMove(x, y);
		
		if(currentGame.isGameOver() && recordGame) {
			closeRecording();
		}
		
		 if(isComputersTurn()) {
			 handleComputerMove();
		 }
	}
	
	public void handleComputerMove() {
		int[] move;
		String symbol;
		if(isBluePlayerComputer ^ isRedPlayerComputer) {
			move = computerPlayer.getComputerMove();
			symbol = computerPlayer.getSymbolForMove();
			if(recordGame) {
				recordGameMove(move[0], move[1]);
			}
			currentGame.makeMove(move[0], move[1], symbol);
			if(currentGame.isGameOver() && recordGame) {
				closeRecording();
			}
		}
		else if(isBluePlayerComputer && isRedPlayerComputer) {
			while(isBluePlayerComputer && isRedPlayerComputer) {
				move = computerPlayer.getComputerMove();
				symbol = computerPlayer.getSymbolForMove();
				if(recordGame) {
					recordGameMove(move[0], move[1]);
				}
				currentGame.makeMove(move[0], move[1], symbol);
				if(currentGame.isGameOver()) {
					if(recordGame) {
						closeRecording();
					}
					gui.setPlayersHuman();
					isBluePlayerComputer = false;
					isRedPlayerComputer = false;
					break;
				}
				
				
			}
		}
	}
	
	public Boolean isComputersTurn() {
			if(isBluePlayerComputer) {
				if(currentGame.getPlayerTurn() == "Blue") {
					return true;
				}
			}
			if(isRedPlayerComputer) {
				if(currentGame.getPlayerTurn() == "Red") {
					return true;
				}
			}
		return false;
	}
	
	public void setUpRecording() {
		try {
			recordFile = new PrintWriter("gameRecord.txt");
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	public void recordGameMove(int x, int y) {
		String player = currentGame.getPlayerTurn();
		String symbol = currentGame.getCurrentPlayerSymbol();
		recordFile.println(player + " " + symbol + " " + x + " " + y);
	}
	
	public void closeRecording() {
		recordFile.close();
	}
	
	public void newGame() {
		gameBoard.reset();
		gui.resetPlayerTurn();
		currentGame.resetGame();
		simpleGame.setIsWinner(false);
		if(recordGame) {
			closeRecording();
			setUpRecording();
		}
	}
}
