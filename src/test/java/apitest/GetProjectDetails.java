package apitest;

import baseclass.ApiBase;
import com.relevantcodes.extentreports.LogStatus;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetProjectDetails extends ApiBase {

	@Test(priority = 4)
	public void getProjectDetails() {
		extentTest = extent.startTest("Create  Test - Fetch project member details");
		Config config = ConfigFactory.load("api-config.properties");
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.header("Content-Type","application/json")
				.header("webmate.user",config.getString("webMateUser"))
				.header("webmate.api-token",config.getString("apiToken"))
				.when()
				.get(config.getString("getTeamDetails").replace("{projectId}",config.getString("projectId")));
		extentTest.log(LogStatus.PASS,"API Request created ");
		String statusLine=res.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
		logger.info("Valid GetBooking Test Completed");
	}
}
