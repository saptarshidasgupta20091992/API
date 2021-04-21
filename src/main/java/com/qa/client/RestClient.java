package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	
	//get without header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		
		//GET() Method
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);
		return closeableHttpResponse;
		
	}
	
	//get with header
		public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
			
			//GET() Method
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);
			
			for(Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpget.addHeader(entry.getKey(), entry.getValue());
			}
			
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);
			return closeableHttpResponse;
			
		}
		
	//post method with header
public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
			
			//POST() Method
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(url);//https post request
			
			httppost.setEntity(new StringEntity(entityString));//for request payload
			
			//for headers
			for(Map.Entry<String, String> entry : headerMap.entrySet()) {
				httppost.addHeader(entry.getKey(), entry.getValue());
			}
			
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httppost);
			return closeableHttpResponse;
			
		}


//put method with header
public CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
			
			//POST() Method
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPut httpput = new HttpPut(url);
			httpput.setEntity(new StringEntity(entityString));//for request payload
			
			//for headers
			for(Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpput.addHeader(entry.getKey(), entry.getValue());
			}
			
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpput);
			return closeableHttpResponse;
			
		}

//delete with header
		public CloseableHttpResponse delete(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
			
			//GET() Method
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpDelete httpdelete = new HttpDelete(url);
			
			for(Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpdelete.addHeader(entry.getKey(), entry.getValue());
			}
			
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpdelete);
			return closeableHttpResponse;
			
		}
		
		
		//patch method with header
		public CloseableHttpResponse patch(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
					
					//POST() Method
					CloseableHttpClient httpClient = HttpClients.createDefault();
					HttpPatch httppatch = new HttpPatch(url);
					httppatch.setEntity(new StringEntity(entityString));//for request payload
					
					//for headers
					for(Map.Entry<String, String> entry : headerMap.entrySet()) {
						httppatch.addHeader(entry.getKey(), entry.getValue());
					}
					
					CloseableHttpResponse closeableHttpResponse = httpClient.execute(httppatch);
					return closeableHttpResponse;
					
				}

}
