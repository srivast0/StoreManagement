package loginsys;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import admin.MainFrame;
import user.UserFrame;
@SuppressWarnings("deprecation")
public class Login {

	public JFrame frmAuthentication;
	private JTextField uname;
	private JPasswordField pword;
	public static String fname;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmAuthentication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmAuthentication = new JFrame();
		frmAuthentication.setBackground(new Color(0, 204, 204));
		frmAuthentication.getContentPane().setForeground(new Color(0, 204, 204));
		frmAuthentication.getContentPane().setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(0, 204, 153));
		panel.setBounds(0, 0, 444, 275);
		frmAuthentication.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Login");
		label.setBackground(new Color(255, 255, 255));
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("Aparajita", Font.BOLD, 36));
		label.setBounds(171, 29, 197, 37);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Username");
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(71, 93, 74, 23);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(71, 127, 74, 28);
		panel.add(label_2);
		
		uname = new JTextField();
		uname.setBackground(new Color(255, 255, 204));
		uname.setForeground(new Color(0, 0, 0));
		uname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		uname.setColumns(10);
		uname.setBounds(165, 96, 203, 20);
		panel.add(uname);
		
		pword = new JPasswordField();
		pword.setBackground(new Color(255, 255, 204));
		pword.setForeground(new Color(0, 51, 0));
		pword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pword.setBounds(165, 133, 203, 20);
		panel.add(pword);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con=DBConnection.getCon();
					PreparedStatement ps=con.prepareStatement("select * from MLOGIN where username=? and password=?");
					ps.setString(1, uname.getText());
					ps.setString(2, pword.getText());
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						fname=rs.getString(4);
						String type=rs.getString(3);
						if(type.equals("Admin"))
						{	
							MainFrame mf=new MainFrame();
							mf.frame.show();
							frmAuthentication.hide();
						}
						else
						{
							UserFrame uz=new UserFrame();
							uz.frmUserZone.show();
						}
					}
					else
					{
						JOptionPane.showConfirmDialog(null, "Invalid Credentials","Login Error",JOptionPane.PLAIN_MESSAGE);
						uname.setText(null);
						pword.setText(null);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		button.setBounds(118, 190, 89, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("Reset");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uname.setText(null);
				pword.setText(null);
			}
		});
		button_1.setBounds(233, 190, 89, 23);
		panel.add(button_1);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(0, 0, 129, 73);
		panel.add(label_3);
		try {
			Connection con=DBConnection.getCon();
			PreparedStatement ps=con.prepareStatement("select * from pic");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				byte[] img=rs.getBytes("pic");
				ImageIcon image=new ImageIcon(img);
				Image im=image.getImage().getScaledInstance(label_3.getWidth(), label_3.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon newImg=new ImageIcon(im);
				label_3.setIcon(newImg);
						
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
		
	
		
		

		
		frmAuthentication.setTitle("Authentication");
		frmAuthentication.setResizable(false);
		frmAuthentication.setBounds(450, 200, 450, 301);
		frmAuthentication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
