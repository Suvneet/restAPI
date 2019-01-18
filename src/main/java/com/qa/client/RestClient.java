package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;



public class RestClient {
	
	//1. Get method without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException, JSONException {
		CloseableHttpClient  httpClient = HttpClients.createDefault();   //make client connection
		HttpGet httpget = new  HttpGet(url);    // make connection with url
		CloseableHttpResponse closeAbleHttpResponse =  httpClient.execute(httpget);   //hit the get url
		return closeAbleHttpResponse;	
	}
	
	//2. Get method with headers
		public CloseableHttpResponse get(String url, HashMap <String, String> headerMap) throws ClientProtocolException, IOException, JSONException {
			CloseableHttpClient  httpClient = HttpClients.createDefault();   //make client connection
			HttpGet httpget = new  HttpGet(url);    // make connection with url
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpget.addHeader(entry.getKey(),entry.getValue());			
			}			
			CloseableHttpResponse closeAbleHttpResponse =  httpClient.execute(httpget);   //hit the get url
			return closeAbleHttpResponse;	
		}
		
		
	//3. Post method with headers
		public CloseableHttpResponse post(String url, String entityString, HashMap <String, String> headerMap) throws ClientProtocolException, IOException, JSONException {
			CloseableHttpClient  httpClient = HttpClients.createDefault();   //make client connection
			HttpPost httppost = new  HttpPost(url);    // make connection with url
			httppost.setEntity(new StringEntity(entityString));
			
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httppost.addHeader(entry.getKey(),entry.getValue());			
			}			
			CloseableHttpResponse closeAbleHttpResponse =  httpClient.execute(httppost);   //hit the get url
			return closeAbleHttpResponse;	
				}
		
		
	

}
