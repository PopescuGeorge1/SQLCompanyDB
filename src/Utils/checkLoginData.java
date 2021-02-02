package Utils;



import java.sql.*;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

public class checkLoginData {
public static boolean checkUserPass(String user, String pass) {
		Statement statUser=null;
		ResultSet rsUser = null;
		Statement statPass=null;
		ResultSet rsPass = null;
		boolean userOk = false;
		boolean passOk = false;
		Connection con =null;
		try {
			con = (Connection) ConnectionUtil.conDB();
			String sqlUser = "SELECT emp_user from employees;";
			String sqlPass = "SELECT emp_pass from employees;";
			
			//experiment, use one connection to check 2 inputs
			statUser = con.createStatement();
			rsUser = statUser.executeQuery(sqlUser);
			statPass = con.createStatement();
			rsPass = statPass.executeQuery(sqlPass);
			while(rsUser.next()) {
				if(user.equals(rsUser.getString(1)))
					userOk=true;
			}
			while(rsPass.next()) {
				if(pass.equals(rsPass.getString(1)))
					passOk=true;
			}
			//TODO experiment, use one result set for both statements (in the while loop)
			//otherwise, 2 while loops required
			
		}catch(Exception e) {
			
		}
		if(userOk==true && passOk==true)
			return true;
		else
			return false;
	}
}
