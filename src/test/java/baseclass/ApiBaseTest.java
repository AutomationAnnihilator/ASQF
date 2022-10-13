package baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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

public class ApiBaseTest {
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public final static Logger logger = Logger.getLogger(ApiBaseTest.class);

	
	@BeforeMethod
	public static void setUp() {
		Config config = ConfigFactory.load("Config.properties");
		String targetUrl = config.getString("url");
		RestAssured.baseURI = targetUrl;
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
		extent = new ExtentReports("./Reports/extentreport.html");
	}
	
	@AfterSuite
	public void endReport() {
		extent.flush();
		//extent.close();
	}
	
	@AfterMethod
	public void attachScreenhsot(ITestResult res) {
		if(res.getStatus()==ITestResult.FAILURE) {
			extentTest.log(LogStatus.PASS, "Test Case failed");
			extent.endTest(extentTest);
		}
		else if (res.getStatus()==ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case passed");
			extent.endTest(extentTest);
		}
	}
	
	
	//Reading data from file and storing them to use globally
	Config config = ConfigFactory.load("Config.properties");
	String targetUrl = config.getString("url");
	public String username = config.getString("username");
	public String password = config.getString("password");
	public String url = config.getString("url");
	
	public static String token = null;
	public static int bookingid;
	
	public String firstname = config.getString("firstname");
	public String lastname = config.getString("lastname");
	public int totalprice = Integer.parseInt(config.getString("totalprice"));
	boolean depositpaid = Boolean.parseBoolean(config.getString("depositpaid"));
	public String checkin = config.getString("checkin");
	public String checkout = config.getString("checkout");
	public String additionalneeds = config.getString("additionalneeds");
	
	public String firstname_u = config.getString("firstname_u");
	public String lastname_u = config.getString("lastname_u");
	public int totalprice_u = Integer.parseInt(config.getString("totalprice_u"));
	boolean depositpaid_u = Boolean.parseBoolean(config.getString("depositpaid_u"));
	public String checkin_u = config.getString("checkin_u");
	public String checkout_u = config.getString("checkout_u");
	public String additionalneeds_u = config.getString("additionalneeds_u");
	
	
	public String CreateBody = "{\r\n"
			+ "    \"firstname\" : \""+firstname+"\",\r\n"
			+ "    \"lastname\" : \""+lastname+"\",\r\n"
			+ "    \"totalprice\" : "+totalprice+",\r\n"
			+ "    \"depositpaid\" : "+depositpaid+",\r\n"
			+ "    \"bookingdates\" : {\r\n"
			+ "        \"checkin\" : \""+checkin+"\",\r\n"
			+ "        \"checkout\" : \""+checkout+"\"\r\n"
			+ "    },\r\n"
			+ "    \"additionalneeds\" : \""+additionalneeds+"\"\r\n"
			+ "}";
	
	public String UpdateBody = "{\r\n"
			+ "    \"firstname\" : \""+firstname_u+"\",\r\n"
			+ "    \"lastname\" : \""+lastname_u+"\",\r\n"
			+ "    \"totalprice\" : "+totalprice_u+",\r\n"
			+ "    \"depositpaid\" : "+depositpaid_u+",\r\n"
			+ "    \"bookingdates\" : {\r\n"
			+ "        \"checkin\" : \""+checkin_u+"\",\r\n"
			+ "        \"checkout\" : \""+checkout_u+"\"\r\n"
			+ "    },\r\n"
			+ "    \"additionalneeds\" : \""+additionalneeds_u+"\"\r\n"
			+ "}";
}
