package com.qa.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

public class PostAPITest extends TestBase {
	
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
	public void postAPITestWithHeader() throws ClientProtocolException, IOException {
		
		System.out.println();
		System.out.println("Inside postAPITestWithHeader Method");
		System.out.println("=====================================");
		HashMap<String, String> requestHeader = new HashMap<String, String>();
		requestHeader.put("Content-Type","application/json");
		//requestHeader.put("GIT-url","https://github.com/saptarshidasgupta20091992/API.git");
		//requestHeader.put("UserName","saptarshidasgupta20091992");
		//requestHeader.put("Password","Mimisap@143");
			
		String fileName=(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\data\\PostReqPayload.txt");
		String entityString = new String(Files.readAllBytes(Paths.get(fileName)));
		restClient = new RestClient();
		closeableHttpResponse = restClient.post(URL, entityString, requestHeader);
				
		
		
		        //a. STATUS CODE
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("API Response Status Code ----> "+statusCode);
				Assert.assertEquals(Integer.parseInt(JSON_POST_SUCCESS_201OK_CODE), statusCode);
				
				//b. JSON String
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("API Response JSON Payload ----> "+responseJson);
				
				    //Extracting the per page value from JSon Payload
				     String id = TestUtil.getValueByJPath(responseJson, "/id");
				     System.out.println("ID Value --->"+id);
				     
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
