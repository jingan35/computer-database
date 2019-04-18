package com.excilys.cdb.service;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.excilys.cdb.mapper.DtoComputer;
import com.excilys.cdb.mapper.DtoComputer;
import com.excilys.cdb.model.*;
import com.excilys.cdb.persistance.DaoComputer;

public class ServiceComputer {
	
	private ServiceComputer()
    {}
 
    /** Instance unique pré-initialisée */
    private static ServiceComputer INSTANCE = new ServiceComputer();
     
    /** Point d'accès pour l'instance unique du singleton */
    public static ServiceComputer getInstance()
    {   return INSTANCE;
    }
	
  //transforme modelComputer en dtoComputer
  	public DtoComputer modelComputerToDtoComputer(ModelComputer mC){
  		DtoComputer dC= new DtoComputer();
  		dC.setId(String.valueOf(mC.getId()));
  		dC.setName(mC.getName());
  		dC.setIntroduced(String.valueOf(mC.getIntroduced()));
  		dC.setDiscontinued(String.valueOf(mC.getDiscontinued()));
  		dC.setCompanyId(String.valueOf(mC.getCompanyId()));
  		if(mC.getCompanyName()!=null)
  			dC.setCompanyName(mC.getCompanyName());;
  		return dC;
  		
  	}
  	
  	//transforme dtoComputer en  modelComputer
  	public ModelComputer dtoComputerToModelComputer(DtoComputer dC){
  		ModelComputer mC= new ModelComputer();
  		mC.setId(Integer.parseInt(dC.getId()));
  		mC.setName(dC.getName());
  		mC.setIntroduced(Timestamp.valueOf(dC.getIntroduced()));
  		mC.setDiscontinued(Timestamp.valueOf(dC.getDiscontinued()));
  		mC.setCompanyId(Integer.parseInt(dC.getCompanyId()));
  		return mC;
  		
  	}
  	
  	public ArrayList<DtoComputer> selectComputer(){
  		DaoComputer dC = new DaoComputer();
  		//le liste renvoyée
  		ArrayList<DtoComputer> resultList = new ArrayList<DtoComputer>();
  		ArrayList<ModelComputer> list= dC.select();
  		for(int i=0;i<list.size();i++) {
  			resultList.add(modelComputerToDtoComputer(list.get(i)));
  		}
		return resultList;
  		
  	}
	
  	public DtoComputer computerDetails(int id) {
  		DaoComputer dc = new DaoComputer();
  		ModelComputer mc = dc.selectOne(id);
  		
  		DtoComputer result=modelComputerToDtoComputer(mc);
  		return result;
  	}
  	
  	
	
}
