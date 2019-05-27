package com.excilys.cdb.validator;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.InvalidDateOrderException;
import com.excilys.cdb.exception.InvalidDateValueException;
import com.excilys.cdb.exception.NotAIntegerException;
import com.excilys.cdb.exception.PageNumberLessThan1Exception;
import com.excilys.cdb.exception.RequiredElementException;
import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.mapper.DtoComputer;
import com.excilys.cdb.ui.UI;

@Component
public class Validator {

	protected Logger logger = LoggerFactory.getLogger(Validator.class);

	private Validator() {
	}


	private void validateId(String id) throws NotAIntegerException {
		if (!id.equals("null")) {
			try {
				int localId = Integer.parseInt(id);
				if (localId < 0)
					throw new Exception();
			} catch (Exception e) {
				throw new NotAIntegerException();
			}
		}
	}

	private void required(String name, String element) throws RequiredElementException {
		if (element == null || element.trim().equals("")) {
			throw new RequiredElementException(name);
		}
	}

	private void validateDate(String date) throws InvalidDateValueException {
		if (date != null) {
			try {
				Timestamp.valueOf(date + " 12:00:00");
			} catch (Exception e) {
				RuntimeException exception = new InvalidDateValueException(date);
				this.logger.error(exception.getMessage());
				throw exception;
			}
		}
	}

	private void validateDateOrder(String before, String after) throws InvalidDateOrderException {
		if (before != null && after != null) {
			if (Timestamp.valueOf(before + " 12:00:00").after(Timestamp.valueOf(after + " 12:00:00"))) {
				InvalidDateOrderException exception = new InvalidDateOrderException(before, after);
				this.logger.error(exception.getMessage());
				throw exception;

			}
		}
	}

	public void validateCompanyDto(DtoCompany companyDto) throws NotAIntegerException, RequiredElementException {
		this.validateId(companyDto.getId());
		this.required("name", companyDto.getName());
	}

	public void validateComputerDto(DtoComputer computerDto)
			throws NotAIntegerException, RequiredElementException, InvalidDateOrderException {
		this.validateId(computerDto.getId());
		this.required("name", computerDto.getName());
		if ((computerDto.getIntroduced()!=null)&&(!computerDto.getIntroduced().equals(""))) {
			this.validateDate(computerDto.getIntroduced());
		}
		if ((computerDto.getDiscontinued()!=null)&&(!computerDto.getDiscontinued().equals(""))) {
			this.validateDate(computerDto.getDiscontinued());
			this.validateDateOrder(computerDto.getIntroduced(), computerDto.getDiscontinued());
		}
		this.validateId(computerDto.getCompanyId());
	}

	public void validatePagination(String paginationValue) throws NotAIntegerException, PageNumberLessThan1Exception {
		int localId;
		try {
			localId = Integer.parseInt(paginationValue);
		}
		catch (NumberFormatException e) {
			throw new NotAIntegerException();
		}
		if (localId <= 0)
			throw new PageNumberLessThan1Exception(localId);
		
	}
	
	public void idsToDelete(String Ids) {
		String[] tabIds = Ids.split(",");
	}

}
