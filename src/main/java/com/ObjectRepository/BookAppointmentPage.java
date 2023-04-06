package com.ObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericUtilities.WebDriverUtilities;

public class BookAppointmentPage {
	
	//Declaration
	@FindBy(xpath = "//select[@name='Doctorspecialization']") private WebElement doctorSpecialization;
	@FindBy(xpath = "//select[@name='doctor']") private WebElement doctorName;
	@FindBy(xpath = "//input[@name='appdate']") private WebElement calender;
	@FindBy(xpath = "//div[@class='datepicker-days']/descendant::tr/th[@class='datepicker-switch']") private WebElement monthDay;
	@FindBy(xpath = "//div[@class='datepicker-days']/descendant::tbody/tr/td[@class='day']") private List<WebElement> days;
	@FindBy(xpath = "//div[@class='datepicker-days']/descendant::tr/th[@class='next']") private WebElement next;
	@FindBy(xpath = "//input[@id='timepicker1']") private WebElement timePicker;
	@FindBy(xpath = "//button[@name='submit']") private WebElement submitButton;
	
	//Initialization
	public BookAppointmentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public void bookAppointment(WebDriverUtilities wbUtil,String value, String text, String month, String year, String date) {
		wbUtil.selectDropDown(doctorSpecialization, value);
		wbUtil.selectDropDown(text, doctorName);
		calender.click();
		boolean flag = false;
		while(true) {
			String monthday = monthDay.getText();
			if(monthday.equalsIgnoreCase(month+" "+year)) {
				List<WebElement> day = days;
				for (WebElement eachday : day) {
					String eday = eachday.getText();
					if(eday.contains(date)) {
						eachday.click();
						flag =true;
						break;
					}
				}
			}
			if(flag || !(monthday.contains(year)))
				break;
			else
				next.click();	
		}
		timePicker.click();
		submitButton.click();
	}
}
