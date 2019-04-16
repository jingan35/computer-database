package com.excilys.cdb.mapper;

import java.sql.Timestamp;

public class DtoComputer {
	int id;
	String name;
	Timestamp introduced;
	Timestamp discontinued;
	int company_id;
	
	public DtoComputer(){
		id=-1;
		name="";
		introduced=null;
		discontinued=null;
		company_id=-1;
	}
	
	public DtoComputer(int id, String name,Timestamp introduced, Timestamp discontinued,int company_id){
		this.id= id;
		this.name=name;
		this.introduced=introduced;
		this.discontinued=discontinued;
		this.company_id=company_id;
	}

}
