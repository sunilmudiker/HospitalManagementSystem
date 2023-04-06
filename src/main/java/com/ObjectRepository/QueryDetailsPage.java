package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This pom page consists of all WebElements and respective business libraries related to query details page
 * @author sunil
 *
 */
public class QueryDetailsPage {

	//Declaration
	@FindBy(xpath = "//textarea[@name='adminremark']") private WebElement adminRemark;
	@FindBy(xpath = "//button[@name='update']") private WebElement updateButton;
	
	//Initialization
	public QueryDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/**
	 * This method is used to add admin remarks.
	 * @param adminRemark
	 */
	public void addAdminRemark(String adminRemark) {
		this.adminRemark.sendKeys(adminRemark);
		updateButton.click();
	}
}
