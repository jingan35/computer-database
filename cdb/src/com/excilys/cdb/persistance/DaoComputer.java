package com.excilys.cdb.persistance;
import java.sql.*;
import java.util.ArrayList;

import com.excilys.cdb.model.*;


public class DaoComputer extends Dao<ModelComputer>{

	
	public DaoComputer(){
		
		
	}
	
	public ArrayList<ModelComputer> select() {
		ArrayList<ModelComputer> computerList = new ArrayList<ModelComputer>();
		ResultSet resultat =null;
		try (Connection connexion=DriverManager.getConnection( url, utilisateur, motDePasse )){
		    Statement statement=connexion.createStatement();
		    /* requête BDD */
		    resultat = statement.executeQuery( "SELECT * FROM computer;" );
		    
			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
			    int id = resultat.getInt( "id" );
			    String name = resultat.getString( "name" );
			    int cId = resultat.getInt( "company_id" );
			    Timestamp introduced = resultat.getTimestamp( "introduced" );
			    Timestamp discontinued = resultat.getTimestamp( "discontinued" );
			  //Test_affichage
			    System.out.println("id="+id+" name: "+name);
			    
			    //creer le modelComputer et ajout dans la liste
			    computerList.add( new ModelComputer(id,name,introduced,discontinued,cId));
			}

		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
			
			e.printStackTrace();
		} 
		/* Exécution d'une requête de lecture */
		
		
		return computerList;
		
	}
	
	public ModelComputer selectOne(String nom) {
		ModelComputer mC = null;
		ResultSet resultat =null;
		try (Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse )){
		    
		    Statement statement=connexion.createStatement();
		    /* requête BDD */
		    resultat = statement.executeQuery( "SELECT * FROM computer WHERE name='"+nom+"';" );
			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
			    int id = resultat.getInt( "id" );
			    String name = resultat.getString( "name" );
			    int cId = resultat.getInt( "company_id" );
			    Timestamp introduced = resultat.getTimestamp( "introduced" );
			    Timestamp discontinued = resultat.getTimestamp( "discontinued" );
			  //Test_affichage
			    System.out.println("id="+id+" name: "+name+ " introduced Time:"+introduced+ " discontinued Time:"+ discontinued+ " company_id= "+cId);

			    
			    //creer le modelComputer
			    mC = new ModelComputer(id,name,introduced,discontinued,cId);
			}

		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
			
			e.printStackTrace();
		} 
		/* Exécution d'une requête de lecture */
		
		return mC;
		
		
	}
	
	public ModelComputer selectOne(int id) {
		//Connection connexion=null;
		ModelComputer mC = null;
		ResultSet resultat =null;
		try (Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse )){
		    
		    Statement statement=connexion.createStatement();
		    /* requête BDD */
		    resultat = statement.executeQuery( "SELECT * FROM computer WHERE id="+id+";" );
			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
			    int Id = resultat.getInt( "id" );
			    String name = resultat.getString( "name" );
			    int cId = resultat.getInt( "company_id" );
			    Timestamp introduced = resultat.getTimestamp( "introduced" );
			    Timestamp discontinued = resultat.getTimestamp( "discontinued" );
			  //Test_affichage
			    System.out.println("id="+Id+" name: "+name+ " introduced Time:"+introduced+ " discontinued Time:"+ discontinued+ " company_id= "+cId);

			    
			    //creer le modelComputer
			    mC = new ModelComputer(id,name,introduced,discontinued,cId);
			}

		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
			
			e.printStackTrace();
		}
		/* Exécution d'une requête de lecture */
		
		return mC;
		
		
	}

	@Override
	public void update(int id, ModelComputer model) {	
		// TODO Auto-generated method stub
		ResultSet resultat =null;
		try (Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse )){
		    
		    Statement statement=connexion.createStatement();
		    /* requête BDD */
		    int statut = statement.executeUpdate(" UPDATE computer SET "
		    		+ "id = "+model.getId()+", name = '"+model.getName()+"', introduced = "
		    		+model.getIntroduced()+", discontinued = "+model.getDiscontinued()+", company_id= "+model.getCompanyId()+""
		    		+ " WHERE id="+id+";");
			

		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
			
			e.printStackTrace();
		} 
	}

	@Override
	public void insert(ModelComputer model) {
		// TODO Auto-generated method stub
		ResultSet resultat =null;
		try(Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse )) {
		    
		    Statement statement=connexion.createStatement();
		    /* requête BDD */
		    int statut = statement.executeUpdate( "INSERT INTO computer (id,name,introduced,discontinued,company_id) "
		    		+ "VALUES ("+model.getId()+", '"+model.getName()+"', "+model.getIntroduced()+","+ model.getDiscontinued()+","+ model.getCompanyId()+");" );
			

		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
			
			e.printStackTrace();
		} 
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
				ResultSet resultat =null;
				try (Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse )){
				    
				    Statement statement=connexion.createStatement();
				    /* requête BDD */
				    int statut = statement.executeUpdate( "DELETE FROM computer WHERE id='"+ id +"';");
					

				} catch ( SQLException e ) {
				    /* Gérer les éventuelles erreurs ici */
					
					e.printStackTrace();
				} 
		
	}
	
	
}
