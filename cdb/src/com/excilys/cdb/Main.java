package com.excilys.cdb;

import java.sql.*;

import com.excilys.cdb.model.ModelComputer;
import com.excilys.cdb.persistance.DaoComputer;
import com.excilys.cdb.ui.UI;
import com.excilys.cdb.model.ModelCompany;
import com.excilys.cdb.persistance.DaoCompany;

public class Main {

	public static void main(String[] args) {
		
		/*DaoCompany daoC= new DaoCompany();
		daoC.select();*/
		UI ui=UI.getInstance();
		ui.start();
		
		
	}
	
}
