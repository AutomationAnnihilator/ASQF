package com.selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class TestDevicesPage {


	WebDriver driver;

	public TestDevicesPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	


	@FindBy(how = How.XPATH , using="//img[@class='wm-sidenav-item-logo-devices']")
	public WebElement testDevicesLogo ;

	@FindBy(how = How.XPATH , using="//span[@class='label'][1]")
	public WebElement mobileAccess ;

	@FindBy(how = How.XPATH , using="/html[1]/body[1]/app-root[1]/app-root[1]/webmate[1]/div[1]/div[2]/projects[1]/div[1]/div[2]/div[1]/wm-devices[1]/div[1]/div[2]/div[2]/div[2]/wm-resource-summary-container[1]/div[1]/div[1]/wm-resource-summary[1]/wm-deviceslot-summary[1]/div[1]/div[1]/div[3]/div[3]/button[1]/fa-icon[1]/*[name()='svg'][1]")
	public WebElement sandwichDeployment ;

	@FindBy(how = How.XPATH , using="//span[normalize-space()='Enable daily redeployment']")
	public WebElement enableDeployemnet ;


	public void clickDevicesTab()
	{
		testDevicesLogo.click();
	}

	public void clickMobileAccess(){
		mobileAccess.click();
	}

	public void clickSanwichDeployement(){
		sandwichDeployment.click();
	}

	public void clickEnableDeployement()
	{
		enableDeployemnet.click();
	}





}


