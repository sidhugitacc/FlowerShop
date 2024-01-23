package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.GridLayout;

public class Shop extends JFrame {

	private JPanel contentPane;
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
					Shop frame = new Shop(0);
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
	public Shop(int id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize (1650,1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ActionListener add = new ActionListener() {
	         public void actionPerformed(ActionEvent event) {
	        	 if(id==0) {
						JOptionPane.showMessageDialog(null, "Kindly Login or Signup", "Alert", JOptionPane.ERROR_MESSAGE);
						Flower obj = new Flower();   
						setVisible(false); // Hide current frame
						obj.setVisible(true); //second frame
						
					}
	        	 else {
	        		 
	        		 String name;
	        		 int price=0;
	        		 int count=1;
	        		 name = event.getActionCommand();
	        		 if(name=="Roses")price = 200;
	        		 else if(name == "Daisy") price = 150;
	        		 else if(name == "Dafodils") price = 280;
	        		 else if(name == "Lily") price = 160;
	        		 else if(name == "Marigold") price = 220;
	        		 else if(name == "Orchid") price = 500;
	        		 else if(name == "Peony") price = 450;
	        		 else if(name == "Tulip") price = 200;
	        		 else if(name == "Carnation") price = 300;
	        		 try {	
	        			 String query="";
	        			 int flag=0,temp;
	        			 Connection conn = null;
	        			 Statement stmt = null;
	        			 Class.forName("com.mysql.cj.jdbc.Driver");
	        			 conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        			 stmt = conn.createStatement();
	        			 String sql1="select * from cart";
	        			 ResultSet rs = stmt.executeQuery(sql1);
	        			 String x=""+id;
	        			 while(rs.next()) {
	        				 String test = rs.getString(2);
	        				 String test2 = rs.getString(1);
	        				 if(test.equals(name) && test2.equals(x)) {
	        					 flag=1;
	        					 temp=Integer.parseInt(rs.getString(4));
	        					 temp++;
	        					 query = "update cart set count="+temp+" where item='"+name+"' and ID="+id;
	        				 }
	        			 }
	        			 if(flag==0) {
	        				 System.out.println(id);
	        				 query = "insert into cart values ("+id+",'"+name+"',"+price+","+count+")";
	        			 }
	        			 stmt.executeUpdate(query);
	        			 JOptionPane.showMessageDialog(null, "Successfully Added to the Cart", "Success", JOptionPane.PLAIN_MESSAGE);
	        		 } catch (Exception e1) {
	        			 System.out.println(e1);
	        		 }
	        	 }
	        	 }
	    };		
		
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
			@Override
			public void mouseClicked(MouseEvent e) {
				
						Flower obj = new Flower();   
				        setVisible(false); // Hide current frame
				        obj.setVisible(true); //second frame
			
			}
		});
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\logout.png"));
		lblNewLabel.setBounds(14, 6, 65, 65);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int count=0;
				if(id==0) {
					JOptionPane.showMessageDialog(null, "Kindly Login or Signup", "Alert", JOptionPane.ERROR_MESSAGE);
					Flower obj = new Flower();   
					setVisible(false); // Hide current frame
					obj.setVisible(true); //second frame
				}
				else {
					
					try {	
						
						Connection conn = null;
						Statement stmt = null;
						Class.forName("com.mysql.cj.jdbc.Driver");
						conn = DriverManager.getConnection(DB_URL, USER, PASS);
						stmt = conn.createStatement();
						String sql1="select * from cart";
						ResultSet rs = stmt.executeQuery(sql1);
						while(rs.next()) {
							count++;
						}
					} catch (Exception e1) {
						System.out.println(e1);
					}
					if(count==0) {
						JOptionPane.showMessageDialog(null, "CART IS Empty", "Alert", JOptionPane.ERROR_MESSAGE);
					}else {
						Cart obj = new Cart(id);   
						setVisible(false); // Hide current frame
						obj.setVisible(true); //second frame	
					}
				}
				}
		});
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\cart.png"));
		lblNewLabel_3.setBounds(1460, 6, 65, 65);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 74, 1538, 781);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\dafodils.jpg"));
		lblNewLabel_9.setBounds(1095, 47, 136, 136);
		panel_1.add(lblNewLabel_9);

		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\rose.jpg"));
		lblNewLabel_5.setBounds(65, 47, 136, 136);
		panel_1.add(lblNewLabel_5);
		JLabel lblNewLabel_31 = new JLabel("Roses");
		lblNewLabel_31.setBackground(new Color(0, 0, 0));
		lblNewLabel_31.setForeground(new Color(0, 0, 0));
		lblNewLabel_31.setBounds(225, 59, 80, 21);
		lblNewLabel_31.setFont(new Font("Georgia", Font.BOLD, 20));
		panel_1.add(lblNewLabel_31);
		
		JLabel lblNewLabel_7 = new JLabel("(Pack of 12) Pink Roses");
		lblNewLabel_7.setForeground(new Color(0, 0, 0));
		lblNewLabel_7.setBounds(225, 86, 213, 26);
		lblNewLabel_7.setFont(new Font("Georgia", Font.ITALIC, 18));
		panel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Rs: 200");
		lblNewLabel_8.setForeground(new Color(0, 0, 0));
		lblNewLabel_8.setBounds(225, 119, 80, 21);
		lblNewLabel_8.setFont(new Font("Georgia", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_8);
		
		JButton btnNewButton = new JButton("Add to Cart");
		btnNewButton.setToolTipText("Add Item to Cart");
		btnNewButton.setBounds(225, 149, 118, 23);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Georgia", Font.PLAIN, 11));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setActionCommand("Roses");
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(add);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\daisy.jpg"));
		lblNewLabel_6.setBounds(580, 47, 136, 136);
		panel_1.add(lblNewLabel_6);
		

		
		JLabel lblNewLabel_31_1 = new JLabel("Daisy");
		lblNewLabel_31_1.setFont(new Font("Georgia", Font.BOLD, 20));
		lblNewLabel_31_1.setBounds(740, 59, 80, 21);
		panel_1.add(lblNewLabel_31_1);
		
		JLabel lblNewLabel_7_1 = new JLabel("(Pack of 10) White Daisy");
		lblNewLabel_7_1.setFont(new Font("Georgia", Font.ITALIC, 18));
		lblNewLabel_7_1.setBounds(740, 86, 213, 26);
		panel_1.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("Rs: 150");
		lblNewLabel_8_1.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNewLabel_8_1.setBounds(740, 119, 80, 21);
		panel_1.add(lblNewLabel_8_1);
		
		JButton btnNewButton_1 = new JButton("Add to Cart");
		btnNewButton_1.setToolTipText("Add Item to Cart");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Georgia", Font.PLAIN, 11));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(740, 149, 118, 23);
		btnNewButton_1.setActionCommand("Daisy");
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(add);
		
		
		

		JLabel lblNewLabel_14 = new JLabel("Dafodils");
		lblNewLabel_14.setFont(new Font("Georgia", Font.BOLD, 18));
		lblNewLabel_14.setBounds(1255, 59, 80, 21);
		panel_1.add(lblNewLabel_14);
		JLabel lblNewLabel_15 = new JLabel("(Pack of 12) Dafodils");
		lblNewLabel_15.setFont(new Font("Georgia", Font.ITALIC, 18));
		lblNewLabel_15.setBounds(1255, 86, 213, 26);
		panel_1.add(lblNewLabel_15);
		JLabel lblNewLabel_16 = new JLabel("Rs : 280");
		lblNewLabel_16.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNewLabel_16.setBounds(1255, 119, 80, 21);
		panel_1.add(lblNewLabel_16);
		JButton btnNewButton_2 = new JButton("Add to Cart");
		btnNewButton_2.setToolTipText("Add Item to Cart");
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Georgia", Font.PLAIN, 11));
		btnNewButton_2.setBounds(1255, 149, 118, 23);
		panel_1.add(btnNewButton_2);
		btnNewButton_2.setActionCommand("Dafodils");
		btnNewButton_2.addActionListener(add);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		lblNewLabel_10.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\lily.jpg"));
		lblNewLabel_10.setBounds(65, 297, 136, 136);
		panel_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_17 = new JLabel("Lily");
		lblNewLabel_17.setFont(new Font("Georgia", Font.BOLD, 18));
		lblNewLabel_17.setBounds(225, 309, 80, 21);
		panel_1.add(lblNewLabel_17);
		JLabel lblNewLabel_18 = new JLabel("(Pack of 5) Lilies");
		lblNewLabel_18.setFont(new Font("Georgia", Font.ITALIC, 18));
		lblNewLabel_18.setBounds(225, 336, 213, 26);
		panel_1.add(lblNewLabel_18);
		JLabel lblNewLabel_19 = new JLabel("Rs : 160");
		lblNewLabel_19.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNewLabel_19.setBounds(225, 369, 80, 21);
		panel_1.add(lblNewLabel_19);
		JButton btnNewButton_3 = new JButton("Add to Cart");
		btnNewButton_3.setToolTipText("Add Item to Cart");
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Georgia", Font.PLAIN, 11));
		btnNewButton_3.setBounds(225, 399, 118, 23);
		panel_1.add(btnNewButton_3);
		btnNewButton_3.setActionCommand("Lily");
		btnNewButton_3.addActionListener(add);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		lblNewLabel_11.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\marigold (1).jpg"));
		lblNewLabel_11.setBounds(580, 297, 136, 136);
		panel_1.add(lblNewLabel_11);
		JLabel lblNewLabel_27 = new JLabel("Marigold");
		lblNewLabel_27.setFont(new Font("Georgia", Font.BOLD, 20));
		lblNewLabel_27.setBounds(740, 309, 129, 21);
		panel_1.add(lblNewLabel_27);
		JLabel lblNewLabel_28 = new JLabel("(Pack of 10) Marigold");
		lblNewLabel_28.setFont(new Font("Georgia", Font.ITALIC, 18));
		lblNewLabel_28.setBounds(740, 336, 213, 26);
		panel_1.add(lblNewLabel_28);
		JLabel lblNewLabel_29 = new JLabel("Rs : 220");
		lblNewLabel_29.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNewLabel_29.setBounds(740, 369, 80, 21);
		panel_1.add(lblNewLabel_29);
		JButton btnNewButton2_1 = new JButton("Add to Cart");
		btnNewButton2_1.setToolTipText("Add Item to Cart");
		btnNewButton2_1.setForeground(new Color(0, 0, 0));
		btnNewButton2_1.setBackground(new Color(255, 255, 255));
		btnNewButton2_1.setFont(new Font("Georgia", Font.PLAIN, 11));
		btnNewButton2_1.setBounds(740, 399, 118, 23);
		panel_1.add(btnNewButton2_1);
		btnNewButton2_1.setActionCommand("Marigold");
		btnNewButton2_1.addActionListener(add);
		
		
		JLabel lblNewLabel_12 = new JLabel("New label");
		lblNewLabel_12.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\orchid.jpg"));
		lblNewLabel_12.setBounds(1095, 295, 136, 136);
		panel_1.add(lblNewLabel_12);
		JLabel lblNewLabel_20 = new JLabel("Orchid");
		lblNewLabel_20.setFont(new Font("Georgia", Font.BOLD, 18));
		lblNewLabel_20.setBounds(1255, 309, 80, 21);
		panel_1.add(lblNewLabel_20);
		JLabel lblNewLabel_21 = new JLabel("(Pack of 10) Orchids");
		lblNewLabel_21.setFont(new Font("Georgia", Font.ITALIC, 18));
		lblNewLabel_21.setBounds(1255, 336, 213, 26);
		panel_1.add(lblNewLabel_21);
		JLabel lblNewLabel_23 = new JLabel("Rs : 500");
		lblNewLabel_23.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNewLabel_23.setBounds(1255, 369, 80, 21);
		panel_1.add(lblNewLabel_23);
		
		JButton btnNewButton_4 = new JButton("Add to Cart");
		btnNewButton_4.setToolTipText("Add Item to Cart");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.setForeground(new Color(0, 0, 0));
		btnNewButton_4.setFont(new Font("Georgia", Font.PLAIN, 11));
		btnNewButton_4.setBounds(1255,399, 118, 23);
		panel_1.add(btnNewButton_4);
		btnNewButton_4.setActionCommand("Orchid");
		btnNewButton_4.addActionListener(add);
		
		JLabel lblNewLabel_13 = new JLabel("New label");
		lblNewLabel_13.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\peony (1).jpg"));
		lblNewLabel_13.setBounds(65, 547, 136, 136);
		panel_1.add(lblNewLabel_13);
		JLabel lblNewLabel_30_1 = new JLabel("Peony");
		lblNewLabel_30_1.setFont(new Font("Georgia", Font.BOLD, 18));
		lblNewLabel_30_1.setBounds(225, 559, 80, 21);
		panel_1.add(lblNewLabel_30_1);
		JLabel lblNewLabel_28_1_1 = new JLabel("(Pack of 12) Poenies");
		lblNewLabel_28_1_1.setFont(new Font("Georgia", Font.ITALIC, 18));
		lblNewLabel_28_1_1.setBounds(225, 586, 213, 26);
		panel_1.add(lblNewLabel_28_1_1);
		JLabel lblNewLabel_29_1_1 = new JLabel("Rs : 450");
		lblNewLabel_29_1_1.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNewLabel_29_1_1.setBounds(225, 619, 80, 21);
		panel_1.add(lblNewLabel_29_1_1);
		JButton btnNewButton_2_1_1_1 = new JButton("Add to Cart");
		btnNewButton_2_1_1_1.setForeground(new Color(0, 0, 0));
		btnNewButton_2_1_1_1.setFont(new Font("Georgia", Font.PLAIN, 11));
		btnNewButton_2_1_1_1.setBackground(Color.WHITE);
		btnNewButton_2_1_1_1.setActionCommand("Peony");
		btnNewButton_2_1_1_1.setBounds(225,649, 118, 23);
		panel_1.add(btnNewButton_2_1_1_1);
		btnNewButton_2_1_1_1.addActionListener(add);
		
		JLabel lblNewLabel_22 = new JLabel("New label");
		lblNewLabel_22.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\tulips.jpg"));
		lblNewLabel_22.setBounds(580, 547, 136, 136);
		panel_1.add(lblNewLabel_22);
		JLabel lblNewLabel_24 = new JLabel("Tulip");
		lblNewLabel_24.setFont(new Font("Georgia", Font.BOLD, 18));
		lblNewLabel_24.setBounds(740, 559, 80, 21);
		panel_1.add(lblNewLabel_24);
		JLabel lblNewLabel_25 = new JLabel("(Pack of 12) Yellow Tulips");
		lblNewLabel_25.setFont(new Font("Georgia", Font.ITALIC, 18));
		lblNewLabel_25.setBounds(740, 586, 213, 26);
		panel_1.add(lblNewLabel_25);
		JLabel lblNewLabel_26 = new JLabel("Rs : 200");
		lblNewLabel_26.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNewLabel_26.setBounds(740, 619, 80, 21);
		panel_1.add(lblNewLabel_26);
		JButton btnNewButton_5 = new JButton("Add to Cart");
		btnNewButton_5.setToolTipText("Add Item to Cart");
		btnNewButton_5.setForeground(new Color(0, 0, 0));
		btnNewButton_5.setBackground(new Color(255, 255, 255));
		btnNewButton_5.setFont(new Font("Georgia", Font.PLAIN, 11));
		btnNewButton_5.setBounds(740, 649, 118, 23);
		panel_1.add(btnNewButton_5);
		btnNewButton_5.setActionCommand("Tulip");
		btnNewButton_5.addActionListener(add);
		
		JLabel lblNewLabel_30 = new JLabel("New label");
		lblNewLabel_30.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\carnation.jpg"));
		lblNewLabel_30.setBounds(1095, 545, 136, 136);
		panel_1.add(lblNewLabel_30);
		
		JLabel lblNewLabel_27_1 = new JLabel("Carnation");
		lblNewLabel_27_1.setFont(new Font("Georgia", Font.BOLD, 20));
		lblNewLabel_27_1.setBounds(1255, 559, 129, 21);
		panel_1.add(lblNewLabel_27_1);
		JLabel lblNewLabel_28_1 = new JLabel("(Pack of 5) White Caration");
		lblNewLabel_28_1.setFont(new Font("Georgia", Font.ITALIC, 18));
		lblNewLabel_28_1.setBounds(1255, 586, 260, 26);
		panel_1.add(lblNewLabel_28_1);
		JLabel lblNewLabel_29_1 = new JLabel("Rs : 300");
		lblNewLabel_29_1.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNewLabel_29_1.setBounds(1255, 619, 80, 21);
		panel_1.add(lblNewLabel_29_1);
		JButton btnNewButton_2_1_1 = new JButton("Add to Cart");
		btnNewButton_2_1_1.setForeground(new Color(0, 0, 0));
		btnNewButton_2_1_1.setFont(new Font("Georgia", Font.PLAIN, 11));
		btnNewButton_2_1_1.setBackground(Color.WHITE);
		btnNewButton_2_1_1.setActionCommand("Carnation");
		btnNewButton_2_1_1.setBounds(1255, 649, 118, 23);
		panel_1.add(btnNewButton_2_1_1);
		btnNewButton_2_1_1.addActionListener(add);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\main.jpg"));
		lblNewLabel_4.setBounds(0, 0, 1538, 770);
		panel_1.add(lblNewLabel_4);
		
	}
}
