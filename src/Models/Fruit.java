package Models;

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
	private int points;
	
	/**
	 * Initializing new Fruit
	 */
	public Fruit() {
		super();
		setFruitType();	
		setFruitProperties();
		setStartDirection();
	}
	
	/**
	 * Set the fruit type
	 */
	private void setFruitType() {
		fruitType = FruitType.getFruitType(Helper.generateRandomNumber(1, 3));	
	}
	
	/**
	 * Get the fruit type
	 * @return FruitType: the fruit type
	 */
	public FruitType getFruitType() {
		return fruitType;
	}
	
	/**
	 * Get the points for this fruit
	 * @return int: The points
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Set the fruit properties based on the fruit type
	 */
	private void setFruitProperties() {
		
		switch (fruitType) {
		case Apple:
			setImage("assets/apple.png");
			setSize(new Size(45, 45));
			points = 50;
			break;
		case Orange:
			setImage("assets/orange.png");
			setSize(new Size(50, 50));
			points = 50;
			break;
		case Stawberry:
			setImage("assets/strawberry.png");
			setSize(new Size(25, 30));
			points = 100;
			break;
		default:
			setImage("assets/apple.png");
			points = 50;
			break;
		}
	}
	
}
