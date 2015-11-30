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
	
	/**
	 * Initializing new game object
	 */
	public GameObject() {
		speed = 5;
	}
	
	/**
	 * Get the current position
	 * @return Position: the current position
	 */
	public Point getPosition() {
		return position;
	}
	
	/**
	 * Set the position
	 * @param position: The new position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}
	
	/**
	 * Get the game object size
	 * @return Size: the game object size
	 */
	public Size getSize() {
		return size;
	}
	
	/**
	 * Set the game object size
	 * @param size: the new game object size
	 */
	public void setSize(Size size) {
		this.size = size;
	}
	
	/**
	 * Get the game object direction
	 * @return Direction: the game object direction
	 */
	public StartDirection getStartDirection() {
		return startDirection;
	}
	
	/**
	 * Set the start direction
	 */
	protected void setStartDirection() {
		startDirection = StartDirection.getStartDirection(Helper.generateRandomNumber(1, 4));
		setStartPosition();
	}
	
	/**
	 * Generate a random start position
	 */
	public void setStartPosition() {
		switch (startDirection) {
		case North:
			setPosition(new Point(Helper.generateRandomNumber(40, 430),600));
			break;
		case East:
			setPosition(new Point(-100,Helper.generateRandomNumber(40, 370)));
			break;
		case South:
			setPosition(new Point(Helper.generateRandomNumber(40, 430),-100));
			break;
		case West:
			setPosition(new Point(600,Helper.generateRandomNumber(40, 370)));
			break;
		default:
			break;
		}
	}
	
	/**
	 * Get the game object image
	 * @return BufferedImage: the game object image
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * Set the game object image
	 * @param imagePath: the path for the image file
	 */
	protected void setImage(String imagePath) {
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Get the game object speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Check if the game object collides with the mouse position
	 * @param mousePosition: the current mouse position
	 * @return True: if yes, False: if not
	 */
	public boolean collidesWithMousePosition(Point mousePosition) {
		
		double mouseX = mousePosition.getX();
		double mouseY = mousePosition.getY();
		double gameObjectX = this.getPosition().getX();
		double gameObjectY = this.getPosition().getY();
		
		int gameObjectWidth = this.getSize().getWidth();
		
		return (mouseX > gameObjectX && mouseX < gameObjectX + gameObjectWidth) && 
				(mouseY > gameObjectY && mouseY < gameObjectY + gameObjectWidth);
		
//		if (    (mouseX > gameObjectX && mouseX < gameObjectX + gameObjectWidth) && 
//				(mouseY > gameObjectY && mouseY < gameObjectY + gameObjectWidth)) {
//			return true;
//		}
//		
//		return false;
	}
	
}
