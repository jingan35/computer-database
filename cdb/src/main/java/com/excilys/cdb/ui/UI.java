package com.excilys.cdb.ui;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.controller.Controller;
import com.excilys.cdb.exception.*;
import com.excilys.cdb.service.ServiceCompany;

public class UI {
	
	private int nbElementsByPages=20;
	
	private UI()
    {}
 
    /** Instance unique pré-initialisée */
    private static UI INSTANCE = new UI();
    //instance de controller avec qui il va echanger
    Controller c= Controller.getInstance();
     
    /** Point d'accès pour l'instance unique du singleton */
    public static UI getInstance()
    {   return INSTANCE;
    }
    
    public void start() {
    	boolean stop=false;
    	Scanner sc = new Scanner(System.in);
    	while (!stop) {
    		System.out.println("Help "
    				+ "\nComputers list : cl;page "
    				+ "\nCompanies list : lc;page "
    				+ "\nUpdate a Computer info : uc; id ;AttributeToModify ; NewValue; AttributeToModify2; NewValue2;etc.. "
    				+ "\nDelete a Computer : dc; id "
    				+ "\nShow a computer Details: sc;id "
    				+ "\nAdd a computer: ac; id; name; introducedTimestamp('n' if null); discontinuedTimestamp('n' if null); company_id "); 
    		System.out.println("Quel est vôtre requête ? ");
    		
    		try {
    			String request=sc.nextLine();
    			if(request.startsWith("dc")) {
    				System.out.println("vous êtes sûr de vouloir supprimer l'ordinateur ?");
    				String confirmation= sc.nextLine();
    				if(confirmation.startsWith("o")||confirmation.startsWith("y")) {
    					c.requete(request,nbElementsByPages);
    				}else
    					System.out.println("annulé");
    			}else if(request.startsWith("page")) {
    				String[] requestTab=request.split(";");
    				if(requestTab.length==2) {
	        			try {
	        				nbElementsByPages=Integer.parseInt(requestTab[1]);
	        			}catch( NumberFormatException e) {
	        				throw new NotIntExpectedException();
	        			}
    				}
        		}else
    				c.requete(request,nbElementsByPages);
    		}catch(NotIntExpectedException | NotACommandException | AllAttributesNeededException| PasLeBonFormatTimestamp |
    				NotAIntegerException | RequeteSansResultatException| NotIntForPageException| TimestampDiscotinuedInferiorToTimestampIntroduced 
    				|BaseVide |PasDePagesNegException  e){
    			Logger logger = LoggerFactory.getLogger(UI.class);
    			logger.error(e.getMessage());
    		}
    		
    	}
    	sc.close();
    }

}
