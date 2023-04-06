package typesOfExecutions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CrossBrowser {
	@Parameters("browser")
	@Test()
	public void google(String browser) {
		WebDriver driver = null;
		switch(browser) {
		case "chrome" : WebDriverManager.chromedriver().setup();
						driver = new ChromeDriver(); break;
		case "firefox" : WebDriverManager.firefoxdriver().setup();
						driver = new FirefoxDriver(); break;
		default : System.out.println("invalid browser");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
		driver.close();
	}
	
	@Parameters("browser")
	@Test
	public void facebook(String browser) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = null;
		switch(browser) {
		case "chrome" : WebDriverManager.chromedriver().setup();
						driver = new ChromeDriver(); break;
		case "firefox" : WebDriverManager.firefoxdriver().setup();
						driver = new FirefoxDriver(); break;
		default : System.out.println("invalid browser");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		driver.close();
	}
	
}
