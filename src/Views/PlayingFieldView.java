package Views;


import javax.swing.JFrame;
import javax.swing.JPanel;
import Helpers.Size;
import java.awt.Color;

public class PlayingFieldView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Size size;
	private int topPanelHeight;
	private int fieldPanelHeight;
	
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
		this.setBounds(100, 100, size.getWidth(), size.getHeight());
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
		topPanel.setBounds(0, 0, size.getWidth(), topPanelHeight);
		topPanel.setBackground(Color.ORANGE);
		getContentPane().add(topPanel);
		
		JPanel playFieldPanel = new JPanel();
		playFieldPanel.setBounds(0, 50, size.getWidth(),fieldPanelHeight);
		playFieldPanel.setBackground(Color.YELLOW);
		getContentPane().add(playFieldPanel);
		
	}

}
