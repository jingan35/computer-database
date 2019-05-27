package com.excilys.cdb.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.model.ModelCompany;

@Component
public class MapperCompany {
	private MapperCompany() {
		
	}
	
	
	//transforme modelCompany en dtoCompany
	public DtoCompany modelCompanyToDtoCompany(ModelCompany mC){
		DtoCompany dC= new DtoCompany();
		dC.setId(String.valueOf(mC.getId()));
  		dC.setName(mC.getName());
  		return dC;
		
	}
	
	//transforme dtoCompany en  modelCompany
	public ModelCompany dtoCompanyToModelCompany(DtoCompany dC){
		ModelCompany mC= new ModelCompany();
		mC.setId(Integer.parseInt((dC.getId())));
  		mC.setName(dC.getName());
  		return mC;
		
	}

}
