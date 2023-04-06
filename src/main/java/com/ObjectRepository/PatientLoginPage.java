package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/*
 * This pom page consists of all webElements and respective business libraries related to patient login page.
 */
public class PatientLoginPage {

	//Declaration
	@FindBy(xpath = "//input[@name='username']") private WebElement username;
	@FindBy(xpath = "//input[@name='password']") private WebElement password;
	@FindBy(xpath = "//button[@name='submit']") private WebElement submitButton;
	@FindBy(xpath = "//a[contains(.,'Forgot Password ?')]") private WebElement forgotPassword;
	@FindBy(xpath = "//a[contains(.,'Create an account')]") private WebElement createAnAccount;
	@FindBy(xpath = "//span[@style='color:red;']") private WebElement errorMsg;
	
	//Initialization
	public PatientLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/*
	 * This method is used to login as patient
	 */
	public void loginAsPatient(String username, String password) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.submitButton.click();
	}
	/*
	 * This method is used to go to forgot password page.
	 */
	public void forgotPasswordLink() {
		forgotPassword.click();
	}
	/*
	 * This method is sued to go to create a new account page.
	 */
	public void createAnAccountLink() {
		createAnAccount.click();
	}
	/**
	 * This method is used to get error message.
	 * @return
	 */
	public String getErrorMsg() {
		return errorMsg.getText();
	}
}
