package com.excilys.cdb.service;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.model.*;
import com.excilys.cdb.persistance.DaoCompany;

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
			
			public ArrayList<DtoCompany> selectCompany(){
				DaoCompany dC = new DaoCompany();
		  		//le liste renvoyée
		  		ArrayList<DtoCompany> resultList = new ArrayList<DtoCompany>();
		  		ArrayList<ModelCompany> list= dC.select();
		  		for(int i=0;i<list.size();i++) {
		  			resultList.add(modelCompanyToDtoCompany(list.get(i)));
		  		}
				return resultList;
				
			}
	
}
