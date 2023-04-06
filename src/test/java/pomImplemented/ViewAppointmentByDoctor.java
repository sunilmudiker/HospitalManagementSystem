package pomImplemented;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.GenericUtilities.DatabaseUtilities;
import com.GenericUtilities.ExcelUtilities;
import com.GenericUtilities.FileUtilities;
import com.GenericUtilities.JavaUtilities;
import com.GenericUtilities.WebDriverUtilities;
import com.ObjectRepository.BookAppointmentPage;
import com.ObjectRepository.DoctorAppointmentPage;
import com.ObjectRepository.DoctorDashboardPage;
import com.ObjectRepository.DoctorLoginPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.PatientDashboardPage;
import com.ObjectRepository.PatientLoginPage;

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
		
		HomePage homePage = new HomePage(driver);
		homePage.clickPatientLogin();
		PatientLoginPage patientLoginPage = new PatientLoginPage(driver);
		patientLoginPage.loginAsPatient(map.get("patientusername"), map.get("patientpassword"));
		PatientDashboardPage patientDashboardPage = new PatientDashboardPage(driver);
		patientDashboardPage.clickBookAppointment();
		
		BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(driver);
		bookAppointmentPage.bookAppointment(wbUtil, map.get("doctorspecialization"), map.get("doctorname"), map.get("month"), map.get("year"), map.get("date"));
		String alertMsg = wbUtil.getTextOfAlert();
		if (alertMsg.contains("successfully booked"))
			System.out.println("appointment booked successfully");
		else
			System.out.println("appointment not booked successfully");
		wbUtil.acceptAlert();
		patientDashboardPage.logoutAsPatient();
		homePage.clickDoctorLogin();
		DoctorLoginPage doctorLoginPage = new DoctorLoginPage(driver);
		doctorLoginPage.doctorLogin(map.get("doctorusername"), map.get("doctorpassword"));
		DoctorDashboardPage doctorDashboardPage = new DoctorDashboardPage(driver);
		doctorDashboardPage.clickMyAppointments();
		DoctorAppointmentPage doctorAppointmentPage = new DoctorAppointmentPage(driver);
		String patientName = doctorAppointmentPage.getPatientName();
		if(patientName.contains(map.get("patientName")))
			System.out.println("appointment details displayed successfully");
		else
			System.out.println("appointment details not displayed successfully");
		doctorDashboardPage.logoutAsDoctor();
		excelUtil.closeWorkbook();
		wbUtil.closeAllWindows();
	}
	
	public static void main(String[] args) {
		ViewAppointmentByDoctor va = new ViewAppointmentByDoctor();
		va.viewAppointment();
	}
}
