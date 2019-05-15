package com.excilys.cdb.persistance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DAOFactory {

	String configFile = "/hikari.properties";
	HikariConfig cfg = new HikariConfig(configFile);
	HikariDataSource ds = new HikariDataSource(cfg);

	private DAOFactory() {

	}

	private static DAOFactory INSTANCE = null;

	/**
	 * Point d'accès pour l'instance unique du singleton
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static DAOFactory getInstance() {
		if (INSTANCE == null) {
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
			INSTANCE = new DAOFactory();

		}
		return INSTANCE;
	}

	public Connection getConnection() throws SQLException {

		return ds.getConnection();
	}
}
