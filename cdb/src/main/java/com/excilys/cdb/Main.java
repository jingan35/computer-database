package com.excilys.cdb;

import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.model.ModelComputer;
import com.excilys.cdb.persistance.DaoComputer;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.servlet.AddComputerServlet;
import com.excilys.cdb.servlet.DashboardServlet;
import com.excilys.cdb.servlet.EditComputerServlet;
import com.excilys.cdb.spring.AppConfig;
import com.excilys.cdb.ui.UI;
import com.excilys.cdb.validator.Validator;
import com.excilys.cdb.model.ModelCompany;
import com.excilys.cdb.persistance.DaoCompany;

public class Main {

	public static void main(String[] args) {
		
		/*DaoCompany daoC= new DaoCompany();
		daoC.select();*/
		UI ui=UI.getInstance();
		ui.start();
		/*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        ServiceComputer serviceComputer = ctx.getBean(ServiceComputer.class);
        ServiceCompany serviceCompany =ctx.getBean(ServiceCompany.class);
        Validator validator= ctx.getBean(Validator.class);*/
        
		
	}
	
}
