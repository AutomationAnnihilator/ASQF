package test;

import baseclass.ApiBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PingTestApi extends ApiBaseTest {

	@BeforeClass
	public static void pingTest() {
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response res = httpRequest
				.header("Content-Type","application/json")
				.when()
				.get("ping").then().log().all().assertThat()
				.statusCode(201).extract().response();

		String statusLine=res.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 201 Created");
		
		logger.info("Valid Ping Test Completed");
	}
}
