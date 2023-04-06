package doctorModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchPatient {
	public void searchPatient() {
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
		Sheet sh = wb.getSheet("searchPatient");
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
		driver.findElement(By.xpath("//h3[contains(.,'Doctors Login')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']")).click();
		
//		String doctorLoginPageHeader = driver.findElement(By.xpath("//a[@href='../../index.html']")).getText();
//		if(doctorLoginPageHeader.contains("Doctor Login"))
//			System.out.println("doctor login page displayed");
//		else
//			System.out.println("doctor login page not displayed");
		for (Entry<String, String> set : map.entrySet()) {
			if(set.getKey().equalsIgnoreCase("searchdata"))
				continue;
			else
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
//		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("test@demo.com");
//		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//button[@name='submit']")).click();
//		String doctorDash=driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
//		if(doctorDash.contains("DASHBOARD"))
//			System.out.println("doctor dashboard page is displayed");
//		else
//			System.out.println("doctor dashboard page not displayed");
		driver.findElement(By.xpath("//span[normalize-space( text())='Search']")).click();
//		String managePatientPage = driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
//		if(managePatientPage.equalsIgnoreCase("Doctor | Manage Patients"))
//			System.out.println("manage patients page displayed");
//		else
//			System.out.println("manage patients page not displayed");
		driver.findElement(By.xpath("//input[@id='searchdata']")).sendKeys(map.get("searchdata"));
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		List<WebElement> patientNames = driver.findElements(By.xpath("//table[@id='sample-table-1']/tbody/tr/td[@class='hidden-xs']"));
		for(int i=0; i<patientNames.size();i++) {
			String patientName = patientNames.get(i).getText();
			if(patientName.equalsIgnoreCase(map.get("searchdata"))) {
				driver.findElement(By.xpath("//a[@href='view-patient.php?viewid=251']")).click();
				break;
			}	
		}
		String patientName = driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[1]")).getText();
		if(patientName.equalsIgnoreCase(map.get("searchdata")))
			System.out.println("patient details displayed");
		else
			System.out.println("patient details not displayed");
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
		SearchPatient sp = new SearchPatient();
		sp.searchPatient();
	}
}
