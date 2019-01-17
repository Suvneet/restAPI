package com.qa.tester;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.base.testbase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPIwithHeaders extends testbase {
	
	testbase testbases;
	String serviceURL;
	String apiURL;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeAbleHttpResponse;
	String userPerPage;
	String totalpagesInResponse;
	
	
	//Logger log = Logger.getLogger(GetAPIwithHeaders.class);
	
	@BeforeClass
	public void setup() throws ClientProtocolException, IOException, JSONException {		
		testbases = new testbase();
		serviceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");
		url = serviceURL + apiURL;
		userPerPage = prop.getProperty("perPageUser");
		totalpagesInResponse =prop.getProperty("totalPages");
		//log.info("Inside GetAPIwithHeaderClass");
		System.out.println("");
		System.out.println("==========================================");
		System.out.println("       Inside GET API With Headers        ");
		System.out.println("==========================================");	
		System.out.println("");
		
		}

	@Test(enabled = true )
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException, JSONException {
		restClient = new RestClient();		
		//PASS HEADER's TO REQUEST
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("Content-Type", "application/json");		
		closeAbleHttpResponse = restClient.get(url);
		System.out.println("");
		System.out.println("==================================");
		System.out.println("       Get Request hitted!!!      ");
		System.out.println("==================================");	
		System.out.println("");
		}
	
	@Test(enabled = true)
	public void validateStatusCode() {
		//1. Status code   #########################
		int statusCode = closeAbleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("");
		System.out.println("==================================");
		System.out.println("   Status code is : "+statusCode);
		System.out.println("==================================");	
		System.out.println("");
		Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200, "ASSERTION Failed :Status code is not 200!!!");
		System.out.println("ASSERTION Passed : Status code is as expected!!!");
		}
		
		
	@Test(enabled = true)
		public void validateResponseData() throws JSONException, ParseException, IOException {		
			//2. JSON string   #########################
			String responseString = EntityUtils.toString(closeAbleHttpResponse.getEntity(), "UTF-8");
			JSONObject Responsejson = new JSONObject(responseString);
			//System.out.println("Response JSON from API --->"+Responsejson);
				
			System.out.println("                                                ");
			System.out.println("==================================");
			System.out.println("     VALIDATE RESPONSE VALUE's    ");
			System.out.println("==================================");
				
			String perPageValue = TestUtil.getValueByJPath(Responsejson, "/per_page");
							
			Assert.assertEquals(perPageValue, userPerPage, "ASSERTION Failed : Per page value is not matched");
			System.out.println("ASSERTION Passed : Per page value is as expected");
				
				
			String total_pages = TestUtil.getValueByJPath(Responsejson, "/total_pages");
				
			Assert.assertEquals(Integer.parseInt(total_pages), 4, "ASSERTION Failed : Total page is is not matched");
			System.out.println("ASSERTION Passed : Total page is as expected");
				
			System.out.println();	
			String id = TestUtil.getValueByJPath(Responsejson, "data[0]/id");
			String first_name = TestUtil.getValueByJPath(Responsejson, "data[0]/first_name");
			String last_name = TestUtil.getValueByJPath(Responsejson, "data[0]/last_name");
			String avatar = TestUtil.getValueByJPath(Responsejson, "data[0]/avatar");
			System.out.println("User_id :"+id);
			System.out.println("First_name :"+first_name);
			System.out.println("Last name :"+last_name);
			System.out.println("Avatar link :"+avatar);
			}
		
		@Test(enabled = true)
		public void validateHeaders() {	
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
					}
			System.out.println("==================================");
			System.out.println("TOTAL number of header's in response :"+i);
			System.out.println("==================================");
			System.out.println("");			
			}
		
		@AfterClass
		public void teardown() {
			System.out.println("");
			System.out.println("==========================================");
			System.out.println("Out from GET API With Headers");
			System.out.println("==========================================");	
			System.out.println("");
			
		}

}