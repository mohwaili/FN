package Models;

import Helpers.*;

public class Bomb extends GameObject {

	public Bomb() {
		super();
		
		setImage("assets/bomb.png");
		setSize(new Size(50, 50));
		setStartDirection();
		
	}
	
}
