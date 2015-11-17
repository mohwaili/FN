package Models;

import java.awt.Point;

public class SlashTrailSection {
	
	private Point beginPosition;
	private Point endPosition;
	
	public SlashTrailSection(Point beginPosition) {
		this.beginPosition = beginPosition;
	}
	
	/**
	 * Get the begin position
	 * @return Point: The begin position
	 */
	public Point getBeginPosition() {
		return beginPosition;
	}
	
	/**
	 * Get the end position
	 * @return Point: The end position 
	 */
	public Point getEndPosition() {
		return endPosition;
	}
	
	/**
	 * Set the new end position
	 * @param endPosition: the new end position
	 */
	public void setEndPosition(Point endPosition) {
		this.endPosition = endPosition;
	}
	
}
