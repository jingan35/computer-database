package com.excilys.cdb.service;
import java.sql.Timestamp;

import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.model.*;

public class ServiceCompany {
	private ServiceCompany()
    {}
 
    /** Instance unique pré-initialisée */
    private static ServiceCompany INSTANCE = new ServiceCompany();
     
    /** Point d'accès pour l'instance unique du singleton */
    public static ServiceCompany getInstance()
    {   return INSTANCE;
    }
	
	//transforme modelCompany en dtoCompany
			public DtoCompany modelCompanyToDtoCompany(ModelComputer mC){
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
