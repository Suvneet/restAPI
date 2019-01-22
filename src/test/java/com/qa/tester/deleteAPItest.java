package com.qa.tester;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.Test;

import com.qa.base.testbase;
import com.qa.client.RestClient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class deleteAPItest extends testbase{

	testbase testbases;
	String serviceURL;
	String apiURL;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeAbleHttpResponse;
	String userPerPage;
	String totalpagesInResponse;
	Response resp;
	
	@Test
	public void deleteAPI() {
		RestAssured.baseURI=prop.getProperty("URL");
			given().contentType("application/json")
			.when().delete(prop.getProperty("serviceURL"))
			.then()
			.statusCode(RESPONSE_STATUS_CODE_200)
			.extract().response();				
	}
	
}
