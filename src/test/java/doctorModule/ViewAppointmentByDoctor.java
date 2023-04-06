package doctorModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class ViewAppointmentByDoctor {
	public void viewAppointment() {
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
//		if (driver.getTitle().equalsIgnoreCase("Hospital Management System"))
//			System.out.println("home page displayed");
//		else
//			System.out.println("home page not displayed");
		driver.findElement(By.xpath(
				"//h3[contains(.,'Patients')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']"))
				.click();
//		String patientLoginPageHeader = driver.findElement(By.xpath("//a[@href='../index.html']")).getText();
//		if (patientLoginPageHeader.equalsIgnoreCase("HMS | Patient Login"))
//			System.out.println("patient login page displayed ");
//		else
//			System.out.println("patient login page not displayed");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(map.get("patientusername"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(map.get("patientpassword"));
		driver.findElement(By.xpath("//button[@name='submit']")).click();
//		String userDashboard = driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
//		if (userDashboard.equalsIgnoreCase("User | Dashboard"))
//			System.out.println("user dashoard is displayed");
//		else
//			System.out.println("user dashboard is not displayed");
		driver.findElement(By.xpath("//span[normalize-space(text())='Book Appointment']")).click();
//		String bookAppointment = driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
//		if (bookAppointment.equalsIgnoreCase("User | Book Appointment"))
//			System.out.println("book appointment page is displayed");
//		else
//			System.out.println("book appointment page is not displayed");
		WebElement docSpecialization = driver.findElement(By.xpath("//select[@name='Doctorspecialization']"));
		Select select = new Select(docSpecialization);
		select.selectByValue(map.get("doctorspecialization"));

		WebElement selectDoctor = driver.findElement(By.xpath("//select[@name='doctor']"));
		select = new Select(selectDoctor);
		select.selectByVisibleText(map.get("doctorname"));

		driver.findElement(By.xpath("//input[@name='appdate']")).click();
		driver.findElement(By.xpath("//td[.='17']")).click();
		WebElement x = driver.findElement(By.xpath("//input[@id='timepicker1']"));
		x.click();
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		String alertMsg = driver.switchTo().alert().getText();
		if (alertMsg.contains("successfully booked"))
			System.out.println("appointment booked successfully");
		else
			System.out.println("appointment not booked successfully");
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		driver.findElement(By.xpath(
				"//h3[contains(.,'Doctors Login')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']"))
				.click();

//		String doctorLoginPageHeader = driver.findElement(By.xpath("//a[@href='../../index.html']")).getText();
//		if (doctorLoginPageHeader.contains("Doctor Login"))
//			System.out.println("doctor login page displayed");
//		else
//			System.out.println("doctor login page not displayed");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(map.get("doctorusername"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(map.get("doctorpassword"));
		driver.findElement(By.xpath("//button[@name='submit']")).click();
//		String doctorDash = driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
//		if (doctorDash.contains("DASHBOARD"))
//			System.out.println("doctor dashboard page is displayed");
//		else
//			System.out.println("doctor dashboard page not displayed");
		driver.findElement(By.xpath("//a[contains(.,'View Appointment History')]")).click();
		String patientName = driver.findElement(By.xpath("//table[@id='sample-table-1']/tbody/tr[last()]/td[@class='hidden-xs']")).getText();
		if(patientName.equalsIgnoreCase("ANNA"))
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
