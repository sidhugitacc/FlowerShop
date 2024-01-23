package main;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class PreCheck {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    public static final String USER = "root";
    public static final String PASS = "Appu@2023";
    void makeOrder(int id) {
		try {
			int i =JOptionPane.showConfirmDialog (null, "Are you Sure with your Order?",
        	         "Confirmation",
        	         JOptionPane.YES_NO_OPTION);
//        	System.out.println(i);
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
       	    				 if(j==1) {
       	    					q2 = "("+id+",'"+rs.getString(2)+"',"+rs.getString(3)+","+rs.getString(4)+")";	
       	    				 }else{
       	    					q2 = ",("+id+",'"+rs.getString(2)+"',"+rs.getString(3)+","+rs.getString(4)+")";
       	    				 }
       	    				 query+=q2; 
       	    			 	}
        		            q1+=query;
        		            System.out.println(q1);
        		            stmt.executeUpdate(q1);
        		            query = "delete from cart where ID="+id;
        		            stmt.executeUpdate(query);
        		            Order obj = new Order(id); 
//            	        	setVisible(false); // Hide current frame
    				        obj.setVisible(true); //second frame
        	        	}catch(Exception e3){
        	        		System.out.println(e3);
        	        	}
        	        	
        	        }
		}catch(Exception e2) {
			System.out.println(e2);
		}
	}
    void ask(int id) {
    	try {
			Connection conn = null;
            java.sql.Statement stmt = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String ID = ""+id;
            int flag=0;
            String sql1="select * from address";
			 	ResultSet rs = stmt.executeQuery(sql1);
   			while(rs.next()) {
				 String test2 = rs.getString(1);
				 if(test2.equals(ID)) {
					 flag=1;
				 }
			 }
            if(flag==1) {
            	int i =JOptionPane.showConfirmDialog (null, "Address Already Exists, do you want to coutinue with the same address?",
            	         "Confirmation",
            	         JOptionPane.YES_NO_OPTION);
            	System.out.println(i);
            	        if (i == 0) {
            	        	makeOrder(id);
            	        	Order obj = new Order(id); 
//            	        	setVisible(false); // Hide current frame
    				        obj.setVisible(true); //second frame
            	        }
            	        else {
            	        	Delivery obj = new Delivery(id);
            	        	obj.setVisible(true);
            	        }
            	        
            }else {
            	Delivery obj = new Delivery(id);
	        	obj.setVisible(true);
            }
		}catch(Exception e2) {
			System.out.println(e2);
		}
    }
	PreCheck(){
		System.out.println("hi");
	}
}
