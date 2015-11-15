package Models;

import java.awt.Point;

import Helpers.Helper;
import Helpers.Size;


public abstract class GameObject {
	
	public enum StartDirection {
		North, South, West, East;
		
		public static StartDirection getStartDirection(int index) {
			switch (index) {
			case 1:
				return North;
			case 2:
				return East;
			case 3:
				return South;
			case 4:
				return West;
			default:
				return North;
			}
		}
	}

	private Point position;
	private Size size;
	private String background;
	private StartDirection startDirection;
	
	public GameObject() {
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public Size getSize() {
		return size;
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
	
	public String getBackground() {
		return background;
	}
	
	public void setBackgroud(String background) {
		this.background = background;
	}
	
	public StartDirection getStartDirection() {
		return startDirection;
	}
	
	protected void setStartDirection() {
		startDirection = StartDirection.getStartDirection(Helper.generateRandomNumber(1, 4));
		setStartPosition();
	}
	
	public void setStartPosition() {
		setPosition(new Point(200, 200)); //TODO: uncomment code below
//		switch (startDirection) {
//		case North:
//			setPosition(new Position(Helper.generateRandomNumber(40, 470),600));
//			break;
//		case East:
//			setPosition(new Position(-100,Helper.generateRandomNumber(40, 370)));
//			break;
//		case South:
//			setPosition(new Position(Helper.generateRandomNumber(40, 470),-100));
//			break;
//		case West:
//			setPosition(new Position(600,Helper.generateRandomNumber(40, 370)));
//			break;
//		default:
//			break;
//		}
	}
	
}
