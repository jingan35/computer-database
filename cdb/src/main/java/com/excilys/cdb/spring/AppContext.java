package com.excilys.cdb.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.service.MapperCompany;
import com.excilys.cdb.service.MapperComputer;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.validator.Validator;

public class AppContext {

private static AppContext INSTANCE = new AppContext();
	
	AnnotationConfigApplicationContext ctx;
	
	private AppContext() {
		ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        try {
        	ctx.refresh();
        } catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode qui renvoie l'objet singleton AppContext.
	 * @return Un objet de type AppContext
	 */
	public static AppContext getInstance() {
		return INSTANCE;
	}

	public ServiceCompany getServiceCompany() {
		return ctx.getBean(ServiceCompany.class);
	}

	public ServiceComputer getServiceComputer() {
		return ctx.getBean(ServiceComputer.class);
	}

	public MapperCompany getMapperCompany() {
		return ctx.getBean(MapperCompany.class);
	}

	public MapperComputer getMapperComputer() {
		return ctx.getBean(MapperComputer.class);
	}

	public Validator getValidator() {
		return ctx.getBean(Validator.class);
}
	
}
