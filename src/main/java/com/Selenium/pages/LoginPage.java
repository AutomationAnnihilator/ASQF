package com.Selenium.pages;

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
		
		//@FindBy(how = How.XPATH , using="//input[@name='submit']")
		//public WebElement submitButton ;
		
		
		public void enterUserName(String user) {
			usernameText.sendKeys("asd");
		}
		
		public void enterPassword(String pass) {
			passwordText.sendKeys();
		}
		
		public void submit() {
			
			WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(10)).
					until(ExpectedConditions.presenceOfElementLocated(By.name("submit")));
			submitButton.click();
		}
	}
		
	