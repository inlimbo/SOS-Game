package sos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class LineComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	private JTable gameTable;
	private JFrame frame;
	private Vector<Line> lines = new Vector<Line>();

	public LineComponent(JTable table, JFrame frame) {
		gameTable = table;
		this.frame = frame;
	}
	
	 protected void paintComponent ( Graphics g )
    {
		super.paintComponent(g);
        Graphics2D g2d = ( Graphics2D ) g;

    	g2d.setStroke(new BasicStroke(3));
        for(int i = 0; i < lines.size(); i++) {
        	Line currentLine = lines.get(i);
        	g2d.setColor(currentLine.getTeamColor());
        	Point cell1 = getCellCenter(currentLine.getPointX1(), currentLine.getPointY1());
        	Point cell2 = getCellCenter(currentLine.getPointX2(), currentLine.getPointY2());
        	SwingUtilities.convertPointToScreen(cell1, gameTable);
        	SwingUtilities.convertPointToScreen(cell2, gameTable);
        	g2d.drawLine(cell1.x - 10, cell1.y - 30, cell2.x - 10, cell2.y - 30);
        }
        
        
        
	}
	 
	 public Point getCellCenter(int row1, int column1) {
		 Rectangle r = gameTable.getCellRect(row1, column1, false);
		 return new Point((int)r.getCenterX(),(int)r.getCenterY());
	 }
	 
	 public void addLine(int rowPoint1, int columnPoint1, int rowPoint2, int columnPoint2, Color teamColor) {
		Line myLine = new Line(rowPoint1, columnPoint1, rowPoint2, columnPoint2, teamColor);
		lines.add(myLine);
		repaint();
	 }
	 public void addLine(Line currentLine) {
		 lines.add(currentLine);
		 repaint();
	 }
	 
	 public void addWinningLines(Vector<Line> winningLines) {
		 for(int i = 0; i < lines.size(); i++) {
			 lines.add(winningLines.elementAt(i));
			 repaint();
		 }
	 }
	 public void reset() {
		 lines = new Vector<Line>();
	 }
}

