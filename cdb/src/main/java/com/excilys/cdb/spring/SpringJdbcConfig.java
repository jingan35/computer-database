package com.excilys.cdb.spring;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.cdb.persistance.DAOFactory;

@Component
public class SpringJdbcConfig {
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	DAOFactory daoFactory;
	JdbcTemplate jdbcTemplate ;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	SpringJdbcConfig(){
		ctx.register(AppConfig.class);
        ctx.refresh();
        this.daoFactory=ctx.getBean(DAOFactory.class);
        this.jdbcTemplate=new JdbcTemplate(daoFactory.getDataSource());
        this.namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(daoFactory.getDataSource());
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public NamedParameterJdbcTemplate getnamesParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
}
