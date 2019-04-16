package com.excilys.cdb.model;

import java.sql.*;

public class ModelComputer {
	int id;
	String name;
	Timestamp introduced;
	Timestamp discontinued;
	int company_id;
	
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
}
