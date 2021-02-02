package Utils;

import java.sql.*;

public class createNewAccount {
	public static boolean createAccount(String user, String pass) {
		Statement stat=null;
		ResultSet rs=null;
		Connection con = null;
		user='"'+user+'"';
		pass='"'+pass+'"';
		try {
			con = (Connection) ConnectionUtil.conDB();
			String SQL = "INSERT INTO employees (emp_user,emp_pass) VALUES ("+user+", "+pass+");";
			stat=con.createStatement();
//			rs = stat.executeUpdate(SQL);
			
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
