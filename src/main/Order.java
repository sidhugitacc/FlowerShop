package main;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

public class Order extends JFrame {
	private JPanel contentPane;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    public static final String USER = "root";
    public static final String PASS = "Appu@2023";
    private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order frame = new Order(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void removeAll() {
		try {
			 Connection conn = null;
	         Statement stmt = null;
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         stmt = conn.createStatement();
	         String query = "truncate table orders";
	         stmt.executeUpdate(query);   
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public Order(int id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1650,1080);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
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
			public void mouseClicked(MouseEvent e) {
//				System.out.println(id);
				removeAll();
				Shop obj = new Shop(id);   
				setVisible(false); // Hide current frame
				obj.setVisible(true); //second frame
			
			}
		});

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\shop.png"));
		lblNewLabel.setBounds(14, 6, 65, 65);
		panel.add(lblNewLabel);
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				removeAll();
					Flower obj = new Flower();   
					setVisible(false); // Hide current frame
					obj.setVisible(true); //second frame	
			}
		});
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\logout.png"));
		lblNewLabel_3.setBounds(1460, 6, 65, 65);
		panel.add(lblNewLabel_3);
		JLabel lblNewLabel_13 = new JLabel("Thanks for Shopping");
		lblNewLabel_13.setBounds(533, 133, 546, 68);
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 60));
		contentPane.add(lblNewLabel_13);
		JLabel lblNewLabel_3_1 = new JLabel("Your Order has been Successfully Placed");
		lblNewLabel_3_1.setBounds(529, 205, 546, 68);
		lblNewLabel_3_1.setForeground(new Color(0, 128, 64));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 35));
		contentPane.add(lblNewLabel_3_1);
		JLabel lblNewLabel_5 = new JLabel("YOUR ORDER");
		lblNewLabel_5.setBounds(670, 293, 243, 50);
		lblNewLabel_5.setFont(new Font("Bodoni MT", Font.PLAIN, 25));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_5);
		Connection conn=null;
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(533, 354, 542, 167);
		contentPane.add(scrollPane);
		table = new JTable();
		table.setToolTipText("Order Overview");
		table.setSurrendersFocusOnKeystroke(true);
		table.setBorder(null);
		table.setFont(new Font("Georgia", Font.ITALIC, 14));
		table.getTableHeader().setFont(new Font("Georgia", Font.BOLD, 14));
		table.setRowHeight(25);
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
	    renderer.setHorizontalAlignment( SwingConstants.CENTER );
		DefaultTableModel model =  (DefaultTableModel)table.getModel();
		scrollPane.setViewportView(table);
		float tlt=0;
		try {
            java.sql.Statement stmt = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully");
            stmt = conn.createStatement();
            String query = "select * from orders where ID="+id;
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colName = new String[]{"ITEM","PRICE","COUNT","TOTAL"}; 
            	model.setColumnIdentifiers(colName);
            	String no,name,price,count,t;
            	float total;	
            	while(rs.next()) {
            		no=rs.getString(1);
            		name=rs.getString(2);
            		price = rs.getString(3);
            		count = rs.getString(4);
            		total = Float.parseFloat(rs.getString(3))*Float.parseFloat(rs.getString(4));
            		t=""+total;
            		tlt+=total;
            		String[] row = {name,price,count,t};	
            		model.addRow(row);
            	}
  		}
		catch(Exception e) {
			System.out.println(e);	
		}
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(46, 74, 500, 500);
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\last.gif"));
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_7 = new JLabel("Grand Total:");
		lblNewLabel_7.setFont(new Font("Bodoni MT", Font.ITALIC, 18));
		lblNewLabel_7.setForeground(new Color(0, 0, 0));
		lblNewLabel_7.setBounds(690, 532, 138, 31);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("Rs: "+tlt);
		lblNewLabel_7_1.setForeground(Color.BLACK);
		lblNewLabel_7_1.setFont(new Font("Bodoni MT", Font.BOLD, 18));
		lblNewLabel_7_1.setBounds(800, 532, 138, 31);
		contentPane.add(lblNewLabel_7_1);
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setFont(new Font("Georgia", Font.ITALIC, 12));
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(-5, 74, 1537, 773);
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\last1 (1).png"));
		contentPane.add(lblNewLabel_6);
	}
	void doOrder() {
		try {
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
