package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	
	//get
	public void get(String url) throws ClientProtocolException, IOException {
		
		//GET() Method
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);
		
		//a. STATUS CODE
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("API Response Status Code ----> "+statusCode);
		
		//b. JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("API Response JSON Payload ----> "+responseJson);
		
		//c. All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String,String> allHeaderMap = new HashMap<String,String>();
		for(Header header : headersArray) {
			allHeaderMap.put(header.getName(),header.getValue());
		}
		System.out.println("API Response Header List ----> "+allHeaderMap);
		
	}

}
