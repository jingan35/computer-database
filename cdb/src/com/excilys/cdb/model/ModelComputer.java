package com.excilys.cdb.model;

import java.sql.*;

public class ModelComputer {
	private int id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private int company_id;
	private String company_name;
	
	public ModelComputer(){
		id=-1;
		name="";
		introduced=null;
		discontinued=null;
		company_id=-1;
	}
	
	public ModelComputer(int id, String name,Timestamp introduced, Timestamp discontinued,int company_id){
		this.id= id;
		this.name=name;
		this.introduced=introduced;
		this.discontinued=discontinued;
		this.company_id=company_id;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public Timestamp getIntroduced() {
		// TODO Auto-generated method stub
		return introduced;
	}

	public Timestamp getDiscontinued() {
		// TODO Auto-generated method stub
		return discontinued;
	}

	public int getCompanyId() {
		// TODO Auto-generated method stub
		return company_id;
	}
	
	public void setId(int newId) {
		// TODO Auto-generated method stub
		id=newId;
	}

	public void setName(String newName) {
		// TODO Auto-generated method stub
		newName=name;
	}

	public void setIntroduced(Timestamp T) {
		// TODO Auto-generated method stub
		introduced=T;
	}

	public void setDiscontinued(Timestamp T) {
		// TODO Auto-generated method stub
		discontinued=T;
	}

	public void setCompanyId(int newCompanyId) {
		// TODO Auto-generated method stub
		company_id=newCompanyId;
	}
}
