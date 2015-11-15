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
	
	public Fruit() {
		super();
		setFruitType();	
		setFruitProperties();
		setStartDirection();
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
			setSize(new Size(25, 25));
			points = 100;
			break;
		default:
			setImage("assets/apple.png");
			points = 50;
			break;
		}
		
	}
	
}
