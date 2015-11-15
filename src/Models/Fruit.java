package Models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Helpers.*;

public class Fruit extends GameObject {
	
	public enum FruitType {
		Apple, Orange, Stawberry;
		
		public static FruitType getFruitType(int index) {
			switch (index) {
			case 1:
				return Apple;
			case 2:
				return Orange;
			case 3:
				return Stawberry;
			default:
				return Apple;
			}
		}
		
	}
	
	private FruitType fruitType;
	private BufferedImage image;
	private int speed;
	private int points;
	
	public Fruit() {
		super();
		setFruitType();	
		setFruitProperties();
		setStartDirection();
		speed = 5;
	}
	
	private void setFruitType() {
		fruitType = FruitType.getFruitType(Helper.generateRandomNumber(1, 3));	
	}
	
	public FruitType getFruitType() {
		return fruitType;
	}
	
	public int getPoints() {
		return points;
	}
	
	private void setFruitProperties() {
		try {
			switch (fruitType) {
			case Apple:
				image = ImageIO.read(new File("assets/apple.png"));
				setSize(new Size(45, 45));
				points = 50;
				break;
			case Orange:
				image = ImageIO.read(new File("assets/orange.png"));
				setSize(new Size(50, 50));
				points = 50;
				break;
			case Stawberry:
				image = ImageIO.read(new File("assets/strawberry.png"));
				setSize(new Size(25, 25));
				points = 100;
				break;
			default:
				image = ImageIO.read(new File("assets/apple.png"));
				points = 50;
				break;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getSpeed() {
		return speed;
	}
}
