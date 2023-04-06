package practicePackage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGAnnotations {

	@BeforeSuite
	public void connectToDataBase() {
		System.out.println("connected to database");
	}
	
	@AfterSuite
	public void disconnectFromDataBase() {
		System.out.println("disconnected from database");
	}
	
	@BeforeTest
	public void parallelExecution() {
		System.out.println("parallel execution of testscripts");
	}
	
	@AfterTest
	public void nothingToDo() {
		System.out.println("nothing to do here");
	}
	
	@BeforeClass
	public void launchBrowser() {
		System.out.println("launch the browser");
	}
	
	@AfterClass
	public void closeBrowser() {
		System.out.println("close the browser");
	}
	
	@BeforeMethod
	public void loginToApp() {
		System.out.println("login to the app");
	}
	
	@AfterMethod
	public void logoutFromApp() {
		System.out.println("logout from app");
	}
	
	@Test
	public void executeTestscript_1() {
		System.out.println("execution of testscript 1");
	}
	
	@Test
	public void executeTestscript_2() {
		System.out.println("execution of testscript 2");
	}
}
