package com.excilys.cdb.controller;

import java.util.ArrayList;

import com.excilys.cdb.exception.*;
import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.mapper.DtoComputer;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;

public class Controller {
	
	private Controller()
    {}
 
    /** Instance unique pré-initialisée */
    private static Controller INSTANCE = new Controller();
    ServiceComputer sComputer= ServiceComputer.getInstance();
    ServiceCompany sCompany= ServiceCompany.getInstance();
     
    /** Point d'accès pour l'instance unique du singleton */
    public static Controller getInstance()
    {   return INSTANCE;
    }
    
    //identifie les commandes et appelle des bonnes fonctions 
    
    public void requete(String cmd, int nbRowByPage) throws NotIntExpectedException, NotACommandException, AllAttributesNeededException, PasLeBonFormatTimestamp,
    NotAIntegerException, RequeteSansResultatException, NotIntForPageException, TimestampDiscotinuedInferiorToTimestampIntroduced, BaseVide, PasDePagesNegException {
    	String[] cmdTab= cmd.split(";");
    	if(cmdTab.length==2) {
    		if(cmdTab[0].equalsIgnoreCase("cl")) {
    			int page=0;
    			try {
    				page=Integer.parseInt(cmdTab[1]);
    			}catch( NumberFormatException e) {
    				throw new NotIntForPageException();
    			}
    			// Demander la List des computers
    			computerList(nbRowByPage,page);
    		}
    		else if(cmdTab[0].equalsIgnoreCase("lc")) {
    			int page=0;
    			try {
    				page=Integer.parseInt(cmdTab[1]);
    			}catch( NumberFormatException e) {
    				throw new NotIntForPageException();
    			}
    			//liste des compagnies
    			companyList(nbRowByPage,page);
    		}
    		else if(cmdTab[0].equalsIgnoreCase("sc")) {
    			//
    			System.out.println("ds le sc");
    			int id=-1;
    			try {
    				id=Integer.parseInt(cmdTab[1]);
    				computerDetails(id);
    			}catch( NumberFormatException e) {
    				throw new NotIntExpectedException();
    			}
    			//fonction pour details de computer
    		}
    		else if(cmdTab[0].equalsIgnoreCase("dc")){
    			int id=-1;
    			try {
    				id=Integer.parseInt(cmdTab[1]);
    				delete(id);
    			}catch( NumberFormatException e) {
    				throw new NotIntExpectedException();
    			}
    			//fonction pour sppr
    		}
    		else {
    			throw new NotACommandException();
    		}
    	}
    	else {
    		
    		
    		if(cmdTab[0].equalsIgnoreCase("ac")) {
    			//si tous les attributs du nouvel ordinateur données
    			if(cmdTab.length==6) {
    				//fonction pour l'ajout d'un computer
    				DtoComputer dtoToAdd = new DtoComputer(cmdTab[1],cmdTab[2],"null","null","null");
    				if(!cmdTab[3].equals("n")) {
    					dtoToAdd.setIntroduced(cmdTab[3]);
    				}
    				if(!cmdTab[4].equals("n")) {
    					dtoToAdd.setDiscontinued(cmdTab[4]);
    				}
    				if(!cmdTab[5].equals("n")) {
    					dtoToAdd.setCompanyId(cmdTab[5]);
    				}
    				
    				insertComputer(dtoToAdd);
    				
    			}
    			else {
    				throw new AllAttributesNeededException();
    			}
    		}
    		
    		else if(cmdTab[0].equalsIgnoreCase("uc")) {
	    		if(cmdTab.length==6) {
	    			int id=-1;
	    			try {
	    				id=Integer.parseInt(cmdTab[1]);
	    			}catch( NumberFormatException e) {
	    				throw new NotIntExpectedException();
	    			}
	    			DtoComputer dtoToUpdate = new DtoComputer(cmdTab[1],cmdTab[2],"null","null","null");
    				if(!cmdTab[3].equals("n")) {
    					dtoToUpdate.setIntroduced(cmdTab[3]);
    				}
    				if(!cmdTab[4].equals("n")) {
    					dtoToUpdate.setDiscontinued(cmdTab[4]);
    				}
    				if(!cmdTab[5].equals("n")) {
    					dtoToUpdate.setCompanyId(cmdTab[5]);
    				}
    				
	    			//fonction pour update d'un computer
    				updateComputer(dtoToUpdate);
	    		}
	    		else {
    				throw new AllAttributesNeededException();
    			}
    		}
    		else {
    			throw new NotACommandException();
    		}
    	}
    }
    
    String computerList(int nbRowByPage,int page) throws BaseVide, PasDePagesNegException {
    	ArrayList<DtoComputer> computerList=sComputer.selectComputer(nbRowByPage,page);
    	String result="";
    	for(int i=0;i<computerList.size();i++) {
    		result+="id : "+computerList.get(i).getId()+" name : "+computerList.get(i).getName() +"\n";
    	}
    	System.out.println(result);
    	return result;
    }
    
    String companyList(int nbRowByPage,int page) throws BaseVide {
    	ArrayList<DtoCompany> companyList=sCompany.selectCompany(nbRowByPage,page);
    	String result="";
    	for(int i=0;i<companyList.size();i++) {
    		result+=companyList.get(i).toString()+"\n";
    	}
    	System.out.println(result);
    	return result;
    }
    
    String computerDetails(int id) throws RequeteSansResultatException {
    	String result="";
    	DtoComputer dC= sComputer.computerDetails(id); 
    	result+=dC.toString()+"\n";
    	System.out.println("toString : "+result);
    	return result;
    }
    
    void insertComputer(DtoComputer dC) throws PasLeBonFormatTimestamp, NotAIntegerException, TimestampDiscotinuedInferiorToTimestampIntroduced {
    	sComputer.insertComputer(dC);
    	
    }
    
    void updateComputer(DtoComputer dC) throws PasLeBonFormatTimestamp, NotAIntegerException, TimestampDiscotinuedInferiorToTimestampIntroduced {
    	sComputer.updateComputer(dC);
    	
    }
    void delete(int id) {
    	sComputer.deleteComputer(id);
    }
}
