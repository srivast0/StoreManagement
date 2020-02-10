package loginsys;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static Connection con;

	static
	{
		try {
			Class.forName("org.sqlite.JDBC");
			
			con=DriverManager.getConnection("jdbc:sqlite::resource:MStore.db");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getCon()
	{
		return con;
	}

}
