package Helpers;

public class Size {

	private int width;
	private int height;
	
	/**
	 * Initializing the size
	 * @param width: The width
	 * @param height: The height
	 */
	public Size(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	/**
	 * Get the width
	 * @return int: The width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Set the height
	 * @param width: The new width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Get the height
	 * @return int: The height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Set the height
	 * @param height: The new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
}
