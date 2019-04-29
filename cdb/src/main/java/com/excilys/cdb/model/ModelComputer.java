package com.excilys.cdb.model;

import java.sql.*;

public class ModelComputer {
	private int id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private Integer company_id;
	private String companyName=null;
	
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

	public String getCompanyName() {
		// TODO Auto-generated method stub
		return companyName;
	}
	
	public Timestamp getIntroduced() {
		// TODO Auto-generated method stub
		return introduced;
	}

	public Timestamp getDiscontinued() {
		// TODO Auto-generated method stub
		return discontinued;
	}

	public Integer getCompanyId() {
		// TODO Auto-generated method stub
		return company_id;
	}
	
	public void setId(int newId) {
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

	public void setIntroduced(Timestamp T) {
		// TODO Auto-generated method stub
		introduced=T;
	}

	public void setDiscontinued(Timestamp T) {
		// TODO Auto-generated method stub
		discontinued=T;
	}

	public void setCompanyId(Integer newCompanyId) {
		// TODO Auto-generated method stub
		company_id=newCompanyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company_id == null) ? 0 : company_id.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelComputer other = (ModelComputer) obj;
		if (company_id == null) {
			if (other.company_id != null)
				return false;
		} else if (!company_id.equals(other.company_id))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
