package com.excilys.cdb.WebUiObject;

public enum OrderByEnum {
	NAME("computer.name"),
	INTRODUCED("introduced IS NULL, introduced ASC"),
	DISCONTINUED("discontinued IS NULL, discontinued ASC"),
	COMPANY_NAME("company_name IS NULL, company_name ASC"),
	NAME_DESC("computer.name DESC"),
	INTRODUCED_DESC("introduced IS NULL ,introduced DESC"),
	DISCONTINUED_DESC("discontinued IS NULL ,discontinued DESC"),
	COMPANY_NAME_DESC("company_name IS NULL ,company_name DESC");
	
	OrderByEnum(String name){
		this.name=name;
		//this.type=("ASC");
	}
	
	OrderByEnum(String name, String type){
		this.name=name;
		//this.type=type;
	}
	
	String name;
	//String type;
	
	public String getName() {
		return name;
	}
	
	/*public String getType() {
		return type;
	}*/
	
	public void setName(String name) {
		this.name= name;
	}
	
	/*public void setType(String type) {
		this.type=type;
	}*/
	
}

	