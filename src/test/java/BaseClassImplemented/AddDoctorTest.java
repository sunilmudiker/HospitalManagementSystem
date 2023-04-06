package BaseClassImplemented;
import java.util.Map;
import org.testng.annotations.Test;
import com.GenericUtilities.BaseClass;
public class AddDoctorTest extends BaseClass {
	@Test
	public void addDoctor() {
		Map<String, String> map = excelUtil.readMultipleData(0, 1, "addDoctor");
		// click adminLogin button
		homePage.clickAdminLogin();
		// login as administrator
		adminLoginPage.login(USERNAME, PASSWORD);
		// click add doctor
		adminDashboardPage.clickAddDoctor(wbUtil);
		// add doctor
		addDoctorPage.addDoctor(wbUtil, map.get("doctorspecialization"), javaUtil, map.get("docname"),
				map.get("clinicaddress"), map.get("docfees"), map.get("email"), map.get("npass"), map.get("cfpass"));
		// accepting alert.
		wbUtil.acceptAlert();
		// logout from application.
		adminDashboardPage.logout();
	}

}
