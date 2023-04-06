package pomImplemented;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.GenericUtilities.DatabaseUtilities;
import com.GenericUtilities.ExcelUtilities;
import com.GenericUtilities.FileUtilities;
import com.GenericUtilities.JavaUtilities;
import com.GenericUtilities.WebDriverUtilities;
import com.ObjectRepository.AdminDashboardPage;
import com.ObjectRepository.AdminLoginPage;
import com.ObjectRepository.ContactUsPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.ManageUnreadQueriesPage;
import com.ObjectRepository.QueryDetailsPage;

public class Query {
	public void verifyQuery() {
		//creating object of generic classes
		DatabaseUtilities dbUtil = new DatabaseUtilities();
		ExcelUtilities excelUtil = new ExcelUtilities();
		FileUtilities fileUtil = new FileUtilities();
		JavaUtilities javaUtil = new JavaUtilities();
		//fetching the data from property file
		String URL = fileUtil.readData("url");
		String USERNAME = fileUtil.readData("username");
		String PASSWORD = fileUtil.readData("password");
		//fetching the data from excel file
		excelUtil.initializeExcelFile();
		Map<String, String> map = excelUtil.readMultipleData(0, 1,"query");
		//launching browser
		WebDriverUtilities wbUtil = new WebDriverUtilities();
		WebDriver driver=wbUtil.launchBrowser("chrome");
		wbUtil.maximizeBrowser();
		//navigating to the application
		driver.get(URL);
		HomePage homePage = new HomePage(driver);
		homePage.clickContactUs();
		ContactUsPage contactUsPage = new ContactUsPage(driver);
		contactUsPage.sendAQuery(map.get("fullname"),map.get("email"), javaUtil, map.get("description"));
		//verify submitted successfully
		String s = wbUtil.getTextOfAlert();
		if(s.contains("succesfully submitted"))
			System.out.println("submitted");
		else
			System.out.println("not submitted");
		wbUtil.acceptAlert();
		homePage.clickHome();
		homePage.clickAdminLogin();
		//login as administrator
		AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
		adminLoginPage.login(USERNAME, PASSWORD);
		//adding admin remark
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		adminDashboardPage.clickNewQueries();
		ManageUnreadQueriesPage manageUnreadQueriesPage = new ManageUnreadQueriesPage(driver);
		manageUnreadQueriesPage.clickActionButton();
		QueryDetailsPage queryDetailsPage = new QueryDetailsPage(driver);
		queryDetailsPage.addAdminRemark(map.get("adminremark"));
		System.out.println(wbUtil.getTextOfAlert());
		wbUtil.acceptAlert();
		//logout as administrator
		adminDashboardPage.logout();
		//close workbook
		excelUtil.closeWorkbook();
		//close all active windows
		wbUtil.closeAllWindows();
		
	}

	public static void main(String[] args) {
		Query q = new Query();
		q.verifyQuery();
	}
}
