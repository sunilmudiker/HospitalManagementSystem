package pomImplemented;

import java.util.Map;
import java.util.Map.Entry;

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
import com.ObjectRepository.HomePage;

public class AddDoctor {

	public void addDoctor() {
		// creating object of all generic classes
		DatabaseUtilities dbUtil = new DatabaseUtilities();
		ExcelUtilities excelUtil = new ExcelUtilities();
		FileUtilities fileUtil = new FileUtilities();
		JavaUtilities javaUtil = new JavaUtilities();
		// reading data from property file
		String URL = fileUtil.readData("url");
		String USERNAME = fileUtil.readData("username");
		String PASSWORD = fileUtil.readData("password");
		// reading data from excel file.
		excelUtil.initializeExcelFile();
		Map<String, String> map = excelUtil.readMultipleData(0, 1,"addDoctor");
		// launching browser
		WebDriverUtilities wbUtil = new WebDriverUtilities();
		WebDriver driver = wbUtil.launchBrowser("chrome");
		wbUtil.maximizeBrowser();
		driver.get(URL);
		HomePage homePage = new HomePage(driver);
		homePage.clickAdminLogin();
		// login to the application
		AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
		adminLoginPage.login(USERNAME, PASSWORD);
		// adding new doctor
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		adminDashboardPage.clickAddDoctor(wbUtil);
		AddDoctorPage addDoctorPage = new AddDoctorPage(driver);
		addDoctorPage.addDoctor(wbUtil, map.get("doctorspecialization"), javaUtil, map.get("docname"),
				map.get("clinicaddress"), map.get("docfees"), map.get("email"), map.get("npass"), map.get("cfpass"));
		// accepting alert.
		wbUtil.acceptAlert();
		// logout from application.
		adminDashboardPage.logout();
		// closing workbook.
		excelUtil.closeWorkbook();
		// closing all windows.
		wbUtil.closeAllWindows();

	}

	public static void main(String[] args) {
		AddDoctor ad = new AddDoctor();
		ad.addDoctor();
	}
}
