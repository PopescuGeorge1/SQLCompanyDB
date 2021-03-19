package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Home.person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConnectionUtil {
	Connection conn = null;
	public static Connection conDB()
	{
		try {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con=DriverManager.getConnection(
				 credentialsDB.credentials().get(0)[0],
				 credentialsDB.credentials().get(1)[0],
				 credentialsDB.credentials().get(2)[0]
						 );
		 return con;
		}catch(ClassNotFoundException e) {
			return null;
		}catch(SQLException e) {
			return null;
		}
	}
	
	public static ObservableList<person> getData() {
		ObservableList<person> datalist = FXCollections.observableArrayList();
		Connection conn = conDB();
		Statement id_stat = null;
		Statement fname_stat = null;
		Statement lname_stat = null;
		Statement birthday_stat = null;
		Statement salary_stat = null;
		Statement user_stat = null;
		Statement pass_stat = null;
		Statement branch_stat = null;
		Statement supervisor_stat = null;

		
		ResultSet id_rs = null;
		ResultSet fname_rs = null;
		ResultSet lname_rs = null;
		ResultSet birthday_rs = null;
		ResultSet salary_rs = null;
		ResultSet user_rs = null;
		ResultSet pass_rs = null;
		ResultSet branch_rs = null;
		ResultSet supervisor_rs = null;
		person p=null;
		try {
			conn=ConnectionUtil.conDB();
			String id_SQL = "Select emp_id from employees;";
			String fname_SQL = "Select emp_firstname from employees;";
			String lname_SQL = "Select emp_lastname from employees;";
			String birthday_SQL = "Select birthday from employees;";
			String salary_SQL = "Select salary from employees;";
			String user_SQL = "Select emp_user from employees;";
			String pass_SQL = "Select emp_pass from employees;";
			String branch_SQL = "Select branch_id from employees;";
			String supervisor_SQL = "Select super_id from employees;";
			//attention, only strings are returned, modify table columns
			id_stat=(Statement) conn.createStatement();
			id_rs=id_stat.executeQuery(id_SQL);
			fname_stat=(Statement) conn.createStatement();
			fname_rs=fname_stat.executeQuery(fname_SQL);
			lname_stat=(Statement) conn.createStatement();
			lname_rs=lname_stat.executeQuery(lname_SQL);
			birthday_stat=(Statement) conn.createStatement();
			birthday_rs=birthday_stat.executeQuery(birthday_SQL);
			salary_stat=(Statement) conn.createStatement();
			salary_rs=salary_stat.executeQuery(salary_SQL);
			user_stat = (Statement) conn.createStatement();
			user_rs = user_stat.executeQuery(user_SQL);
			pass_stat = (Statement) conn.createStatement();
			pass_rs = pass_stat.executeQuery(pass_SQL);
			branch_stat = (Statement) conn.createStatement();
			branch_rs = branch_stat.executeQuery(branch_SQL);
			supervisor_stat = (Statement) conn.createStatement();
			supervisor_rs = supervisor_stat.executeQuery(supervisor_SQL);
			while (id_rs.next()) {
				fname_rs.next();
				lname_rs.next();
				birthday_rs.next();
				salary_rs.next();
				user_rs.next();
				pass_rs.next();
				branch_rs.next();
				supervisor_rs.next();
				datalist.add(new person(
								checkNullString(id_rs.getString(1))
								,checkNullString(fname_rs.getString(1))
								,checkNullString(lname_rs.getString(1))
								,checkNullString(birthday_rs.getString(1))
								,checkNullString(salary_rs.getString(1))
								,checkNullString(user_rs.getString(1))
								,checkNullString(pass_rs.getString(1))
								,checkNullString(branch_rs.getString(1))
								,checkNullString(supervisor_rs.getString(1))
								));
				//problem: user and rest of data are mixed
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return datalist;
		
	}
	
	private static String checkNullString(String s) {
		if(s==null)
			return "null";
		else
			return s;
	}
}
