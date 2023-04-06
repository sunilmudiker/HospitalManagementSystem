package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/*
 * This pom page consists of all webElements and business libraries related to admin login page
 */
public class AdminLoginPage {

	//Declaration
	@FindBy(xpath = "//input[@name='username']") private WebElement username;
	@FindBy(xpath = "//input[@name='password']") private WebElement password;
	@FindBy(xpath = "//button[@name='submit']") private WebElement loginButton;
	
	//Initialization
	public AdminLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/*
	 * This method is used to login as administrator.
	 */
	public void login(String username, String password) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.loginButton.click();
	}
}
