package baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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

	static File file = new File ("./Resources/config.properties");
	static FileInputStream fis= null;
	static Properties prop = new Properties();
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public final static Logger logger = Logger.getLogger(ApiBaseTest.class);
	
	 static {
		
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@BeforeMethod
	public static void setUp() {
		
		RestAssured.baseURI = prop.getProperty("url");
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
		extent = new ExtentReports("./Resources/extentreport.html");
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
	public String username = prop.getProperty("username");
	public String password = prop.getProperty("password");
	public String url = prop.getProperty("url");
	
	public static String token = null;
	public static int bookingid;
	
	public String firstname = prop.getProperty("firstname");
	public String lastname = prop.getProperty("lastname");
	public int totalprice = Integer.parseInt(prop.getProperty("totalprice"));
	boolean depositpaid = Boolean.parseBoolean(prop.getProperty("depositpaid"));
	public String checkin = prop.getProperty("checkin");
	public String checkout = prop.getProperty("checkout");
	public String additionalneeds = prop.getProperty("additionalneeds");
	
	public String firstname_u = prop.getProperty("firstname_u");
	public String lastname_u = prop.getProperty("lastname_u");
	public int totalprice_u = Integer.parseInt(prop.getProperty("totalprice_u"));
	boolean depositpaid_u = Boolean.parseBoolean(prop.getProperty("depositpaid_u"));
	public String checkin_u = prop.getProperty("checkin_u");
	public String checkout_u = prop.getProperty("checkout_u");
	public String additionalneeds_u = prop.getProperty("additionalneeds_u");
	
	
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
