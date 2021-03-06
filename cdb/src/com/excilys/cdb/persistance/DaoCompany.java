package com.excilys.cdb.persistance;
import java.sql.*;
import java.util.ArrayList;

import com.excilys.cdb.model.*;

public class DaoCompany extends Dao<ModelCompany>{
	Connection connexion;
	/* Création de l'objet gérant les requêtes */
	Statement statement;
	
	public DaoCompany(){
		
	}

	@Override
	public ArrayList<ModelCompany> select() {
		// TODO Auto-generated method stub
		ArrayList<ModelCompany> companyList = new ArrayList<ModelCompany>();
		ResultSet resultat =null;
		try (Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse )){
		    
		    Statement statement=connexion.createStatement();
		    /* requête BDD */
		    resultat = statement.executeQuery( "SELECT * FROM company;" );
		    
			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
			    int id = resultat.getInt( "id" );
			    String name = resultat.getString( "name" );
			    //creer le modelComputer et ajout dans la liste
			    companyList.add( new ModelCompany(id,name));
			}

		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
			
			e.printStackTrace();
		} 
		/* Exécution d'une requête de lecture */
		
		
		return companyList;
	}

	@Override
	public void update(int id, ModelCompany model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(ModelCompany model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
}
