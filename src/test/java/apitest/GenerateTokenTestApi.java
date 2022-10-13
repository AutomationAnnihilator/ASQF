package apitest;

import java.util.HashMap;
import java.util.Map;

import baseclass.ApiBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.Util;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GenerateTokenTestApi extends ApiBaseTest {
	
	@Test(priority = 1, groups = {"post"})
	public void Token() {
		try {
			extentTest = extent.startTest("GenerateToken Test - Valid");
		
		Map<String,String> user = new HashMap<String,String>();
		user.put("username", username);
		user.put("password", password);
		
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.header("Content-Type","application/json")
				.body(user)
				.when()
				.post("auth").then().log().all().assertThat()
				.statusCode(200).extract().response();
		
		JsonPath response = Util.rawToJson(res);
		token=response.get("token");
		System.out.println("Fetched token is :" + token);
		
		String statusLine=res.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
		logger.info("Valid Token generation Test Completed");
		extentTest.log(LogStatus.PASS, "Valid Generate Token Test Passed Sucessfully");
		extent.endTest(extentTest);
		}
		catch(AssertionError e) {
			extentTest.log(LogStatus.FAIL,"Valid Generate Token Test failed");
		}
	}

}
