package baseclass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
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
		
		static File file = new File ("./Resources/config.properties");
		static FileInputStream fis= null;
		static Properties prop = new Properties();
		
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
		public static void initializeDriver() {
			switch (prop.getProperty("browserName")){
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
			
			driver.get(prop.getProperty("urlui"));
		}
		@AfterMethod
		public static void close(ITestResult res) {
			driver.close();
			if(res.getStatus()== ITestResult.FAILURE) {
				extentTest.log(LogStatus.PASS, "Test Case failed");
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


