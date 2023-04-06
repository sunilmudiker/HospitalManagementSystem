package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericUtilities.JavaUtilities;

public class PatientSignUPPage {
	private String userEmail;
	private String userFullName;

	
	//Declaration
	@FindBy(name = "full_name") private WebElement fullname;
	@FindBy(name = "address")	private WebElement address;
	@FindBy(name = "city") private WebElement city;
	@FindBy(xpath = "//label[@for='rg-female']") private WebElement female;
	@FindBy(xpath = "//label[@for='rg-male']") private WebElement male;
	@FindBy(name = "email") private WebElement email;
	@FindBy(name = "password") private WebElement password;
	@FindBy(name = "password_again") private WebElement confirm_password;
	@FindBy(id = "submit") private WebElement submit;
	@FindBy(xpath = "//a[contains(.,'Log-in')]") private WebElement loginLink;
	
	//Initialization
	public PatientSignUPPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public void signUp(String fullname, String address, String city, String gender, JavaUtilities javaUtil, String email, String password, String confirm_password) {
		userFullName = fullname + javaUtil.getRandomNum(1000);
		this.fullname.sendKeys (userFullName);
		this.address.sendKeys(address);
		this.city.sendKeys(city);
		switch(gender) {
		case "female" : female.click();break;
		case "male" : male.click();break;
		default : System.out.println("invalid choice");
		}
		userEmail = javaUtil.getRandomNum(1000)+email;
		this.email.sendKeys(userEmail);
		this.password.sendKeys(password);
		this.confirm_password.sendKeys(confirm_password);
		this.submit.click();
		//we will get alert. "successfully Registered"
	}
	
	public void clickLogin() {
		loginLink.click();
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public String getUserName() {
		return userFullName;
	}
}

