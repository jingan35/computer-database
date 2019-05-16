package com.excilys.cdb.mapper;

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

	public String getDateIntroduced() {
		// TODO Auto-generated method stub
		return introduced;
	}

	public String getDateDiscontinued() {
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company_id == null) ? 0 : company_id.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DtoComputer other = (DtoComputer) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
