package admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import loginsys.DBConnection;
import loginsys.Login;
import sun.awt.RequestFocusController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window.Type;
import javax.swing.JSeparator;

@SuppressWarnings({"rawtypes","deprecation","unused","unchecked"})
public class MainFrame {

	public JFrame frame;
	/**
	 * @wbp.nonvisual location=-30,209
	 */
	
	private final JPanel panel = new JPanel();
	private JTextField pname;
	private JTextField pprice;
	private JTextField pqty;
	private JTextField mpname;
	private JTextField mpprice;
	private JTextField mpqty;
	Connection con=DBConnection.getCon();
	private JTable table;
	String selectedItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		show_product();
		show_User();
		show_Log();
	}

	public ArrayList<GetProduct> getProduct()
	{
		ArrayList<GetProduct> getlist=new ArrayList<GetProduct>();
		Connection con=DBConnection.getCon();
		try {
		PreparedStatement ps=con.prepareStatement("select * from products order by pname asc");
		ResultSet rs=ps.executeQuery();
		GetProduct getp;
		while(rs.next())
		{
			getp=new GetProduct(rs.getString(1), rs.getString(5), rs.getInt(3), rs.getDouble(2));
			getlist.add(getp);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return getlist;
	}
	DefaultTableModel model;
	private JTable table_1;
	private JTable table_2;
	private JTextField uname;
	private JTextField pword;
	private JTextField fname;
	private JTextField lname;
	private JTextField mob;
	private JTextField mail;
	public void show_product()
	{
		ArrayList<GetProduct> list=getProduct();
		model=(DefaultTableModel)table.getModel();
		Object [] row=new Object[4];
		for(int i=0;i<list.size();i++)
		{
			row[0]=list.get(i).getPname();
			//row[1]=list.get(i).getPprice();
			row[1]=list.get(i).getPqty();
			row[2]=list.get(i).getAdd_dt();
			model.addRow(row);
		}
		
	}
	
	public ArrayList<GetLogin> getLog()
	{
		ArrayList<GetLogin> login=new ArrayList<GetLogin>();
		try {
			PreparedStatement ps2=con.prepareStatement("select * from mlogin");	
			ResultSet rs2=ps2.executeQuery();
			GetLogin getl;
			while(rs2.next())
			{
				getl=new GetLogin(rs2.getString(1),rs2.getString(2), rs2.getString(3), rs2.getString(5));
				login.add(getl);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return login;
	}
	
	public void show_Log()
	{
		ArrayList<GetLogin> gl=getLog();
		DefaultTableModel dm=(DefaultTableModel)table_2.getModel();
		Object[] row=new Object[4];
		for(int i=0;i<gl.size();i++)
		{
			row[0]=gl.get(i).getUname();
			row[1]=gl.get(i).getPword();
			row[2]=gl.get(i).getType();
			row[3]=gl.get(i).getAdd_dt();
			dm.addRow(row);
		}
	}
	
	
	public ArrayList<GetUser> getU()
	{
		ArrayList<GetUser> useral=new ArrayList<GetUser>();
		
		try {
		PreparedStatement ps=con.prepareStatement("select * from musers");
		ResultSet rs=ps.executeQuery();
		GetUser userget;
		while(rs.next())
		{
			userget=new GetUser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getString(8), rs.getLong(6));
			useral.add(userget);
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return useral;
	}
	
	public void show_User()
	{
		ArrayList<GetUser> al=getU();
		
		DefaultTableModel dm=(DefaultTableModel)table_1.getModel();
		Object[] row=new Object[8];
		for(int i=0;i<al.size();i++)
		{
			row[0]=al.get(i).getUname();
			row[1]=al.get(i).getPword();
			row[2]=al.get(i).getFname();
			row[3]=al.get(i).getLname();
			row[4]=al.get(i).getAddrs();
			row[5]=al.get(i).getMob();
			row[6]=al.get(i).getMail();
			row[7]=al.get(i).getGender();
			dm.addRow(row);
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 204, 255));
		
		frame.setBounds(0, 0, 1366, 766);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane ptab = new JTabbedPane(JTabbedPane.TOP);
		ptab.setFont(new Font("Dialog", Font.BOLD, 14));
		ptab.setBounds(10, 75, 1330, 564);
		frame.getContentPane().add(ptab);
		
		JTabbedPane ProductPane = new JTabbedPane(JTabbedPane.TOP);
		ProductPane.setFont(new Font("Dialog", Font.BOLD, 13));
		ptab.addTab("Product Management", null, ProductPane, null);
		ProductPane.hide();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 204, 153));
		ProductPane.addTab("Add Products", null, panel_1, null);
		panel_1.setLayout(null);
		
		JTextArea txtarea = new JTextArea();
		txtarea.setBackground(new Color(255, 255, 204));
		txtarea.setBounds(405, 36, 440, 200);
		txtarea.setEditable(false);
		panel_1.add(txtarea);
		
		JLabel label = new JLabel("Product Name");
		label.setBounds(471, 273, 105, 25);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(label);
		
		/*JLabel label_1 = new JLabel("Product Price");
		label_1.setBounds(471, 311, 105, 25);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(label_1);*/
		
		JLabel label_2 = new JLabel("Product Quantity");
		label_2.setBounds(471, 311, 132, 25);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(label_2);
		
		pname = new JTextField();
		pname.setBackground(new Color(255, 255, 204));
		pname.setBounds(599, 271, 172, 31);
		pname.setColumns(10);
		panel_1.add(pname);
		
		/*pprice = new JTextField();
		pprice.setBackground(new Color(255, 255, 204));
		pprice.setBounds(599, 309, 172, 31);
		pprice.setColumns(10);
		panel_1.add(pprice);*/
		
		pqty = new JTextField();
		pqty.setBackground(new Color(255, 255, 204));
		pqty.setBounds(599, 309, 172, 31);
		pqty.setColumns(10);
		panel_1.add(pqty);
		
		JButton button = new JButton("Review");
		button.setBounds(565, 385, 105, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//double price=Double.parseDouble(pprice.getText());
				int qty=Integer.parseInt(pqty.getText());
				//double total=price*qty;
				txtarea.setText("Product Name: "+pname.getText()+"\nQuantity: "+pqty.getText());
			}
		});
		panel_1.add(button);
		
		JButton button_1 = new JButton("Add Product");
		button_1.setBounds(682, 385, 115, 30);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con=DBConnection.getCon();
					String name=pname.getText();
					//double price=Double.parseDouble(pprice.getText());
					int qty=Integer.parseInt(pqty.getText());
					//double total=price*qty;
					Date dt=new Date();
					PreparedStatement ps=con.prepareStatement("insert into products values(?,?,?,?,?,?)");
					ps.setString(1, name.toUpperCase());
					ps.setDouble(2, 0.0);
					ps.setInt(3, qty);
					ps.setDouble(4, 0.0);
					ps.setString(5, dt.toString());
					ps.setString(6, "-");
					int k=ps.executeUpdate();
					model.setRowCount(0);
					show_product();
					if(k>0)
					{
						JOptionPane.showConfirmDialog(null, "Product Added Successfully","Done",JOptionPane.PLAIN_MESSAGE);
						pname.setText(null);
						//pprice.setText(null);
						pqty.setText(null);
						txtarea.setText(null);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		panel_1.add(button_1);
		
		JButton btnRes = new JButton("Reset");
		btnRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pname.setText(null);
				pprice.setText(null);
				pqty.setText(null);
				txtarea.setText(null);
				
			}
		});
		btnRes.setBounds(448, 385, 105, 30);
		panel_1.add(btnRes);
		
		JLabel lblReviewPanel = new JLabel("Review Panel");
		lblReviewPanel.setForeground(new Color(255, 255, 255));
		lblReviewPanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReviewPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblReviewPanel.setBounds(539, 11, 154, 14);
		panel_1.add(lblReviewPanel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 204, 153));
		ProductPane.addTab("Modify Products", null, panel_2, null);
		panel_2.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String selectedItem=(String)comboBox.getSelectedItem();	
				PreparedStatement ps=con.prepareStatement("select * from products where pname=?");
				ps.setString(1, selectedItem);
				ResultSet rs=ps.executeQuery();
				model.setRowCount(0);
				show_product();
				while(rs.next())
				{
					mpname.setText(rs.getString(1));
					//mpprice.setText(rs.getString(2));
					mpqty.setText(rs.getString(3));
				}
			}catch (Exception e3) {
				e3.printStackTrace();
			}
			}
		});
		comboBox.setBounds(648, 127, 172, 31);
		panel_2.add(comboBox);
		JLabel label_6 = new JLabel("Select Product Name");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(500, 127, 120, 31);
		panel_2.add(label_6);
		
		mpname = new JTextField();
		mpname.setBackground(new Color(255, 255, 204));
		mpname.setColumns(10);
		mpname.setBounds(648, 196, 172, 31);
		panel_2.add(mpname);
		
		/*mpprice = new JTextField();
		mpprice.setBackground(new Color(255, 255, 204));
		mpprice.setColumns(10);
		mpprice.setBounds(648, 196, 172, 31);
		panel_2.add(mpprice);*/
		
		mpqty = new JTextField();
		mpqty.setBackground(new Color(255, 255, 204));
		mpqty.setColumns(10);
		mpqty.setBounds(648, 238, 172, 31);
		panel_2.add(mpqty);
		
		JLabel label_7 = new JLabel("Modify Product Name");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBounds(500, 196, 138, 31);
		panel_2.add(label_7);
		
		/*JLabel label_8 = new JLabel("Modify Price");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(500, 196, 120, 31);
		panel_2.add(label_8);*/
		
		JLabel label_9 = new JLabel("Modify Quantity");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(500, 238, 120, 31);
		panel_2.add(label_9);
		
		JButton button_4 = new JButton("Modify");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String selectedItem=(String)comboBox.getSelectedItem();
					Connection con=DBConnection.getCon();
					PreparedStatement ps=con.prepareStatement("update products set pname=?,pqty=? where pname=?");
					ps.setString(1, mpname.getText());
					ps.setInt(2, Integer.parseInt(mpqty.getText()));
					ps.setString(3, selectedItem);
					int i=ps.executeUpdate();
					if(i>0)
					{
						JOptionPane.showConfirmDialog(null, "Product Updated Successfully","Done",JOptionPane.PLAIN_MESSAGE);
						mpname.setText(null);
						mpqty.setText(null);
					}
					else
					{
						JOptionPane.showConfirmDialog(null, "0 rows Updated","Error",JOptionPane.PLAIN_MESSAGE);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
}
		});
		button_4.setBounds(692, 293, 104, 27);
		panel_2.add(button_4);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mpname.setText(null);
				mpprice.setText(null);
				mpqty.setText(null);
			}
		});
		btnReset.setBounds(576, 293, 104, 27);
		panel_2.add(btnReset);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 204, 153));
		ProductPane.addTab("Delete Products", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel dpname = new JLabel("");
		dpname.setFont(new Font("Tahoma", Font.BOLD, 14));
		dpname.setForeground(new Color(255, 255, 255));
		dpname.setBackground(Color.DARK_GRAY);
		dpname.setBounds(575, 128, 128, 31);
		panel_3.add(dpname);
		
		JLabel dpqty = new JLabel("");
		dpqty.setFont(new Font("Tahoma", Font.BOLD, 14));
		dpqty.setForeground(new Color(255, 255, 255));
		dpqty.setBounds(725, 128, 128, 31);
		panel_3.add(dpqty);
		
		JLabel dpadddate = new JLabel("");
		dpadddate.setFont(new Font("Tahoma", Font.BOLD, 14));
		dpadddate.setForeground(new Color(255, 255, 255));
		dpadddate.setBounds(874, 128, 335, 31);
		panel_3.add(dpadddate);
		
		JComboBox comboBox_1 = new JComboBox();
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				PreparedStatement ps5=con.prepareStatement("Delete from products where pname=?");
				ps5.setString(1, selectedItem);
				int p=ps5.executeUpdate();
				if(p>0)
				{
					JOptionPane.showConfirmDialog(null, "Product Deleted Successfully","Done",JOptionPane.PLAIN_MESSAGE);
					dpname.setText(null);
					dpqty.setText(null);
					dpadddate.setText(null);
					comboBox_1.repaint();
					
				}
				}catch (Exception e5) {
					e5.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(627, 249, 89, 31);
		panel_3.add(btnDelete);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProductName.setBounds(575, 86, 387, 31);
		panel_3.add(lblProductName);
		
		JLabel lblQuantityLeft = new JLabel("Quantity Left");
		lblQuantityLeft.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantityLeft.setBounds(725, 86, 237, 31);
		panel_3.add(lblQuantityLeft);
		
		JLabel lblAddDate_1 = new JLabel("Add Date");
		lblAddDate_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAddDate_1.setBounds(874, 86, 88, 31);
		panel_3.add(lblAddDate_1);
		
		
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select"}));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setBounds(344, 128, 175, 31);
		panel_3.add(comboBox_1);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectedItem=(String)comboBox_1.getSelectedItem();
				PreparedStatement ps=con.prepareStatement("select * from products where pname=?");
				ps.setString(1, selectedItem);
				ResultSet rs=ps.executeQuery();
				model.setRowCount(0);
				show_product();
				while(rs.next())
				{
					dpname.setText(rs.getString(1));
					dpqty.setText(rs.getString(3));
					dpadddate.setText(rs.getString(5));
				}
			}catch (Exception e3) {
				e3.printStackTrace();
			}
			}
		});
		
		
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 204, 153));
		ProductPane.addTab("Show Products", null, panel_5, null);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(212, 71, 865, 332);
		panel_5.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Name", "Quantity", "Add Date"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(158);
		table.getColumnModel().getColumn(2).setPreferredWidth(251);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 204, 153));
		ProductPane.addTab("Show Sales Report", null, panel_6, null);
		panel_6.setLayout(null);
		
		JLabel lblUnderConstruction = new JLabel("Under Construction");
		lblUnderConstruction.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnderConstruction.setFont(new Font("Dialog", Font.BOLD, 23));
		lblUnderConstruction.setBounds(513, 238, 317, 37);
		panel_6.add(lblUnderConstruction);
		
		JPanel panel_4 = new JPanel();
		ptab.addTab("User Management", null, panel_4, null);
		panel_4.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 13));
		tabbedPane.setBounds(0, 0, 1325, 531);
		panel_4.add(tabbedPane);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(0, 204, 153));
		tabbedPane.addTab("Show Users", null, panel_7, null);
		panel_7.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(181, 80, 982, 195);
		panel_7.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setEnabled(false);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"UserName", "Password", "First Name", "Last Name", "Address", "Mobile", "Mail", "Gender"
			}
		));
		
		JLabel lblUserDetails = new JLabel("User Details");
		lblUserDetails.setForeground(Color.WHITE);
		lblUserDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUserDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserDetails.setBounds(600, 40, 129, 29);
		panel_7.add(lblUserDetails);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(452, 318, 440, 150);
		panel_7.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_2.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Username", "Password", "Type", "Registration Date"
			}
		));
		table_2.getColumnModel().getColumn(0).setPreferredWidth(116);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(106);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(197);
		
		JLabel lblLoginDetails = new JLabel("Login Details");
		lblLoginDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLoginDetails.setForeground(Color.WHITE);
		lblLoginDetails.setBounds(609, 284, 120, 23);
		panel_7.add(lblLoginDetails);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(101);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(97);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(88);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(91);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(139);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(108);
		table_1.getColumnModel().getColumn(6).setPreferredWidth(146);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(0, 204, 153));
		tabbedPane.addTab("Create User", null, panel_9, null);
		panel_9.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(506, 87, 74, 25);
		panel_9.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(506, 117, 74, 25);
		panel_9.add(lblPassword);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFirstName.setBounds(506, 148, 74, 25);
		panel_9.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLastName.setBounds(506, 179, 74, 25);
		panel_9.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddress.setBounds(506, 274, 74, 25);
		panel_9.add(lblAddress);
		
		uname = new JTextField();
		uname.setBackground(new Color(255, 255, 204));
		uname.setBounds(590, 87, 158, 20);
		panel_9.add(uname);
		uname.setColumns(10);
		
		pword = new JTextField();
		pword.setBackground(new Color(255, 255, 204));
		pword.setColumns(10);
		pword.setBounds(590, 117, 158, 20);
		panel_9.add(pword);
		
		fname = new JTextField();
		fname.setBackground(new Color(255, 255, 204));
		fname.setColumns(10);
		fname.setBounds(590, 148, 158, 20);
		panel_9.add(fname);
		
		lname = new JTextField();
		lname.setBackground(new Color(255, 255, 204));
		lname.setColumns(10);
		lname.setBounds(590, 183, 158, 20);
		panel_9.add(lname);
		
		JTextArea addrs = new JTextArea();
		addrs.setBackground(new Color(255, 255, 204));
		addrs.setBounds(590, 276, 158, 60);
		panel_9.add(addrs);
		
		JLabel label_3 = new JLabel("Mobile");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(506, 207, 74, 25);
		panel_9.add(label_3);
		
		JLabel label_4 = new JLabel("E-Mail");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(506, 243, 74, 25);
		panel_9.add(label_4);
		
		mob = new JTextField();
		mob.setBackground(new Color(255, 255, 204));
		mob.setColumns(10);
		mob.setBounds(590, 211, 158, 20);
		panel_9.add(mob);
		
		mail = new JTextField();
		mail.setBackground(new Color(255, 255, 204));
		mail.setColumns(10);
		mail.setBounds(590, 243, 158, 20);
		panel_9.add(mail);
		
		JComboBox gender = new JComboBox();
		gender.setModel(new DefaultComboBoxModel(new String[] {"Select Gender", "Male", "Female"}));
		gender.setBounds(590, 347, 158, 22);
		panel_9.add(gender);
		
		JComboBox utype = new JComboBox();
		utype.setModel(new DefaultComboBoxModel(new String[] {"Select User Type", "Admin", "SalesMan"}));
		utype.setBounds(590, 380, 158, 25);
		panel_9.add(utype);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement ps=con.prepareStatement("insert into musers values(?,?,?,?,?,?,?,?)");
					ps.setString(1, uname.getText());
					ps.setString(2, pword.getText());
					ps.setString(3, fname.getText());
					ps.setString(4, lname.getText());
					ps.setString(5, addrs.getText());
					ps.setLong(6, Long.parseLong(mob.getText()));
					ps.setString(7, mail.getText());
					ps.setString(8, gender.getSelectedItem().toString());
					PreparedStatement ps2=con.prepareStatement("insert into mlogin values(?,?,?,?,?)");
					Date dt=new Date();
					ps2.setString(1, uname.getText());
					ps2.setString(2, pword.getText());
					ps2.setString(3, utype.getSelectedItem().toString());
					ps2.setString(4, fname.getText());
					ps2.setString(5, dt.toString());
					int r=ps.executeUpdate();
					int l=ps2.executeUpdate();
					if(r>0&&l>0){
						JOptionPane.showConfirmDialog(null, "User Added Successfully","Done",JOptionPane.PLAIN_MESSAGE);
						uname.setText(null);
						pword.setText(null);
						fname.setText(null);
						lname.setText(null);
						addrs.setText(null);
						mail.setText(null);
						mob.setText(null);
					}
				} catch (Exception e2) {
					JOptionPane.showConfirmDialog(null, "One or More Column Are Empty","Error",JOptionPane.PLAIN_MESSAGE);
					e2.printStackTrace();
				}
			}
		});
		btnRegister.setBounds(627, 431, 97, 31);
		panel_9.add(btnRegister);
		
		JButton btnReset_1 = new JButton("Reset");
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uname.setText(null);
				pword.setText(null);
				fname.setText(null);
				lname.setText(null);
				addrs.setText(null);
				mail.setText(null);
				mob.setText(null);
			
			}
		});
		btnReset_1.setBounds(520, 430, 97, 32);
		panel_9.add(btnReset_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(458, 74, 356, 2);
		panel_9.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(458, 414, 356, 2);
		panel_9.add(separator_1);
		
		
		
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(0, 204, 153));
		tabbedPane.addTab("Modify Users", null, panel_8, null);
		panel_8.setLayout(null);
		
		JLabel lblUnderConstruction_1 = new JLabel("Under Construction");
		lblUnderConstruction_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnderConstruction_1.setFont(new Font("Dialog", Font.BOLD, 23));
		lblUnderConstruction_1.setBounds(510, 196, 317, 37);
		panel_8.add(lblUnderConstruction_1);
		
		JLabel lblMedicalStoreManagement = new JLabel("Store Management");
		lblMedicalStoreManagement.setFont(new Font("Dialog", Font.BOLD, 23));
		lblMedicalStoreManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedicalStoreManagement.setBounds(517, 12, 317, 37);
		frame.getContentPane().add(lblMedicalStoreManagement);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mf=new MainFrame();
				frame.hide();
				mf.frame.show();
			}
		});
		btnRefresh.setBounds(1261, 0, 89, 31);
		frame.getContentPane().add(btnRefresh);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 116, 64);
		frame.getContentPane().add(lblNewLabel);
		try {
			Connection con=DBConnection.getCon();
			PreparedStatement ps=con.prepareStatement("select * from pic");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				byte[] img=rs.getBytes("pic");
				ImageIcon image=new ImageIcon(img);
				Image im=image.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon newImg=new ImageIcon(im);
				lblNewLabel.setIcon(newImg);
						
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			PreparedStatement ps=con.prepareStatement("select * from products");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ArrayList<String> al=new ArrayList<String>();
				al.add(rs.getString(1));
				comboBox.addItem(al.get(0));
				comboBox_1.addItem(al.get(0));
				}
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnUser = new JMenu("USER");
		mnUser.setFont(new Font("Dialog", Font.BOLD, 14));
		menuBar.add(mnUser);
		
		JMenuItem mntmLogout = new JMenuItem("LogOut");
		mntmLogout.setFont(new Font("Dialog", Font.BOLD, 14));
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				Login l=new Login();
				l.frmAuthentication.show();
			}
		});
		mnUser.add(mntmLogout);
		
		
	}
}