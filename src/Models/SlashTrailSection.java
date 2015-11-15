package Models;


import java.awt.Point;

//import Helpers.Position;

public class SlashTrailSection {
	
	private Point beginPosition;
	private Point endPosition;
	
	public boolean beginPositionIsSet;
	
	public SlashTrailSection() {
		beginPositionIsSet = false;
	}
	
	public SlashTrailSection(Point beginPosition) {
		setBeginPosition(beginPosition);
	}
	
	public Point getBeginPosition() {
		return beginPosition;
	}
	
	public void setBeginPosition(Point beginPosition) {
		this.beginPosition = beginPosition;
	}
	
	public Point getEndPosition() {
		return endPosition;
	}
	
	public void setEndPosition(Point endPosition) {
		this.endPosition = endPosition;
	}
	
}
