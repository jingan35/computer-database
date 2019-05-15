package com.excilys.cdb.persistance;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.model.*;
import com.excilys.cdb.ui.UI;

public class DaoCompany {
	Connection connexion;
	/* Création de l'objet gérant les requêtes */
	DAOFactory daoFactory= DAOFactory.getInstance();
	
	private DaoCompany(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Logger logger = LoggerFactory.getLogger(DaoCompany.class);
			logger.error(e.getMessage(), e);
		}
		
		// load a properties file
		
	}
	private static DaoCompany INSTANCE=new DaoCompany();
	
	public static DaoCompany getInstance() {
		return INSTANCE;
	}
	

	public ArrayList<ModelCompany> select(int nbRowByPage,int page) throws BaseVide {
		// TODO Auto-generated method stub
		ArrayList<ModelCompany> companyList = new ArrayList<ModelCompany>();
		ResultSet resultat =null;
		try (Connection connexion = daoFactory.getConnection()){
		    
			String requete="SELECT id, name FROM company LIMIT ? OFFSET ?;";
		    PreparedStatement preparedStatement=connexion.prepareStatement(requete);
		    preparedStatement.setInt(1, nbRowByPage);
		    preparedStatement.setInt(2, nbRowByPage*(page-1));
		    /* requête BDD */
		    resultat = preparedStatement.executeQuery( );
		    
		    if ( resultat.next() ) {
			    int id = resultat.getInt( "id" );
			    String name = resultat.getString( "name" );
			    //creer le modelComputer et ajout dans la liste
			    companyList.add(new ModelCompany(id,name));
			}
		    
		    else {
		    	throw new BaseVide();
		    }
		    
			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
			    int id = resultat.getInt( "id" );
			    String name = resultat.getString( "name" );
			    //creer le modelComputer et ajout dans la liste
			    companyList.add( new ModelCompany(id,name));
			}

		} catch ( SQLException e ) {
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
		ResultSet resultat =null;
		try (Connection connexion = daoFactory.getConnection()){
		    
			String requete="SELECT id, name FROM company;";
		    PreparedStatement preparedStatement=connexion.prepareStatement(requete);
		    
		    /* requête BDD */
		    resultat = preparedStatement.executeQuery( );
		    
		    if ( resultat.next() ) {
			    int id = resultat.getInt( "id" );
			    String name = resultat.getString( "name" );
			    //creer le modelComputer et ajout dans la liste
			    companyList.add(new ModelCompany(id,name));
			}
		    
		    else {
		    	throw new BaseVide();
		    }
		    
			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
			    int id = resultat.getInt( "id" );
			    String name = resultat.getString( "name" );
			    //creer le modelComputer et ajout dans la liste
			    companyList.add( new ModelCompany(id,name));
			}

		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
			Logger logger = LoggerFactory.getLogger(DaoCompany.class);
			logger.error(e.getMessage(), e);
		} 
		/* Exécution d'une requête de lecture */
		
		
		return companyList;
	}

}
