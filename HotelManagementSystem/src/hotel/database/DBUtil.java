package hotel.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String username = "root"; 
	private static final String password ="12345";
	private static final String dbUrl = "jdbc:mysql://localhost:3306/hotel"; //path to connect to mysql
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public static void showErrorMessage (SQLException e){
		System.err.println("Error: " + e.getMessage());
		System.err.println("Error Code: " + e.getErrorCode());
	}
}
