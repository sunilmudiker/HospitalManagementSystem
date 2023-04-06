package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This pom page consists of all webElements and respective business libraries related to doctor login page.
 * @author sunil
 *
 */
public class DoctorLoginPage {
	
	//Declaration
	@FindBy(xpath = "//input[@name='username']") private WebElement doctorUsername;
	@FindBy(xpath = "//input[@name='password']") private WebElement doctorPassword;
	@FindBy(xpath = "//button[@name='submit']") private WebElement loginButton;
	@FindBy(xpath = "//a[contains(.,'Forgot Password ?')]") private WebElement forgotPasswordLink;
	
	//Initialization
	public DoctorLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/**
	 * this method is used to login to the application as doctor.
	 * @param doctorUsername
	 * @param doctorPassword
	 */
	public void doctorLogin(String doctorUsername, String doctorPassword) {
		this.doctorUsername.sendKeys(doctorUsername);
		this.doctorPassword.sendKeys(doctorPassword);
		loginButton.click();
	}
	/**
	 * Thise method is used to go to forgot password link.
	 */
	public void forgotPassword() {
		forgotPasswordLink.click();
	}
	
	
}
