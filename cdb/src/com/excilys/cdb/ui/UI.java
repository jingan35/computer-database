package com.excilys.cdb.ui;

import java.util.Scanner;
import com.excilys.cdb.controller.Controller;
import com.excilys.cdb.exception.*;
import com.excilys.cdb.service.ServiceCompany;

public class UI {
	
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
    	while (!stop) {
    		System.out.println("Help "
    				+ "\nComputers list : cl "
    				+ "\nCompanies list : lc "
    				+ "\nUpdate a Computer info : uc; id ;AttributeToModify ; NewValue; AttributeToModify2; NewValue2;etc.. "
    				+ "\nDelete a Computer : dc; id "
    				+ "\nShow a computer Details: sc;id "
    				+ "\nAdd a computer: ac; id; name; introducedTimestamp('n' if null); discontinuedTimestamp('n' if null); company_id "); 
    		System.out.println("Quel est vôtre requête ? ");
    		Scanner sc = new Scanner(System.in);
    		try {
    			c.requete(sc.nextLine());
    		}catch(NotIntExpectedException nIEE){
    			System.out.println(nIEE.getMessage());
    		}
    		catch(NotACommandException nACE){
    			System.out.println(nACE.getMessage());
    		}
    		catch(AllAttributesNeededException aANE) {
    			System.out.println(aANE.getMessage());
    		}
    		catch(PasLeBonFormatTimestamp pLBFTs) {
    			System.out.println(pLBFTs.getMessage());
    		}
    		catch(NotAIntegerException nAIE) {
    			System.out.println(nAIE.getMessage());
    		}
    	}
    }

}
