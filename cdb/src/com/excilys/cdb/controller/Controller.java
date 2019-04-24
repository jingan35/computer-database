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
    public void requete(String cmd) throws NotIntExpectedException, NotACommandException, AllAttributesNeededException, PasLeBonFormatTimestamp, NotAIntegerException {
    	String[] cmdTab= cmd.split(";");
    	if(cmdTab.length==1) {
    		if(cmdTab[0].equalsIgnoreCase("cl")) {
    			// Demander la List des computers
    			computerList();
    		}
    		else if(cmdTab[0].equalsIgnoreCase("lc")) {
    			//liste des compagnies
    			companyList();
    		}
    		else {
    			throw new NotACommandException();
    		}
    	}
    	else {
    		if(cmdTab[0].equalsIgnoreCase("sc")) {
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
    			}catch( NumberFormatException e) {
    				throw new NotIntExpectedException();
    			}
    			//fonction pour sppr
    		}
    		
    		else if(cmdTab[0].equalsIgnoreCase("ac")) {
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
    			int id=-1;
    			try {
    				id=Integer.parseInt(cmdTab[1]);
    			}catch( NumberFormatException e) {
    				throw new NotIntExpectedException();
    			}
    			//fonction pour update d'un computer
    		}
    		else {
    			throw new NotACommandException();
    		}
    	}
    }
    
    String computerList() {
    	ArrayList<DtoComputer> computerList=sComputer.selectComputer();
    	String result="";
    	for(int i=0;i<computerList.size();i++) {
    		result+="id : "+computerList.get(i).getId()+" name : "+computerList.get(i).getName() +"\n";
    	}
    	System.out.println(result);
    	return result;
    }
    
    String companyList() {
    	ArrayList<DtoCompany> companyList=sCompany.selectCompany();
    	String result="";
    	for(int i=0;i<companyList.size();i++) {
    		result+=companyList.get(i).toString()+"\n";
    	}
    	System.out.println(result);
    	return result;
    }
    
    String computerDetails(int id) {
    	String result="";
    	DtoComputer dC= sComputer.computerDetails(id); 
    	result+=dC.toString()+"\n";
    	System.out.println("toString : "+result);
    	return result;
    }
    
    void insertComputer(DtoComputer dC) throws PasLeBonFormatTimestamp, NotAIntegerException {
    	sComputer.insertComputer(dC);
    	
    }
}
