package apitest;

import baseclass.ApiBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteBookingTestApi extends ApiBaseTest {

	@Test(priority = 5, groups = {"delete"})
	public void ValidDelete() {
	try {
		extentTest = extent.startTest("Delete Booking Test - Valid");
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.header("Content-Type","application/json")
				.header("Cookie","token="+ GenerateTokenTestApi.token)
				.when()
				.delete("/booking/"+ CreateBookingTestApi.bookingid).then().log().all().assertThat()
				.statusCode(201).extract().response();
		
		String statusLine=res.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 201 Created");
		logger.info("Valid Delete Booking Test Completed");
		extentTest.log(LogStatus.PASS, "Valid DeleteBooking Test Passed Sucessfully");
		extent.endTest(extentTest);
		}
		catch(AssertionError e) {
			extentTest.log(LogStatus.FAIL,"Valid Delete Booking Test failed");
		}
		
	}
	
	
		@Test(priority = 6, groups = {"delete"})
		public void InvalidDelete() {
			
			try {
				extentTest = extent.startTest("Delete Booking Test - InValid");
			RequestSpecification httpRequest = RestAssured.given();
			Response res = httpRequest.header("Content-Type","application/json")
					.header("Cookie","token="+ GenerateTokenTestApi.token)
					.when()
					.delete("/booking/"+ CreateBookingTestApi.bookingid).then().log().all().assertThat()
					.statusCode(405).extract().response();
			
			logger.info("InValid DeleteBooking Test Completed");
			extentTest.log(LogStatus.PASS, "InValid DeleteBooking Test Passed Sucessfully");
			extent.endTest(extentTest);
			}
			catch(AssertionError e) {
				extentTest.log(LogStatus.FAIL,"InValid Delete Booking Test failed");
			}
			
		}
}
	