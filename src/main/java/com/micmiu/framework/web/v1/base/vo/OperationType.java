package com.micmiu.framework.web.v1.base.vo;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * 
 */
public enum OperationType {

	CREATE("create", "global.opera.create"),

	READ("read", "global.opera.read"),

	UPDATE("update", "global.opera.update"),

	DELETE("delete", "global.opera.delete"),

	EXPORT("export", "global.opera.export"),

	PRINT("print", "global.opera.print");

	private static Map<String, OperationType> valueMap = Maps.newHashMap();

	public String value;
	public String display;

	static {
		for (OperationType oper : OperationType.values()) {
			valueMap.put(oper.value, oper);
		}
	}

	OperationType(String value, String display) {
		this.value = value;
		this.display = display;
	}

	public static OperationType parse(String value) {
		return valueMap.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplay() {
		return display;
	}

}
