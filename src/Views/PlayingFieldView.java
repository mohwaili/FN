package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Helpers.Size;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class PlayingFieldView extends JFrame {

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PlayingFieldView window = new PlayingFieldView();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public PlayingFieldView() {
		initialize();
	}
	
	public void setSize(Size size) {
		this.setBounds(100, 100, size.getWidth(), size.getHeight());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.ORANGE);
		getContentPane().add(topPanel);
		
		JPanel playFieldPanel = new JPanel();
		playFieldPanel.setBackground(Color.YELLOW);
		getContentPane().add(playFieldPanel);
		
//		JPanel panel = new JPanel();
//		getContentPane().add(panel, BorderLayout.CENTER);
//		this.add(panel);
	}

}
