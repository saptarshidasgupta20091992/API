package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	public String JSON_GET_SUCCESS_200OK_CODE = "200";
	public String JSON_POST_SUCCESS_201OK_CODE = "201";
	public String JSON_PUT_SUCCESS_200OK_CODE = "200";
	public String JSON_DELETE_SUCCESS_204_CODE = "204";
	String payload;
	
	//constructor
	public TestBase() {
		
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties");
		    prop.load(fis);
		}catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public String fetchPayload(String fileName) throws IOException {
		
		String entityString = null;
		try {
		String filePath=(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\data\\"+fileName);
		 entityString = new String(Files.readAllBytes(Paths.get(filePath)));
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		return entityString;
		
	}

}
