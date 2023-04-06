package practicePackage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePicker {
	public static void m1() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disabled-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		driver.findElement(By.xpath("//span[@class='ic_circularclose_grey']")).click();
		driver.findElement(By.xpath("//li[@data-cy='roundTrip']")).click();
		String fromCity = "Mumbai";
		driver.findElement(By.xpath("//input[@id='fromCity']")).sendKeys(fromCity);
		List<WebElement> cityList = driver.findElements(By.xpath("//p[@class='font14 appendBottom5 blackText']"));
		for (WebElement eachCity : cityList) {
			if (eachCity.getText().contains(fromCity)) {
				eachCity.click();
				break;
			}
		}
		String toCity = "Delhi";

		driver.findElement(By.xpath("//input[@id='toCity']")).sendKeys(toCity);
		cityList = driver.findElements(By.xpath("//p[@class='font14 appendBottom5 blackText']"));
		for (WebElement eachCity : cityList) {
			if (eachCity.getText().contains(toCity)) {
				eachCity.click();
				break;
			}
		}
		WebElement prevm = driver.findElement(By.xpath("(//span[@aria-label='Previous Month'])"));
		WebElement nextm = driver.findElement(By.xpath("(//span[@aria-label='Next Month'])"));
		//
		String  fmonth = "March", fday = "12", fyear = "2023";
		String  tmonth = "March", tday = "15",tyear = "2023";
		boolean flag = false;
		
		while (true) {
			List<WebElement> fromdate = driver
					.findElements(By.xpath("//div[contains(@class,'DayPicker-Day') and @aria-disabled='false']"));
			for (int i = 0; i < fromdate.size(); i++) {
				if (fromdate.get(i).getAttribute("aria-label")
						.contains(fmonth.substring(0, 3) + " " + fday + " " + fyear)) {
					fromdate.get(i).click();
					flag = true;
					break;
				}
			}
			for (int i = 0; i < fromdate.size(); i++) {
				if (fromdate.get(i).getAttribute("aria-label")
						.contains(tmonth.substring(0, 3) + " " + tday + " " + tyear)) {
					fromdate.get(i).click();
					flag = true;
					break;
				}
			}
			if (flag == false && nextm.isDisplayed()) {
				nextm.click();
			} else {
				break;
			}
		}
		

	}

	public static void main(String[] args) {
		m1();
	}
}
