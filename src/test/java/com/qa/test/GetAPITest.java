package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
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
//TestNG Class
public class GetAPITest extends TestBase {
	
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
	
	@Test(priority=1)
	public void getAPITestWithoutHeader() throws ClientProtocolException, IOException {
		System.out.println();
		System.out.println("Inside getAPITestWithoutHeader Method");
		System.out.println("=====================================");
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(URL);
		
		
		        //a. STATUS CODE
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("API Response Status Code ----> "+statusCode);
				Assert.assertEquals(JSON_GET_SUCCESS_200OK_CODE, statusCode);
				
				//b. JSON String
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("API Response JSON Payload ----> "+responseJson);
				
				    //Extracting the per page value from JSon Payload
				     String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				     System.out.println("Value of per Page --->"+perPageValue);
				     Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				     
				   //Extracting the total value from JSon Payload
				     String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				     System.out.println("Value of total --->"+totalValue);
				     Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				   //Extracting  value from JSon Array
				     String ArrayValueIndex = "2";
				     String firstName = TestUtil.getValueByJPath(responseJson, "/data["+ArrayValueIndex+"]/first_name");
				     String lastName = TestUtil.getValueByJPath(responseJson, "/data["+ArrayValueIndex+"]/last_name");
				     String iD = TestUtil.getValueByJPath(responseJson, "/data["+ArrayValueIndex+"]/id");
				     String avatar = TestUtil.getValueByJPath(responseJson, "/data["+ArrayValueIndex+"]/avatar");
				     
				     System.out.println("id_"+iD+" : "+firstName+" "+lastName+" -with avatar- "+avatar);
				     
				//c. All Headers
				Header[] headersArray = closeableHttpResponse.getAllHeaders();
				HashMap<String,String> allHeaderMap = new HashMap<String,String>();
				for(Header header : headersArray) {
					allHeaderMap.put(header.getName(),header.getValue());
				}
				System.out.println("API Response Header List ----> "+allHeaderMap);
				
	}

	
	@Test(priority=2)
	public void getAPITestWithHeader() throws ClientProtocolException, IOException {
		
		System.out.println();
		System.out.println("Inside getAPITestWithHeader Method");
		System.out.println("=====================================");
		HashMap<String, String> requestHeader = new HashMap<String, String>();
		requestHeader.put("Content-Type","application/json");
		//requestHeader.put("GIT-url","https://github.com/saptarshidasgupta20091992/API.git");
		//requestHeader.put("UserName","saptarshidasgupta20091992");
		//requestHeader.put("Password","Mimisap@143");
			
			
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(URL,requestHeader);
		
		
		        //a. STATUS CODE
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("API Response Status Code ----> "+statusCode);
				Assert.assertEquals(JSON_GET_SUCCESS_200OK_CODE, statusCode);
				
				//b. JSON String
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("API Response JSON Payload ----> "+responseJson);
				
				    //Extracting the per page value from JSon Payload
				     String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				     System.out.println("Value of per Page --->"+perPageValue);
				     Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				     
				   //Extracting the total value from JSon Payload
				     String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				     System.out.println("Value of total --->"+totalValue);
				     Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				   //Extracting  value from JSon Array
				     String ArrayValueIndex = "0";
				     String firstName = TestUtil.getValueByJPath(responseJson, "/data["+ArrayValueIndex+"]/first_name");
				     String lastName = TestUtil.getValueByJPath(responseJson, "/data["+ArrayValueIndex+"]/last_name");
				     String iD = TestUtil.getValueByJPath(responseJson, "/data["+ArrayValueIndex+"]/id");
				     String avatar = TestUtil.getValueByJPath(responseJson, "/data["+ArrayValueIndex+"]/avatar");
				     
				     System.out.println("id_"+iD+" : "+firstName+" "+lastName+" -with avatar- "+avatar);
				     
				//c. All Headers
				Header[] headersArray = closeableHttpResponse.getAllHeaders();
				HashMap<String,String> allHeaderMap = new HashMap<String,String>();
				for(Header header : headersArray) {
					allHeaderMap.put(header.getName(),header.getValue());
				}
				System.out.println("API Response Header List ----> "+allHeaderMap);
				
	}

	
	
	
}
