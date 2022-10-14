package uitest;

import baseclass.UiBaseTest;
import com.selenium.pages.TestDevicesPage;
import com.selenium.pages.TestLabPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class DevicesTest extends UiBaseTest {

	@Test
	public void DevicesMobile() throws InterruptedException {
		LoginTestUi.validLogin();
		TestLabPage testLab = new TestLabPage(driver);
		testLab.setTeamHome();
		extentTest = extent.startTest("Device Access Test");
		TestDevicesPage devices = new TestDevicesPage(driver);
		devices.clickDevicesTab();
		String mainWindowHandle = driver.getWindowHandle();
		devices.clickMobileAccess();
		Thread.sleep(3000);
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();

		while (iterator.hasNext()) {
			String ChildWindow = iterator.next();
			if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				Boolean b = driver.findElement(By.xpath("(//canvas)[1]")).isDisplayed();
				Assert.assertTrue(b);
			}
		}
		driver.switchTo().window(mainWindowHandle);
	}


	@Test
	public void DesktopDeployment() throws InterruptedException {
		LoginTestUi.validLogin();
		TestLabPage testLab = new TestLabPage(driver);
		testLab.setTeamHome();
		TestDevicesPage devices = new TestDevicesPage(driver);
		devices.clickDevicesTab();
		extentTest = extent.startTest("Deployment Test");
		devices.clickSanwichDeployement();
		devices.clickEnableDeployement();
		Boolean a = driver.findElement(By.xpath("//fa-icon[@icon=\"history\"]")).isDisplayed();
		Assert.assertTrue(a);
	}


}

