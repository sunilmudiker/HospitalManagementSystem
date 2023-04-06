package BaseClassImplemented;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.GenericUtilities.BaseClass;

public class QueryTest extends BaseClass {

	@Test
	public void verifyQuery() {
		Map<String, String> map = excelUtil.readMultipleData(0, 1, "query");
		// click on contact us tab.
		homePage.clickContactUs();
		// create a query and send it.
		contactUsPage.sendAQuery(map.get("fullname"), map.get("email"), javaUtil, map.get("description"));
		// verify submitted successfully
		String s = wbUtil.getTextOfAlert();
		Assert.assertTrue(s.contains("succesfully submitted"));
//		if (s.contains("succesfully submitted"))
//			System.out.println("submitted");
//		else
//			System.out.println("not submitted");
		wbUtil.acceptAlert();
		// click on home tab
		homePage.clickHome();
		// click on admin login button
		homePage.clickAdminLogin();
		// login as administrator
		adminLoginPage.login(USERNAME, PASSWORD);
		// click on new queries
		adminDashboardPage.clickNewQueries();
		// click on action button
		manageUnreadQueriesPage.clickActionButton();
		// add admin remark
		queryDetailsPage.addAdminRemark(map.get("adminremark"));
		System.out.println(wbUtil.getTextOfAlert());
		wbUtil.acceptAlert();
		// logout as administrator
		adminDashboardPage.logout();

	}
}
