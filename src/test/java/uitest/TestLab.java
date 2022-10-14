package uitest;

import baseclass.UiBaseTest;
import com.selenium.pages.TestLabPage;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TestLab extends UiBaseTest {

	Config config = ConfigFactory.load("Config.properties");
	String path = config.getString("downloadpath");

	
	@Test 
	public void TestLab() throws InterruptedException {
		LoginTestUi.validLogin();
		TestLabPage testLab = new TestLabPage(driver);
		extentTest = extent.startTest("Valid Test Report");
		testLab.setTeamHome();
		testLab.setTestLabLogo();
		testLab.setReportTimeDrop();
		testLab.setPastDay();
		testLab.setStatus();
		testLab.setStatusPassed();
		testLab.clickSave();
		testLab.setReportName();
		testLab.clickSaveButton();
	}

	@Test
	public void TestCSV() throws InterruptedException, IOException {
		LoginTestUi.validLogin();
		TestLabPage testLab = new TestLabPage(driver);
		extentTest = extent.startTest("Verify CSV Data");
		testLab.setTeamHome();
		testLab.setTestLabLogo();
		testLab.clickExportCsv();
		int noOfLines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(String.format("C:\\Users\\%s\\Downloads\\webmate_testreport.csv",path)))) {
			while (reader.readLine() != null) {
				noOfLines++;
			}
		}
		Assert.assertEquals(testLab.getTestReport(),noOfLines-1 + " Tests");
	}
}

