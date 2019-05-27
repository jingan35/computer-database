package com.excilys.cdb.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.excilys.cdb.persistance", "com.excilys.cdb.controller", "com.excilys.cdb.service"
		, "com.excilys.cdb.validator"})
public class AppConfig {

	
	
	
}
