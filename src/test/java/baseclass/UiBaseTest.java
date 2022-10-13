package baseclass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class UiBaseTest {


	public static WebDriver driver;
		
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
		public static void close() {
			driver.close();
		}
		
	}


