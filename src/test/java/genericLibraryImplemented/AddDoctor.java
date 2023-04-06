package genericLibraryImplemented;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.GenericUtilities.DatabaseUtilities;
import com.GenericUtilities.ExcelUtilities;
import com.GenericUtilities.FileUtilities;
import com.GenericUtilities.JavaUtilities;
import com.GenericUtilities.WebDriverUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddDoctor {
	
	public void addDoctor() {
		//creating object of all generic classes
		DatabaseUtilities dbUtil = new DatabaseUtilities();
		ExcelUtilities excelUtil = new ExcelUtilities();
		FileUtilities fileUtil = new FileUtilities();
		JavaUtilities javaUtil = new JavaUtilities();
		//reading data from property file
		String URL = fileUtil.readData("url");
		String USERNAME = fileUtil.readData("username");
		String PASSWORD = fileUtil.readData("password");
		//reading data from excel file.
		excelUtil.initializeExcelFile();
		Map<String, String> map = excelUtil.readMultipleData(0, 1,"addDoctor");
		//launching browser
		WebDriverUtilities wbUtil = new WebDriverUtilities();
		WebDriver driver=wbUtil.launchBrowser("chrome");
		wbUtil.maximizeBrowser();
		driver.get(URL);

		driver.findElement(By.xpath("//h3[.='Admin Login']/parent::div/descendant::a[.='Click Here']")).click();
		//login to the application
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		//adding new doctor
		driver.findElement(By.xpath("//div[@class='item-inner']/span[contains(.,'Doctors')]")).click();
		WebElement addDoc = driver.findElement(By.xpath("//span[contains(.,'Add')]"));
		wbUtil.waitTillVisibilityOfElement(addDoc);
		addDoc.click();

		WebElement doctorSpecialization = driver.findElement(By.xpath("//select[@name='Doctorspecialization']"));
		String doc = map.get("doctorspecialization");
		wbUtil.selectDropDown(doctorSpecialization,map.get("doctorspecialization"));

		int randomNum=javaUtil.getRandomNum(10000);
		long ranmob = javaUtil.getRandomNum(10000);
		
		for (Entry<String, String> set : map.entrySet()) {
			if(set.getKey().contains("docname"))
				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue()+randomNum);
			else if(set.getKey().contains("email")||set.getKey().contains("doctorspecialization"))
				continue;
			else
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
		driver.findElement(By.xpath("//input[@name='doccontact']")).sendKeys(""+ranmob);
		driver.findElement(By.xpath("//input[@name='docemail']")).sendKeys(map.get("email")+randomNum+map.get("email1"));

		driver.findElement(By.xpath("//button[@id='submit']")).click();
		// accepting alert.
		wbUtil.acceptAlert();
		//logout from application.
		driver.findElement(By.xpath("//span[@class='username']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
		//closing workbook.
		excelUtil.closeWorkbook();
		//closing all windows.
		wbUtil.closeAllWindows();

	}
	
	public static void main(String[] args) {
		AddDoctor ad = new AddDoctor();
		ad.addDoctor();
	}
}
