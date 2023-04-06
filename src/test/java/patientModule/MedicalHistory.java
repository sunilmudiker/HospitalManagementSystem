package patientModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class MedicalHistory {
	public void medicalHistory() {
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
		Sheet sh = wb.getSheet("viewappointment");
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
		driver.findElement(By.xpath("//h3[contains(.,'Patients')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']")).click();
//		String patientLoginPageHeader = driver.findElement(By.xpath("//a[@href='../index.html']")).getText();
//		if(patientLoginPageHeader.equalsIgnoreCase("HMS | Patient Login"))
//			System.out.println("patient login page displayed ");
//		else
//			System.out.println("patient login page not displayed");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(map.get("patientusername"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(map.get("patientpassword"));
		driver.findElement(By.xpath("//button[@name='submit']")).click();
//		String userDashboard=driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
//		if(userDashboard.equalsIgnoreCase("User | Dashboard"))
//			System.out.println("user dashoard is displayed");
//		else
//			System.out.println("user dashboard is not displayed");
		driver.findElement(By.xpath("//span[normalize-space(text())='Medical History']")).click();
//		String medicalHistory = driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
//		if(medicalHistory.equalsIgnoreCase("Users | Medical History"))
//			System.out.println("medical history page displayed");
//		else
//			System.out.println("medical history page not displayed");
		List<WebElement> patientMH = driver.findElements(By.xpath("//table[@id='sample-table-1']/tbody/tr/td[@class='hidden-xs']"));
		for(int i=0; i<patientMH.size();i++) {
			String patientname = patientMH.get(i).getText();
			if(patientname.contains(map.get("patientname"))) {
				driver.findElement(By.xpath("//a[@href='view-medhistory.php?viewid=250']")).click();
				break;
			}
		}
		
		String verifyPatientName = driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[1]")).getText();
		if(verifyPatientName.equalsIgnoreCase(map.get("patientname")))
			System.out.println("patient medical history displayed");
		else
			System.out.println("patient medical history not displayed");
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		driver.close();
	}
	
	public static void main(String[] args) {
		MedicalHistory mh = new MedicalHistory();
		mh.medicalHistory();
	}
}
