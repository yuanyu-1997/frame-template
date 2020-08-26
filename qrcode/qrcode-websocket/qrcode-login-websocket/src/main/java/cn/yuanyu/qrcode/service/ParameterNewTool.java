package cn.yuanyu.qrcode.service;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ParameterNewTool {

	public static List<Map<String, String>> json2List(String jsonData) {
		List<Map<String, String>> obj = new ArrayList<Map<String, String>>();
		if(StringUtils.isBlank(jsonData))return obj;
		if(!jsonData.startsWith("[") || !jsonData.endsWith("]"))return obj;
		
		JSONArray array = JSONArray.fromObject(jsonData);
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			Iterator it=jsonObject.names().iterator();

			Map<String,String> entry=new HashMap<String,String>();
			while(it.hasNext()) {
				String key=(String) it.next();
				String value=jsonObject.get(key).toString();
				entry.put(key,"null".equals(value)?"":value);
			}
			if(entry!= null)
				obj.add(entry);
		}
		return obj;
	}
	public static Map<String,String> json2Map(String jsonData){
		Map<String, String> map = new HashMap<>();
		if(StringUtils.isBlank(jsonData))return map;
		if(!jsonData.startsWith("{") || !jsonData.endsWith("}"))return map;
		List<Map<String,String>> lst = json2List("["+jsonData+"]");
		return lst.size()>0 ? lst.get(0) : map;
	}
}
