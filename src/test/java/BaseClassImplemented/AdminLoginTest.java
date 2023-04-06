package BaseClassImplemented;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.GenericUtilities.BaseClass;
public class AdminLoginTest extends BaseClass {
	
	@Test(retryAnalyzer = com.GenericUtilities.RetryAnalyzerImpClass.class)
	public void login() {
		//perform login and logout .
		
		homePage.clickAdminLogin();
		adminLoginPage.login(USERNAME, PASSWORD);
		adminDashboardPage.logout();

	}
	
	
}
