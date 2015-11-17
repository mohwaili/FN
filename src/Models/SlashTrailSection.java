package Models;

import java.awt.Point;

public class SlashTrailSection {
	
	private Point beginPosition;
	private Point endPosition;
	private GameMusic slashEffect;
	
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
	
	/**
	 * Check if the slash is valid
	 * @return True: the slash is valid, False: the slash is not valid
	 */
	public boolean slashIsValid() {
		return (this.getBeginPosition().getX() != this.getEndPosition().getX()) ||
				(this.getBeginPosition().getY() != this.getEndPosition().getY());
	}
	
	/**
	 * Play the slash sound effect
	 */
	public void playSlashEffect() {
		slashEffect = new GameMusic("assets/slash.wav");
		slashEffect.play();
	}
	
}
