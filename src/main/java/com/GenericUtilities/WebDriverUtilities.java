package com.GenericUtilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This class has all reusable methods of WebDriver utility.
 * @author sunil
 *
 */
public class WebDriverUtilities {
	protected WebDriver driver;
	protected WebDriverWait wait ;
	protected Actions action ;
	protected Select select;
	protected Robot robo;
	/**
	 * This method is used to launch the browser
	 * @param browser
	 * @return
	 */
	public WebDriver launchBrowser(String browser) {
		switch(browser) {
		case "chrome" : WebDriverManager.chromedriver().setup();
						driver = new ChromeDriver();
						break;
		case "firefox" : WebDriverManager.firefoxdriver().setup();
						driver = new FirefoxDriver();
						break;
		case "edge" : WebDriverManager.edgedriver().setup();
						driver = new EdgeDriver();
						break;
		default : System.out.println("invalid browser");
		}
		return driver;
		
	}
	/**
	 * This method is used to maximize the browser.
	 */
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}
	/**
	 * This method is used to full screen the browser.
	 */
	public void fullScreenBrowser() {
		driver.manage().window().fullscreen();
	}
	/**
	 * This method is used until all elements loads to the page.
	 */
	public void implicitlyWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	/**
	 * This method is used to wait till the element becomes visible
	 * @param element
	 */
	public void waitTillVisibilityOfElement(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * This method is used to wait till element becomes clickable
	 * @param element
	 */
	public void waitTillElementToBeClickable(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This method is used to wait till title matches with  expected title.
	 * @param title
	 */
	public void waitTillTitleContains(String title) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains(title));
	}
	/**
	 * This method is used to close current window.
	 */
	public void closeWindow() {
		driver.close();
	}
	/**
	 * This method is used to close all the windows.
	 */
	public void closeAllWindows() {
		driver.quit();
	}
	/**
	 * This method is used to scroll the window using values.
	 * @param x
	 * @param y
	 */
	public void scrollByValue(int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy("+x+","+y+")");
	}
	/**
	 * This method is used to scroll the window til the element.
	 * @param element
	 */
	public void scrollIntoView(String element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
		
	}
	/**
	 * This method is used to move to the element.
	 * @param element
	 */
	public void movetoElement(WebElement element) {
		action =  new Actions(driver);
		action .moveToElement(element).perform();
	}
	/**
	 * This method is used to perform double click.
	 */
	public void doubleClickOnElement() {
		action =  new Actions(driver);
		action.doubleClick().perform();
	}
	/**
	 * This method is used to perform right click.
	 */
	public void rightClickOnElement() {
		action =  new Actions(driver);
		action.contextClick().perform();
	}
	/**
	 * This method is used to perform drag and drop option.
	 * @param source
	 * @param target
	 */
	public void dragAndDrop(WebElement source, WebElement target) {
		action =  new Actions(driver);
		action.dragAndDrop(source, target).perform();;
	}
	/**
	 * This method is used to switch to frame using index
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * This method is used to switch to frame using id attribute value.
	 * @param idattributeValue
	 */
	public void switchToFrame(String idattributeValue) {
		driver.switchTo().frame(idattributeValue);
	}
	/**
	 * This method is used to switch back from frame.
	 */
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}
	/**
	 * This method is sued to swith to child window.
	 * @param partialTitle
	 */
	public void switchToChildWindow(String partialTitle) {
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> itr = ids.iterator();
		while(itr.hasNext()) {
			String id = itr.next();
			String title = driver.switchTo().window(id).getTitle();
			if(title.contains(partialTitle))
				break;
		}
	}
	/**
	 * This method is used to select the drop down using index.
	 * @param element
	 * @param index
	 */
	public void selectDropDown(WebElement element, int index) {
		select = new Select(element);
		select.selectByIndex(index);
	}
	/**
	 * This method is used to select the drop down using value.
	 * @param element
	 * @param value
	 */
	public void selectDropDown(WebElement element, String value) {
		select = new Select(element);
		select.selectByValue(value);
	}
	/**
	 * This method is used to select the drop down using visible text.
	 * @param text
	 * @param element
	 */
	public void selectDropDown(String text, WebElement element) {
		select = new Select(element);
		select.selectByVisibleText(text);
	}
	/**
	 * This method is used to accept the alert.
	 */
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}
	/**
	 * This method is used to dismiss alert.
	 */
	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}
	/**
	 * This method is used to get the text on the alert.
	 * @return 
	 */
	public String getTextOfAlert() {
	String text = driver.switchTo().alert().getText();
	return text;
	}
	/**
	 * This method is used to disable notifications.
	 * @return
	 */
	public ChromeOptions disableNotificationsInChrome() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disabled-notifications");
		return option;
	}
	/**
	 * This method is sued to get screen shot of the page.
	 * @param name
	 * @return 
	 */
	public String getScreenshotOfPage(WebDriver driver,String name) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		JavaUtilities javaUtil = new JavaUtilities();
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/"+name+javaUtil.getFormattedDate()+".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dest.getAbsolutePath();
	}
	/**
	 * This method is used to press ENTER button.
	 */
	public void pressEnterbutton() {
		try {
			robo = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robo.keyPress(KeyEvent.VK_ENTER);
	}
	/**
	 * This method is used to release ENTER button.
	 */
	public void releaseEnter() {
		try {
			robo = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robo.keyRelease(KeyEvent.VK_ENTER);
	}
	/**
	 * This method is used to press V button.
	 */
	public void pressVButton() {
		try {
			robo = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robo.keyPress(KeyEvent.VK_V);
	}
	/**
	 * This method is used to release V button.
	 */
	public void releaseVButton() {
		try {
			robo = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robo.keyRelease(KeyEvent.VK_V);
	}
	/**
	 * This method is used to press CTRL button.
	 */
	public void pressCTRLButton() {
		try {
			robo = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robo.keyPress(KeyEvent.VK_CONTROL);
	}
	/**
	 * This method is used to release CTRL button.
	 */
	public void releaseCTRLButton() {
		try {
			robo = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robo.keyRelease(KeyEvent.VK_CONTROL);
	}
}
