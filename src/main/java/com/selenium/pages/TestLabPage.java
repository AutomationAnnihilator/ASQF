package com.selenium.pages;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestLabPage {

	
	WebDriver driver;

	Config config = ConfigFactory.load("Config.properties");
	WebDriverWait wait ;

	public TestLabPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	@FindBy(how=How.XPATH , using="//div[@class='p-element wm-projectsummary-description-header']")
	public WebElement teamHome;
	
	@FindBy(how = How.XPATH , using="//img[@class='wm-sidenav-item-logo-testlab']")
	public WebElement testLabLogo ;

	@FindBy(how = How.XPATH , using="/html[1]/body[1]/app-root[1]/app-root[1]/webmate[1]/div[1]/div[2]/projects[1]/div[1]/div[2]/div[1]/wm-project-testlab[1]/wm-project-testreports[1]/div[1]/div[1]/div[1]/div[1]/div[2]/p-dropdown[1]/div[1]/div[2]/span[1]")
	public WebElement reportTimeDrop ;
	
	@FindBy(how = How.XPATH , using="//span[normalize-space()='Past 24 hours']")
	public WebElement pastDay ;
	
	@FindBy(how = How.XPATH , using="/html[1]/body[1]/app-root[1]/app-root[1]/webmate[1]/div[1]/div[2]/projects[1]/div[1]/div[2]/div[1]/wm-project-testlab[1]/wm-project-testreports[1]/div[1]/div[1]/div[1]/div[1]/div[4]/wm-multiselect[1]/div[1]/div[3]/span[1]")
	public WebElement statusDrop;


	@FindBy(how = How.XPATH , using="//span[normalize-space()='passed (2)']")
	public WebElement statusPassed;

	@FindBy(how = How.XPATH , using="//span[@class='p-element spec-save-toggle ng-star-inserted']//*[name()='svg']")
	public WebElement saveClick;

	@FindBy(how = How.XPATH , using="//input[@placeholder = 'Report Name']")
	public WebElement reportName;

	@FindBy(how = How.XPATH , using="//span[@ptooltip='Save']")
	public WebElement saveButton;

	@FindBy(how = How.XPATH , using="//button[@label='Export CSV']")
	public WebElement exportCsv;

	@FindBy(how = How.XPATH , using="//div[@class='statistics-summary-total']")
	public WebElement testReport;
	
	public void setTeamHome() {
		teamHome.click();
	}
	
	public void setTestLabLogo() {
		testLabLogo.click();
	}
	public void setReportTimeDrop() {
		reportTimeDrop.click();
	}
	public void setPastDay() {
		pastDay.click();
	}
	

	public void setStatus() throws InterruptedException {
		statusDrop.click();
	}

	public void setStatusPassed(){
		wait.until(ExpectedConditions.visibilityOf(statusPassed));
		statusPassed.click();
	}

	public void clickSave()
	{
		saveClick.click();
	}

	public void setReportName(){
		reportName.sendKeys("Test");
	}

	public void clickSaveButton() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
	}

	public void clickExportCsv() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(exportCsv));
		exportCsv.click();
	}

	public String getTestReport()
	{
		String t = testReport.getText();
		return t;
	}



}


