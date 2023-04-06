package com.ObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This pom page consists of all WebElements and respective business libraries related tp medical history page.
 * @author sunil
 *
 */
public class MedicalHistoryPage {

	//Declaration
	@FindBy(xpath = "//table[@id='sample-table-1']/tbody/tr/td[@class='hidden-xs']") private List<WebElement> patients;
	@FindBy(xpath = "//table[@id='sample-table-1']/tbody/tr/td[@class='hidden-xs']/following::td[last()]/a") private WebElement viewHistory;

	//Initialization
	public MedicalHistoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/**
	 * This method is used to click view medical history of patient.
	 * @param patientName
	 */
	public void clickViewMedicalHistory(String patientName) {
		List<WebElement> patientMH =patients ;
		for(int i=0; i<patientMH.size();i++) {
			String patientname = patientMH.get(i).getText();
			if(patientname.contains(patientName)){
				viewHistory.click();
				break;
			}
		}
	}
}
