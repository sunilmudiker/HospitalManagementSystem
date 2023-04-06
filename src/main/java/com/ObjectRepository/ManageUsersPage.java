package com.ObjectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageUsersPage {

	//Declaration
	@FindBy(xpath = "//table[@id='sample-table-1']/tbody/tr/td[2]") private List<WebElement> usersNames;
	@FindBy(xpath = "//table[@id='sample-table-1']/tbody/tr/td[last()]/div/a") private  List<WebElement> deleteUser;
	@FindBy(xpath = "//p[@style='color:red;']") private WebElement confirmationMsg;
	
	//Initialization
	public ManageUsersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public void deleteUser(String userName) {
		for(int i=0 ; i<usersNames.size();i++) {
			String user = usersNames.get(i).getText();
			if(user.contains(userName)) {
				deleteUser.get(i).click();
				break;
				//popup will come.
			}
		}
	}
	
	public String getConfirmationMsg() {
		return confirmationMsg.getText();
	}
}
