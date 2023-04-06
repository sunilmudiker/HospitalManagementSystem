package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericUtilities.JavaUtilities;
/**
 * this pom page consists of all WebElements and respective business libraries.
 * @author sunil
 *
 */
public class ContactUsPage {
	
	//Declaration
	@FindBy(name = "fullname") private WebElement name;
	@FindBy(name = "emailid") private WebElement email;
	@FindBy(name = "mobileno") private WebElement mobileNo;
	@FindBy(name = "description") private WebElement description;
	@FindBy(name = "submit") private WebElement submitButton;
	
	//Initialization
	public ContactUsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/**
	 * This method is used to submit a new query.
	 * @param name
	 * @param email
	 * @param javaUtil
	 * @param description
	 */
	public void sendAQuery(String name,String email,JavaUtilities javaUtil, String description) {
		this.name.sendKeys(name);
		this.email.sendKeys(javaUtil.getRandomNum(1000)+email);
		this.mobileNo.sendKeys(""+javaUtil.getRandomNum(1000000000));
		this.description.sendKeys(description);
		submitButton.click();
	}
	
}
