package typesOfExecutions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseC {
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuiteSetup() {
		System.out.println("before suite executed");
	}
	@BeforeTest(alwaysRun = true)
	public void beforeTestSetup() {
		System.out.println("before test executed");
	}
	@BeforeClass(alwaysRun = true)
	public void beforeClassSetup() {
		System.out.println("before class executed");
	}
	@BeforeMethod(alwaysRun = true)
	public void beforeMethodSetup() {
		System.out.println("before method executed");
	}
	@AfterMethod(alwaysRun = true)
	public void afterMethodSetup() {
		System.out.println("after method executed");
	}
	@AfterClass(alwaysRun = true)
	public void afterClassSetup() {
		System.out.println("after class executed");
	}
	@AfterTest(alwaysRun = true)
	public void afterTestSetup() {
		System.out.println("after test exceuted");
	}
	@AfterSuite(alwaysRun = true)
	public void afterSuiteSetup() {
		System.out.println("after suite executed");
	}
}
