package pomImplemented;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.GenericUtilities.DatabaseUtilities;
import com.GenericUtilities.ExcelUtilities;
import com.GenericUtilities.FileUtilities;
import com.GenericUtilities.JavaUtilities;
import com.GenericUtilities.WebDriverUtilities;
import com.ObjectRepository.AdminDashboardPage;
import com.ObjectRepository.AdminLoginPage;
import com.ObjectRepository.HomePage;

public class AdminLogin {
	
	public void login() {
		DatabaseUtilities dbUtil = new DatabaseUtilities();
		ExcelUtilities excelUtil = new ExcelUtilities();
		FileUtilities fileUtil = new FileUtilities();
		JavaUtilities javaUtil = new JavaUtilities();
		
		//reading data from property file.
		String URL = fileUtil.readData("url");
		String USERNAME = fileUtil.readData("username");
		String PASSWORD = fileUtil.readData("password");

		
		//launching browser.
		WebDriverUtilities wbUtil = new WebDriverUtilities();
		WebDriver driver=wbUtil.launchBrowser("chrome");
		wbUtil.maximizeBrowser();
		driver.get(URL);
		//perform login and logout .
		HomePage homePage = new HomePage(driver);
		homePage.clickAdminLogin();
		AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
		adminLoginPage.login(USERNAME, PASSWORD);
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		adminDashboardPage.logout();

		//close all the active windows.
		wbUtil.closeAllWindows();
		
	}
	
	public static void main(String[] args) {
		AdminLogin al = new AdminLogin();
		al.login();
	}
}
