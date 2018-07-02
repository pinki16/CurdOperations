package info.inetsolv.curdoperations.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {

	private static String uname = "jdbc";
	private static String pwd = "jdbc123";
	private static String url = "jdbc:oracle:thin:@localhost:1521/XE";
	private static String driverClassName = "oracle.jdbc.driver.OracleDriver";

	public static Connection getConnection() {
		Connection con = null;
		try {

			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, uname, pwd);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

}
