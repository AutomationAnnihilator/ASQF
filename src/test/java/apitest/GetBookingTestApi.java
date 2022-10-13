package apitest;

import baseclass.ApiBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetBookingTestApi extends ApiBaseTest {

	@Test(priority = 4, groups = {"get"})
	public void Getbooking() {
		
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.header("Content-Type","application/json")
				.when()
				.get("/booking/"+ CreateBookingTestApi.bookingid).then().log().all().assertThat()
				.statusCode(200).extract().response();
		
		String statusLine=res.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
		logger.info("Valid GetBooking Test Completed");
	}
}
