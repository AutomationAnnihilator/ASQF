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

public class GetUserDetails extends ApiBase {

	@Test(priority = 4)
	public void getTestTemplates() {
		extentTest = extent.startTest("Fetch current user details");
		Config config = ConfigFactory.load("Config.properties");
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.header("Content-Type","application/json")
				.header("webmate.user",config.getString("webMateUser"))
				.header("webmate.api-token",config.getString("apiToken"))
				.when()
				.get(config.getString("getUserDetails"));
		extentTest.log(LogStatus.PASS,"API Request created ");
		String statusLine=res.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");

		JsonPath jsonPathEvaluator = res.jsonPath();
		Assert.assertEquals(jsonPathEvaluator.get("name"),"Aditya Rawat");
		extentTest.log(LogStatus.PASS,"Validated data against name: "+jsonPathEvaluator.get("name"));
		Assert.assertEquals(jsonPathEvaluator.get("email"),"aditya.rawat@nagarro.com");
		extentTest.log(LogStatus.PASS,"Validated data against email: "+jsonPathEvaluator.get("email"));
		Assert.assertEquals(jsonPathEvaluator.get("accountState"),"active");
		extentTest.log(LogStatus.PASS,"Validated data against account state: "+jsonPathEvaluator.get("accountState"));
		logger.info("Current user details Test Completed");
	}
}
