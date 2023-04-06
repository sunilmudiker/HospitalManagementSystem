package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This pom page consists of all WebElements and respective business libraries related to manage unread queries page. 
 * @author sunil
 *
 */
public class ManageUnreadQueriesPage {
	
	//Declaration
	@FindBy(xpath = "//table[@id='sample-table-1']/tbody/tr[last()]/td[last()]/div/a") private WebElement newQueryAction;
	
	//Initialization
	public ManageUnreadQueriesPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/**
	 * this method is used to click action button.
	 */
	public void clickActionButton() {
		newQueryAction.click();
	}
	
}
