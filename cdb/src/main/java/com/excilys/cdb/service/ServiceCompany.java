package com.excilys.cdb.service;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.model.*;
import com.excilys.cdb.persistance.DaoCompany;

@Service
public class ServiceCompany {
	
	DaoCompany daoCompany ;
    /** Instance unique pré-initialisée */
    MapperCompany mapperCompany;
     
    private ServiceCompany(DaoCompany daoCompany, MapperCompany mapperCompany){
    	this.daoCompany=daoCompany;
    	this.mapperCompany=mapperCompany;
    }
			
			public ArrayList<DtoCompany> selectCompany(int nbRowByPage,int page) throws BaseVide{
				
		  		//le liste renvoyée
		  		ArrayList<DtoCompany> resultList = new ArrayList<DtoCompany>();
		  		ArrayList<ModelCompany> list= daoCompany.select(nbRowByPage,page);
		  		for(int i=0;i<list.size();i++) {
		  			resultList.add(mapperCompany.modelCompanyToDtoCompany(list.get(i)));
		  		}
				return resultList;
				
			}
			
			public ArrayList<DtoCompany> selectAllCompanies() throws BaseVide{
				
		  		//le liste renvoyée
		  		ArrayList<DtoCompany> resultList = new ArrayList<DtoCompany>();
		  		ArrayList<ModelCompany> list= daoCompany.selectAll();
		  		for(int i=0;i<list.size();i++) {
		  			resultList.add(mapperCompany.modelCompanyToDtoCompany(list.get(i)));
		  		}
				return resultList;
				
			}
			
			public void deleteCompanyEtc(int id) {
				daoCompany.delete(id);
			}
	
}
