package com.excilys.cdb.exception;

public class NotAIntegerException extends Exception{

	public NotAIntegerException(){
		super("Il faut un entier pour les ids ");
	}
}
