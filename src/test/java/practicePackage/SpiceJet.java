package practicePackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpiceJet {
	
	@Test
	public void selectDate() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.spicejet.com/");
		
		String day ="22", month = "March" , year = "2024"; 
		
		driver.findElement(By.xpath("//div[@data-testid='departure-date-dropdown-label-test-id']")).click();
		//going back to get all 13 months WebElements.
		driver.findElement(By.xpath("(//*[name()='svg' and @viewBox='0 0 50 50'])[2]")).click();
		List<WebElement> lists = driver.findElements(By.xpath("//div[contains(@data-testId,'undefined-month')]"));
//		System.out.println(lists.size());
//		for(int i=0; i<lists.size();i++) {
//			System.out.println(lists.get(i).getText());
//		}
//		System.out.println(lists.size());
		//running loop til 13 month.
		for(int i=0; i<lists.size();i++) {
			String s = lists.get(i).getAttribute("data-testid");
			boolean flag = false;
			if(s.contains(month+"-"+year)){
				List<WebElement> date = driver.findElements(By.xpath("//div[@data-testid='undefined-month-"+month+"-"+year+"']/../descendant::div[contains(@class,'r-ubezar r-16dba41')]"));
				for(int j=0; j<date.size();j++) {
					String eachd = date.get(j).getText();
					if(eachd.equals(day)) {
						date.get(j).click();
						flag = true;
						break;
					}
				}
			}
			if(flag)
				break;
		}
	}

}
