package com.excilys.cdb.service;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.excilys.cdb.exception.NotAIntegerException;
import com.excilys.cdb.exception.PasLeBonFormatTimestamp;
import com.excilys.cdb.mapper.DtoComputer;
import com.excilys.cdb.model.ModelComputer;

@Component
public class MapperComputer {
	
	private MapperComputer(){
		
	}
	
	 //transforme modelComputer en dtoComputer
  	public DtoComputer modelComputerToDtoComputer(ModelComputer mC){
  		DtoComputer dC= new DtoComputer();
  		dC.setId(String.valueOf(mC.getId()));
  		dC.setName(mC.getName());
  		dC.setIntroduced(String.valueOf(mC.getIntroduced()));
  		dC.setDiscontinued(String.valueOf(mC.getDiscontinued()));
  		dC.setCompany_id(String.valueOf(mC.getCompany_id()));
  		if(mC.getCompanyName()!=null)
  			dC.setCompanyName(mC.getCompanyName());;
  		return dC;
  		
  	}
  	
  	//transforme dtoComputer en  modelComputer
  	public ModelComputer dtoComputerToModelComputer(DtoComputer dC) throws PasLeBonFormatTimestamp, NotAIntegerException{
  		ModelComputer mC= new ModelComputer();
  		try {
  		mC.setId(Integer.valueOf(dC.getId()));
  		}catch(java.lang.NumberFormatException nFE) {
				throw new NotAIntegerException();
		}
  		mC.setName(dC.getName());
  		if(dC.getIntroduced() != null && !(dC.getIntroduced().equals(""))) {
  			System.out.println(dC.getIntroduced()+" introducedMagic");
  			try {
  			mC.setIntroduced(Timestamp.valueOf(dC.getIntroduced()+" 12:00:00"));
  			}catch(java.lang.IllegalArgumentException e) {
  				throw new PasLeBonFormatTimestamp();
  			}
  			
  		}
  		else {
  			System.out.println("nullIntroduced");
  			mC.setIntroduced(null);
  		}
  		if(dC.getDiscontinued()!= null &&!(dC.getDiscontinued().trim().equals(""))) {
  			System.out.println(dC.getDiscontinued()+" DiscontinuededMagic");
  			try {
  	  			mC.setDiscontinued(Timestamp.valueOf(dC.getDiscontinued()+" 12:00:00"));
  	  			}catch(java.lang.IllegalArgumentException e) {
  	  				throw new PasLeBonFormatTimestamp();
  	  			}
  			}
  		else {
  			System.out.println("nullDiscontinued");
  			mC.setDiscontinued(null);
  		}
  		if(!dC.getCompany_id().equals("null")) {
  			try {
  				mC.setCompany_id(Integer.valueOf(dC.getCompany_id()));
  			}catch(java.lang.NumberFormatException nFE) {
  				throw new NotAIntegerException();
  			}
  		}
  		else {
  			mC.setCompany_id(null);
  		}
  		return mC;
  		
  	}

}
