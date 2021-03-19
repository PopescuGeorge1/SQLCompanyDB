package Utils;

import java.sql.*;

public class createNewAccount {
	public static boolean createAccount(String user, String pass, String firstName, String lastName) {
		Statement stat=null;
		Connection con = null;
		firstName='"'+firstName+'"';
		lastName='"'+lastName+'"';
		user='"'+user+'"';
		pass='"'+pass+'"';
		try {
			con = (Connection) ConnectionUtil.conDB();
			String SQL = "INSERT INTO employees (emp_user,emp_pass,emp_firstname,emp_lastname) "
					+ "VALUES ("+user+", "+pass+", "+firstName+", "+lastName+");";
			stat=con.createStatement();
			if(stat.executeUpdate(SQL)>0)
				return true;
			else
				return false;
		}catch(Exception e) {
			e.getCause();
			e.printStackTrace();
			return false;
		}
	}
}
