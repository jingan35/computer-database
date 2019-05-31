package com.excilys.cdb.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.ModelCompany;
import com.excilys.cdb.model.ModelComputer;

public class ModelCompanyRowMapper implements RowMapper<ModelCompany>{
	
	public ModelCompany mapRow(ResultSet rs, int rowNum) throws SQLException {
		ModelCompany company=null;
		int id = rs.getInt("id");
		String name = rs.getString("name");
		company=new ModelCompany(id, name);
		return company;
	}

}
