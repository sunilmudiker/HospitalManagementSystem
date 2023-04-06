package practicePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class readDataFromProperties {
	
	public static void hospitalManagementSystem() {
		
		FileInputStream fis = null;
		Properties property = null;
		try {
			fis = new FileInputStream(".\\src\\test\\resources\\practicePackageResources\\commonData.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(property.getProperty("url"));
		driver.findElement(By.xpath("//input[@name = 'username']")).sendKeys(property.getProperty("username"));
		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys(property.getProperty("password"));
		driver.findElement(By.xpath("//button[@name = 'submit']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
	}
	
	public static void main(String[] args) {
		hospitalManagementSystem();
	}
}
