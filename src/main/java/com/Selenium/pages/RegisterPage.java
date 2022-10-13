package com.Selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	
	WebDriver driver;
	
	public  RegisterPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(how=How.LINK_TEXT , using="REGISTER")
	public WebElement registerLink;
	
	@FindBy(how = How.ID , using="email")
	public WebElement usernameText ;
	
	@FindBy(how = How.NAME , using="password")
	public WebElement passwordText ;
	
	@FindBy(how = How.NAME , using="confirmPassword")
	public WebElement confirmpasswordText ;
	
	@FindBy(how = How.NAME , using="submit")
	public WebElement submitButton ;
	
	
	public void clickRegister() {
		registerLink.click();
	}
	
	public void enterUserName(String username) {
		usernameText.sendKeys(username);
	}
	public void enterPassword(String password) {
		passwordText.sendKeys(password);
	}
	public void enterConfirmPassword(String confirmpassword) {
		confirmpasswordText.sendKeys(confirmpassword);
	}
	

	public void submit()
	{
		submitButton.click();
	}
	
}


