package com.excilys.cdb.exception;

public class RequiredElementException extends Exception {

	public RequiredElementException(String name){
		super("l'element "+ name +" est requis dans DtoComputer");
	}
	
}
