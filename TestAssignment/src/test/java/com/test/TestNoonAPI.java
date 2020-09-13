package com.test;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.util.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class TestNoonAPI {
	public Logger log = LogManager.getLogger(TestNoonAPI.class.getName());
	public Properties prop;
	public String response;
	String accessToken;

	@BeforeTest
	public void getData() throws IOException {
		prop = ReusableMethods.getProperties();
	}

	// Access noon api with valid token payload so get access token
	@Test(priority = 1)
	public void getRequestToken() {
		RestAssured.baseURI = prop.getProperty("baseURI");
		try {

			response = given().log().all().header("Content-Type", "application/json")
					.body(ReusableMethods.gettokenPayload1()).when().post("user/validate").then().log().all()
					.assertThat().statusCode(200).extract().response().asString();

			JsonPath js = new JsonPath(response);
			accessToken = js.getString("data.token");

			Assert.assertEquals(js.getString("success"), "true");
			
			log.info("Received request token and value is::" + accessToken+" and received response as::"+response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Getting error message and unable to get request tokens::" + e.toString());
		}
	}

	// Access API to get users transaction history
	@Test(priority = 2)
	public void getUsersTransactionHistory() {

		RestAssured.baseURI = prop.getProperty("baseURI");
		try {

			response = given().log().all().queryParam("token", accessToken).when().get("user/txn/history").then().log()
					.all().assertThat().statusCode(200).extract().response().asString();

			JsonPath js = new JsonPath(response);
			Assert.assertEquals(js.getString("success"), "true");

			log.info("User is able to view users transaction history successfully::" + response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Getting error message and unable to get users transaction history::" + e.toString());
		}
	}

}
