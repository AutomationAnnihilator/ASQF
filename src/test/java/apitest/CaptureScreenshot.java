
package apitest;

import baseclass.ApiBase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CaptureScreenshot extends ApiBase {
	
	@Test(priority = 2)
	public void ValidCreateBooking() {

			Config config = ConfigFactory.load("Config.properties");
			extentTest = extent.startTest("Create  Test - Capture ScreenShot");
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.header("Content-Type","application/json")
				.header("webmate.user",config.getString("webMateUser"))
				.header("webmate.api-token",config.getString("apiToken"))
				.when()
				.post(config.getString("screenShot").replace("{deviceId}",deviceId)).then().log().all().assertThat()
				.statusCode(200).extract().response();
			extentTest.log(LogStatus.INFO,"API Request created ");
		String statusLine=res.getStatusLine();
			extentTest.log(LogStatus.INFO, res.getStatusLine());
		Assert.assertEquals(res.getStatusCode(),200);

		extentTest.log(LogStatus.PASS, "Capture Screenshot Test Passed Sucessfully");
		extent.endTest(extentTest);

		
	}
	
	
}
