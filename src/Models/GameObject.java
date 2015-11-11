package Models;

import Helpers.Position;
import Helpers.Size;

public abstract class GameObject {

	private Position position;
	private Size size;
	private String background;
	
	public GameObject(Position position, Size size) {
		setPosition(position);
		setSize(size);
	}
	
	public GameObject(Position position, Size size, String background) {
		setPosition(position);
		setSize(size);
		setBackgroud(background);
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Size getSize() {
		return size;
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
	
	public String getBackground() {
		return background;
	}
	
	public void setBackgroud(String background) {
		this.background = background;
	}
	
}
