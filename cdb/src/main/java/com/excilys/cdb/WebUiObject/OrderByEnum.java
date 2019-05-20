package com.excilys.cdb.WebUiObject;

public enum OrderByEnum {
	NAME("computer.name"),
	INTRODUCED("introduced"),
	DISCONTINUED("discontinued"),
	COMPANY_NAME("company_name"),
	NAME_DESC("computer.name","DESC"),
	INTRODUCED_DESC("introduced","DESC"),
	DISCONTINUED_DESC("discontinued","DESC"),
	COMPANY_NAME_DESC("company_name","DESC");
	
	OrderByEnum(String name){
		this.name=name;
		this.type=("ASC");
	}
	
	OrderByEnum(String name, String type){
		this.name=name;
		this.type=type;
	}
	
	String name;
	String type;
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setName(String name) {
		this.name= name;
	}
	
	public void setType(String type) {
		this.type=type;
	}
	
}

	