package test;

import baseclass.ApiBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.Reusable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateBookingTestApi extends ApiBaseTest {
	
	@Test(priority = 2, groups = {"post"})
	public void ValidCreateBooking() {
		try {
			extentTest = extent.startTest("Create Booking Test - Valid");
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.header("Content-Type","application/json")
				.body(CreateBody)
				.when()
				.post("booking").then().log().all().assertThat()
				.statusCode(200).extract().response();
		
		JsonPath response = Reusable.rawToJson(res);
		bookingid=response.get("bookingid");
		System.out.println("Fetched Booking ID is :" + bookingid);
		
		String statusLine=res.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
		
		logger.info("Valid CreateBooking Test Completed");
		
		extentTest.log(LogStatus.PASS, "Valid Create Booking Test Passed Sucessfully");
		extent.endTest(extentTest);
		}
		catch(AssertionError e) {
			extentTest.log(LogStatus.FAIL,"Valid Create Booking Test failed");
		}
		
	}
	
	
}
