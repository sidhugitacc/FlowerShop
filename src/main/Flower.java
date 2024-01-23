package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class Flower extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Flower frame = new Flower();
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
	public Flower() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize (1650,1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("\" Happiness held is the seed; ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Bodoni MT Condensed", Font.PLAIN, 60));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(319, 147, 925, 82);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Happiness shared is the flower \"");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Bodoni MT Condensed", Font.PLAIN, 60));
		lblNewLabel_3_1.setBounds(416, 208, 737, 82);
		contentPane.add(lblNewLabel_3_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setToolTipText("Go to Shopping Page");
//		btnNewButton.setBorder(new RoundedBorder(20));
		btnNewButton.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Bodoni MT", Font.BOLD, 20));
		btnNewButton.setBounds(806, 310, 115, 40);
		contentPane.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Login obj = new Login();   
			        setVisible(false); // Hide current frame
			        obj.setVisible(true); //second frame
				}
			});
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.setToolTipText("Go to Shopping Page");
		btnSignup.setForeground(Color.BLACK);
		btnSignup.setFont(new Font("Bodoni MT", Font.BOLD, 20));
		btnSignup.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnSignup.setBackground(Color.WHITE);
		btnSignup.setBounds(645, 310, 115, 40);
		contentPane.add(btnSignup);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup obj = new Signup();   
		        setVisible(false); // Hide current frame
		        obj.setVisible(true); //second frame
			}
		});
		
		JButton btnStartShopping = new JButton("Start Shopping");
		//////////
		btnStartShopping.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Shop obj = new Shop(0);   
		        setVisible(false); // Hide current frame
		        obj.setVisible(true); //second frame
			}
		});
		btnStartShopping.setToolTipText("Go to Shopping Page");
		btnStartShopping.setForeground(Color.BLACK);
		btnStartShopping.setFont(new Font("Bodoni MT", Font.BOLD, 20));
		btnStartShopping.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnStartShopping.setBackground(Color.WHITE);
		btnStartShopping.setBounds(704, 373, 167, 40);
		contentPane.add(btnStartShopping);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\sidha\\eclipse-workspace\\FlowerShop\\Images\\main (2).jpg"));
		lblNewLabel.setBounds(0, 73, 1538, 783);
		contentPane.add(lblNewLabel);
		
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
		
	}
}
