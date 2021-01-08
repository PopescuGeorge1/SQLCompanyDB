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



import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {

public static void main(String[] args) {
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GUI() {
		setTitle("CompanyDB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		
		JButton btnNewButton_1 = new JButton("GetInfo");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
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
				
				//DataBase info
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
		
		
		JButton btnNewButton = new JButton("Add new employee");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
						new AddNewEmployee();
					
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		
		btnNewButton.setBounds(30, 52, 155, 21);
		getContentPane().add(btnNewButton);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
