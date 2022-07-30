package sos;

import java.awt.Color;

public class Line {
	private int row1, column1, row2, column2;
	private Color teamColor;
	public Line(int row1, int column1, int row2, int column2, Color teamColor) {
		this.row1 = row1;
		this.column1 = column1;
		this.row2 = row2;
		this.column2 = column2;
		this.teamColor = teamColor;
	}

	public int getPointX1() {
		return row1;
	}
	public int getPointY1() {
		return column1;
	}
	public int getPointX2() {
		return row2;
	}
	public int getPointY2() {
		return column2;
	}
	public Color getTeamColor() {
		return teamColor;
	}
	public void setColor(Color color) {
		teamColor = color;
	}
}
