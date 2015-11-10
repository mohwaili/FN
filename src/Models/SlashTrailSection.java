package Models;

import Helpers.Position;

public class SlashTrailSection {
	
	private Position beginPosition;
	private Position endPosition;
	
	public SlashTrailSection() {
		
	}
	
	public SlashTrailSection(Position beginPosition) {
		setBeginPosition(beginPosition);
	}
	
	public Position getBeginPosition() {
		return beginPosition;
	}
	
	public void setBeginPosition(Position beginPosition) {
		this.beginPosition = beginPosition;
	}
	
	public Position getEndPosition() {
		return endPosition;
	}
	
	public void setEndPosition(Position endPosition) {
		this.endPosition = endPosition;
	}
	
}
