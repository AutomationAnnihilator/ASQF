package uitest;

import baseclass.UiBaseTest;
import com.selenium.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class LoginTestUi extends UiBaseTest {
		@Test
		public void validLogin() {
			
			LoginPage login = new LoginPage(driver);
			extentTest = extent.startTest("Valid Login");
			login.enterUserName("Nandini");
			extentTest.log(LogStatus.PASS, "PAssed");
			login.enterPassword("1234");
			login.submit();
		}

		@Test
		public void invalidLogin() {
			
			LoginPage login = new LoginPage(driver);
			login.enterUserName("Nandini123546666");
			login.enterPassword("12345555");
			login.submit();
		}	
	}


