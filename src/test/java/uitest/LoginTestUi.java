package uitest;

import baseclass.UiBaseTest;
import com.selenium.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;



public class LoginTestUi extends UiBaseTest {



		@Test
		public static void validLogin() throws InterruptedException {
			LoginPage login = new LoginPage(driver);
			extentTest = extent.startTest("Valid Login");
			login.login();
			Assert.assertEquals("webmate by Testfabrik",driver.getTitle());
		}

	}


