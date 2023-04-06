package adminModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AdminLogin {
	
	public void login() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(".\\src\\test\\resources\\practicePackageResources\\commonData.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String URL = property.getProperty("url");
		String USERNAME = property.getProperty("username");
		String PASSWORD = property.getProperty("password");
		
		driver.manage().window().maximize();
		driver.get(URL);
//		if(driver.getTitle().equalsIgnoreCase("Hospital Management System"))
//			System.out.println("home page displayed");
//		else
//			System.out.println("home page not displayed");
		driver.findElement(By.xpath("//h3[.='Admin Login']/parent::div/descendant::a[.='Click Here']")).click();
//		if(driver.getTitle().equalsIgnoreCase("Admin-Login"))
//			System.out.println("admin login page displayed");
//		else
//			System.out.println("admin login page not displayed");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		WebElement title = driver.findElement(By.xpath("//h1[@class='mainTitle']"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(title));
//		String header = title.getText();
//		if(header.contains("ADMIN"))
//			System.out.println("admin page displayed");
//		else
//			System.out.println("admin page not displayed");
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		driver.close();
		
	}
	
	public static void main(String[] args) {
		AdminLogin al = new AdminLogin();
		al.login();
	}
}
