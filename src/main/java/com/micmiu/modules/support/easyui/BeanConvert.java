package com.micmiu.modules.support.easyui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.codehaus.jackson.map.ObjectMapper;

import com.micmiu.modules.json.jsonlib.JsonDateValueProcessor;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@SuppressWarnings("rawtypes")
public class BeanConvert {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static List<PropertyGridData> convertPropertyGridData(Object bean) {
		return convertPropertyGridData(bean, null);
	}

	public static List<PropertyGridData> convertPropertyGridData(Object bean,
			Map<String, String> showMap) {
		List<PropertyGridData> volist = new ArrayList<PropertyGridData>();
		try {

			JSONObject obj = JSONObject.fromObject(mapper
					.writeValueAsString(bean));
			Iterator it = obj.keys();

			while (it.hasNext()) {
				String key = (String) it.next();
				Object valObj = obj.get(key);
				String value = null;
				if (null == valObj
						|| (valObj instanceof JSONObject && ((JSONObject) valObj)
								.isNullObject())) {
					value = "";
				} else {
					value = valObj.toString();
				}

				if (null == showMap) {
					volist.add(new PropertyGridData(key, value));
				} else {
					if (null != showMap.get(key)) {
						volist.add(new PropertyGridData(showMap.get(key), value));
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return volist;
	}

	public static List<PropertyGridData> convertPPGridData(Object bean,
			Map<String, String> showMap) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());

		JSONObject obj = JSONObject.fromObject(bean, jsonConfig);

		Iterator it = obj.keys();
		List<PropertyGridData> volist = new ArrayList<PropertyGridData>();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (null == showMap) {
				volist.add(new PropertyGridData(key, obj.get(key).toString()));
			} else {
				if (null != showMap.get(key)) {
					volist.add(new PropertyGridData(showMap.get(key), obj.get(
							key).toString()));
				}
			}

		}
		return volist;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
