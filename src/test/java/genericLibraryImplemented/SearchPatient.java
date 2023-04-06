package genericLibraryImplemented;

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

import com.GenericUtilities.DatabaseUtilities;
import com.GenericUtilities.ExcelUtilities;
import com.GenericUtilities.FileUtilities;
import com.GenericUtilities.JavaUtilities;
import com.GenericUtilities.WebDriverUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchPatient {
	public void searchPatient() {

		//creating object for all generic classes
		DatabaseUtilities dbUtil = new DatabaseUtilities();
		ExcelUtilities excelUtil = new ExcelUtilities();
		FileUtilities fileUtil = new FileUtilities();
		JavaUtilities javaUtil = new JavaUtilities();
		//reading the data from property file
		String URL = fileUtil.readData("url");
		//reading the data from excel file
		excelUtil.initializeExcelFile();
		Map<String, String> map = excelUtil.readMultipleData(0, 1,"searchPatient");
		//launching the browser
		WebDriverUtilities wbUtil = new WebDriverUtilities();
		WebDriver driver=wbUtil.launchBrowser("chrome");
		wbUtil.maximizeBrowser();
		//navigate to the application
		driver.get(URL);
		//login to the application
		driver.findElement(By.xpath("//h3[contains(.,'Doctors Login')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']")).click();
		for (Entry<String, String> set : map.entrySet()) {
			if(set.getKey().equalsIgnoreCase("searchdata"))
				continue;
			else
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}

		driver.findElement(By.xpath("//button[@name='submit']")).click();
		//search the patient
		driver.findElement(By.xpath("//span[normalize-space( text())='Search']")).click();
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
		//verify patient details
		String patientName = driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[1]")).getText();
		if(patientName.equalsIgnoreCase(map.get("searchdata")))
			System.out.println("patient details displayed");
		else
			System.out.println("patient details not displayed");
		//logout as doctor
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		//close workbook
		excelUtil.closeWorkbook();
		//close all active window
		wbUtil.closeAllWindows();
	}
	
	public static void main(String[] args) {
		SearchPatient sp = new SearchPatient();
		sp.searchPatient();
	}
}
