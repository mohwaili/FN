package Views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import Models.Fruit;

public class BackgroundPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private int backgroundCenterX;
	private int backgroundCenterY;
	private Fruit fruit;
	
	public BackgroundPanel(BufferedImage image) {
		this.image = image;
		this.backgroundCenterX = image.getWidth(this)/2;
		this.backgroundCenterY = image.getHeight(this)/2;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (image != null) {
			int x = this.getParent().getWidth()/2 - backgroundCenterX;
			int y = this.getParent().getHeight()/2 - backgroundCenterY;
			g.drawImage(image, x, y, this);
		}
		
		if (fruit != null) {
			//TODO: draw fruit
			if (fruit.getImage() != null) {
				g.drawImage(fruit.getImage(), fruit.getPosition().getX(), fruit.getPosition().getY(), this);
			}
			
		}
	}
	
	public void setFruit(Fruit fruit) {
		this.fruit = fruit;
	}
	
}
