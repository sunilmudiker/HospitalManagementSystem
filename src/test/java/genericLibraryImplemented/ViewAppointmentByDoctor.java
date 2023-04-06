package genericLibraryImplemented;

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
import org.openqa.selenium.support.ui.Select;

import com.GenericUtilities.DatabaseUtilities;
import com.GenericUtilities.ExcelUtilities;
import com.GenericUtilities.FileUtilities;
import com.GenericUtilities.JavaUtilities;
import com.GenericUtilities.WebDriverUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ViewAppointmentByDoctor {
	public void viewAppointment() {
		
		//creating object for all generic classes
				DatabaseUtilities dbUtil = new DatabaseUtilities();
				ExcelUtilities excelUtil = new ExcelUtilities();
				FileUtilities fileUtil = new FileUtilities();
				JavaUtilities javaUtil = new JavaUtilities();
				//reading the data from property file
				String URL = fileUtil.readData("url");
				//reading the data from excel file
				excelUtil.initializeExcelFile();
				Map<String, String> map = excelUtil.readMultipleData(0, 1,"viewAppointment");
				System.out.println(map);
				//launching the browser
				WebDriverUtilities wbUtil = new WebDriverUtilities();
				WebDriver driver=wbUtil.launchBrowser("chrome");
				wbUtil.maximizeBrowser();

		driver.get(URL);

		driver.findElement(By.xpath(
				"//h3[contains(.,'Patients')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']"))
				.click();

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(map.get("patientusername"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(map.get("patientpassword"));
		driver.findElement(By.xpath("//button[@name='submit']")).click();

		driver.findElement(By.xpath("//span[normalize-space(text())='Book Appointment']")).click();

		WebElement docSpecialization = driver.findElement(By.xpath("//select[@name='Doctorspecialization']"));
		//
		wbUtil.selectDropDown(docSpecialization, (map.get("doctorspecialization")));
		WebElement selectDoctor = driver.findElement(By.xpath("//select[@name='doctor']"));
		wbUtil.selectDropDown(map.get("doctorname"), selectDoctor);
		driver.findElement(By.xpath("//input[@name='appdate']")).click();
		String month = "June",date = "25" , year = "2023"; boolean flag = false;
		
		while(true) {
			String monthday = driver.findElement(By.xpath("//div[@class='datepicker-days']/descendant::tr/th[@class='datepicker-switch']")).getText();
			if(monthday.equalsIgnoreCase(month+" "+year)) {
				List<WebElement> day = driver.findElements(By.xpath("//div[@class='datepicker-days']/descendant::tbody/tr/td[@class='day']"));
				for (WebElement eachday : day) {
					String eday = eachday.getText();
					if(eday.contains(date)) {
						eachday.click();
						flag =true;
						break;
					}
				}
			}
			if(flag || !(monthday.contains(year)))
				break;
			else
				driver.findElement(By.xpath("//div[@class='datepicker-days']/descendant::tr/th[@class='next']")).click();	
		}
		WebElement x = driver.findElement(By.xpath("//input[@id='timepicker1']"));
		x.click();
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		String alertMsg = wbUtil.getTextOfAlert();
		if (alertMsg.contains("successfully booked"))
			System.out.println("appointment booked successfully");
		else
			System.out.println("appointment not booked successfully");
		wbUtil.acceptAlert();
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		driver.findElement(By.xpath(
				"//h3[contains(.,'Doctors Login')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']"))
				.click();

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(map.get("doctorusername"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(map.get("doctorpassword"));
		driver.findElement(By.xpath("//button[@name='submit']")).click();

		driver.findElement(By.xpath("//a[contains(.,'View Appointment History')]")).click();
		String patientName = driver.findElement(By.xpath("//table[@id='sample-table-1']/tbody/tr[last()]/td[@class='hidden-xs']")).getText();
		System.out.println(patientName);
		System.out.println(map.get("patientName"));
		if(patientName.contains(map.get("patientName")))
			System.out.println("appointment details displayed successfully");
		else
			System.out.println("appointment details not displayed successfully");
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		driver.close();
	}
	
	public static void main(String[] args) {
		ViewAppointmentByDoctor va = new ViewAppointmentByDoctor();
		va.viewAppointment();
	}
}
