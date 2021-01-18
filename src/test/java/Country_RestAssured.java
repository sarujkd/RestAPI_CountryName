import static io.restassured.RestAssured.when;

import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import ExtentReport.ExtentReportClass;

import io.restassured.RestAssured;

import io.restassured.response.Response;

public class Country_RestAssured extends ExtentReportClass {

	Scanner sc = new Scanner(System.in);

	@BeforeClass

	public void setup() {
		CreateReport();

	}

	@Test(priority=1,enabled=true)
	public void test1_getAll_Info() {

		logger = report.startTest("test1_getAllInfo");
		logger.log(LogStatus.INFO, "test1_getAllInfo test is started");
		Response res = RestAssured.get("https://restcountries.eu/rest/v2/all");

		System.out.println("\nResponse ="+ res.asString());
		int statusCode = res.getStatusCode();
		System.out.println("StatusCode= " + statusCode);
		Assert.assertEquals(statusCode, 200);
		logger.log(LogStatus.INFO, "statusCode " + statusCode + "is displayed");
		logger.log(LogStatus.INFO, "Response is displayed");
		logger.log(LogStatus.PASS, "test1_getAll_Info test is passed");

	}

	@Test(priority=2,enabled = true)

	public void test2_AddCountryCode_ToGetCapital() {

		logger = report.startTest("test2_AddCountryCode_ToGetCapitalName");
		logger.log(LogStatus.INFO, "test2_AddCountryCode_ToGetCapitalName test is started");

		String CountryCode = "notExit";
		while (!CountryCode.equalsIgnoreCase("exit")) {

			System.out.println("Enter alpha2Code or else Enter exit to quit");
			CountryCode = sc.nextLine();

			if (CountryCode.equalsIgnoreCase("exit"))
				break;
			else {

				Response res = RestAssured.get("https://restcountries.eu/rest/v2/alpha/{code}", CountryCode);

				int StatusCode = res.getStatusCode();

				System.out.println("ContentType = " + res.contentType());
				System.out.println("StatusCode = " + StatusCode);
				System.out.println("Response body: " + res.asString());
				System.out.println("Capital = " + res.jsonPath().get("capital"));

				Assert.assertTrue(StatusCode == 200);
			}
		}
		logger.log(LogStatus.INFO, "Capital is displayed and user quit by entering exit ");
		logger.log(LogStatus.PASS, "test2_get_CapitalName test is passed");

	}

	@Test(priority=3,enabled = true)

	public void test3_AddCountryName_TogetCapital() {

		logger = report.startTest("test3_ get_CapitalName");
		logger.log(LogStatus.INFO, "test3_get_CapitalName test is started");

		String name = "notExit";

		while (!name.equalsIgnoreCase("exit")) {
			System.out.println("Enter name or else Enter exit to quit");
			name = sc.nextLine();
			if (name.equalsIgnoreCase("exit"))
				break;
			else {

				Response res = RestAssured.get("https://restcountries.eu/rest/v2/name/{Cname}", name);
				System.out.println(
						"Capital of " + res.jsonPath().getString("name") + " is: " + res.jsonPath().get("capital"));

				int statusCode = res.statusCode();
				Assert.assertTrue(statusCode == 200);

			}
		}
		logger.log(LogStatus.INFO, "Capital is displayed");
		logger.log(LogStatus.PASS, "test3_get_CapitalName test is passed");
		sc.close();
	}

	@Test(priority=4,enabled = true)
	public void test4_Invalid_HTTPMethod() {

		logger = report.startTest("test4_Invalid_HTTPMethod");
		logger.log(LogStatus.INFO, "test4_Invalid_HTTPMethod test is started");
		Response res = RestAssured.post("https://restcountries.eu/rest/v2/alpha?codes=IN");

		int statusCode = res.getStatusCode();

		System.out.println("StatusCode= " + statusCode);
		Assert.assertEquals(statusCode, 405);
		logger.log(LogStatus.INFO, "statusCode "+statusCode+" is displayed");
		logger.log(LogStatus.PASS, "test4_Invalid_HTTPMethod test is passed");

	}

	@Test(priority=5,enabled = false)
	public void test5_Invalid_URL() {

		logger = report.startTest("test5_Invalid_URL");
		logger.log(LogStatus.INFO, "test5_Invalid_URL test is started");
		Response res = RestAssured.get("https://restcountries.eu/rest/v2//alpha?codes=IN");

		int statusCode = res.getStatusCode();
		System.out.println("StatusCode= " + statusCode);
		Assert.assertEquals(statusCode, 404);
		logger.log(LogStatus.INFO, "statusCode "+statusCode +" is displayed");
		logger.log(LogStatus.PASS, "test5_Invalid_URL test is passed");

	}

	@AfterClass

	public void TearDown() {

		CloseReport();
	}

}
