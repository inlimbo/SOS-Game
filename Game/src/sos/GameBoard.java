package sos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class GameBoard {
	private JTable gameTable;
	private GUI gameGUI;
	private LineComponent lineManager;
	private GameManager gameManager;
	public GameBoard(GUI gui) {
		
		gameGUI = gui;
		
		setUpBoard();
		
		lineManager = new LineComponent(getGameTable(), gameGUI.getFrame());
		gameGUI.getFrame().setGlassPane(lineManager);
		gameGUI.getFrame().getGlassPane().setVisible(true);
		
	
	}
	
	public void setUpBoard() {
		gameTable = new JTable(8,8);
		for(int x = 0; x < gameTable.getColumnCount(); x++) {
			for(int y = 0; y < gameTable.getRowCount(); y++)
				gameTable.setValueAt("", y, x);
		}
		gameTable.setPreferredSize(new Dimension(250,250));
		gameTable.setRowHeight(30);
		gameTable.setEnabled(false);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );

		for(int i = 0; i < 8; i++) {
		gameTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);			
		}
		
		gameGUI.setGameBoard(gameTable);
		setMouseListener();
	}
	public void setupBoard(int newSize) {
		gameTable = new JTable(newSize, newSize);
		for(int x = 0; x < gameTable.getColumnCount(); x++) {
			for(int y = 0; y < gameTable.getRowCount(); y++)
				gameTable.setValueAt("", y, x);
		}
		gameTable.setPreferredSize(new Dimension(300,300));
		gameTable.setRowHeight(30);
		gameTable.setEnabled(false);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );

		for(int i = 0; i < newSize; i++) {
		gameTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);			
		}
		
		gameGUI.setNewGameBoard(gameTable);
		setMouseListener();
	}
	
	public void setMouseListener() {
		gameTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					int row, column, x, y;
					 row = gameTable.rowAtPoint(e.getPoint());
					 column = gameTable.columnAtPoint(e.getPoint());
					 x = column;
					 y = row;
					 if(x < 0 || y < 0) {
						 return;
					 }
					 if(gameTable.getValueAt(row, column) == "S" ||
							 gameTable.getValueAt(row, column) == "O") {
						//return message to pick an empty square
					 }
					 else { 
						 gameManager.handleMove(x, y);

						gameTable.repaint();
					 }
					gameTable.revalidate();
				}
			}
		});	
	}
	
	public JTable getGameTable() {
		return gameTable;
	}
	public void setMoveAt(String symbol, int x, int y) {
		if(gameTable.getValueAt(y, x) == "S" || gameTable.getValueAt(y, x) == "O") {
			//return message to pick an empty square
		}
		else {
			if(y >= gameTable.getRowCount() || x >= gameTable.getColumnCount()) {
				throw new IndexOutOfBoundsException("Index " + x + ", " + y + " out of table bounds");
			}
			gameTable.setValueAt(symbol, y, x);
		}
	}

	public void drawLines(Vector<Line> winningLines) {
		for(int i = 0; i < winningLines.size(); i++) {
			lineManager.addLine(winningLines.elementAt(i));
		}
	}

	public Boolean isGameBoardFull() {
		
		for(int x = 0; x < gameTable.getColumnCount(); x++) {
			for(int y = 0; y < gameTable.getRowCount(); y++)
				if(gameTable.getValueAt(y, x).toString() == "") {
					return false;
				}
		}
		return true;
	}
	
	public String getSymbolAtPos(int x, int y) {

		if (x < 0 || x >= gameTable.getColumnCount() || y < 0 || y >= gameTable.getRowCount())
		{
			return "";
		}
		if(gameTable.getValueAt(y, x) == null) {
			return "";
		}
		else
		{
			return gameTable.getValueAt(y, x).toString();
		}
	}
	
	public void reset() {
		for(int x = 0; x < gameTable.getColumnCount(); x++) {
			for(int y = 0; y < gameTable.getRowCount(); y++)
				gameTable.setValueAt("", y, x);
		}
		lineManager.reset();
	}

	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	
	public int getGameBoardSize() {
		return gameTable.getRowCount();
	}
	
	public void resizeBoard(int size) {
		setupBoard(size);
	}
	
	public void repaintGameTable() {
		gameTable.repaint();
		gameTable.revalidate();
	}
}
