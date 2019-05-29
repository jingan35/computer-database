package com.excilys.cdb.persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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

	public ArrayList<ModelCompany> select(int nbRowByPage, int page) throws BaseVide {
		// TODO Auto-generated method stub
		ArrayList<ModelCompany> companyList = new ArrayList<ModelCompany>();
		ResultSet resultat = null;
		try (Connection connexion = daoFactory.getConnection()) {

			String requete = "SELECT id, name FROM company LIMIT ? OFFSET ?;";
			PreparedStatement preparedStatement = connexion.prepareStatement(requete);
			preparedStatement.setInt(1, nbRowByPage);
			preparedStatement.setInt(2, nbRowByPage * (page - 1));
			/* requête BDD */
			resultat = preparedStatement.executeQuery();

			if (resultat.next()) {
				int id = resultat.getInt("id");
				String name = resultat.getString("name");
				// creer le modelComputer et ajout dans la liste
				companyList.add(new ModelCompany(id, name));
			}

			else {
				throw new BaseVide();
			}

			/* Récupération des données du résultat de la requête de lecture */
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String name = resultat.getString("name");
				// creer le modelComputer et ajout dans la liste
				companyList.add(new ModelCompany(id, name));
			}

		} catch (SQLException e) {
			/* Gérer les éventuelles erreurs ici */
			Logger logger = LoggerFactory.getLogger(DaoCompany.class);
			logger.error(e.getMessage(), e);
		}
		/* Exécution d'une requête de lecture */

		return companyList;
	}

	public ArrayList<ModelCompany> selectAll() throws BaseVide {
		// TODO Auto-generated method stub
		ArrayList<ModelCompany> companyList = new ArrayList<ModelCompany>();
		ResultSet resultat = null;
		try (Connection connexion = daoFactory.getConnection()) {

			String requete = "SELECT id, name FROM company;";
			PreparedStatement preparedStatement = connexion.prepareStatement(requete);

			/* requête BDD */
			resultat = preparedStatement.executeQuery();

			if (resultat.next()) {
				int id = resultat.getInt("id");
				String name = resultat.getString("name");
				// creer le modelComputer et ajout dans la liste
				companyList.add(new ModelCompany(id, name));
			}

			else {
				throw new BaseVide();
			}

			/* Récupération des données du résultat de la requête de lecture */
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String name = resultat.getString("name");
				// creer le modelComputer et ajout dans la liste
				companyList.add(new ModelCompany(id, name));
			}

		} catch (SQLException e) {
			/* Gérer les éventuelles erreurs ici */
			Logger logger = LoggerFactory.getLogger(DaoCompany.class);
			logger.error(e.getMessage(), e);
		}
		/* Exécution d'une requête de lecture */

		return companyList;
	}

	public void delete(int id) {
		String requestComputer = "DELETE FROM computer WHERE company_id=? ;";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("id", id);
		int idUpdated = namedParameterJdbcTemplate.update(requestComputer, namedParameters);

	}

}
