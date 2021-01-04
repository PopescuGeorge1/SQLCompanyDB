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
	}
}
