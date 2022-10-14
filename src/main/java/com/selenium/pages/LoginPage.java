package com.selenium.pages;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
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

	Config config = ConfigFactory.load("Config.properties");
	WebDriverWait wait ;
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	}

		@FindBy(how = How.XPATH , using = "//input[@name='email']")
		public WebElement usernameText;
		
		@FindBy(how = How.XPATH , using="//input[@name='password']")
		public WebElement passwordText;
		
		@FindBy(how = How.XPATH , using="//button")
		public WebElement submitButton ;
		
		
		public void enterUserName(String user) {
			wait.until(ExpectedConditions.visibilityOf(usernameText));
			usernameText.sendKeys(user);
		}
		
		public void enterPassword(String pass) {
			wait.until(ExpectedConditions.visibilityOf(passwordText));
			passwordText.sendKeys(pass);
		}
		
		public void submit() {
			submitButton.click();
		}

		public void login() throws InterruptedException {
			enterUserName(config.getString("username"));
			enterPassword(config.getString("password"));
			submit();
		}
	}
		
	