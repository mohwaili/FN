package Models;

import Helpers.*;

public class Fruit extends GameObject {
	
	enum FruitType {
		Banan, Apple
	}
	
	private FruitType fruitType;
	
	public Fruit(Position position, Size size) {
		setPosition(position);
		setSize(size);
		setFruitType();
	}
	
	private void setFruitType() {
		//TODO: generate a random fruit type
	}
	
	public FruitType getFruitType() {
		return fruitType;
	}
}
