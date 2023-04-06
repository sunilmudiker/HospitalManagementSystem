package com.GenericUtilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.ObjectRepository.AddDoctorPage;
import com.ObjectRepository.AdminDashboardPage;
import com.ObjectRepository.AdminLoginPage;
import com.ObjectRepository.BookAppointmentPage;
import com.ObjectRepository.ContactUsPage;
import com.ObjectRepository.DoctorAppointmentPage;
import com.ObjectRepository.DoctorDashboardPage;
import com.ObjectRepository.DoctorLoginPage;
import com.ObjectRepository.DoctorSpecializationPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.ManageUnreadQueriesPage;
import com.ObjectRepository.ManageUsersPage;
import com.ObjectRepository.MedicalHistoryPage;
import com.ObjectRepository.PatientDashboardPage;
import com.ObjectRepository.PatientDetailsPage;
import com.ObjectRepository.PatientLoginPage;
import com.ObjectRepository.PatientMedicalHistoryPage;
import com.ObjectRepository.PatientSignUPPage;
import com.ObjectRepository.QueryDetailsPage;
import com.ObjectRepository.SearchPatientPage;

public class BaseClass {
	protected DatabaseUtilities databaseUtil;
	protected ExcelUtilities excelUtil;
	protected FileUtilities fileUtil;
	protected JavaUtilities javaUtil;
	protected WebDriverUtilities wbUtil;
	
	
	protected AddDoctorPage addDoctorPage;
	protected AdminDashboardPage adminDashboardPage;
	protected AdminLoginPage	adminLoginPage;
	protected BookAppointmentPage bookAppointmentPage;
	protected ContactUsPage contactUsPage;
	protected DoctorAppointmentPage doctorAppointmentPage;
	protected DoctorDashboardPage doctorDashboardPage;
	protected DoctorLoginPage doctorLoginPage;
	protected DoctorSpecializationPage doctorSpecializationPage;
	protected HomePage homePage;
	protected ManageUnreadQueriesPage manageUnreadQueriesPage;
	protected MedicalHistoryPage medicalHistoryPage;
	protected PatientDashboardPage patientDashboardPage;
	protected PatientDetailsPage patientDetailsPage;
	protected PatientLoginPage patientLoginPage;
	protected PatientMedicalHistoryPage patientMedicalHistoryPage;
	protected QueryDetailsPage queryDetailsPage;
	protected SearchPatientPage searchPatientPage;
	protected PatientSignUPPage patientSignUPPage;
	protected ManageUsersPage manageUsersPage;
	protected ListenersImplementationClass listenersImplementationClass;
	protected WebDriver driver;
	protected String URL ;
	protected String USERNAME;
	protected String PASSWORD;
	protected String BROWSER;
	protected static WebDriver sdriver; 
	
	@BeforeSuite(alwaysRun = true)
	public void configBeforeSuite() {
		databaseUtil = new DatabaseUtilities();
		databaseUtil.DBConnection();
	}
	
	//@BeforeTest
	//@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void configBeforeClass() {
		excelUtil = new ExcelUtilities();
		fileUtil = new FileUtilities();
		javaUtil = new JavaUtilities();
		wbUtil = new WebDriverUtilities();
		listenersImplementationClass = new ListenersImplementationClass();
		
		URL= fileUtil.readData("url");
		USERNAME = fileUtil.readData("username");
		PASSWORD = fileUtil.readData("password");
		 BROWSER = fileUtil.readData("browser");
		 
		driver = wbUtil.launchBrowser(BROWSER); 
		sdriver= driver;
		wbUtil.maximizeBrowser();
		wbUtil.implicitlyWait();
		driver.get(URL);
	}
	@BeforeMethod(alwaysRun = true)
	public void configBeforeMethod() {
		addDoctorPage = new AddDoctorPage(driver);
		adminDashboardPage = new AdminDashboardPage(driver)  ;
		adminLoginPage =  new AdminLoginPage(driver);
		bookAppointmentPage = new BookAppointmentPage(driver) ;
		contactUsPage = new ContactUsPage(driver);
		 doctorAppointmentPage = new DoctorAppointmentPage(driver);
		 doctorDashboardPage = new DoctorDashboardPage(driver) ;
		 doctorLoginPage =  new DoctorLoginPage(driver);
		 doctorSpecializationPage = new DoctorSpecializationPage(driver);
		homePage = new HomePage(driver) ;
		manageUnreadQueriesPage = new ManageUnreadQueriesPage(driver);
		 medicalHistoryPage =  new MedicalHistoryPage(driver);
		patientDashboardPage =  new PatientDashboardPage(driver);
		 patientDetailsPage = new PatientDetailsPage(driver);
			patientLoginPage =  new PatientLoginPage(driver);
		 patientMedicalHistoryPage = new PatientMedicalHistoryPage(driver) ;
		 queryDetailsPage = new QueryDetailsPage(driver);
		 searchPatientPage = new SearchPatientPage(driver);
		 patientSignUPPage = new PatientSignUPPage(driver);
		 manageUsersPage = new ManageUsersPage(driver);
		 excelUtil.initializeExcelFile();
		
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void configAfterMethod() {
		excelUtil.closeWorkbook();
	}
	
	@AfterClass(alwaysRun = true)
	public void configAfterClass() {
		wbUtil.closeAllWindows();
	}
//	@AfterTest
	
	@AfterSuite(alwaysRun = true)
	public void configAfterSuite() {
		databaseUtil.closeDataBase();
	}
}
