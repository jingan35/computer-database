package com.excilys.cdb.mapper;

import java.sql.Timestamp;

public class DtoComputer {
	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private String company_id;
	private String companyName= null;
	
	public DtoComputer(){
		id="-1";
		name="noName";
		introduced=null;
		discontinued=null;
		company_id="-1";
	}
	
	public DtoComputer(String id, String name,String introduced, String discontinued,String company_id){
		this.id= id;
		this.name=name;
		this.introduced=introduced;
		this.discontinued=discontinued;
		this.company_id=company_id;
	}
	
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public String getCompanyName() {
		// TODO Auto-generated method stub
		return companyName;
	}

	public String getIntroduced() {
		// TODO Auto-generated method stub
		return introduced;
	}

	public String getDiscontinued() {
		// TODO Auto-generated method stub
		return discontinued;
	}

	public String getCompanyId() {
		// TODO Auto-generated method stub
		return company_id;
	}
	
	public void setId(String newId) {
		// TODO Auto-generated method stub
		id=newId;
	}

	public void setName(String newName) {
		// TODO Auto-generated method stub
		name=newName;
	}
	
	public void setCompanyName(String newName) {
		// TODO Auto-generated method stub
		companyName=newName;
	}

	public void setIntroduced(String T) {
		// TODO Auto-generated method stub
		introduced=T;
	}

	public void setDiscontinued(String T) {
		// TODO Auto-generated method stub
		discontinued=T;
	}

	public void setCompanyId(String newCompanyId) {
		// TODO Auto-generated method stub
		company_id=newCompanyId;
	}
	
	public String toString() {
		String s="";
		s=s+"id="+id+" name: "+name+ " introduced Time:"+introduced+ " discontinued Time:"+ discontinued+ " company_name= "+companyName;
		return s;
	}

}
