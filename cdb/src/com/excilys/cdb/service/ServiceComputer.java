package com.excilys.cdb.service;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.excilys.cdb.exception.NotAIntegerException;
import com.excilys.cdb.exception.PasLeBonFormatTimestamp;
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
	
 
  	
  	public ArrayList<DtoComputer> selectComputer(){
  		DaoComputer dC = new DaoComputer();
  		//le liste renvoyée
  		ArrayList<DtoComputer> resultList = new ArrayList<DtoComputer>();
  		ArrayList<ModelComputer> list= dC.select();
  		for(int i=0;i<list.size();i++) {
  			resultList.add(MapperComputer.modelComputerToDtoComputer(list.get(i)));
  		}
		return resultList;
  		
  	}
	
  	public DtoComputer computerDetails(int id) {
  		DaoComputer dc = new DaoComputer();
  		ModelComputer mc = dc.selectOne(id);
  		
  		DtoComputer result=MapperComputer.modelComputerToDtoComputer(mc);
  		return result;
  	}
  	
  	public void insertComputer(DtoComputer dtoComputer) throws PasLeBonFormatTimestamp, NotAIntegerException {
  		ModelComputer computerToAdd = MapperComputer.dtoComputerToModelComputer( dtoComputer);
  		DaoComputer daoComputer = new DaoComputer();
  		daoComputer.insert(computerToAdd);
  		
  	}
  	
  	
	
}
