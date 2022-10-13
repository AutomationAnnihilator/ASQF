package test;

import baseclass.ApiBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateBookingTestApi extends ApiBaseTest {

	@Test(priority = 3, groups = {"put"})
	public void ValidUpdate()
	{
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.header("Content-Type","application/json")
				.header("Cookie","token="+ GenerateTokenTestApi.token)
				.body(UpdateBody)
				.when()
				.put("/booking/"+ CreateBookingTestApi.bookingid).then().log().all().assertThat()
				.statusCode(200).extract().response();
		
		String statusLine=res.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
		logger.info("Valid UpdateBooking Test Completed");
	}
	
	
}
