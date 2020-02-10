package user;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class UserFrame {

	public JFrame frmUserZone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame window = new UserFrame();
					window.frmUserZone.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUserZone = new JFrame();
		frmUserZone.setTitle("User Zone");
		frmUserZone.setBounds(100, 100, 1366, 766);
		frmUserZone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
