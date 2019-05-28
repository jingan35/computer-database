package com.excilys.cdb.persistance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class DAOFactory {

	String configFile = "/hikari.properties";
	HikariConfig cfg = new HikariConfig(configFile);
	HikariDataSource ds = new HikariDataSource(cfg);

	private DAOFactory() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public Connection getConnection() throws SQLException {

		return ds.getConnection();
	}
	
	public HikariDataSource getDataSource() {
		return ds;
	}
}
