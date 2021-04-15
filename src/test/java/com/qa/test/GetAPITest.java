package com.qa.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
//TestNG Class
public class GetAPITest extends TestBase {
	
	TestBase testBaseObj;
	String EndPointURL, ServiceURL, URL;
	RestClient restClient;
	
	
	@BeforeMethod
	public void setUp() {
		testBaseObj = new TestBase();
		EndPointURL=prop.getProperty("endPointURL");
		ServiceURL=prop.getProperty("serviceURL");
		URL = EndPointURL+ServiceURL;
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		restClient.get(URL);
	}

}
