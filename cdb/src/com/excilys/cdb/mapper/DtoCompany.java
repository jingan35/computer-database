package com.excilys.cdb.mapper;

public class DtoCompany {
	private String id;
	private String name;
	
	public DtoCompany(){
		this.id="-1";
		this.name=null;
	}
	
	public DtoCompany(String id,String name){
		this.id=id;
		this.name=name;
	}
	
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void setId(String newId) {
		// TODO Auto-generated method stub
		id=newId;
	}

	public void setName(String newName) {
		// TODO Auto-generated method stub
		newName=name;
	}
}
