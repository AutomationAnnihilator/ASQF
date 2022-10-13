package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

		@FindBy(how = How.NAME , using = "userName")
		public WebElement usernameText;
		
		@FindBy(how = How.NAME , using="password")
		public WebElement passwordText;
		
		public void enterUserName(String user) {
			usernameText.sendKeys(user);
		}
		
		public void enterPassword(String pass) {
			passwordText.sendKeys(pass);
		}
		
		public void submit() {
			
			WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(10)).
					until(ExpectedConditions.presenceOfElementLocated(By.name("submit")));
			submitButton.click();
		}
	}
		
	