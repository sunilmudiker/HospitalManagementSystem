package typesOfExecutions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BC {
	public WebDriver driver;

	@BeforeSuite
	public void bs() {
		System.out.println("bs executed");
	}
	@BeforeTest
	public void bt() {
		System.out.println("bt executed");
	}
	@Parameters("browser")
	@BeforeClass
	public void bc(String browser) {
		System.out.println("bc executed");
		switch(browser) {
		case "chrome": WebDriverManager.chromedriver().setup();
						driver = new ChromeDriver(); break;
		case "firefox": WebDriverManager.firefoxdriver().setup();
						driver = new FirefoxDriver();break;
		default : System.out.println("invalid browser");
		}
		driver.get("https://demo.actitime.com/login.do");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@BeforeMethod
	public void bm() {
		System.out.println("bm executed");
		
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("pwd")).sendKeys("manager");
		driver.findElement(By.id("loginButton")).click();
		
	}
	@AfterMethod
	public void am() {
		System.out.println("am executed");
		WebElement ele = driver.findElement(By.id("logoutLink"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(ele));
		ele.click();
		
	}
	@AfterClass
	public void ac() {
		System.out.println("ac executed");
		driver.close();
	}
	@AfterTest
	public void at() {
		System.out.println("at executed");
	}
	@AfterSuite
	public void as() {
		System.out.println("as executed");
	}
}
