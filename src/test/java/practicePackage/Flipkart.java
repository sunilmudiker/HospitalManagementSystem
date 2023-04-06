package practicePackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {
	public static void m1() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		WebElement searchField = driver.findElement(By.xpath("//input[contains(@title,'Search for products')]"));
		WebElement search = driver.findElement(By.xpath("//*[name()='svg']"));
	
		JavascriptExecutor js = (JavascriptExecutor)driver;		
		js.executeScript("arguments[0].value = arguments[1]",searchField, "mobiles");
		js.executeScript("arguments[0].click;",search);
	}
	
	public static void main(String[] args) {
		m1();
	}
}
