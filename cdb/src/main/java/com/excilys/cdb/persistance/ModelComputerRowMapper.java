package com.excilys.cdb.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.ModelComputer;

public class ModelComputerRowMapper implements RowMapper<ModelComputer>{
	
	@Override
	public ModelComputer mapRow(ResultSet rs, int rowNum) throws SQLException {
		ModelComputer currentComputer=null;
		int Id = rs.getInt( "computer.id" );
		String name = rs.getString( "computer.name" );
		Integer cId = (rs.getInt( "company.id" )!=0)? (rs.getInt( "company.id" )):null;
		Timestamp introduced = rs.getTimestamp( "introduced" );
		Timestamp discontinued = rs.getTimestamp( "discontinued" );
		String companyName = rs.getString( "company_name" );
		currentComputer = new ModelComputer(Id,name,introduced,discontinued,cId);
		if(companyName!=null) {
			currentComputer.setCompanyName(companyName);
		 }
	    //creer le modelComputer et ajout dans la liste
		return currentComputer;
	}


}

