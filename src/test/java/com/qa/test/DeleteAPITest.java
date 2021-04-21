package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

import junit.framework.Assert;

public class DeleteAPITest extends TestBase {
	
	TestBase testBaseObj;
	String EndPointURL, ServiceURL, URL;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp() {
		testBaseObj = new TestBase();
		EndPointURL=prop.getProperty("endPointURL");
		ServiceURL=prop.getProperty("serviceURL");
		URL = EndPointURL+ServiceURL;
	}

	
	@Test
	public void deleteAPITestWithHeader() throws ClientProtocolException, IOException {
		
		System.out.println();
		System.out.println("Inside deleteAPITestWithHeader Method");
		System.out.println("=====================================");
		HashMap<String, String> requestHeader = new HashMap<String, String>();
		requestHeader.put("Content-Type","application/json");
		//requestHeader.put("GIT-url","https://github.com/saptarshidasgupta20091992/API.git");
		//requestHeader.put("UserName","saptarshidasgupta20091992");
		//requestHeader.put("Password","Mimisap@143");
			
			
		restClient = new RestClient();
		closeableHttpResponse = restClient.delete(URL,requestHeader);
		
		
		        //a. STATUS CODE
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("API Response Status Code ----> "+statusCode);
				Assert.assertEquals(Integer.parseInt(JSON_DELETE_SUCCESS_204_CODE), statusCode);
				
				
				
	}

	
	
	
}
