package com.excilys.cdb.model;

public class ModelCompany {
	private int id;
	private String name;
	
	public ModelCompany(){
		this.id=-1;
		this.name=null;
	}
	
	public ModelCompany(int id,String name){
		this.id=id;
		this.name=name;
	}
	
}
