package baseclass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class UiBaseTest {


	public static WebDriver driver;

	public static ExtentReports extent;
	public static ExtentTest extentTest;

		@BeforeMethod
		public static void initializeDriver() {
			Config config = ConfigFactory.load("Config.properties");
			String targetUrl = config.getString("url");
			switch (targetUrl){
				case "Chrome":
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					break;
				case "Firefox":
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				case "Edge":
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			}
		}
		
		@BeforeMethod
		public static void openUrl() {
			Config config = ConfigFactory.load("Config.properties");
			String targetUrl = config.getString("urlui");
			driver.get(targetUrl);
		}
		@AfterMethod
		public static void close(ITestResult res) {
			driver.close();
			if(res.getStatus()== ITestResult.FAILURE) {
				extentTest.log(LogStatus.FAIL, "Test Case failed");
				extent.endTest(extentTest);
			}
			else if (res.getStatus()==ITestResult.SUCCESS) {
				extentTest.log(LogStatus.PASS, "Test Case passed");
				extent.endTest(extentTest);
			}
		}

	@BeforeSuite
	public void setExtent() {
		extent = new ExtentReports("./Reports/extentreport.html");
	}

	@AfterSuite
	public void endReport() {
		extent.flush();
	}
		
	}


