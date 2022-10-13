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

import java.util.List;

public class GetTestTemplates extends ApiBase {

	@Test(priority = 4)
	public void getTestTemplates() {
		extentTest = extent.startTest("Fetch test templates available on the website");
		Config config = ConfigFactory.load("Config.properties");
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.header("Content-Type","application/json")
				.header("webmate.user",config.getString("webMateUser"))
				.header("webmate.api-token",config.getString("apiToken"))
				.when()
				.get(config.getString("getTemplates").replace("{projectId}",config.getString("projectId")));
		extentTest.log(LogStatus.PASS,"API Request created ");
		String statusLine=res.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");

		JsonPath jsonPathEvaluator = res.jsonPath();
		templateId=jsonPathEvaluator.get("data[0].info.id");
		extentTest.log(LogStatus.PASS,templateId);
		logger.info("Valid GetBooking Test Completed");
	}
}
