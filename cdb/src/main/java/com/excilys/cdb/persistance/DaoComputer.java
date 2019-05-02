package com.excilys.cdb.persistance;
import java.sql.*;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.exception.RequeteSansResultatException;
import com.excilys.cdb.exception.TimestampDiscotinuedInferiorToTimestampIntroduced;
import com.excilys.cdb.model.*;


public class DaoComputer extends Dao<ModelComputer>{

	
	private DaoComputer(){
		
		
	}
	private static DaoComputer INSTANCE=new DaoComputer();
	
	public static DaoComputer getInstance() {
		return INSTANCE;
	}
	
	public ArrayList<ModelComputer> select(int nbRowByPage,int page) throws BaseVide {
		ArrayList<ModelComputer> computerList = new ArrayList<ModelComputer>();
		ResultSet resultat =null;
		try (Connection connexion=DriverManager.getConnection( url, utilisateur, motDePasse )){
			String requete="SELECT * FROM computer LIMIT ? OFFSET ?;";
		    PreparedStatement preparedStatement=connexion.prepareStatement(requete);
		    preparedStatement.setInt(1, nbRowByPage);
		    preparedStatement.setInt(2, nbRowByPage*(page-1));
		    /* requête BDD */
		    resultat = preparedStatement.executeQuery( );
		    
		    if ( resultat.next() ) {
			    int id = resultat.getInt( "id" );
			    String name = resultat.getString( "name" );
			    int cId = resultat.getInt( "company_id" );
			    Timestamp introduced = resultat.getTimestamp( "introduced" );
			    Timestamp discontinued = resultat.getTimestamp( "discontinued" );
			  			    
			    //creer le modelComputer et ajout dans la liste
			    computerList.add(new ModelComputer(id,name,introduced,discontinued,cId));
			}
		    
		    else {
		    	throw new BaseVide();
		    }
		    
			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
			    int id = resultat.getInt( "id" );
			    String name = resultat.getString( "name" );
			    int cId = resultat.getInt( "company_id" );
			    Timestamp introduced = resultat.getTimestamp( "introduced" );
			    Timestamp discontinued = resultat.getTimestamp( "discontinued" );
			  			    
			    //creer le modelComputer et ajout dans la liste
			    computerList.add( new ModelComputer(id,name,introduced,discontinued,cId));
			}

		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
			
			Logger logger = LoggerFactory.getLogger(DaoCompany.class);
			logger.trace(e.getMessage(), e);
		} 
		/* Exécution d'une requête de lecture */
		
		
		return computerList;
		
	}
	
	
	public ModelComputer selectOne(int id) throws RequeteSansResultatException {
		//Connection connexion=null;
		ModelComputer mC = null;
		ResultSet resultat =null;
		try (Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse )){
		    String detailsRequest="SELECT computer.id,computer.name,introduced,discontinued,company.name AS company_name,company.id "
		    		+ "FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id=?;";
		    PreparedStatement preparedStatement=connexion.prepareStatement(detailsRequest);
		    preparedStatement.setInt(1, id);
		    /* requête BDD */
		    resultat = preparedStatement.executeQuery(  );
			/* Récupération des données du résultat de la requête de lecture */
			if(resultat.next()) {
				int Id = resultat.getInt( "computer.id" );
				String name = resultat.getString( "computer.name" );
				Integer cId = (Integer)(resultat.getObject( "company.id" ));
				Timestamp introduced = resultat.getTimestamp( "introduced" );
				Timestamp discontinued = resultat.getTimestamp( "discontinued" );
				String companyName = resultat.getString( "company_name" );
				    
				    //creer le modelComputer
				    mC = new ModelComputer(Id,name,introduced,discontinued,cId);
				    if(companyName!=null) {
				    	mC.setCompanyName(companyName);
				    }
			}
			else {
				throw new RequeteSansResultatException();
				
			}
			

		}
		/* Exécution d'une requête de lecture */ 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mC;
		
		
	}

	@Override
	public void update(int id, ModelComputer model) {	
		// TODO Auto-generated method stub
		ResultSet resultat =null;
		int idUpdated=0;
		try (Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse )){
			/* requête BDD */
		    String updateSql= " UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id=? WHERE id=?;";
		    PreparedStatement preparedStatement=connexion.prepareStatement(updateSql,Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setInt(1, model.getId());
		    preparedStatement.setString(2, model.getName());
		    preparedStatement.setTimestamp(3, model.getIntroduced());
		    preparedStatement.setTimestamp(4, model.getDiscontinued());
		    if(model.getCompanyId()!=null)
		    	preparedStatement.setInt(5, model.getCompanyId());
		    else
		    	preparedStatement.setObject(5, model.getCompanyId());
		    preparedStatement.setInt(6, model.getId());
		    int statut = preparedStatement.executeUpdate();
		    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		    if(generatedKeys.next())
		    	idUpdated=generatedKeys.getInt(1);
		    
		    /*
		    Statement statement=connexion.createStatement();
		    
		    int statut = statement.executeUpdate(" UPDATE computer SET "
		    		+ "id = "+model.getId()+", name = '"+model.getName()+"', introduced = "
		    		+model.getIntroduced()+", discontinued = "+model.getDiscontinued()+", company_id= "+model.getCompanyId()+""
		    		+ " WHERE id="+id+";");
			*/

		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
			
			e.printStackTrace();
		} 
	}

	@Override
	public int insert(ModelComputer model) {
		// TODO Auto-generated method stub
		ResultSet resultat =null;
		int idAdded=0;
		try(Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse )) {
		    
		    /* requête BDD */
			
		    String insertSql= "INSERT INTO computer (id,name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?, ?);" ;
		    PreparedStatement preparedStatement= connexion.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setInt(1, model.getId());
		    preparedStatement.setString(2, model.getName());
		    preparedStatement.setTimestamp(3, model.getIntroduced());
		    preparedStatement.setTimestamp(4, model.getDiscontinued());
		    if(model.getCompanyId()==null) {
		    	preparedStatement.setObject(5, model.getCompanyId());
		    }else
		    	preparedStatement.setInt(5, model.getCompanyId());
		    int statut = preparedStatement.executeUpdate();
		    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		    if(generatedKeys.next())
		    	idAdded=generatedKeys.getInt(1);
		    

		}catch(java.sql.SQLIntegrityConstraintViolationException iCVE) {
			System.out.println("besoin d'une id de company existente pour maj et ajout ou d'un id de computer non existente pour ajout ");
		}
		catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
			
			e.printStackTrace();
		}
		
		return idAdded;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
				ResultSet resultat =null;
				try (Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse )){
				    
				    String deleteRequest ="DELETE FROM computer WHERE id=?;";
				    PreparedStatement preparedStatement=connexion.prepareStatement(deleteRequest);
				    preparedStatement.setInt(1, id);
				    /* requête BDD */
				    int statut = preparedStatement.executeUpdate( );
					

				} catch ( SQLException e ) {
				    /* Gérer les éventuelles erreurs ici */
					
					e.printStackTrace();
				} 
		
	}
	
	
}
