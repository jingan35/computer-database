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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		ModelCompany other = (ModelCompany) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
