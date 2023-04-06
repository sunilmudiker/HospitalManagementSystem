package genericLibraryImplemented;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Map.Entry;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.GenericUtilities.DatabaseUtilities;
import com.GenericUtilities.ExcelUtilities;
import com.GenericUtilities.FileUtilities;
import com.GenericUtilities.JavaUtilities;
import com.GenericUtilities.WebDriverUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Query {
	public void verifyQuery() {

		//creating object of generic classes
		DatabaseUtilities dbUtil = new DatabaseUtilities();
		ExcelUtilities excelUtil = new ExcelUtilities();
		FileUtilities fileUtil = new FileUtilities();
		JavaUtilities javaUtil = new JavaUtilities();
		//fetching the data from property file
		String URL = fileUtil.readData("url");
		String USERNAME = fileUtil.readData("username");
		String PASSWORD = fileUtil.readData("password");
		//fetching the data from excel file
		excelUtil.initializeExcelFile();
		Map<String, String> map = excelUtil.readMultipleData(0, 1,"query");
		//launching browser
		WebDriverUtilities wbUtil = new WebDriverUtilities();
		WebDriver driver=wbUtil.launchBrowser("chrome");
		wbUtil.maximizeBrowser();
		//navigating to the application
		driver.get(URL);
		
		driver.findElement(By.xpath("//div[@class='header']/descendant::a[.='contact']")).click();
		//generating random number
		javaUtil.getRandomNum(10000);
		int num = javaUtil.getRandomNum(10000);
		long ranmob = javaUtil.getRandomNum(10000);
		for (Entry<String, String> set : map.entrySet()) {

			if (set.getKey().contains("email")||set.getKey().contains("adminremark"))
				continue;
			else
				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(map.get("email") + num + map.get("email1"));
		driver.findElement(By.xpath("//input[@name='mobileno']")).sendKeys("" + ranmob);
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		//verify submitted successfully
		String s = wbUtil.getTextOfAlert();
		System.out.println(s);
		if(s.contains("succesfully submitted"))
			System.out.println("submitted");
		else
			System.out.println("not submitted");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wbUtil.acceptAlert();
		driver.findElement(By.xpath("//div[@class='header']/descendant::a[.='Home']")).click();
		driver.findElement(By.xpath("//h3[.='Admin Login']/parent::div/descendant::a[.='Click Here']")).click();
		//login as administrator
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		//adding admin remark
		driver.findElement(By.xpath("//p[@class='links cl-effect-1']/a[@href='unread-queries.php']")).click();
		driver.findElement(By.xpath("//table[@id='sample-table-1']/tbody/tr[last()]/td[last()]/div/a")).click();
		driver.findElement(By.xpath("//textarea[@name='adminremark']")).sendKeys(map.get("adminremark"));
		driver.findElement(By.xpath("//button[@name='update']")).click();
		System.out.println(wbUtil.getTextOfAlert());
		wbUtil.acceptAlert();
		//logout as administrator
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		//close workbook
		excelUtil.closeWorkbook();
		//close all active windows
		wbUtil.closeAllWindows();
		
	}

	public static void main(String[] args) {
		Query q = new Query();
		q.verifyQuery();
	}
}
