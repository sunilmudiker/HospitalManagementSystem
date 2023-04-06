package practicePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadTheDataFromDatabase {
	public static void readData() throws Throwable {
		Connection connect = null;
		try {
		Driver dbdriver = new Driver();
		DriverManager.registerDriver(dbdriver);
		 connect= DriverManager.getConnection("jdbc:mysql://localhost:3306/qspiders","root","root");
		Statement state = connect.createStatement();
		ResultSet result = state.executeQuery("select * from students where sbranch = 'HEBBAL';");
		while(result.next())
		{
			System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3));
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			connect.close();
		}
		
	}
	
	
	public static void nonSelectStatements()
	{
		Connection connect = null;
		try {
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
		
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/qspiders","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement state = null;
		try {
			state = connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			int result = state.executeUpdate("create table classrooms (cid int(3) primary key, cname varchar(10) not null);");
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void actitime() throws Throwable
	{
		String username = null;
		String password = null;
		Connection connect = null;
		try {
		Driver dbdriver = new Driver();
		DriverManager.registerDriver(dbdriver);
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/qspiders","root","root");
		Statement state = connect.createStatement();
		ResultSet result = state.executeQuery("select username, password from actitime where id = 1;");
		while(result.next()) {
			username = result.getString("username");
			password = result.getString("password");	
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			connect.close();
		}
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.actitime.com/login.do");
		driver.findElement(By.xpath("//input[@name = 'username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name = 'pwd']")).sendKeys(password);
		driver.findElement(By.xpath("//a[@id='loginButton']")).click();
		Thread.sleep(2000);
		driver.close();
	}
	
	public static void main(String[] args) throws Throwable {
		actitime();
	}
}
