import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class GUI extends JFrame {

	public GUI() {
		setTitle("CompanyDB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(46, 39, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//establish connection
				String connectionUrl = "jdbc:sqlserver://localhost:3306;"+
				"databaseName=FirstCompanyDB;integratedSecurity=true;";
				
//				declare JDBC objects
				Connection con=null;
				Statement stat = null;
				ResultSet rs = null;
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con=DriverManager.getConnection(connectionUrl);
					
//					create and execute SQL statement that returns data
					String SQL = "SELECT * FROM employees";
					stat = con.createStatement();
					rs=stat.executeQuery(SQL);
					
//					iterate through the data and display it
					while(rs.next())
						System.out.println(rs.getString(1));
					
				}catch(Exception ee) {
					ee.printStackTrace();
				}
			}
		});
			
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Buton2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Statement stat = null;
				ResultSet rs = null;
				
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				String url = "jdbc:mysql://localhost:3306/FirstCompanyDB?autoReconnect=true&useSSL=false";
				String username = "root";
				String password = "password1";
				Connection connection = null;
				try {
					System.out.println("Connecting database...");
					connection = DriverManager.getConnection(url,username,password);
					
					String SQL = "SELECT first_name FROM employees";
					stat = connection.createStatement();
					rs=stat.executeQuery(SQL);
					
//					iterate through the data and display it
					while(rs.next())
						System.out.println(rs.getString(1));
					
				}catch(SQLException ee){
					throw new RuntimeException("Can't connect the database", ee);
				}finally {
					System.out.println("Closing connection.");
					if(connection!=null);
						try {connection.close();}
						catch(SQLException ignore) {}
				}
			}
		});
		btnNewButton_1.setBounds(276, 39, 85, 21);
		getContentPane().add(btnNewButton_1);
	}
}
