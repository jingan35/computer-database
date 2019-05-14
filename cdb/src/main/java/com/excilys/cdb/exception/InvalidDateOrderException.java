package com.excilys.cdb.exception;

import java.sql.Timestamp;

public class InvalidDateOrderException extends Exception{
	
	public InvalidDateOrderException(String before, String after){
		super("bad order date "+before + " not before "+ after);
	}

}
