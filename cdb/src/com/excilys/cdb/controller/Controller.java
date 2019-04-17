package com.excilys.cdb.controller;

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
    public void requete(String cmd) {
    	String[] cmdTab= cmd.split(";");
    	if(cmdTab.length==1) {
    		if(cmdTab[0].equalsIgnoreCase("cl")) {
    			// Demander la List des computers
    		}
    		else if(cmdTab[0].equalsIgnoreCase("lc")) {
    			//liste des compagnies
    		}
    		else {
    			System.out.println("commande invalide ou mauvaise syntaxe");
    		}
    	}
    	else {
    		if(cmdTab[0].equalsIgnoreCase("sc")) {
    			//
    			System.out.println("ds le sc");
    			int id=-1;
    			try {
    				id=Integer.parseInt(cmdTab[1]);
    			}catch( NumberFormatException e) {
    				System.out.println("L'id doit être un entier");
    			}
    			//fonction pour details de computer
    		}
    		else if(cmdTab[0].equalsIgnoreCase("dc")){
    			int id=-1;
    			try {
    				id=Integer.parseInt(cmdTab[1]);
    			}catch( NumberFormatException e) {
    				System.out.println("L'id doit être un entier");
    			}
    			//fonction pour sppr
    		}
    		
    		else if(cmdTab[0].equalsIgnoreCase("ac")) {
    			//si tous les attributs du nouvel ordinateur données
    			if(cmdTab.length==6) {
    				//fonction pour l'ajout d'un computer 
    			}
    			else {
    				System.out.println("pas mis tous les attributs du nouvel ordinateur");
    			}
    		}
    		
    		else if(cmdTab[0].equalsIgnoreCase("uc")) {
    			int id=-1;
    			try {
    				id=Integer.parseInt(cmdTab[1]);
    			}catch( NumberFormatException e) {
    				System.out.println("L'id doit être un entier");
    			}
    			//fonction pour update d'un computer
    		}
    		else {
    			System.out.println("commande innexistante ou mauvaise syntaxe");
    		}
    	}
    }

}
