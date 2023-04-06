package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/*
 * This page consists of elements and business libraries related to homePage.
 */
public class HomePage {
	//Declaration
	@FindBy(xpath = "//h3[.='Admin Login']/parent::div/descendant::a[.='Click Here']") private WebElement adminLogin;
	@FindBy(xpath = "//h3[contains(.,'Patients')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']") private WebElement patientLogin;
	@FindBy(xpath = "//h3[contains(.,'Doctors Login')]/following-sibling::div[@class='button']/descendant::a[.='Click Here']") private WebElement doctorLogin;
	@FindBy(xpath = "//div[@class='header']/descendant::a[.='contact']") private WebElement contactUs;
	@FindBy(xpath = "//div[@class='header']/descendant::a[.='Home']") private WebElement home;
	
	//Initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/*
	 * This method is used to click admin login.
	 */
	public void clickAdminLogin() {
		adminLogin.click();
	}
	/*
	 * This method is used to click patient login.
	 */
	public void clickPatientLogin() {
		patientLogin.click();
	}
	/*
	 * This method is used to click doctor login.
	 */
	public void clickDoctorLogin() {
		doctorLogin.click();
	}
	/*
	 * This method is used to click contact us.
	 */
	public void clickContactUs() {
		contactUs.click();
	}
	/*
	 * This method is used to click home.
	 */
	public void clickHome() {
		home.click();
	}
}
