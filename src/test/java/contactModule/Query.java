package contactModule;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class Query {
	public void verifyQuery() {
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

		FileInputStream fi = null;
		try {
			fi = new FileInputStream(".\\src\\test\\resources\\practicePackageResources\\practice.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(fi);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sh = wb.getSheet("query");
		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i <= sh.getLastRowNum(); i++) {
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);
		}

		driver.manage().window().maximize();
		driver.get(URL);
//		if(driver.getTitle().equalsIgnoreCase("Hospital Management System"))
//			System.out.println("home page displayed");
//		else
//			System.out.println("home page not displayed");
		driver.findElement(By.xpath("//div[@class='header']/descendant::a[.='contact']")).click();
//		String pageHeader = driver.findElement(By.xpath("//div[@class='contact-form']")).getText();
//		if(pageHeader.equalsIgnoreCase("Contact Us"))
//			System.out.println("contact us is displayed");
//		else
//			System.out.println("contact us is not displayed");
		Random ran = new Random();
		int num = ran.nextInt(5000);
		long ranmob = ran.nextInt((int) 1000000000l);

		for (Entry<String, String> set : map.entrySet()) {

			if (set.getKey().contains("email"))
				continue;
			else
				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
//		driver.findElement(By.xpath("//input[@name='fullname']")).sendKeys(cname);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(map.get("email") + num + map.get("email1"));
		driver.findElement(By.xpath("//input[@name='mobileno']")).sendKeys("" + ranmob);
//		driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("I am suffering from Eye Sight any Doctor will be Avalble in weekends");
		driver.findElement(By.xpath("//input[@name='submit']")).click();
//		String s = driver.switchTo().alert().getText();
//		if(s.contains("successfully submitted"))
//			System.out.println("submitted");
//		else
//			System.out.println("not submitted");
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("//div[@class='header']/descendant::a[.='Home']")).click();
		driver.findElement(By.xpath("//h3[.='Admin Login']/parent::div/descendant::a[.='Click Here']")).click();
//		if(driver.getTitle().equalsIgnoreCase("Admin-Login"))
//			System.out.println("admin login page displayed");
//		else
//			System.out.println("admin login page not displayed");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@name='submit']")).click();
//		WebElement title = driver.findElement(By.xpath("//h1[@class='mainTitle']"));
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOf(title));
//		String header = title.getText();
//		if(header.contains("ADMIN"))
//			System.out.println("admin page displayed");
//		else
//			System.out.println("admin page not displayed");
		driver.findElement(By.xpath("//p[@class='links cl-effect-1']/a[@href='unread-queries.php']")).click();
		driver.findElement(By.xpath("//table[@id='sample-table-1']/tbody/tr[last()]/td[last()]/div/a")).click();
		driver.findElement(By.xpath("//textarea[@name='adminremark']")).sendKeys("come to hospital");
		driver.findElement(By.xpath("//button[@name='update']")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.close();
		

	}

	public static void main(String[] args) {
		Query q = new Query();
		q.verifyQuery();
	}
}
