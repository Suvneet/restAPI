package com.qa.tester;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.testbase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;
import com.qa.client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Random;

public class putAPITest extends testbase {
	
	testbase testbases;
	String serviceURL;
	String apiURL;
	String url;
	RestClient restClient;
	Response BDD_response;
	String header_sheet ="headers";
	String url_sheet ="urls";
	
		
		@DataProvider
		public Object[][] getExcelData() {
			Object data[][] = TestUtil.getTestData(header_sheet);
			return data;		
		}
		
		@Test(dataProvider="getExcelData")
		public void validatePut(String key,String value) {
			RestAssured.baseURI = prop.getProperty("URL");
			//given().contentType("application/json")
			BDD_response = given().header(key, value)
			
			.when().post(prop.getProperty("serviceURL"))
			.then().extract().response();
			System.out.println(key);
			System.out.println(value);
			}
		
		@Test
		public void validateResponseTime() {
			long time_taken = BDD_response.getTime();
			//System.out.println("Time taken :"+time_taken);
			if (time_taken > 1500) {
				System.out.println("Response is taking long time. ");			
			}
			else {
				System.out.println("Response time is :"+time_taken);
			}			
		}
	
	
		@Test(enabled=false)
		public void validateRandom() throws JSONException {
		
			JSONObject json = new JSONObject();
			int random = new Random().nextInt(1000);
			json.put("id", random);
			json.put("id", random);
		
		}

}
