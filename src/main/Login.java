package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    public static final String USER = "root";
    public static final String PASS = "Appu@2023";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1650,1080);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(-5, -2, 1550, 77);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel_1 = new JLabel("A2S Flower Shop");
		lblNewLabel_1.setBounds(636, 11, 383, 55);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Bodoni MT", Font.BOLD, 30));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\logo.png"));
		lblNewLabel_2.setBounds(636, 6, 65, 65);
		panel.add(lblNewLabel_2);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
						Flower obj = new Flower();   
				        setVisible(false); // Hide current frame
				        obj.setVisible(true); //second frame
			
			}
		});
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\home.png"));
		lblNewLabel.setBounds(14, 6, 65, 65);
		panel.add(lblNewLabel);
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\login.png"));
		lblNewLabel_4.setBounds(474, 193, 315, 400);
		contentPane.add(lblNewLabel_4);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(784, 193, 315, 400);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		textField = new JTextField();
		textField.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		textField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField.setBounds(63, 122, 191, 39);
		panel_1.add(textField);
		textField.setColumns(10);
		JLabel lblNewLabel_3 = new JLabel("USER LOGIN");
		lblNewLabel_3.setBounds(85, 9, 144, 31);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Bodoni MT", Font.BOLD, 25));
		JLabel lblNewLabel1 = new JLabel("Username:");
		lblNewLabel1.setFont(new Font("Bodoni MT", Font.ITALIC, 20));
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setBounds(85, 72, 144, 39);
		panel_1.add(lblNewLabel1);
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Bodoni MT", Font.ITALIC, 20));
		lblPassword.setBounds(85, 174, 144, 39);
		panel_1.add(lblPassword);
		JPasswordField textField_1=new JPasswordField();
		textField_1.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		textField_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_1.setColumns(10);
		textField_1.setBounds(63, 222, 191, 39);
		panel_1.add(textField_1);
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				 	byte flag=0;
				 	String name = textField.getText();
				 	String pass=String.valueOf(textField_1.getPassword());
				 	int id;
		            Connection conn = null;
		            Statement stmt = null;
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            conn = DriverManager.getConnection(DB_URL, USER, PASS);
		            stmt = conn.createStatement();
		            String sql2="select * from login";
		            ResultSet rs = stmt.executeQuery(sql2);
		            System.out.println(name+"----"+pass);
		            while(rs.next()) {
		                if(rs.getString(2).equals(name) && rs.getString(3).equals(pass)) {
		                	flag=1;
		                	id = Integer.parseInt(rs.getString(1));
		                	JOptionPane.showMessageDialog(null, "Successfully Logged In", "Success", JOptionPane.PLAIN_MESSAGE);
			        		Shop obj = new Shop(id);   
					        setVisible(false); // Hide current frame
					        obj.setVisible(true); //second frame
		                }
		            }
		            if(flag==0) {
		            	JOptionPane.showMessageDialog(null, "Invalid Username and Password", "Alert", JOptionPane.ERROR_MESSAGE);
		            }	            
	            } catch (Exception e1) {
	            	System.out.println(e1);
	            }
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnNewButton.setFont(new Font("Bodoni MT", Font.PLAIN, 14));
		btnNewButton.setBounds(116, 291, 91, 23);
		panel_1.add(btnNewButton);
		
	}
}
