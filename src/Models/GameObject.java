package Models;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	private BufferedImage image;
	private int speed;
	private StartDirection startDirection;
	
	public GameObject() {
		speed = 5;
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
	
	public StartDirection getStartDirection() {
		return startDirection;
	}
	
	protected void setStartDirection() {
		startDirection = StartDirection.getStartDirection(Helper.generateRandomNumber(1, 4));
		setStartPosition();
	}
	
	public void setStartPosition() {
		switch (startDirection) {
		case North:
			setPosition(new Point(Helper.generateRandomNumber(40, 470),600));
			break;
		case East:
			setPosition(new Point(-100,Helper.generateRandomNumber(40, 370)));
			break;
		case South:
			setPosition(new Point(Helper.generateRandomNumber(40, 470),-100));
			break;
		case West:
			setPosition(new Point(600,Helper.generateRandomNumber(40, 370)));
			break;
		default:
			break;
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	protected void setImage(String imagePath) {
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getSpeed() {
		return speed;
	}
	
}
