package com.excilys.cdb.service;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.exception.NotAIntegerException;
import com.excilys.cdb.exception.PasDePagesNegException;
import com.excilys.cdb.exception.PasLeBonFormatTimestamp;
import com.excilys.cdb.exception.RequeteSansResultatException;
import com.excilys.cdb.exception.TimestampDiscotinuedInferiorToTimestampIntroduced;
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
	
 
  	
  	public ArrayList<DtoComputer> selectComputer(int nbRowByPage,int page) throws BaseVide, PasDePagesNegException{
  		
  		//le liste renvoyée
  		ArrayList<DtoComputer> resultList = new ArrayList<DtoComputer>();
  		ArrayList<ModelComputer> list= daoComputer.select(nbRowByPage,page);
  		if(page<=0)
  			throw new PasDePagesNegException();
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
  	
  	public void insertComputer(DtoComputer dtoComputer) throws PasLeBonFormatTimestamp, NotAIntegerException, TimestampDiscotinuedInferiorToTimestampIntroduced {
  		ModelComputer computerToAdd = MapperComputer.dtoComputerToModelComputer( dtoComputer);
	  	if(computerToAdd.getDiscontinued().before(computerToAdd.getIntroduced())){
			throw new TimestampDiscotinuedInferiorToTimestampIntroduced();
		}
	  	daoComputer.insert(computerToAdd);
  		
  		
  	}
  	
  	public void updateComputer(DtoComputer dtoComputer) throws PasLeBonFormatTimestamp, NotAIntegerException, TimestampDiscotinuedInferiorToTimestampIntroduced {
  		ModelComputer computerToUpdate = MapperComputer.dtoComputerToModelComputer( dtoComputer);
  		if(computerToUpdate.getDiscontinued().before(computerToUpdate.getIntroduced())){
			throw new TimestampDiscotinuedInferiorToTimestampIntroduced();
		}
  		daoComputer.update(computerToUpdate.getId(), computerToUpdate);
  	}
  	
  	public void deleteComputer(int id) {
  		daoComputer.delete(id);
  	}
  	
  	
	
}
