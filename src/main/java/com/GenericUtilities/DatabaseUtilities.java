package com.GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
/**
 * This class has all the reusable methods related to database utility.
 * @author sunil
 *
 */
public class DatabaseUtilities {
	Connection connect ;
	/**
	 * This method is used to connect to database.
	 */
	public void DBConnection() {
		Driver dbdriver = null;
		try {
			dbdriver = new Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			DriverManager.registerDriver(dbdriver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connect = DriverManager.getConnection(IPathConstants.DBURL,IPathConstants.DBUSERNAME, IPathConstants.DBPASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to execute query.
	 * @param Query
	 * @param columnIndex
	 * @param expData
	 * @return
	 * @throws Throwable
	 */
	public String executeQueryAndGetData(String Query, int columnIndex, String expData) throws Throwable {
		Statement state = connect.createStatement();
		ResultSet result = state.executeQuery(Query);
		boolean flag = false;
		while(result.next()) {
			String value =result.getString(columnIndex);
			if(value.equalsIgnoreCase(expData)) {
				flag= true;
				break;
			}
		}
		if(flag) {
			System.out.println("data is matched");
			return expData;
		}	
		else {
			System.out.println("data is not matched");	
			return null;
		}
	}
	/**
	 * This method is used to close the database connection.
	 */
	public void closeDataBase() {
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
