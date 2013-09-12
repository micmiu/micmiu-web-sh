package com.micmiu.modules.support.easyui;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class PropertyGridData {

	private String name;

	private String value;

	public PropertyGridData() {

	}

	public PropertyGridData(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
