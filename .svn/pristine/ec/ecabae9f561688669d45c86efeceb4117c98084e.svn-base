package business.com.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DisDB {
	
	private Connection conn;
	
	public void dbConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://210.113.102.174:5432/test?currentSchema=apfs","mentee","mentee");
			System.out.println("/////////////DBConnection///////");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void dbClose() {
		try {
			conn.close();
			System.out.println("/////////////DBClose///////");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
