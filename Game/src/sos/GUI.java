package sos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;

public class GUI {
	private JLabel playerTurn;
	private JLabel currentTurn;
	private JRadioButton bluePlayerS;
	private JRadioButton bluePlayerO;
	private JRadioButton redPlayerS;
	private JRadioButton redPlayerO;
	private JPanel panel5;
	private JFrame frame;
	private JRadioButton simpleGame;
	private JRadioButton generalGame;
	private JLabel boardSizeLabel;
	private Integer[] sizes = {2,3,4,5,6,7,8,9,10,11,12,13,14};
	private JComboBox boardSize = new JComboBox(sizes);
	private JButton newGame;
	private JRadioButton humanRedPlayer;
	private JRadioButton computerRedPlayer;
	private JRadioButton humanBluePlayer;
	private JRadioButton computerBluePlayer;
	private JLabel winMessage = new JLabel();
	private JTable gameTable;
	private JCheckBox recordGame;
	

	public GUI() {
		
		frame = new JFrame();
		frame.setSize(700,500);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		panel5 = new JPanel();
		
		JLabel title = new JLabel();
		simpleGame = new JRadioButton();
		
		generalGame = new JRadioButton();
		ButtonGroup gameType = new ButtonGroup();
		simpleGame.setSelected(true);
		
		JLabel bluePlayer = new JLabel();
		bluePlayerS = new JRadioButton();
		bluePlayerO = new JRadioButton();
		ButtonGroup bluePlayerSymbolChoice = new ButtonGroup();
		bluePlayerS.setSelected(true);
		
		
		JLabel redPlayer = new JLabel();
		redPlayerS = new JRadioButton();
		redPlayerO = new JRadioButton();
		ButtonGroup redPlayerSymbolChoice = new ButtonGroup();
		redPlayerS.setSelected(true);
		
		currentTurn = new JLabel("Current Turn: ");
		currentTurn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		JLabel playerTurn = new JLabel("Blue");
		this.playerTurn = playerTurn;
		playerTurn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		recordGame = new JCheckBox("Record Game");
		newGame= new JButton("New Game");
		
		humanBluePlayer = new JRadioButton("Human");
		computerBluePlayer = new JRadioButton("Computer");
		ButtonGroup bluePlayerChoice = new ButtonGroup();
		bluePlayerChoice.add(humanBluePlayer);
		bluePlayerChoice.add(computerBluePlayer);
		humanBluePlayer.setSelected(true);
		
		humanRedPlayer = new JRadioButton("Human");
		computerRedPlayer = new JRadioButton("Computer");
		ButtonGroup redPlayerChoice = new ButtonGroup();
		redPlayerChoice.add(humanRedPlayer);
		redPlayerChoice.add(computerRedPlayer);
		humanRedPlayer.setSelected(true);

		title.setText("SOS Game");
		title.setPreferredSize(new Dimension(80,50));
		panel1.add(title);
		
		simpleGame.setText("Simple Game");
		panel1.add(simpleGame);
		
		generalGame.setText("General Game");
		panel1.add(generalGame);
		
		boardSizeLabel = new JLabel("Board size: ");
		boardSizeLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
		boardSize.setSelectedIndex(6);
		
		panel1.add(boardSizeLabel);
		panel1.add(boardSize);
		
		gameType.add(simpleGame);
		gameType.add(generalGame);
		
		bluePlayer.setText("Blue Player");
		panel3.add(bluePlayer);
		
		panel3.add(humanBluePlayer);
		
		bluePlayerS.setText("S");
		panel3.add(bluePlayerS);
		
		bluePlayerO.setText("O");
		panel3.add(bluePlayerO);
		
		panel3.add(computerBluePlayer);
		
		bluePlayerSymbolChoice.add(bluePlayerS);
		bluePlayerSymbolChoice.add(bluePlayerO);
		
		redPlayer.setText("Red Player");
		panel4.add(redPlayer);
		
		panel4.add(humanRedPlayer);
		
		redPlayerS.setText("S");
		panel4.add(redPlayerS);
		
		redPlayerO.setText("O");
		panel4.add(redPlayerO);
		
		panel4.add(computerRedPlayer);
		
		redPlayerSymbolChoice.add(redPlayerS);
		redPlayerSymbolChoice.add(redPlayerO);
		
		panel2.add(recordGame);
		panel2.add(winMessage);
		panel2.add(currentTurn);
		panel2.add(playerTurn);
		panel2.add(newGame);
		
		panel1.setPreferredSize(new Dimension(100,100));
		panel2.setPreferredSize(new Dimension(100,70));
		panel3.setPreferredSize(new Dimension(90,100));
		panel4.setPreferredSize(new Dimension(90,100));
		panel5.setPreferredSize(new Dimension(100,100));
		
		frame.add(panel1, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.SOUTH);
		frame.add(panel3, BorderLayout.WEST);
		frame.add(panel4, BorderLayout.EAST);
		frame.add(panel5, BorderLayout.CENTER);
		
		frame.setVisible(true);
		
	}
	
	void setGameBoard(JTable gameTable) {
		this.gameTable = gameTable;
		panel5.add(gameTable);
		panel5.revalidate();
	}
	
	void setPlayersHuman() {
		humanRedPlayer.setSelected(true);
		humanBluePlayer.setSelected(true);
	}
	
	void setNewGameBoard(JTable newGameTable) {
		panel5.remove(gameTable);
		gameTable = newGameTable;
		panel5.add(newGameTable);
		panel5.revalidate();
	}
	
	public void setPlayerTurn(String player) {
		playerTurn.setText(player);
	}
	
	public String getBluePlayerSymbol() {
		if(bluePlayerS.isSelected()) {
			return "S";
		}
		else {
			return "O";
		}
		
	}
	
	public String getRedPlayerSymbol() {
		if(redPlayerS.isSelected()) {
			return "S";
		}
		else {
			return "O";
		}
	}
	
	public String getGameType() {
		if(simpleGame.isSelected()) {
			return "SimpleGame";
		}
		else {
			return "GeneralGame";
		}
	}
	
	public void setupButtonListeners(Consumer<String> listener) {
		simpleGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.accept("SIMPLE_GAME");
			}
		});
		generalGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.accept("GENERAL_GAME");		
			}			
		});
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.accept("NEW_GAME");
			}			
		});		
		computerBluePlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.accept("COMPUTER_BLUE_PLAYER");
			}			
		});	
		computerRedPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.accept("COMPUTER_RED_PLAYER");
			}			
		});	
		humanBluePlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.accept("HUMAN_BLUE_PLAYER");
			}			
		});	
		humanRedPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.accept("HUMAN_RED_PLAYER");
			}			
		});	
		boardSize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.accept("BOARD_SIZE");
			}			
		});	
		recordGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.accept("RECORD_GAME");
			}
		});
	}
	
	public void setPlayerWinMessage(String message) {
		winMessage.setText(message);
		winMessage.setFont(new Font("SansSerif", Font.PLAIN, 20));
		playerTurn.setText("");
		currentTurn.setText("");
	}
	
	public void resetPlayerTurn() {
		winMessage.setText("");
		playerTurn.setText("Blue");
		currentTurn.setText("Current Turn: ");
		currentTurn.setFont(new Font("SansSerif", Font.PLAIN, 16));
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public int getSelectedBoardSize() {
		return (Integer)boardSize.getSelectedItem();
	}
	
	public void refreshGUI() {
		gameTable.repaint();
		gameTable.validate();
		panel5.repaint();
		panel5.validate();
		frame.repaint();
		frame.validate();
	}
	
	public Boolean isRecording() {
		return recordGame.isSelected();
	}
}
