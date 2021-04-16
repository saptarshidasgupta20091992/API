package com.qa.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil {

	public static String getValueByJPath(JSONObject responseJson,String JPath) {
		
		Object Obj = responseJson;
		for(String s : JPath.split("/"))
		  if(!s.isEmpty())
			if(!(s.contains("[") || s.contains("]")))
				Obj = ((JSONObject) Obj).get(s);
			else if(s.contains("[") || s.contains("]"))
				Obj = ((JSONArray)((JSONObject) Obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]","")));
		return Obj.toString();
		
	}
	
	
}
