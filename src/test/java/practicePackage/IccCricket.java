package practicePackage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IccCricket {
	public static void m1() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.icc-cricket.com/rankings/mens/team-rankings/t20i");
		List<WebElement> pos = driver.findElements(By.xpath("//tbody/tr/td[1]"));
		List<WebElement> teams = driver.findElements(By.xpath("//tbody/tr/descendant::span[@class='u-hide-phablet']"));
		List<WebElement> matches = driver.findElements(By.xpath("//tbody/tr/td[3]"));
		List<WebElement> points = driver.findElements(By.xpath("//tbody/tr/td[4]"));
		List<WebElement> rating = driver.findElements(By.xpath("//tbody/tr/td[5]"));
		
		for(int i=0; i<pos.size();i++)
		{
			System.out.println(pos.get(i).getText()+ " "+ teams.get(i).getText() + " "+matches.get(i).getText() + " "+ points.get(i).getText()+ " "+rating.get(i).getText());
		}
		
		driver.close();
		
	}
	
	
	public static void m2() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.icc-cricket.com/rankings/mens/team-rankings/t20i");
		List<WebElement> teamnames = driver.findElements(By.xpath("//tbody/tr/td[2]"));
		ArrayList<String> myteams = new ArrayList<String>();
		for (WebElement myteam : teamnames) {
			myteams.add(myteam.getText());
		}
		
		for(int i=0; i<myteams.size();i++)
		{
			String rating = driver.findElement(By.xpath("//span[.='"+myteams.get(i)+"']/parent::td/following-sibling::td[contains(@class,'rating')]")).getText();
			System.out.println("teams -> "+myteams.get(i)+" ratings -> "+rating);
		}
	}
	
	public static void m3() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.icc-cricket.com/rankings/mens/team-rankings/t20i");
		String team = "England";
		String rating = driver.findElement(By.xpath("//span[.='"+team+"']/parent::td/preceding::td[@class='table-body__cell table-body__cell--position u-text-right']")).getText();
		System.out.println("Rating -> "+rating);
	}
	
	public static void main(String[] args) {
		m3();
	}
}
