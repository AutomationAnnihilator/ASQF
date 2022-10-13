package test;

import baseclass.UiBaseTest;
import com.Selenium.pages.RegisterPage;
import org.testng.annotations.Test;

public class RegisterTestUi extends UiBaseTest {
	
	@Test 
	public void registerUser() {
		RegisterPage register = new RegisterPage(driver);
		register.clickRegister();
		register.enterUserName("Himanshu");
		register.enterPassword("123");
		register.enterConfirmPassword("123");
		register.submit();
	}
	@Test
	public void invalidRegisterUser() {
		RegisterPage register = new RegisterPage(driver);
		register.enterUserName("Himanshu");
		register.enterPassword("123");
		register.enterConfirmPassword("123");
		register.submit();
	}
}

