package Models;

import Helpers.*;

public class Bomb extends GameObject {

	/**
	 * Initializing new bomb
	 * @param size: The bomb size
	 */
	public Bomb(Size size) {
		setImage("assets/bomb.png");
		setSize(size);
		setStartDirection();
	}
	
}
