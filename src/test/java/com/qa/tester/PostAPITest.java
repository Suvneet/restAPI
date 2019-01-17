package com.qa.tester;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.testbase;
import com.qa.client.RestClient;
import com.qa.data.users;


public class PostAPITest extends testbase {
	
	testbase testbases;
	String serviceURL;
	String apiURL;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeAbleHttpResponse;
	
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException, JSONException {
		testbases = new testbase();
		serviceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");
		url = serviceURL + apiURL;
		System.out.println("");
		System.out.println("==========================================");
		System.out.println("          Inside POST API Class            ");
		System.out.println("==========================================");	
		System.out.println("");
	}
		
	@Test
	public void PostAPItest() throws JsonGenerationException, JsonMappingException, IOException, JSONException {
		restClient = new RestClient();
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("Content-Type", "application/json");
		
		//Jackson API
		ObjectMapper mapper = new ObjectMapper();
		users user = new users("morpheus","leader");  //Expected user object
		mapper.writeValue( new File("C:\\Users\\Suvneet.Singh\\eclipse-workspace\\restapitest2\\src\\main\\java\\com\\qa\\data\\user.json"), user);
		
		// JAVA Object to JSON in string
		String userJsonString = mapper.writeValueAsString(user);
		System.out.println(userJsonString);
		
		closeAbleHttpResponse = restClient.post(url, userJsonString, hashmap);
		
		//1. Status code   #########################
		int statusCode = closeAbleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("==================================");
		System.out.println("   Status code is : "+statusCode);
		System.out.println("==================================");
		Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_201, "Status code is not 201");
		
		//2. JSON string   #########################
		String responseString = EntityUtils.toString(closeAbleHttpResponse.getEntity(), "UTF-8");
		JSONObject Responsejson = new JSONObject(responseString);
		System.out.println("Response JSON from API --->"+Responsejson);
		
		//JSON to Java Object
		users userResObj = 	mapper.readValue(responseString, users.class); //Actual user object
		System.out.println(userResObj);
		
		Assert.assertTrue(user.getName().equals(userResObj.getName()));
		//System.out.println(user.getName().equals(userResObj.getName()));
		Assert.assertTrue(user.getJob().equals(userResObj.getJob()));
		//System.out.println(user.getJob().equals(userResObj.getJob()));
		
	/*	System.out.println("                                                ");
		System.out.println("==================================");
		System.out.println("     VALIDATE RESPONSE VALUE's    ");
		System.out.println("==================================");
		
		String perPageValue = TestUtil.getValueByJPath(Responsejson, "/per_page");
		Assert.assertEquals(perPageValue, "3");
		System.out.println("ASSERTION PASSED : Per page value : "+perPageValue);
		m,
		
		String total_pages = TestUtil.getValueByJPath(Responsejson, "/total_pages");
		Assert.assertEquals(Integer.parseInt(total_pages), 4);
		System.out.println("ASSERTION PASSED : Total page check : "+total_pages);	
		
		System.out.println();	
		String id = TestUtil.getValueByJPath(Responsejson, "data[0]/id");
		String first_name = TestUtil.getValueByJPath(Responsejson, "data[0]/first_name");
		String last_name = TestUtil.getValueByJPath(Responsejson, "data[0]/last_name");
		String avatar = TestUtil.getValueByJPath(Responsejson, "data[0]/avatar");
		System.out.println("User_id :"+id);
		System.out.println("First_name :"+first_name);
		System.out.println("Last name :"+last_name);
		System.out.println("Avatar link :"+avatar);
		
		//3. All headers ###########################
		int i=1;
		Header[] headerArray = closeAbleHttpResponse.getAllHeaders();
		HashMap<String, String> allheaders = new HashMap<String, String>();
		System.out.println("                                                ");
		System.out.println("==================================");
		System.out.println("      CHECK RESPONSE HEADER's     ");
		System.out.println("==================================");
		for (Header header : headerArray) {
			allheaders.put(header.getName(), header.getValue());
			System.out.println("API_Header"+i+"-->"+header);
			i++;
		}*/
	}
	
	@AfterClass
	public void teardown() {
		System.out.println("");
		System.out.println("==========================================");
		System.out.println("          Out from POST API Class");
		System.out.println("==========================================");	
		System.out.println("");
		
	}
}
