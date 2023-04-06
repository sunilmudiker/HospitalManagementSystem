package practicePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RMGYantra {
	public static void m1() throws Throwable
	{
		Connection con =null;
		try {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://rmgtestingserver:8084/");
		driver.findElement(By.name("username")).sendKeys("rmgyantra");
		driver.findElement(By.name("password")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
		driver.findElement(By.name("projectName")).sendKeys("sunTechs");
		driver.findElement(By.name("createdBy")).sendKeys("deepak");
		WebElement ele = driver.findElement(By.name("status"));
		Select select = new Select(ele);
		select.selectByValue("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement element = driver.findElement(By.xpath("//div[@role='alert']"));
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println(element.getText());
//		wait.until(ExpectedConditions.invisibilityOf(element));
//		List<WebElement> projectList = driver.findElements(By.xpath("//tbody/tr/td[2]"));
//		for (WebElement project : projectList) {
//			String projectName = project.getText();
//			if(projectName.equalsIgnoreCase("sunTechs")) {
//				driver.findElement(By.xpath("//tbody/tr/td[.='"+projectName+"']/following-sibling::td[last()]/a[@class='delete']")).click();
//				break;
//			}
//		}
//		WebElement del = driver.findElement(By.xpath("//input[@value='Delete']"));
//		wait.until(ExpectedConditions.visibilityOf(del));
//		del.click();
//		element = driver.findElement(By.xpath("//div[@role='alert']"));
//		wait.until(ExpectedConditions.visibilityOf(element));
//		System.out.println(element.getText());
		boolean flag = false;
		Driver dbDriver = new Driver();
		DriverManager.registerDriver(dbDriver);
		con = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects","root@%","root");
		Statement st = con.createStatement();
		ResultSet result = st.executeQuery("select project_name from project");
		while(result.next()) {
			String actualpro = result.getString(1);
			if(actualpro.equalsIgnoreCase("sunTechs")) {
				flag = true;
				break;
			}
		}
		if(flag)
			System.out.println("project created");
		else
			System.out.println("project not created");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		finally {
			con.close();
		}
		
	}
	
	public static void main(String[] args) throws Throwable {
		m1();
	}
}
