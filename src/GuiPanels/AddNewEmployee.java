package GuiPanels;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JPanel;
import javax.activation.DataSource;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class AddNewEmployee {

	private JFrame frame;
	private JTextField first_name;
	private JTextField last_name;
	private JTextField birthday;
	private JTextField sx_field;
	private JTextField salary;
	private JTextField id_field;


	public AddNewEmployee() {
		frame = new JFrame();
		frame.setBounds(200,200,500,300);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(21, 57, 45, 13);
		frame.getContentPane().add(lblNewLabel);
		
		first_name = new JTextField();
		first_name.setBounds(112, 57, 96, 19);
		frame.getContentPane().add(first_name);
		first_name.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(21, 86, 56, 13);
		frame.getContentPane().add(lblLastName);
		
		last_name = new JTextField();
		last_name.setColumns(10);
		last_name.setBounds(112, 86, 96, 19);
		frame.getContentPane().add(last_name);
		
		JLabel lblNewLabel_2 = new JLabel("BirthDay");
		lblNewLabel_2.setBounds(21, 115, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		birthday = new JTextField();
		birthday.setColumns(10);
		birthday.setBounds(112, 115, 96, 19);
		frame.getContentPane().add(birthday);
		
		JLabel sx = new JLabel("Sex");
		sx.setBounds(21, 144, 45, 13);
		frame.getContentPane().add(sx);
		
		sx_field = new JTextField();
		sx_field.setColumns(10);
		sx_field.setBounds(112, 144, 96, 19);
		frame.getContentPane().add(sx_field);
		
		JLabel lblNewLabel_4 = new JLabel("Salary");
		lblNewLabel_4.setBounds(21, 173, 45, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		salary = new JTextField();
		salary.setColumns(10);
		salary.setBounds(112, 173, 96, 19);
		frame.getContentPane().add(salary);
		
		JLabel lblNewLabel_1 = new JLabel("Add new Employee");
		lblNewLabel_1.setBounds(44, 10, 137, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		id_field = new JTextField();
		id_field.setColumns(10);
		id_field.setBounds(112, 33, 96, 19);
		frame.getContentPane().add(id_field);
		
		JLabel id = new JLabel("ID");
		id.setBounds(21, 33, 45, 13);
		frame.getContentPane().add(id);
		ArrayList<String>list = new ArrayList<>();
		list.add("M");list.add("F");
		
		JButton add_data = new JButton("Add Data");
		add_data.setBounds(123, 216, 85, 21);
		add_data.addActionListener(new ActionListener() {
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
				ArrayList<String[]>info = LoginPanel.credentials();
			
				Connection connection = null;
				try {
					System.out.println("Connecting database...");
					connection = DriverManager.getConnection(info.get(0)[0],info.get(1)[0],info.get(2)[0]);
					String SQL1 = "INSERT INTO employees VALUES("
					+id_field.getText()+", '"
					+first_name.getText()+"', '"
					+last_name.getText()+"', '"
					+birthday.getText()+"', '"
					+sx_field.getText()+"', "
					+salary.getText()+", "
					+"102"+", "+"2"
					+");";
					stat = connection.createStatement();
					stat.executeUpdate(SQL1);
					
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
		frame.getContentPane().add(add_data);
		
		frame.setVisible(true);
	}
	
}
