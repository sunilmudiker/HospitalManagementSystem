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

public class AppointmentCancellation {
	public void cancelAppointment() {

		//creating object of all generic classes
		DatabaseUtilities dbUtil = new DatabaseUtilities();
		ExcelUtilities excelUtil = new ExcelUtilities();
		FileUtilities fileUtil = new FileUtilities();
		JavaUtilities javaUtil = new JavaUtilities();
		//reading data from property file
		String URL = fileUtil.readData("url");
		//reading data from excel file
		excelUtil.initializeExcelFile();
		Map<String, String> map = excelUtil.readMultipleData(0, 1,"appointmentCancel");
		//launching browser
		WebDriverUtilities wbUtil = new WebDriverUtilities();
		WebDriver driver=wbUtil.launchBrowser("chrome");
		wbUtil.maximizeBrowser();
		//navigating to the application
		driver.get(URL);
		HomePage homePage = new HomePage(driver);
		homePage.clickPatientLogin();
		//login as patient
		PatientLoginPage patientLoginPage = new PatientLoginPage(driver);
		patientLoginPage.loginAsPatient(map.get("patientusername"), map.get("patientpassword"));
		//book appointment
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
		//logout as patient.
		patientDashboardPage.logoutAsPatient();
		homePage.clickDoctorLogin();
		//login as doctor.
		DoctorLoginPage doctorLoginPage = new DoctorLoginPage(driver);
		doctorLoginPage.doctorLogin(map.get("doctorusername"), map.get("doctorpassword"));
		//Canceling appointment.
		DoctorDashboardPage doctorDashboardPage = new DoctorDashboardPage(driver);
		doctorDashboardPage.clickMyAppointments();
		DoctorAppointmentPage doctorAppointmentPage = new DoctorAppointmentPage(driver);
		doctorAppointmentPage.cancelAppointment();
		wbUtil.acceptAlert();
		String cancelMsg = doctorAppointmentPage.getConfirmationMessage();
		if (cancelMsg.contains("canceled"))
			System.out.println("appointment cancelled successfully");
		else
			System.out.println("appointment not cancelled successfully");
		//logout as doctor
		doctorDashboardPage.logoutAsDoctor();
		//closing workbook.
		excelUtil.closeWorkbook();
		//closing all windows.
		wbUtil.closeAllWindows();
	}

	public static void main(String[] args) {
		AppointmentCancellation ac = new AppointmentCancellation();
		ac.cancelAppointment();
	}
}
