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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Cart extends JFrame {
	
	private JPanel contentPane;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    public static final String USER = "root";
    public static final String PASS = "Appu@2023";
    private JButton btn_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cart frame = new Cart(0);
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

	public Cart(int id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize (1650,1080);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ActionListener removeItem = new ActionListener() {
	         public void actionPerformed(ActionEvent event) {
	        	 int count=0;
	        	 try {
		        	 System.out.println(event.getActionCommand());	
		        	 Connection conn = null;
		        	 Statement stmt = null;
		        	 String query="";
		        	 Class.forName("com.mysql.cj.jdbc.Driver");
		        	 conn = DriverManager.getConnection(DB_URL, USER, PASS);
		        	 stmt = conn.createStatement();
		        	 String name = event.getActionCommand();
		        	 stmt = conn.createStatement();
		        	 String sql2="select * from cart";
		        	 ResultSet rs = stmt.executeQuery(sql2);
		        	 String x=""+id;
		        	 int y=0;
		        	 while(rs.next()) {
		        		 String test = rs.getString(2);
        				 String test2 = rs.getString(1);
          				 if(test2.equals(x) && test.equals(name)) {
			        		 y=Integer.parseInt(rs.getString(4));
			        		 break;
          				 }
		        	 }
			        		 System.out.println(name+"-"+id);
			        		 if(y>1) {
			        			 --y;
			        			 query = "update cart set count="+y+" where item='"+name+"' and ID="+id;
			        		 }else {
			        			 query = "delete from cart where item = '"+name+"' and ID="+id;
			        		 }
			        		 stmt.executeUpdate(query);
		        		 Cart obj = new Cart(id);   
					        setVisible(false); // Hide current frame
					        obj.setVisible(true); //second frame
					      
		        	 } 
	        	 catch (Exception e1) {
	        	     System.out.println(e1);
	        	 }
	        }
	    };	 
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

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Shop obj = new Shop(id);   
		        setVisible(false); // Hide current frame
		        obj.setVisible(true); //second frame
			}
		});
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\shop.png"));
		lblNewLabel_3.setBounds(1460, 6, 65, 65);
		panel.add(lblNewLabel_3);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 74, 1538, 781);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		JLabel lblNewLabel1 = new JLabel("CART ITEMS");
		lblNewLabel1.setForeground(new Color(255, 255, 255));
		lblNewLabel1.setFont(new Font("Georgia", Font.PLAIN, 40));
		lblNewLabel1.setBounds(30, 11, 305, 61);
		panel_1.add(lblNewLabel1);
		
		JLabel lblNewLabel_11 = new JLabel("ITEMS");
		lblNewLabel_11.setFont(new Font("Georgia", Font.BOLD, 20));
		lblNewLabel_11.setBounds(494, 55, 87, 24);
		panel_1.add(lblNewLabel_11);
		
		JLabel lblNewLabel_31 = new JLabel("COUNT");
		lblNewLabel_31.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_31.setFont(new Font("Georgia", Font.BOLD, 20));
		lblNewLabel_31.setBounds(646, 55, 87, 24);
		panel_1.add(lblNewLabel_31);
		
		JLabel lblNewLabel_4 = new JLabel("PRICE");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Georgia", Font.BOLD, 20));
		lblNewLabel_4.setBounds(803, 55, 87, 24);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("TOTAL");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Georgia", Font.BOLD, 20));
		lblNewLabel_5.setBounds(948, 55, 87, 24);
		panel_1.add(lblNewLabel_5);
		JLabel lbl;
		JButton btn = null;
		float total=0;
		int y=100;
		try {
			Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql2="select * from cart";
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next()) {
            	if(id==Integer.parseInt(rs.getString(1))){
            		lbl = new JLabel(rs.getString(2));
            		lbl.setBounds(494, y, 87, 24);
            		lbl.setFont(new Font("Georgia", Font.PLAIN, 18));
            		panel_1.add(lbl);
            		lbl = new JLabel(rs.getString(4));
            		lbl.setHorizontalAlignment(SwingConstants.CENTER);
            		lbl.setBounds(646, y, 84, 24);
            		lbl.setFont(new Font("Georgia", Font.PLAIN, 18));
            		panel_1.add(lbl);
            		lbl = new JLabel("Rs: "+rs.getString(3));
            		lbl.setBounds(813, y, 84, 24);
            		lbl.setFont(new Font("Georgia", Font.PLAIN, 18));
            		panel_1.add(lbl);
            		float cost=Integer.parseInt(rs.getString(3))*Integer.parseInt(rs.getString(4));
            		total+=cost;
            		lbl = new JLabel("Rs. "+cost);
            		lbl.setBounds(955, y, 100, 24);
            		lbl.setFont(new Font("Georgia", Font.PLAIN, 18));
            		panel_1.add(lbl);
            		btn = new JButton("remove");
            		btn.setBounds(1100, y, 75, 23);
            		panel_1.add(btn);
            		btn.setToolTipText("Remove Item");
            		btn.setBackground(new Color(255, 255, 255));
            		btn.setFont(new Font("Georgia", Font.PLAIN, 11));
            		btn.setForeground(new Color(0, 0, 0));
            		btn.setActionCommand(rs.getString(2));
            		btn.addActionListener(removeItem);
            		y+=35;
            		
            	}
            }
		}catch(Exception e) {
			System.out.println(e);
		}
		y+=10;
		lbl = new JLabel("TOTAL");
		lbl.setBounds(494, y, 84, 24);
		lbl.setFont(new Font("Georgia", Font.BOLD, 18));
		panel_1.add(lbl);
		lbl = new JLabel("Rs. "+total);
		lbl.setBounds(955, y, 170, 24);
		lbl.setFont(new Font("Georgia", Font.PLAIN, 18));
		panel_1.add(lbl);
		y+=50;
		
		btn_1 = new JButton("BUY NOW");
		btn_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int count=0;
				try {	
      			 	 
	        		 Connection conn = null;
	        		 Statement stmt = null;
	        		 Class.forName("com.mysql.cj.jdbc.Driver");
	        		 conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        		 stmt = conn.createStatement();
			         String sql1="select * from cart where ID="+id;
			         ResultSet rs = stmt.executeQuery(sql1);
			         while(rs.next()) {
			        	 count++;
			         }
	        		} catch (Exception e1) {
      		 System.out.println(e1);
      	 }
				if(count==0) {
					JOptionPane.showMessageDialog(null, "CART IS Empty", "Alert", JOptionPane.ERROR_MESSAGE);
					Shop obj = new Shop(id);   
					setVisible(false); // Hide current frame
					obj.setVisible(true); //second frame
				}else {
					PreCheck obj = new PreCheck();   
					obj.ask(id);
				}
			}
		});
		btn_1.setBounds(732, y, 125, 31);
		panel_1.add(btn_1);
		btn_1.setToolTipText("Buy your Products");
		btn_1.setBackground(new Color(255, 255, 255));
		btn_1.setFont(new Font("Georgia", Font.BOLD, 15));
		btn_1.setForeground(new Color(0, 0, 0));
				
		JLabel lblNewLabel_41 = new JLabel("New label");
		lblNewLabel_41.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\cart1 (1).png"));
		lblNewLabel_41.setBounds(0, 0, 1538, 770);
		panel_1.add(lblNewLabel_41);
	}
}
