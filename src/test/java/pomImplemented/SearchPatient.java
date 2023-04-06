package pomImplemented;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.GenericUtilities.DatabaseUtilities;
import com.GenericUtilities.ExcelUtilities;
import com.GenericUtilities.FileUtilities;
import com.GenericUtilities.JavaUtilities;
import com.GenericUtilities.WebDriverUtilities;
import com.ObjectRepository.DoctorDashboardPage;
import com.ObjectRepository.DoctorLoginPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.PatientDetailsPage;
import com.ObjectRepository.SearchPatientPage;

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
		HomePage homePage = new HomePage(driver);
		homePage.clickDoctorLogin();
		
		DoctorLoginPage doctorLoginPage = new DoctorLoginPage(driver);
		doctorLoginPage.doctorLogin(map.get("username"), map.get("password"));
		//search the patient
		DoctorDashboardPage doctorDashboardPage = new DoctorDashboardPage(driver);
		doctorDashboardPage.clickSearchTab();
		SearchPatientPage searchPatientPage = new SearchPatientPage(driver);
		searchPatientPage.searchPatient(map.get("searchdata"));
		//verify patient details
		PatientDetailsPage  patientDetailsPage = new PatientDetailsPage(driver);
		String patientName=patientDetailsPage.getPatientName();
		if(patientName.equalsIgnoreCase(map.get("searchdata")))
			System.out.println("patient details displayed");
		else
			System.out.println("patient details not displayed");
		//logout as doctor
		doctorDashboardPage.logoutAsDoctor();
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
