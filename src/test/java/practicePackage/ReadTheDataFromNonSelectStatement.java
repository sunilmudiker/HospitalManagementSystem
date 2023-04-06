package practicePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.hc.client5.http.impl.classic.MainClientExec;

import com.mysql.cj.jdbc.Driver;

public class ReadTheDataFromNonSelectStatement {
	public static void nonSelectStatement()
	{
		Driver dbdriver = null;
		try {
			dbdriver = new Driver();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DriverManager.registerDriver(dbdriver);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qspiders","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result = 0;
		try {
			result = st.executeUpdate("insert into actitime values(2,'trainee','trainee'),(3,'employee','employee');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result >= 1)
			System.out.println("database updated");
		else
			System.err.println("database not updated");
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		nonSelectStatement();
	}
	
	
	
}
