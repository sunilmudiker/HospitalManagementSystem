package pomImplemented;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.GenericUtilities.DatabaseUtilities;
import com.GenericUtilities.ExcelUtilities;
import com.GenericUtilities.FileUtilities;
import com.GenericUtilities.JavaUtilities;
import com.GenericUtilities.WebDriverUtilities;
import com.ObjectRepository.AddDoctorPage;
import com.ObjectRepository.AdminDashboardPage;
import com.ObjectRepository.AdminLoginPage;
import com.ObjectRepository.BookAppointmentPage;
import com.ObjectRepository.DoctorDashboardPage;
import com.ObjectRepository.DoctorLoginPage;
import com.ObjectRepository.DoctorSpecializationPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.MedicalHistoryPage;
import com.ObjectRepository.PatientDashboardPage;
import com.ObjectRepository.PatientLoginPage;
import com.ObjectRepository.PatientMedicalHistoryPage;
import com.ObjectRepository.SearchPatientPage;

public class VerifySpecialization {
	/**
	 * 
	 */
	public void verifySpecialization() {
		//creating object for all generic classes
		DatabaseUtilities dbUtil = new DatabaseUtilities();
		ExcelUtilities excelUtil = new ExcelUtilities();
		FileUtilities fileUtil = new FileUtilities();
		JavaUtilities javaUtil = new JavaUtilities();
		//reading the data from property file
		String URL = fileUtil.readData("url");
		String USERNAME = fileUtil.readData("username");
		String PASSWORD = fileUtil.readData("password");
		//reading the data from excel file
		excelUtil.initializeExcelFile();
		Map<String, String> map = excelUtil.readMultipleData(0, 1,"verifySpecialization");
		System.out.println(map);
		//launching the browser
		WebDriverUtilities wbUtil = new WebDriverUtilities();
		WebDriver driver=wbUtil.launchBrowser("chrome");
		wbUtil.maximizeBrowser();
		driver.get(URL);
		HomePage homePage = new HomePage(driver);
		homePage.clickAdminLogin();
		AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
		adminLoginPage.login(USERNAME, PASSWORD);
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		adminDashboardPage.clickDoctorTab();
		adminDashboardPage.clickDoctorSpecialization();

		DoctorSpecializationPage doctorSpecializationPage = new DoctorSpecializationPage(driver);
		doctorSpecializationPage.addDoctorSpecialization(map.get("doctorspecilization"));
		adminDashboardPage.clickDoctorTab();
		adminDashboardPage.selectAddDoctor();
		AddDoctorPage addDoctorPage = new AddDoctorPage(driver);
		addDoctorPage.addDoctor(wbUtil, map.get("doctorspecilization"), javaUtil, map.get("docName"), map.get("clinicaddress"), map.get("docfees"), map.get("email"),map.get("npass"), map.get("cfpass"));
		String confirmMsg = wbUtil.getTextOfAlert();

		if(confirmMsg.contains("Successfully"))
			System.out.println("doctor added successfully");
		else
			System.out.println("doctor not added successfully");
		wbUtil.acceptAlert();

		adminDashboardPage.logout();

		homePage.clickPatientLogin();

		PatientLoginPage patientLoginPage = new PatientLoginPage(driver);
		patientLoginPage.loginAsPatient(map.get("patientusername"), map.get("patientpassword"));

		PatientDashboardPage patientDashboardPage = new PatientDashboardPage(driver);
		patientDashboardPage.clickBookAppointment();
		BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(driver);
		bookAppointmentPage.bookAppointment(wbUtil, map.get("doctorspecilization"), addDoctorPage.getdoctName(), map.get("month"), map.get("year"), map.get("date"));
		String alertMsg = wbUtil.getTextOfAlert();

		if (alertMsg.contains("successfully booked"))
			System.out.println("appointment booked successfully");
		else
			System.out.println("appointment not booked successfully");
		wbUtil.acceptAlert();
		patientDashboardPage.logoutAsPatient();
		
		homePage.clickDoctorLogin();
		DoctorLoginPage doctorLoginPage = new DoctorLoginPage(driver);
		doctorLoginPage.doctorLogin(addDoctorPage.getdocEmail(), map.get("npass"));

		DoctorDashboardPage doctorDashboardPage = new DoctorDashboardPage(driver);
		doctorDashboardPage.clickSearchTab();
		SearchPatientPage searchPatientPage = new SearchPatientPage(driver);
		searchPatientPage.searchPatient(map.get("patientname"));
		
		PatientMedicalHistoryPage patientMedicalHistoryPage = new PatientMedicalHistoryPage(driver);
		patientMedicalHistoryPage.addMedicalHistory(wbUtil, map.get("bp"), map.get("sugar"), map.get("weight"), map.get("temp"), map.get("prescription"));
		String msg = wbUtil.getTextOfAlert();

		if(msg.contains("added"))
			System.out.println("added successfully");
		else
			System.out.println("not added ");
		wbUtil.acceptAlert();
		doctorDashboardPage.logoutAsDoctor();

		homePage.clickPatientLogin();

		patientLoginPage.loginAsPatient(map.get("patientusername"), map.get("patientpassword"));

		patientDashboardPage.clickMedicalHistoryTab();
		MedicalHistoryPage medicalHistoryPage = new MedicalHistoryPage(driver);
		medicalHistoryPage.clickViewMedicalHistory(map.get("patientname"));

		excelUtil.closeWorkbook();
		wbUtil.closeAllWindows();

		
		
	}
	
	public static void main(String[] args) {
		VerifySpecialization vs = new VerifySpecialization();
		vs.verifySpecialization();
	}
}
