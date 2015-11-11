package Models;

import Helpers.*;

public class Bomb extends GameObject {

	public Bomb(Position position, Size size) {
		super(position, size);
		setPosition(position);
		setSize(size);
	}
	
}
