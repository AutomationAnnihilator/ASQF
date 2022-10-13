package apitest;

import baseclass.ApiBase;
import com.relevantcodes.extentreports.LogStatus;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class GetNumberOfDevices extends ApiBase {

	@Test(priority = 1)
	public void getDeviceId() {
		extentTest = extent.startTest("Fetch Device Id of available devices");
		Config config = ConfigFactory.load("Config.properties");
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.header("Content-Type","application/json")
				.header("webmate.user",config.getString("webMateUser"))
				.header("webmate.api-token",config.getString("apiToken"))
				.when()
				.get(config.getString("getDevices").replace("{projectId}",config.getString("projectId"))).then().log().all().assertThat()
				.statusCode(200).extract().response();
		extentTest.log(LogStatus.PASS,"API Request created ");
		String statusLine=res.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");


		List<String> jsonResponse = res.jsonPath().getList("$");
		Assert.assertFalse(jsonResponse.isEmpty());
		extentTest.log(LogStatus.PASS,jsonResponse.toString());

		deviceId=jsonResponse.get(jsonResponse.size()-1);

		logger.info("Valid GetBooking Test Completed");
	}
}
