package test;

import baseclass.UiBaseTest;
import com.Selenium.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTestUi extends UiBaseTest {
		@Test
		public void validLogin() {
			
			LoginPage login = new LoginPage(driver);
			login.enterUserName("Nandini");
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


