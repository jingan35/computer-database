package com.excilys.cdb.service;

import java.sql.Timestamp;

import com.excilys.cdb.exception.NotAIntegerException;
import com.excilys.cdb.exception.PasLeBonFormatTimestamp;
import com.excilys.cdb.mapper.DtoComputer;
import com.excilys.cdb.model.ModelComputer;

public class MapperComputer {
	 //transforme modelComputer en dtoComputer
  	public static DtoComputer modelComputerToDtoComputer(ModelComputer mC){
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
  	public static ModelComputer dtoComputerToModelComputer(DtoComputer dC) throws PasLeBonFormatTimestamp, NotAIntegerException{
  		ModelComputer mC= new ModelComputer();
  		try {
  		mC.setId(Integer.valueOf(dC.getId()));
  		}catch(java.lang.NumberFormatException nFE) {
				throw new NotAIntegerException();
		}
  		mC.setName(dC.getName());
  		if(!(dC.getIntroduced().equals("null"))) {
  			System.out.println(dC.getIntroduced()+" introducedMagic");
  			try {
  			mC.setIntroduced(Timestamp.valueOf(dC.getIntroduced()));
  			}catch(java.lang.IllegalArgumentException e) {
  				throw new PasLeBonFormatTimestamp();
  			}
  			
  		}
  		else {
  			System.out.println("nullIntroduced");
  			mC.setIntroduced(null);
  		}
  		if(!dC.getDiscontinued().equals("null")) {
  			System.out.println(dC.getDiscontinued()+" DiscontinuededMagic");
  			try {
  	  			mC.setDiscontinued(Timestamp.valueOf(dC.getDiscontinued()));
  	  			}catch(java.lang.IllegalArgumentException e) {
  	  				throw new PasLeBonFormatTimestamp();
  	  			}
  			}
  		else {
  			System.out.println("nullDiscontinued");
  			mC.setIntroduced(null);
  		}
  		if(!dC.getCompanyId().equals("null")) {
  			try {
  				mC.setCompanyId(Integer.valueOf(dC.getCompanyId()));
  			}catch(java.lang.NumberFormatException nFE) {
  				throw new NotAIntegerException();
  			}
  		}
  		else {
  			mC.setCompanyId(null);
  		}
  		return mC;
  		
  	}

}
