package com.excilys.cdb.service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.excilys.cdb.WebUiObject.Page;
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

@Service
public class ServiceComputer {
	
	int countComputers=0;
 
	DaoComputer daoComputer ;
    
    
    MapperComputer mapperComputer ;
     
    private ServiceComputer(DaoComputer daoComputer, MapperComputer mapperComputer){
    	this.daoComputer=daoComputer;
    	this.mapperComputer=mapperComputer;
    	
    }
 
  	
  	public ArrayList<DtoComputer> selectComputer(Page page) throws BaseVide, PasDePagesNegException{
  		
  		//le liste renvoyée
  		ArrayList<DtoComputer> resultList = new ArrayList<DtoComputer>();
  		ArrayList<ModelComputer> list= daoComputer.select(page);
  		if(Integer.parseInt(page.getCurrentPage())<=0)
  			throw new PasDePagesNegException();
  		Stream<ModelComputer> streamModelComputerList= list.stream();
  		streamModelComputerList.forEach(element ->{
  			DtoComputer dtoComp= mapperComputer.modelComputerToDtoComputer(element);
  			resultList.add(dtoComp);
  			
  		});
  		/*for(int i=0;i<list.size();i++) {
  			resultList.add(MapperComputer.modelComputerToDtoComputer(list.get(i)));
  		}*/
		return resultList;
  		
  	}
  	
  	public ArrayList<DtoComputer> selectComputerSearch(Page page) throws BaseVide, PasDePagesNegException{
  		
  		//le liste renvoyée
  		ArrayList<DtoComputer> resultList = new ArrayList<DtoComputer>();
  		ArrayList<ModelComputer> list= daoComputer.select(page);
  		if(Integer.parseInt(page.getCurrentPage())<=0)
  			throw new PasDePagesNegException();
  		Stream<ModelComputer> streamModelComputerList= list.stream();
  		streamModelComputerList.forEach(element ->{
  			DtoComputer dtoComp= mapperComputer.modelComputerToDtoComputer(element);
  			resultList.add(dtoComp);
  			
  		});
  		/*for(int i=0;i<list.size();i++) {
  			resultList.add(MapperComputer.modelComputerToDtoComputer(list.get(i)));
  		}*/
		return resultList;
  		
  	}
	
  	public DtoComputer computerDetails(int id) throws RequeteSansResultatException {
  		
  		ModelComputer mc = daoComputer.selectOne(id);
  		
  		DtoComputer result=mapperComputer.modelComputerToDtoComputer(mc);
  		return result;
  	}
  	
  	public void insertComputer(DtoComputer dtoComputer) throws PasLeBonFormatTimestamp, NotAIntegerException, TimestampDiscotinuedInferiorToTimestampIntroduced {
  		ModelComputer computerToAdd = mapperComputer.dtoComputerToModelComputer( dtoComputer);
	  	daoComputer.insert(computerToAdd);
  		
  		
  	}
  	
  	public void updateComputer(DtoComputer dtoComputer) throws PasLeBonFormatTimestamp, NotAIntegerException, TimestampDiscotinuedInferiorToTimestampIntroduced {
  		ModelComputer computerToUpdate = mapperComputer.dtoComputerToModelComputer( dtoComputer);
  		if(computerToUpdate.getDiscontinued()!=null && computerToUpdate.getDiscontinued().before(computerToUpdate.getIntroduced())){
			throw new TimestampDiscotinuedInferiorToTimestampIntroduced();
		}
  		daoComputer.update(computerToUpdate.getId(), computerToUpdate);
  	}
  	
  	public void deleteComputer(int id) {
  		daoComputer.delete(id);
  	}
  	
  	public void deleteComputer(String Ids) {
  		String[] tabIds= Ids.split(",");
  		for(int i=0;i<tabIds.length;i++) {
  			deleteComputer(Integer.parseInt(tabIds[i]));
  		}
  		
  	}
  	
  	public int selectComputerCount(String search) throws BaseVide, PasDePagesNegException{
  		
  		return daoComputer.selectCount(search);
  		
  	}
  	
  	
	
}
