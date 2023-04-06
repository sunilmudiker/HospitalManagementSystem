package pomImplemented;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.GenericUtilities.DatabaseUtilities;
import com.GenericUtilities.ExcelUtilities;
import com.GenericUtilities.FileUtilities;
import com.GenericUtilities.JavaUtilities;
import com.GenericUtilities.WebDriverUtilities;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.MedicalHistoryPage;
import com.ObjectRepository.PatientDashboardPage;
import com.ObjectRepository.PatientLoginPage;
import com.ObjectRepository.PatientMedicalHistoryPage;

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
		HomePage homePage = new HomePage(driver);
		homePage.clickPatientLogin();
		//login as patient
		PatientLoginPage patientLoginPage = new PatientLoginPage(driver);
		patientLoginPage.loginAsPatient(map.get("username"), map.get("password"));
		PatientDashboardPage patientDashboardPage = new PatientDashboardPage(driver);
		patientDashboardPage.clickMedicalHistoryTab();
		MedicalHistoryPage medicalHistoryPage = new MedicalHistoryPage(driver);
		medicalHistoryPage.clickViewMedicalHistory(map.get("patientname"));
		//verify patient medical history
		PatientMedicalHistoryPage patientMedicalHistoryPage = new PatientMedicalHistoryPage(driver);
		String verifyPatientName = patientMedicalHistoryPage.getPatientName();
		if(verifyPatientName.equalsIgnoreCase(map.get("patientname")))
			System.out.println("patient medical history displayed");
		else
			System.out.println("patient medical history not displayed");
		//logout as patient
		patientDashboardPage.logoutAsPatient();
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
