package com.excilys.cdb.persistance;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.WebUiObject.Page;
import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.exception.RequeteSansResultatException;
import com.excilys.cdb.model.*;

@Repository
public class DaoComputer {
	
	
	
	DAOFactory daoFactory;
	JdbcTemplate jdbcTemplate ;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	private static final Logger logger = LoggerFactory.getLogger(DaoComputer.class);
	
	private DaoComputer(DAOFactory daoFactory){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			logger.error(e.getMessage(), e);
		}
		
        this.daoFactory = daoFactory;
		this.jdbcTemplate=new JdbcTemplate(daoFactory.getDataSource());
	    this.namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(daoFactory.getDataSource());
		
	}
	
	
	public List<ModelComputer> select(Page page) throws BaseVide {
		//try (Connection connexion=daoFactory.getConnection()){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			String requete="SELECT computer.id,computer.name,introduced,discontinued,company.name AS company_name,company.id "
		    		+ "FROM computer LEFT JOIN company ON computer.company_id = company.id ";
			
			String searchRequestPart = "WHERE computer.name LIKE :searched ";
			
			String OrderByRequestPart = "ORDER BY %s ";
			
			String paginationRequestPage = "LIMIT :NbComputersByPage OFFSET :departureId ;";
			
			String detailsRequest = requete;
			if(page.getSearched()!=null) {
				namedParameters.addValue("searched", "%"+page.getSearched()+"%", Types.VARCHAR);
				detailsRequest+=searchRequestPart;
			}
			if(page.getIsOrdered()!=false) {
				OrderByRequestPart = String.format(OrderByRequestPart,page.getOrderBy().getName());
				detailsRequest+=OrderByRequestPart;
			}
			detailsRequest+=paginationRequestPage;
			namedParameters.addValue("NbComputersByPage", Integer.parseInt(page.getNbComputersByPage()),Types.INTEGER);
			namedParameters.addValue("departureId", Integer.parseInt(page.getNbComputersByPage())*(Integer.parseInt(page.getCurrentPage())-1),Types.INTEGER);
			
			 
		    
		    /* requête BDD */
			ModelComputerRowMapper rowMapper = new ModelComputerRowMapper();
			List<ModelComputer> listModelComputers= namedParameterJdbcTemplate.query(detailsRequest, namedParameters, rowMapper);
		   
		    
		    if ( listModelComputers.isEmpty()) {
		    	throw new BaseVide();
		    	
			}
		    
		return listModelComputers;
		
	}
	
	
	
	public int selectCount(String search) throws BaseVide {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		List<ModelComputer> computerList ;
		ResultSet resultat =null;
		int computerCount=0;
		String requete=(search==null)?"SELECT COUNT(id) AS idCount FROM computer ;":("SELECT COUNT(computer.id) AS idCount "
	    		+ "FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE :search;");
	    
	    namedParameters.addValue("search", "%"+search+"%", Types.VARCHAR);
	    
	    computerCount=namedParameterJdbcTemplate.queryForObject(requete, namedParameters, Integer.class);
		
		
		return computerCount;
		
	}
	
	
	public ModelComputer selectOne(int id) throws RequeteSansResultatException {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		//Connection connexion=null;
		ModelComputer mC = null;
		
		String detailsRequest="SELECT computer.id,computer.name,introduced,discontinued,company.name AS company_name,company.id "
	    		+ "FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id=:id;";
		
		namedParameters.addValue("id", id);
		ModelComputerRowMapper rowMapper = new ModelComputerRowMapper();
		List<ModelComputer> listModelComputers= namedParameterJdbcTemplate.query(detailsRequest, namedParameters, rowMapper);
		
		if ( listModelComputers.isEmpty()) {
			throw new RequeteSansResultatException();
	    	
		}
	    
		return listModelComputers.get(0);
	/* Exécution d'une requête de lecture */
		/* Exécution d'une requête de lecture */ 
		/*catch (SQLException e) {
			Logger logger = LoggerFactory.getLogger(DaoCompany.class);
			logger.error(e.getMessage(), e);
		}
		 */
		
		
	}

	public void update(int id, ModelComputer model) {
		String updateSql= " UPDATE computer SET id = :id, name = :name, introduced = :introduced, discontinued = :discontinued, company_id=:company_id WHERE id=:id;";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(model);
		int idUpdated = namedParameterJdbcTemplate.update(updateSql, vParams);
		
	}

	public int insert(ModelComputer model) {
		// TODO Auto-generated method stub
		String insertSql= "INSERT INTO computer (id,name,introduced,discontinued,company_id) VALUES (:id, :name, :introduced, :discontinued, :company_id);" ;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(model);
		int idUpdated = namedParameterJdbcTemplate.update(insertSql, vParams);
		
		
		
		ResultSet resultat =null;
		int idAdded=0;
		
		return idUpdated;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
		String deleteRequest ="DELETE FROM computer WHERE id=:id;";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		namedParameters.addValue("id", id);
		int idUpdated = namedParameterJdbcTemplate.update(deleteRequest,namedParameters);
		
		
	}
	
	public void deleteByCompanyId(int id) {
		String requestComputer = "DELETE FROM computer WHERE company_id=:companyId ;";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("companyId", id);
		int idUpdated = namedParameterJdbcTemplate.update(requestComputer, namedParameters);
	}
	
	
}
