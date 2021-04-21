package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

import junit.framework.Assert;

public class PatchAPITest extends TestBase {
	
	TestBase testBaseObj;
	String EndPointURL, PutServiceURL, URL;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp() {
		testBaseObj = new TestBase();
		EndPointURL=prop.getProperty("endPointURL");
		PutServiceURL=prop.getProperty("ServicePutURL");
		URL = EndPointURL+PutServiceURL;
	}
	
	@Test
	public void putAPITestWithHeader() throws ClientProtocolException, IOException {
		
		System.out.println();
		System.out.println("Inside putAPITestWithHeader Method");
		System.out.println("=====================================");
		HashMap<String, String> requestHeader = new HashMap<String, String>();
		requestHeader.put("Content-Type","application/json");
		//requestHeader.put("GIT-url","https://github.com/saptarshidasgupta20091992/API.git");
		//requestHeader.put("UserName","saptarshidasgupta20091992");
		//requestHeader.put("Password","Mimisap@143");
			
		String entityString = testBaseObj.fetchPayload("PatchReqPayload.txt");
		restClient = new RestClient();
		closeableHttpResponse = restClient.patch(URL, entityString, requestHeader);
				
		
		
		        //a. STATUS CODE
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("API Response Status Code ----> "+statusCode);
				Assert.assertEquals(Integer.parseInt(JSON_PUT_SUCCESS_200OK_CODE), statusCode);
				
				//b. JSON String
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("API Response JSON Payload ----> "+responseJson);
				
				    //Extracting the per page value from JSon Payload
				     String UpdateTime = TestUtil.getValueByJPath(responseJson, "/updatedAt");
				     System.out.println("Updated At --->"+UpdateTime);
				     
				   //Extracting the per page value from JSon Payload
				     String name = TestUtil.getValueByJPath(responseJson, "/name");
				     System.out.println("Updated name --->"+name);
				     
				   //Extracting the per page value from JSon Payload
				     String job = TestUtil.getValueByJPath(responseJson, "/job");
				     System.out.println("Updated job --->"+job);
				     
				/*//c. All Headers
				Header[] headersArray = closeableHttpResponse.getAllHeaders();
				HashMap<String,String> allHeaderMap = new HashMap<String,String>();
				for(Header header : headersArray) {
					allHeaderMap.put(header.getName(),header.getValue());
				}
				System.out.println("API Response Header List ----> "+allHeaderMap);
				*/
	}


}
