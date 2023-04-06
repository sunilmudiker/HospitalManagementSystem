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

public class MedicalHistory {
	public void medicalHistory() {
		//creating object for generic classes
		DatabaseUtilities dbUtil = new DatabaseUtilities();
		ExcelUtilities excelUtil = new ExcelUtilities();
		FileUtilities fileUtil = new FileUtilities();
		JavaUtilities javaUtil = new JavaUtilities();
		//reading data from property file
		String URL = fileUtil.readData("url");
		//reading data from excel file
		excelUtil.initializeExcelFile();
		Map<String, String> map = excelUtil.readMultipleData(0, 1,"medicalHistory");
		//launching browser
		WebDriverUtilities wbUtil = new WebDriverUtilities();
		WebDriver driver=wbUtil.launchBrowser("chrome");
		wbUtil.maximizeBrowser();
		//navigate to the application
		driver.get(URL);

		driver.findElement(By.xpath("//h3[contains(.,'Patients')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']")).click();
		//login as patient
		for (Entry<String, String> set : map.entrySet()) {
			if(set.getKey().contains("patientname"))
				continue;
			else
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}

		driver.findElement(By.xpath("//button[@name='submit']")).click();

		driver.findElement(By.xpath("//span[normalize-space(text())='Medical History']")).click();

		List<WebElement> patientMH = driver.findElements(By.xpath("//table[@id='sample-table-1']/tbody/tr/td[@class='hidden-xs']"));
		for(int i=0; i<patientMH.size();i++) {
			String patientname = patientMH.get(i).getText();
			if(patientname.contains(map.get("patientname"))) {
				driver.findElement(By.xpath("//table[@id='sample-table-1']/tbody/tr/td[@class='hidden-xs']/following::td[last()]/a")).click();
				break;
			}
		}
		//verify patient medical history
		String verifyPatientName = driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[1]")).getText();
		if(verifyPatientName.equalsIgnoreCase(map.get("patientname")))
			System.out.println("patient medical history displayed");
		else
			System.out.println("patient medical history not displayed");
		//logout as patient
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		//close excel workbook
		excelUtil.closeWorkbook();
		//close all active window
		wbUtil.closeAllWindows();
	}
	
	public static void main(String[] args) {
		MedicalHistory mh = new MedicalHistory();
		mh.medicalHistory();
	}
}
