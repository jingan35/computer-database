package com.excilys.cdb.service;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.model.ModelCompany;
import com.excilys.cdb.persistance.DaoCompany;
import com.excilys.cdb.persistance.DaoComputer;

@Service
public class ServiceCompany {
	
	DaoCompany daoCompany ;
    /** Instance unique pré-initialisée */
    MapperCompany mapperCompany;
    DaoComputer daoComputer;
     
    private ServiceCompany(DaoCompany daoCompany, MapperCompany mapperCompany, DaoComputer daoComputer){
    	this.daoCompany=daoCompany;
    	this.mapperCompany=mapperCompany;
    	this.daoComputer = daoComputer;
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
			
			@Transactional
			public void deleteCompany(int id) {
				//delete allcomputer where company_id = id
				//delete 1company
			}
	
}
