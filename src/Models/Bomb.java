package Models;

import Helpers.*;

public class Bomb extends GameObject {

	public Bomb(Size size) {
		super();
		setImage("assets/bomb.png");
		setSize(size);
		setStartDirection();
	}
	
}
