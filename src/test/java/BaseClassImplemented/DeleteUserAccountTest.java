package BaseClassImplemented;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.GenericUtilities.BaseClass;

public class DeleteUserAccountTest  extends BaseClass{

	@Test
	public void deleteUser() {
		Map<String, String> map = excelUtil.readMultipleData(0, 1, "DeleteUser");
		//click on patient login tab.
		homePage.clickPatientLogin();
		//click on create an account link
		patientLoginPage.createAnAccountLink();
		//create a new account 
		patientSignUPPage.signUp(map.get("fullname"), map.get("address"), map.get("city"),map.get("gender"), javaUtil, map.get("email"), map.get("password"),map.get("confirm_password"));
		
		String userEmail = patientSignUPPage.getUserEmail();
		String userName = patientSignUPPage.getUserName();
		wbUtil.acceptAlert();
		//click on login button
		patientSignUPPage.clickLogin();
		//navigate back to home page
		driver.navigate().to(URL);
		//click on admin page
		homePage.clickAdminLogin();
		//login as admin
		adminLoginPage.login(USERNAME, PASSWORD);
		//click on users tab
		adminDashboardPage.clickUsers();
		//click on manage users
		adminDashboardPage.clickManageUsers();
		//delete the user
		manageUsersPage.deleteUser(userName);
		wbUtil.acceptAlert();
		String confirmMsg =manageUsersPage.getConfirmationMsg();
		Assert.assertTrue(confirmMsg.contains("deleted"));
		//logout as admin
		adminDashboardPage.logout();
		//click on patient login
		homePage.clickPatientLogin();
		//login as patient
		patientLoginPage.loginAsPatient(userEmail, map.get("password"));
		String errorMsg = patientLoginPage.getErrorMsg();
		Assert.assertTrue(errorMsg.contains("Invalid"));
		
		
	}
}
