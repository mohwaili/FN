package Views;


import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.prism.Image;

import Helpers.Size;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Rectangle;

public class PlayingFieldView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Size size;
	private int topPanelHeight;
	private int fieldPanelHeight;
	
	private BackgroundPanel playFieldPanel;
	
	JLabel lblScoreValue = new JLabel("0");
	JLabel lblLivesValue = new JLabel("3");
	
	/**
	 * Create the application.
	 */
	public PlayingFieldView(Size size) {
		setResizable(false);
		setSize(size);
		initialize();
	}
	
	public void setSize(Size size) {
		this.size = size;
		this.setBounds(100, 100, 500, 500);
		this.topPanelHeight = 50;
		this.fieldPanelHeight = size.getHeight() - topPanelHeight;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, 500, 50);
		topPanel.setBackground(UIManager.getColor("Button.background"));
		getContentPane().add(topPanel);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblScore = new JLabel("Score: ");
		topPanel.add(lblScore);
		topPanel.add(lblScoreValue);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(new Rectangle(0, 0, 30, 0));
		topPanel.add(separator);
		
		JLabel lblLives = new JLabel("Lives");
		topPanel.add(lblLives);
		topPanel.add(lblLivesValue);
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("assets/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		playFieldPanel = new BackgroundPanel(image);
		playFieldPanel.setBounds(0, 50, 500,fieldPanelHeight);
		getContentPane().add(playFieldPanel);
		
	}
	
	public void setScore(Integer score) {
		lblScoreValue.setText(score.toString());
	}
	
	public BackgroundPanel getPlayingField() {
		return playFieldPanel;
	}
}
