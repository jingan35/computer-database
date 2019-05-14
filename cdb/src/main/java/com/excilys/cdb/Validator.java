package com.excilys.cdb;

import java.sql.Timestamp;

import com.excilys.cdb.exception.InvalidDateOrderException;
import com.excilys.cdb.exception.InvalidDateValueException;
import com.excilys.cdb.exception.NotAIntegerException;
import com.excilys.cdb.exception.PageNumberLessThan1;
import com.excilys.cdb.exception.RequiredElementException;
import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.mapper.DtoComputer;

import ch.qos.logback.classic.Logger;

public class Validator {

protected Logger logger;
	
	private Validator() {}
	
	private void validateId(String id) throws NotAIntegerException {
		try {
			int localId = Integer.parseInt(id);
			if (localId < 0)
				throw new Exception();
		} catch (Exception e) {
			throw new NotAIntegerException();
		}
	}
	
	private void required(String name, String element) throws RequiredElementException {
		if (element == null) {
			throw new RequiredElementException(name);
		}
	}
	
	private void validateDate(String date) {
		if (date != null) {
			try {
				Timestamp.valueOf(date+" 12:00:00");
			} catch (Exception e) {
				RuntimeException exception = new InvalidDateValueException(date);
				this.logger.error(exception.getMessage());
				throw exception;
			}
		}
	}
	
	private void validateDateOrder(String before, String after) {
		if (before != null && after != null) {
			if (Timestamp.valueOf(before+" 12:00:00").after(Timestamp.valueOf(after+" 12:00:00"))) {
				InvalidDateOrderException exception = new InvalidDateOrderException(before, after);
				this.logger.error(exception.getMessage());
				
			}
		}
	}
	
	public void validateCompanyDto(DtoCompany companyDto) throws NotAIntegerException, RequiredElementException {
		this.validateId(companyDto.getId());
		this.required("name", companyDto.getName());
	}
	
	public void validateComputerDto(DtoComputer computerDto) throws NotAIntegerException, RequiredElementException {
		this.validateId(computerDto.getId());
		this.required("name", computerDto.getName());
		this.validateDate(computerDto.getIntroduced());
		this.validateDate(computerDto.getDiscontinued());
		this.validateDateOrder(computerDto.getIntroduced(), computerDto.getDiscontinued());
		this.validateId(computerDto.getCompanyId());
	}
	
	public void validatePagination(String paginationValue) throws NotAIntegerException {
		try {
			int localId = Integer.parseInt(paginationValue);
			if (localId <= 0)
				throw new PageNumberLessThan1(localId);
		} catch (Exception e) {
			throw new NotAIntegerException();
		}
}
	
}
