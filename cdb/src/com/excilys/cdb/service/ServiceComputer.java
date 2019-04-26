package com.excilys.cdb.service;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.excilys.cdb.exception.NotAIntegerException;
import com.excilys.cdb.exception.PasLeBonFormatTimestamp;
import com.excilys.cdb.exception.RequeteSansResultatException;
import com.excilys.cdb.mapper.DtoComputer;
import com.excilys.cdb.mapper.DtoComputer;
import com.excilys.cdb.model.*;
import com.excilys.cdb.persistance.DaoComputer;

public class ServiceComputer {
	
	private ServiceComputer()
    {}
 
	DaoComputer daoComputer = DaoComputer.getInstance();
    /** Instance unique pré-initialisée */
    private static ServiceComputer INSTANCE = new ServiceComputer();
     
    /** Point d'accès pour l'instance unique du singleton */
    public static ServiceComputer getInstance()
    {   return INSTANCE;
    }
	
 
  	
  	public ArrayList<DtoComputer> selectComputer(int nbRowByPage,int page){
  		
  		//le liste renvoyée
  		ArrayList<DtoComputer> resultList = new ArrayList<DtoComputer>();
  		ArrayList<ModelComputer> list= daoComputer.select(nbRowByPage,page);
  		for(int i=0;i<list.size();i++) {
  			resultList.add(MapperComputer.modelComputerToDtoComputer(list.get(i)));
  		}
		return resultList;
  		
  	}
	
  	public DtoComputer computerDetails(int id) throws RequeteSansResultatException {
  		
  		ModelComputer mc = daoComputer.selectOne(id);
  		
  		DtoComputer result=MapperComputer.modelComputerToDtoComputer(mc);
  		return result;
  	}
  	
  	public void insertComputer(DtoComputer dtoComputer) throws PasLeBonFormatTimestamp, NotAIntegerException {
  		ModelComputer computerToAdd = MapperComputer.dtoComputerToModelComputer( dtoComputer);
  		
  		daoComputer.insert(computerToAdd);
  		
  	}
  	
  	public void updateComputer(DtoComputer dtoComputer) throws PasLeBonFormatTimestamp, NotAIntegerException {
  		ModelComputer computerToUpdate = MapperComputer.dtoComputerToModelComputer( dtoComputer);
  		daoComputer.update(computerToUpdate.getId(), computerToUpdate);
  	}
  	
  	public void deleteComputer(int id) {
  		daoComputer.delete(id);
  	}
  	
  	
	
}
