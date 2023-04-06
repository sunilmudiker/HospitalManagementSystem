package genericLibraryImplemented;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.GenericUtilities.DatabaseUtilities;
import com.GenericUtilities.ExcelUtilities;
import com.GenericUtilities.FileUtilities;
import com.GenericUtilities.JavaUtilities;
import com.GenericUtilities.WebDriverUtilities;

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
		driver.findElement(By.xpath("//h3[.='Admin Login']/parent::div/descendant::a[.='Click Here']")).click();

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@name='submit']")).click();

		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		//close all the active windows.
		wbUtil.closeAllWindows();
		
	}
	
	public static void main(String[] args) {
		AdminLogin al = new AdminLogin();
		al.login();
	}
}
