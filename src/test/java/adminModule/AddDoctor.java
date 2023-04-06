package adminModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddDoctor {
	
	public void addDoctor() {
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
		Sheet sh = wb.getSheet("Sheet1");
		Map<String, String> map = new HashMap<String, String>();
		
		for(int i=0; i<=sh.getLastRowNum();i++) {
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
		driver.findElement(By.xpath("//h3[.='Admin Login']/parent::div/descendant::a[.='Click Here']")).click();
//		if(driver.getTitle().equalsIgnoreCase("Admin-Login"))
//			System.out.println("admin login page displayed");
//		else
//			System.out.println("admin login page not displayed");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@name='submit']")).click();
//		WebElement title = driver.findElement(By.xpath("//h1[@class='mainTitle']"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOf(title));
//		String header = title.getText();
//		if(header.contains("ADMIN"))
//			System.out.println("admin page displayed");
//		else
//			System.out.println("admin page not displayed");
		driver.findElement(By.xpath("//div[@class='item-inner']/span[contains(.,'Doctors')]")).click();
		WebElement addDoc = driver.findElement(By.xpath("//span[contains(.,'Add')]"));
		wait.until(ExpectedConditions.visibilityOf(addDoc));
		addDoc.click();
//		String addDoctorPageHeader = driver.findElement(By.xpath("//h5[@class='panel-title']")).getText();
//		if(addDoctorPageHeader.equalsIgnoreCase("Add Doctor"))
//			System.out.println("add doctor page displayed");
//		else
//			System.out.println("add doctor page not displayed");
		
		WebElement doctorSpecialization = driver.findElement(By.xpath("//select[@name='Doctorspecialization']"));
		Select select = new Select(doctorSpecialization);
		select.selectByValue("General Physician");
		Random ran = new Random();
		int randomNum=ran.nextInt(500);
		long ranmob = ran.nextInt((int) 1000000000l);
		
		for (Entry<String, String> set : map.entrySet()) {
			if(set.getKey().contains("docname"))
				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue()+randomNum);
			else if(set.getKey().contains("email"))
				continue;
			else
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
//		driver.findElement(By.xpath("//input[@name='docname']")).sendKeys("sun"+randomNum);
//		driver.findElement(By.xpath("//textarea[@name='clinicaddress']")).sendKeys("Bangalore");
//		driver.findElement(By.xpath("//input[@name='docfees']")).sendKeys("1500");
		driver.findElement(By.xpath("//input[@name='doccontact']")).sendKeys(""+ranmob);
		driver.findElement(By.xpath("//input[@name='docemail']")).sendKeys(map.get("email")+randomNum+map.get("email1"));
//		driver.findElement(By.xpath("//input[@name='npass']")).sendKeys("7090800");
//		driver.findElement(By.xpath("//input[@name='cfpass']")).sendKeys("7090800");
		driver.findElement(By.xpath("//button[@id='submit']")).click();
//		String confirmMsg = driver.switchTo().alert().getText();
//		if(confirmMsg.contains("Successfully"))
//			System.out.println("doctor added successfully");
//		else
//			System.out.println("doctor not added successfully");
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
		AddDoctor ad = new AddDoctor();
		ad.addDoctor();
	}
}
