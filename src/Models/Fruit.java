package Models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Helpers.*;

public class Fruit extends GameObject {
	
	enum FruitType {
		Apple, Orange, Stawberry
	}
	
	enum StartDirection {
		North, South, West, East
	}
	
	private FruitType fruitType;
	private StartDirection startDirection;
	private BufferedImage image;
	private int speed;
	
	public Fruit(Position position, Size size) {
		super(position, size);
		setPosition(position);
		setSize(size);
		setFruitType();
		setFruitImage();
		speed = 5;
	}
	
	private void setFruitType() {
		//TODO: generate a random fruit type
	}
	
	public FruitType getFruitType() {
		return fruitType;
	}
	
	private void setFruitImage() {
		try {
			image = ImageIO.read(new File("assets/apple.png"));
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
