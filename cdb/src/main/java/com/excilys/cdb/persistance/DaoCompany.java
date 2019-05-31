package com.excilys.cdb.persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.WebUiObject.Page;
import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.model.*;
import com.excilys.cdb.spring.AppConfig;
import com.excilys.cdb.ui.UI;

@Repository
public class DaoCompany {
	Connection connexion;
	/* Création de l'objet gérant les requêtes */

	DAOFactory daoFactory;
	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private DaoCompany(DAOFactory daoFactory) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Logger logger = LoggerFactory.getLogger(DaoCompany.class);
			logger.error(e.getMessage(), e);
		}
		this.daoFactory = daoFactory;
		this.jdbcTemplate = new JdbcTemplate(daoFactory.getDataSource());
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(daoFactory.getDataSource());

	}

	public List<ModelCompany> select(Page page) throws BaseVide {
		/* requête BDD */
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("NbComputersByPage", Integer.parseInt(page.getNbComputersByPage()),Types.INTEGER);
		namedParameters.addValue("departureId", Integer.parseInt(page.getNbComputersByPage())*(Integer.parseInt(page.getCurrentPage())-1),Types.INTEGER);
		
		String requete = "SELECT id, name FROM company LIMIT :NbComputersByPage OFFSET :departureId ;";
		ModelCompanyRowMapper rowMapper = new ModelCompanyRowMapper();
		List<ModelCompany> listModelCompany= namedParameterJdbcTemplate.query(requete,namedParameters, rowMapper);

		if(listModelCompany.isEmpty()) {
			throw new BaseVide();
		}
		
		return listModelCompany;
	}

	public List<ModelCompany> selectAll() throws BaseVide {
		/* requête BDD */
		String requete = "SELECT id, name FROM company;";
		ModelCompanyRowMapper rowMapper = new ModelCompanyRowMapper();
		List<ModelCompany> listModelCompany= namedParameterJdbcTemplate.query(requete, rowMapper);

		if(listModelCompany.isEmpty()) {
			throw new BaseVide();
		}
		
		return listModelCompany;
	}

	public void delete(int id) {
		String requestCompany = "DELETE FROM company WHERE id=:id ;";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		int idUpdated = namedParameterJdbcTemplate.update(requestCompany, namedParameters);

	}

}
