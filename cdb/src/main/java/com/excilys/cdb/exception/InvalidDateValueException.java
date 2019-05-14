package com.excilys.cdb.exception;

public class InvalidDateValueException extends RuntimeException {
	public InvalidDateValueException(String date){
		super("La valeur de la date "+date+ " n'est pas correcte");
	}

}
