package com.excilys.cdb.WebUiObject;

public enum OrderByEnum {
	NAME("name"),
	INTRODUCED("introduced"),
	DISCONTINUED("discontinued"),
	COMPANYNAME("company_name"),
	NAMEDESC("name","DESC"),
	INTRODUCEDDESC("introduced","DESC"),
	DISCONTINUEDDESC("discontinued","DESC"),
	COMPANYNAMEDESC("company_name","DESC");
	
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
	
	String getName() {
		return name;
	}
	
	String getType() {
		return type;
	}
}

	