package practicePackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.GenericUtilities.ListenersImplementationClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultipleScreenshot {

	@Test
	public void multiScreen() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
		SoftAssert sa = new SoftAssert();
		sa.fail();
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("sunil");
		sa.fail();
		sa.assertAll();
	}
}
