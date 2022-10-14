package baseclass;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;

public class ApiBase {
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public final static Logger logger = Logger.getLogger(ApiBase.class);
	public static String reportPath="./Reports/extentreport.html";

	
	@BeforeMethod
	public static void setUp() {
		Config config = ConfigFactory.load("api-config.properties");
		RestAssured.baseURI = config.getString("url");
		Reporter.log("SetUp Completed");
		logger.info("--------------------------------------- Test Started ---------------------------------------");
		
		System.out.println("--------------------------------------- Test Started -----------------------------------------");
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		logger.info("--------------------------------------- Test Completed ---------------------------------------");
		
		System.out.println("--------------------------------------- Test Completed ---------------------------------------");
	}
	
	@BeforeSuite
	public void setExtent() {
		extent = new ExtentReports(reportPath);
	}
	
	@AfterSuite
	public void endReport() {
		extent.flush();
	}
	
	@AfterMethod
	public void attachScreenshot(ITestResult res) {
		if(res.getStatus()==ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test Case failed");
			extent.endTest(extentTest);
		}
		else if (res.getStatus()==ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case passed");
			extent.endTest(extentTest);
		}
	}
	
	
	//Reading data from file and storing them to use globally
	Config config = ConfigFactory.load("api-config.properties");
	String targetUrl = config.getString("url");
	public static String deviceId = null;
	public static String templateId = null;

}
