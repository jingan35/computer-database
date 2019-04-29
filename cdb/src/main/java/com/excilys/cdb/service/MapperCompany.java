package com.excilys.cdb.service;

import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.model.ModelCompany;

public class MapperCompany {
	//transforme modelCompany en dtoCompany
	public static DtoCompany modelCompanyToDtoCompany(ModelCompany mC){
		DtoCompany dC= new DtoCompany();
		dC.setId(String.valueOf(mC.getId()));
  		dC.setName(mC.getName());
  		return dC;
		
	}
	
	//transforme dtoCompany en  modelCompany
	public static ModelCompany dtoCompanyToModelCompany(DtoCompany dC){
		ModelCompany mC= new ModelCompany();
		mC.setId(Integer.parseInt((dC.getId())));
  		mC.setName(dC.getName());
  		return mC;
		
	}

}
