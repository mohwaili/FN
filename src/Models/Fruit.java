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
	
	public Fruit(Size size) {
		//TODO: remove position from constructor
		//because we'll generate the position based on the start direction
		super(size);
		
//		setPosition(position);
		setSize(size);
		
		setFruitType();		
		setStartDirection();
		
		speed = 5;
	}
	
	private void setFruitType() {
		fruitType = FruitType.getFruitType(Helper.generateRandomNumber(1, 3));
		setFruitImage();
		setPoints();
	}
	
	public FruitType getFruitType() {
		return fruitType;
	}
	
	private void setPoints() {
		switch (fruitType) {
		case Stawberry:
			points = 100;
			break;
		default:
			points = 50;
			break;
		}
	}
	
	public int getPoints() {
		return points;
	}
	
	private void setFruitImage() {
		try {
			switch (fruitType) {
			case Apple:
				image = ImageIO.read(new File("assets/apple.png"));
				break;
			case Orange:
				image = ImageIO.read(new File("assets/orange.png"));
				break;
			case Stawberry:
				image = ImageIO.read(new File("assets/strawberry.png"));
				break;
			default:
				image = ImageIO.read(new File("assets/apple.png"));
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
