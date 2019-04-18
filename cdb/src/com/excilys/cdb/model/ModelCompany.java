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

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void setId(int newId) {
		// TODO Auto-generated method stub
		id=newId;
	}
	
	public void setName(String newName) {
		// TODO Auto-generated method stub
		name=newName;
	}
	
}
