package Views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import Models.GameObject;

public class BackgroundPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private int backgroundCenterX;
	private int backgroundCenterY;
	private GameObject gameObject;
	
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
		
		if (gameObject != null) {
			if (gameObject.getImage() != null) {
				g.drawImage(gameObject.getImage(), (int)gameObject.getPosition().getX(), (int)gameObject.getPosition().getY(), this);
			}
			
		}
	}
	
	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
}
