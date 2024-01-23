package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class Delivery extends JFrame {

	private JPanel contentPane;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    public static final String USER = "root";
    public static final String PASS = "Appu@2023";
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delivery frame = new Delivery(0);
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
	void makeOrder(int id) {
		try {
			int i =JOptionPane.showConfirmDialog (null, "Are you Sure with your Order?",
        	         "Confirmation",
        	         JOptionPane.YES_NO_OPTION);
        	System.out.println(i);
        	        if (i == 0) {
        	        	try {
        	        		Connection conn = null;
        		            java.sql.Statement stmt = null;
        		            String query="";
        		            Class.forName("com.mysql.cj.jdbc.Driver");
        		            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        		            stmt = conn.createStatement();
        		            String ID = ""+id;
        		            String sql1="select * from cart where ID="+id;
        		            ResultSet rs = stmt.executeQuery(sql1);
        		            String q1  = "insert into orders(ID,item,price,count) values ";
        		            String q2="";
        		            int j=0;
        		            while(rs.next()) {
        		            	j++;
       	    				 String test2 = rs.getString(1);
       	    				 if(test2.equals(ID) && j==1) {
       	    					q2 = "("+id+",'"+rs.getString(2)+"',"+rs.getString(3)+","+rs.getString(4)+")";
       	    					System.out.println(query);
       	    					
       	    				 }else if(test2.equals(ID)) {
       	    					q2 = ",("+id+",'"+rs.getString(2)+"',"+rs.getString(3)+","+rs.getString(4)+")";
       	    				 }
       	    				 query+=q2; 
       	    			 	}
        		            q1+=query;
        		            stmt.executeUpdate(q1);
        		            query = "delete from cart where ID="+id;
        		            stmt.executeUpdate(query);
        		            Order obj = new Order(id); 
            	        	setVisible(false); // Hide current frame
    				        obj.setVisible(true); //second frame
        	        	}catch(Exception e3){
        	        		System.out.println(e3);
        	        	}
        	        	
        	        }
		}catch(Exception e2) {
			System.out.println(e2);
		}
	}
	public Delivery(int id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1650,1080);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Order");
		btnNewButton.setBounds(1068, 696, 91, 36);
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnNewButton.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = null;
		            Statement stmt = null;
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            conn = DriverManager.getConnection(DB_URL, USER, PASS);
		            stmt = conn.createStatement();
		            String ID = ""+id;
		            String query=null;
		            int flag=0;
		            String name = textField.getText();
		            String add1 = textField_1.getText();
		            String add2 = textField_2.getText();
		            String city = textField_3.getText();
		            String state = textField_4.getText();
		            String country = textField_5.getText();
		            String pincode = textField_6.getText();
		            String mobile = textField_7.getText();
		            String sql1="select * from address";
       			 	ResultSet rs = stmt.executeQuery(sql1);
	       			while(rs.next()) {
	    				 String test2 = rs.getString(1);
	    				 if(test2.equals(ID)) {
	    					 flag=1;
	    					 query = "update address set name='"+name+"',Address_1='"+add1+"',Address_2='"+add2+"',city='"+city+"',state='"+state+"',country='"+country+"', pincode='"+pincode+"', mobile='"+mobile+"' where ID="+id;
	    				 }
	    			 }
	    			 if(flag==0) {
	    				 System.out.println(id);
	    				 query = "insert into address values ("+id+",'"+name+"','"+add1+"','"+add2+"','"+city+"','"+state+"','"+country+"','"+pincode+"','"+mobile+"')";
	    			 }
	    			 stmt.executeUpdate(query);
				}catch(Exception e1) {
					System.out.println(e1);
				}
				makeOrder(id);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBounds(-5, -2, 1550, 77);
		panel.setBackground(new Color(0, 0, 0));
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
			@Override
			public void mouseClicked(MouseEvent e) {
				
						Cart obj = new Cart(id);   
				        setVisible(false); // Hide current frame
				        obj.setVisible(true); //second frame
			
			}
		});
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\back.png"));
		lblNewLabel.setBounds(14, 6, 65, 65);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("DELIVERY DETAILS");
		lblNewLabel_3.setFont(new Font("Bodoni MT", Font.BOLD, 24));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(997, 122, 235, 50);
		contentPane.add(lblNewLabel_3);
		textField = new JTextField();
		textField.setBounds(927, 215, 395, 45);
		contentPane.add(textField);
		textField.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		textField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel lblNewLabel_3_1 = new JLabel("Name:");
		lblNewLabel_3_1.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(730, 215, 187, 36);
		contentPane.add(lblNewLabel_3_1);
		
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Address Line 1:");
		lblNewLabel_3_1_1.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
		lblNewLabel_3_1_1.setBounds(730, 265, 187, 36);
		contentPane.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Address Line 2:");
		lblNewLabel_3_1_1_1.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
		lblNewLabel_3_1_1_1.setBounds(730, 345, 187, 36);
		contentPane.add(lblNewLabel_3_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("City:");
		lblNewLabel_3_1_1_1_1.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
		lblNewLabel_3_1_1_1_1.setBounds(730, 425, 187, 36);
		contentPane.add(lblNewLabel_3_1_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1_1_1 = new JLabel("State:");
		lblNewLabel_3_1_1_1_1_1.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
		lblNewLabel_3_1_1_1_1_1.setBounds(730, 475, 187, 36);
		contentPane.add(lblNewLabel_3_1_1_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1 = new JLabel("Pincode:");
		lblNewLabel_3_1_1_1_1_1_1.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
		lblNewLabel_3_1_1_1_1_1_1.setBounds(730, 575, 187, 36);
		contentPane.add(lblNewLabel_3_1_1_1_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1_1_1_2 = new JLabel("Mobile:");
		lblNewLabel_3_1_1_1_1_1_2.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
		lblNewLabel_3_1_1_1_1_1_2.setBounds(730, 625, 187, 36);
		contentPane.add(lblNewLabel_3_1_1_1_1_1_2);
		
		JLabel lblNewLabel_3_1_1_1_1_1_3 = new JLabel("Country:");
		lblNewLabel_3_1_1_1_1_1_3.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
		lblNewLabel_3_1_1_1_1_1_3.setBounds(730, 525, 187, 36);
		contentPane.add(lblNewLabel_3_1_1_1_1_1_3);
		
		JLabel lblNewLabel1 = new JLabel("New label");
		lblNewLabel1.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\del.png"));
		lblNewLabel1.setBounds(-5, -237, 1080, 1080);
		contentPane.add(lblNewLabel1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		textField_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_1.setBounds(927, 265, 395, 75);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		textField_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_2.setBounds(927, 345, 395, 75);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		textField_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_3.setBounds(927, 425, 395, 45);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		textField_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_4.setBounds(927, 475, 395, 45);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		textField_5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_5.setBounds(927, 525, 395, 45);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		textField_6.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_6.setBounds(927, 575, 395, 45);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		textField_7.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textField_7.setBounds(927, 625, 395, 45);
		contentPane.add(textField_7);
	}
}
