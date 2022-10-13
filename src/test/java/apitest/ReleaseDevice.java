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

public class ReleaseDevice extends ApiBase {

	@Test(priority = 3)
	public void ValidDelete() {
	try {
		Config config = ConfigFactory.load("Config.properties");
		extentTest = extent.startTest("Delete Booking Test - Valid");
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.header("Content-Type","application/json")
				.header("webmate.user",config.getString("webMateUser"))
				.header("webmate.api-token",config.getString("apiToken"))
				.when()
				.delete(config.getString("deleteDevice").replace("{deviceId}",deviceId)).then().log().all().assertThat()
				.statusCode(201).extract().response();
		extentTest.log(LogStatus.INFO,"API Request created ");

		Assert.assertEquals(res.getStatusCode(),203);
		logger.info("Valid Delete Booking Test Completed");
		extentTest.log(LogStatus.PASS, "Valid Delete Booking Test Passed Successfully");
		extent.endTest(extentTest);
		}
		catch(AssertionError e) {
			extentTest.log(LogStatus.FAIL,"Valid Delete Booking Test failed");
		}
	}
}
