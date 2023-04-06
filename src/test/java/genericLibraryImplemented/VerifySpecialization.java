package genericLibraryImplemented;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifySpecialization {
	/**
	 * 
	 */
	public void verifySpecialization() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("http://rmgtestingserver/domain/Hospital_Management_System/");
		if(driver.getTitle().equalsIgnoreCase("Hospital Management System"))
			System.out.println("home page displayed");
		else
			System.out.println("home page not displayed");
		driver.findElement(By.xpath("//h3[.='Admin Login']/parent::div/descendant::a[.='Click Here']")).click();
		if(driver.getTitle().equalsIgnoreCase("Admin-Login"))
			System.out.println("admin login page displayed");
		else
			System.out.println("admin login page not displayed");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@12345");
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		WebElement title = driver.findElement(By.xpath("//h1[@class='mainTitle']"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(title));
		String header = title.getText();
		if(header.contains("ADMIN"))
			System.out.println("admin page displayed");
		else
			System.out.println("admin page not displayed");
		driver.findElement(By.xpath("//div[@class='item-inner']/span[contains(.,'Doctors')]")).click();
		driver.findElement(By.xpath("//span[normalize-space(text())='Doctor Specialization']")).click();
		String specializationHeader = driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
		if(specializationHeader.contains("SPECIALIZATION"))
			System.out.println("add doctor specialization page is displayed");
		else
			System.out.println("add doctor specialization page is not displayed");
		driver.findElement(By.xpath("//input[@name='doctorspecilization']")).sendKeys("plague doctor");
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		String successMsg =driver.findElement(By.xpath("//p[@style='color:red;']")).getText();
		if(successMsg.contains("successfully"))
			System.out.println("doctor specialization added successfully");
		else
			System.out.println("doctor specialization not added");
		driver.findElement(By.xpath("//div[@class='item-inner']/span[contains(.,'Doctors')]")).click();
		WebElement addDoc = driver.findElement(By.xpath("//span[contains(.,'Add')]"));
		wait.until(ExpectedConditions.visibilityOf(addDoc));
		addDoc.click();
		String addDoctorPageHeader = driver.findElement(By.xpath("//h5[@class='panel-title']")).getText();
		if(addDoctorPageHeader.equalsIgnoreCase("Add Doctor"))
			System.out.println("add doctor page displayed");
		else
			System.out.println("add doctor page not displayed");
		
		WebElement doctorSpecialization = driver.findElement(By.xpath("//select[@name='Doctorspecialization']"));
		Select select = new Select(doctorSpecialization);
		select.selectByValue("plague doctor");
		Random ran = new Random();
		int randomNum=ran.nextInt(500);
		String docName = "sun"+randomNum;
		driver.findElement(By.xpath("//input[@name='docname']")).sendKeys(docName);
		driver.findElement(By.xpath("//textarea[@name='clinicaddress']")).sendKeys("Bangalore");
		driver.findElement(By.xpath("//input[@name='docfees']")).sendKeys("1500");
		driver.findElement(By.xpath("//input[@name='doccontact']")).sendKeys("6363784085");
		String docEmail = "sun"+randomNum+"@gmail.com";
		driver.findElement(By.xpath("//input[@name='docemail']")).sendKeys(docEmail);
		driver.findElement(By.xpath("//input[@name='npass']")).sendKeys("suni@123");
		driver.findElement(By.xpath("//input[@name='cfpass']")).sendKeys("suni@123");
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		String confirmMsg = driver.switchTo().alert().getText();
		if(confirmMsg.contains("Successfully"))
			System.out.println("doctor added successfully");
		else
			System.out.println("doctor not added successfully");
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		
		driver.findElement(By.xpath(
				"//h3[contains(.,'Patients')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']"))
				.click();
		String patientLoginPageHeader = driver.findElement(By.xpath("//a[@href='../index.html']")).getText();
		if (patientLoginPageHeader.equalsIgnoreCase("HMS | Patient Login"))
			System.out.println("patient login page displayed ");
		else
			System.out.println("patient login page not displayed");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("anna@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1234567");
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		String userDashboard = driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
		if (userDashboard.equalsIgnoreCase("User | Dashboard"))
			System.out.println("user dashoard is displayed");
		else
			System.out.println("user dashboard is not displayed");
		driver.findElement(By.xpath("//span[normalize-space(text())='Book Appointment']")).click();
		String bookAppointment = driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
		if (bookAppointment.equalsIgnoreCase("User | Book Appointment"))
			System.out.println("book appointment page is displayed");
		else
			System.out.println("book appointment page is not displayed");
		WebElement docSpecialization = driver.findElement(By.xpath("//select[@name='Doctorspecialization']"));
		select = new Select(docSpecialization);
		select.selectByValue("plague doctor");

		WebElement selectDoctor = driver.findElement(By.xpath("//select[@name='doctor']"));
		select = new Select(selectDoctor);
		select.selectByVisibleText(docName);

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
		
		driver.findElement(By.xpath("//h3[contains(.,'Doctors Login')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']")).click();
		
		String doctorLoginPageHeader = driver.findElement(By.xpath("//a[@href='../../index.html']")).getText();
		if(doctorLoginPageHeader.contains("Doctor Login"))
			System.out.println("doctor login page displayed");
		else
			System.out.println("doctor login page not displayed");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(docEmail);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("suni@123");
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		String doctorDash=driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
		if(doctorDash.contains("DASHBOARD"))
			System.out.println("doctor dashboard page is displayed");
		else
			System.out.println("doctor dashboard page not displayed");
		driver.findElement(By.xpath("//span[normalize-space( text())='Search']")).click();
		String managePatientPage = driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
		if(managePatientPage.equalsIgnoreCase("Doctor | Manage Patients"))
			System.out.println("manage patients page displayed");
		else
			System.out.println("manage patients page not displayed");
		driver.findElement(By.xpath("//input[@id='searchdata']")).sendKeys("ANNA");
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		List<WebElement> patientNames = driver.findElements(By.xpath("//table[@id='sample-table-1']/tbody/tr/td[@class='hidden-xs']"));
		for(int i=0; i<patientNames.size();i++) {
			String patientName = patientNames.get(i).getText();
			if(patientName.equalsIgnoreCase("ANNA")) {
				driver.findElement(By.xpath("//a[contains(@href,'view')]")).click();
				break;
			}	
		}
		driver.findElement(By.xpath("//button[.='Add Medical History']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='modal-body']"))));
		driver.findElement(By.xpath("//input[@name='bp']")).sendKeys("120/80");
		driver.findElement(By.xpath("//input[@name='bs']")).sendKeys("90");
		driver.findElement(By.xpath("//input[@name='weight']")).sendKeys("50");
		driver.findElement(By.xpath("//input[@name='temp']")).sendKeys("97F");
		driver.findElement(By.xpath("//textarea[@name='pres']")).sendKeys("youre normal");
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		String msg = driver.switchTo().alert().getText();
		if(msg.contains("added"))
			System.out.println("added successfully");
		else
			System.out.println("not added ");
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		
		driver.findElement(By.xpath(
				"//h3[contains(.,'Patients')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']"))
				.click();
		patientLoginPageHeader = driver.findElement(By.xpath("//a[@href='../index.html']")).getText();
		if (patientLoginPageHeader.equalsIgnoreCase("HMS | Patient Login"))
			System.out.println("patient login page displayed ");
		else
			System.out.println("patient login page not displayed");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("anna@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1234567");
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		userDashboard = driver.findElement(By.xpath("//h1[@class='mainTitle']")).getText();
		if (userDashboard.equalsIgnoreCase("User | Dashboard"))
			System.out.println("user dashoard is displayed");
		else
			System.out.println("user dashboard is not displayed");
		driver.findElement(By.xpath("//span[normalize-space(text())='Medical History']")).click();
		List<WebElement> username = driver.findElements(By.xpath("//table[@id='sample-table-1']/tbody/tr/td[@class='hidden-xs']"));
		for(int i=0; i<username.size();i++) {
			String nam = username.get(i).getText();
			if(nam.equalsIgnoreCase("ANNA")) {
				driver.findElement(By.xpath("//table[@id='sample-table-1']/tbody/tr/td[@class='hidden-xs']/following-sibling::td[last()]/a")).click();
				break;
			}
		}
		
		String actualUserName = driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[1]")).getText();
		if(actualUserName.equalsIgnoreCase("ANNA"))
			System.out.println("medcial details displayed");
		else
			System.out.println("medicical details not displayed");
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		driver.close();
		
		
	}
	
	public static void main(String[] args) {
		VerifySpecialization vs = new VerifySpecialization();
		vs.verifySpecialization();
	}
}
