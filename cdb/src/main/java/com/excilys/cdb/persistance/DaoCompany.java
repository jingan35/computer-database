package com.excilys.cdb.persistance;
import java.sql.*;
import java.util.ArrayList;

import com.excilys.cdb.model.*;

public class DaoCompany extends Dao<ModelCompany>{
	Connection connexion;
	/* Création de l'objet gérant les requêtes */
	Statement statement;
	
	private DaoCompany(){
		
	}
	private static DaoCompany INSTANCE=new DaoCompany();
	
	public static DaoCompany getInstance() {
		return INSTANCE;
	}
	

	public ArrayList<ModelCompany> select(int nbRowByPage,int page) {
		// TODO Auto-generated method stub
		ArrayList<ModelCompany> companyList = new ArrayList<ModelCompany>();
		ResultSet resultat =null;
		try (Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse )){
		    
			String requete="SELECT * FROM company LIMIT ? OFFSET ?;";
		    PreparedStatement preparedStatement=connexion.prepareStatement(requete);
		    preparedStatement.setInt(1, nbRowByPage);
		    preparedStatement.setInt(2, nbRowByPage*(page-1));
		    /* requête BDD */
		    resultat = preparedStatement.executeQuery( );
		    
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
	public int insert(ModelCompany model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
}
