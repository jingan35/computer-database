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
		name=newName;
	}
	
	public String toString() {
		String s="";
		s=s+"id : "+id+" name : "+name ; //+"introduced : \"+introduced+\"discontinued : \"+discontinued+\" company_id : \"+company_id
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DtoCompany other = (DtoCompany) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
